package moppaapis;

import classes.Task;
import redis.clients.jedis.Jedis;
import com.datastax.driver.mapping.Result;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import cassandradb.CassandraDAOFactory;
import cassandradb.CassandraTaskDAO;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


/**
 * 
 * @author Txuso
 * This class deals with the tasks created by the users
 */
@Path("/moppa/v1/task")
@Api(value = "/task", description = "Feel free to create a task.")
public class TaskAPI {
	    
    /**
     * maximum value accepted to compute.
     */
	private static final int MAX_VALUE = 100;
	
	/**
	 * code for OK api response.
	 */
	private static final int C200 = 200;
	
	/**
	 * code for WRONG api response.
	 */
	private static final int C500 = 500;
	
    /**
     * 
     * @param input information given by the user
     * @return it return OK message if the task has been created
     * @throws MoppaException
     */
    @SuppressWarnings("resource")
	@POST
	@Path("/createTask")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create task.", notes = "What else do you need?")
	@ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK."),
        @ApiResponse(code = C500, message = "Something wrong in the Server.")})
	public final Response createTaskInJSON(final String input) {
    	
    	JsonReader jsonReader = Json.createReader(new StringReader(input));
    	JsonObject jsonObject = jsonReader.readObject();
    	jsonReader.close();
    	
    	CassandraDAOFactory factory = new CassandraDAOFactory();
      
      try {
        CassandraTaskDAO task = factory.getTaskDAO();
        
        String taskValue = jsonObject.getString("taskValue");
        String userName = jsonObject.getString("userName");
        UUID uuid = UUID.randomUUID();
        
        int taskValueInt = Integer.parseInt(taskValue);

        if (taskValueInt < 0 || taskValueInt > MAX_VALUE) {
          JsonObject payload = Json.createObjectBuilder()
              .add("message", "Value must be between 1 and 100")
              .build();
          return Response.status(Status.NOT_ACCEPTABLE)
                 .entity(payload)
                 .build();
        }
        
        Result<Task> tasks = task.checkIfTaskExists(taskValueInt);
        
        if (!tasks.isExhausted()) {
          Task taskIterator = tasks.one();
          String taskResult = taskIterator.getResult();
          JsonObject payload = Json.createObjectBuilder()
              .add("taskValue", taskValue)
              .add("taskResult", taskResult)
              .add("message", "Task already exists. If the value is 0, please wait and try in a few hours.")
              .build();
          return Response.status(Status.NOT_FOUND)
              .entity(payload)
              .build();
        }
        
        boolean success = task.insertTask(uuid, userName, taskValueInt);
        
        if (success) {
          JsonObject payload = Json.createObjectBuilder()
              .add("taskID", uuid.toString())
              .add("taskValue", taskValue)
              .add("message", "Task created")
              .build();
          Jedis jedis = new Jedis("localhost");  //Needs replacement
          jedis.rpush("tasks", payload.toString());

          return Response.status(C200).entity(payload).build();
        }
        
        else {
          JsonObject payload = Json.createObjectBuilder()
              .add("message", "Unable to create the task")
              .build();
          return Response.status(Status.NOT_FOUND)
              .entity(payload)
              .build();
        }
      } catch (Exception e) {
        //Log
    
      } finally {
       factory.closeConnection();
      }
      JsonObject payload = Json.createObjectBuilder()
          .add("message", "Task has not been created. Please provide correct parameters or contact the administrator.")
          .build();
      return Response.status(Status.NOT_FOUND)
          .entity(payload)
          .build();
    }
    /**
     * 
     * @param input tasks assigned to the corresponding user name.
     * @return it returns all the tasks assigned to the user name
     */
  @POST
	@Path("/findTaskByUsername")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find tasks by username.", notes = "What else do you need?")
	@ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK."),
        @ApiResponse(code = C500, message = "Something wrong in the Server.")})
	public final Response findTaskByUsernameInJSON(final String input) {
      
      JsonReader jsonReader = Json.createReader(new StringReader(input));
      JsonObject jsonObject = jsonReader.readObject();
      jsonReader.close();
      
      String userName = jsonObject.getString("userName").trim();
      
    	if (userName.isEmpty()) {
        JsonObject payload = Json.createObjectBuilder()
            .add("message", "Username not provided")
            .build();
        return Response.status(Status.NOT_ACCEPTABLE)
               .entity(payload)
               .build();
    	}
    		
      CassandraDAOFactory factory = new CassandraDAOFactory();
      
      try {
        CassandraTaskDAO task = factory.getTaskDAO();
        
        Result<Task> tasks = task.findTasksbyUsername(userName);
        JSONObject value = new JSONObject();
        Collection<JSONObject> tasksJSON = new ArrayList<JSONObject>();
      
        if (tasks.isExhausted()) {
          JsonObject payload = Json.createObjectBuilder()
              .add("userName", userName)
              .add("message", "No tasks assigned")
              .build();
          return Response.status(Status.NOT_FOUND)
                 .entity(payload)
                 .build();
        }
        
      	for (Task taskIterator : tasks) {
      	  JSONObject taskJSON = new JSONObject();
          taskJSON.put("taskID", taskIterator.getTaskid().toString());
          taskJSON.put("taskValue", taskIterator.getProblem());
          taskJSON.put("result", taskIterator.getResult());
          taskJSON.put("taskState", taskIterator.getState());
          taskJSON.put("userName", taskIterator.getUsername());  
          tasksJSON.add(taskJSON);
    		}
    	
    	value.put("tasks", new JSONArray(tasksJSON));
      return Response.status(C200)
             .entity(value.toString()).build();
      } catch (Exception e) {
    	  //Log
    	} finally {
        factory.closeConnection();
      }
      
    JsonObject payload = Json.createObjectBuilder()
        .add("message", "Please provide correct parameters or contact the administrator")
        .build();
    return Response.status(Status.NOT_ACCEPTABLE)
           .entity(payload).build();
    }
    /**
     * @param input the username
     * @return it returns all the tasks with the given tasks states
     */
    @POST
  @Path("/findTaskByTaskState")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Find tasks by their state Done/Waiting.", notes = "What else do you need?")
  @ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in the Server.")})
  public final Response findTaskByStateInJSON(final String input) {
    	
    	JsonReader jsonReader = Json.createReader(new StringReader(input));
    	JsonObject jsonObject = jsonReader.readObject();
    	jsonReader.close();
    	
    	String userName = jsonObject.getString("userName").trim();
    	String taskState = jsonObject.getString("taskState").trim();
    	
      CassandraDAOFactory factory = new CassandraDAOFactory();
    	
    	try {
      	CassandraTaskDAO task = factory.getTaskDAO();
      	
      	Result<Task> tasks = task.findTasksbyState(userName, taskState); 
        JSONObject value = new JSONObject();
        Collection<JSONObject> tasksJSON = new ArrayList<JSONObject>();
        
        if (tasks.isExhausted()) {
          JsonObject payload = Json.createObjectBuilder()
              .add("userName", userName)
              .add("taskState",  taskState)
              .add("message", "No tasks assigned with this state or username")
              .build();
          return Response.status(Status.NOT_FOUND)
                 .entity(payload)
                 .build();
        }
      	
      	for (Task taskIterator : tasks) {
      	  
          JSONObject taskJSON = new JSONObject();
          taskJSON.put("taskID", taskIterator.getTaskid().toString());
          taskJSON.put("taskValue", taskIterator.getProblem());
          taskJSON.put("result", taskIterator.getResult());
          taskJSON.put("taskState", taskIterator.getState());
          taskJSON.put("userName", taskIterator.getUsername());
          
          tasksJSON.add(taskJSON);      
        }
        value.put("tasks", new JSONArray(tasksJSON));
        return Response.status(C200).entity(value.toString()).build();
      } catch (Exception e) {
        //Log
          
      } finally {
    	  factory.closeConnection();
    	}
    	
      JsonObject payload = Json.createObjectBuilder()
          .add("message", "Please provide correct parameters or contact the administrator")
          .build();
      return Response.status(Status.NOT_ACCEPTABLE)
             .entity(payload).build();
   }  	
}