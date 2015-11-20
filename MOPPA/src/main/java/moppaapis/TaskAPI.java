package moppaapis;

import classes.Task;
import exceptions.InvalidData;

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
     private ArrayList<Task> tasks = new ArrayList<Task>();
    
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
    	
    	
        UUID taskID = new UUID(5, 5);
        int taskValue = object.getInt("taskValue");
        String result = object.getString("result");
        String taskState = object.getString("taskState");
        String creatorUsername = object.getString("creatorUsername");
        
        if (taskValue < 0 || taskValue > MAX_VALUE) {
    		throw new InvalidData("The value cannot be "
    		+ "negative or greater than 100").except();
        }

        Task newTask = new Task(taskID, creatorUsername, taskValue, result,
        taskState);
        tasks.add(newTask);
        return Response.status(C200).build();

    }
    /**
     * 
     * @param username tasks assigned to the corresponding user name.
     * @return it returns all the tasks assigned to the user name
     */
    @POST
	@Path("/findTaskByUsername")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Say Hello World", notes = "Anything Else?")
	@ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in Server")})
	public final Response findTaskByUsernameInJSON(final String username) {
    	tasks.add(new Task(new UUID(5, 5), "Txuso", 5, "120", "Waiting"));
    	tasks.add(new Task(new UUID(6, 6), "Mario", 4, "24", "Done"));
    	if (username.isEmpty()) {
    		throw new InvalidData("The introduced data isn't correct").except();
    	}
    		
    	for (Task task : tasks) {
    		if (task.getUsername().equals(username)) {
    			JsonObject value = Json.createObjectBuilder()
                        .add("taskID", task.getTaskid().toString())
                        .add("taskValue", task.getProblem())
                        .add("result", task.getResult())
                        .add("taskState", task.getState())
                        .add("creatorUsername", task.getUsername())
                        .build();
                return Response.status(C200).entity(value).build();
    		} 		
    	}
    	
        return Response.status(Status.NOT_FOUND)
        .entity(" There aren't tasks assigned to " + username).build();
    }
    /**
     * 
     * @param taskState the input task state
     * @return it returns all the tasks with the given tasks states
     */
    @POST
	@Path("/findTaskByTaskState")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Say Hello World", notes = "Anything Else?")
	@ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in Server")})
	public final Response findTaskByStateInJSON(final String userName, final String taskState) {
      
      DAOFactory factory = new CassandraDAOFactory();
      CassandraTaskDAO taskDAO = (CassandraTaskDAO) factory.getTaskDAO();
      Result<Task> tasks = taskDAO.findTasksbyState(userName, taskState);
      
    	for (Task task : tasks) {
    		if (task.getState().equals(taskState)) {
    			JsonObject value = Json.createObjectBuilder()
                        .add("taskID", task.getTaskid().toString())
                        .add("taskValue", task.getProblem())
                        .add("result", task.getResult())
                        .add("taskState", task.getState())
                        .add("creatorUsername", task.getUsername())
                        .build();
                return Response.status(C200).entity(value).build();
    		}
			
    	}
        return Response.status(Status.NOT_FOUND)
        .entity(" There aren't tasks with the state" + taskState).build();
     }
    
    
    
}