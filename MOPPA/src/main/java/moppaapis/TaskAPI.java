package moppaapis;

import classes.Task;
import exceptions.InvalidData;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import cassandradb.CassandraDAOFactory;
import cassandradb.CassandraTaskDAO;
import cassandradb.DAOFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.GET;
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
@Api(value = "/task", description = "I am a task!")
public class TaskAPI {
	
	/**
	 * @value tasks here we store all the tasks
	 */
     private ArrayList<Task> tasks = new ArrayList<Task>(); // Why do we use this? 28.11.2015 - Mario Measic-Gavran
    
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
	 * @return it returns all the tasks
	 * @throws MoppaException
	 */
    @GET
	@Path("/getTask")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value = {
    @ApiResponse(code = C200, message = "OK"),
    @ApiResponse(code = C500, message = "Something wrong in Server")})
	public final Response getAllTasks() {
    	tasks.add(new Task(new UUID(5, 5), "Txuso", 5, "120", "Waiting"));
    	tasks.add(new Task(new UUID(6, 6), "Mario", 4, "24", "Done"));
    	if (tasks.size() == 0) {
			throw new InvalidData("There"
			+ " are not tasks created yet").except();
		}
    	
    	for (Task task : tasks) {
    		JsonObject value = Json.createObjectBuilder()
                    .add("taskID", task.getTaskid().toString())
                    .add("taskValue", task.getProblem())
                    .add("result", task.getResult())
                    .add("taskState", task.getState())
                    .add("creatorUsername", task.getUsername())
                    .build();            
            return Response.status(C200).entity(value).build();
    	}
        return Response.status(C200).
        entity("All your tasks have been displayed").build();

    }
    /**
     * 
     * @param input information given by the user
     * @return it return OK message if the task has been created
     */
    @POST
	@Path("/createTask")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Say Hello World", notes = "Anything Else?")
	@ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in Server")})
	public final Response createTaskInJSON(final String input) {
    	
    	JsonReader jsonReader = Json.createReader(new StringReader(input));
    	JsonObject object = jsonReader.readObject();
    	jsonReader.close();
    	
    	CassandraDAOFactory factory = new CassandraDAOFactory();
      
      try {
        CassandraTaskDAO task = factory.getTaskDAO();
        
        int taskValue = object.getInt("taskValue");
        String creatorUsername = object.getString("creatorUsername");

        if (taskValue < 0 || taskValue > MAX_VALUE) {
        throw new InvalidData("The value cannot be "
        + "negative or greater than 100").except();
        }
        
        UUID uuid = task.insertTask(creatorUsername, taskValue);
        
        if (!uuid.toString().isEmpty()) {
          JsonObject value = Json.createObjectBuilder()
              .add("newTaskID", uuid.toString())
              .build();
  
          return Response.status(C200).entity(value).build();
        }
      } catch (Exception e) {
        //Log
    
      } finally {
       factory.closeConnection();
      }
      return Response.status(Status.NOT_FOUND)
          .entity(" Task has not been created, contact the administrator.")
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
	@ApiOperation(value = "Say Hello World", notes = "Anything Else?")
	@ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in Server")})
	public final Response findTaskByUsernameInJSON(final String input) {
      
      JsonReader jsonReader = Json.createReader(new StringReader(input));
      JsonObject object = jsonReader.readObject();
      jsonReader.close();
      
    	if (object.getString("userName").isEmpty()) {
    		throw new InvalidData("System couldn't read your username. "
    		                      + "Please contact the administrator.")
    		                      .except();
    	}
    		
      CassandraDAOFactory factory = new CassandraDAOFactory();
      
      try {
        CassandraTaskDAO task = factory.getTaskDAO();
        
        Result<Task> tasks = task.findTasksbyUsername(
                             object.getString("userName"));
        JSONObject value = new JSONObject();
        Collection<JSONObject> tasksJSON = new ArrayList<JSONObject>();
        
        
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
      return Response.status(Status.NOT_FOUND)
      .entity(" There are no tasks assigned to this username: "
      + object.getString("userName"))
      .build();
    }
    /**
     * @param input the username
     * @return it returns all the tasks with the given tasks states
     */
    @POST
  @Path("/findTaskByTaskState")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Say Hello World", notes = "Anything Else?")
  @ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in Server")})
  public final Response findTaskByStateInJSON(final String input) {
    	
    	JsonReader jsonReader = Json.createReader(new StringReader(input));
    	JsonObject object = jsonReader.readObject();
    	jsonReader.close();
    	
      CassandraDAOFactory factory = new CassandraDAOFactory();
    	
    	try {
      	CassandraTaskDAO task = factory.getTaskDAO();
      	
      	Result<Task> tasks = task.findTasksbyState(
      	                     object.getString("userName"), 
      	                     object.getString("taskState"));
        JSONObject value = new JSONObject();
        Collection<JSONObject> tasksJSON = new ArrayList<JSONObject>();
      	
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

 
    	return Response.status(Status.NOT_FOUND)
             .entity(" There aren't tasks assigned to " 
             + object.getString("taskState")).build();    
   }  	
}