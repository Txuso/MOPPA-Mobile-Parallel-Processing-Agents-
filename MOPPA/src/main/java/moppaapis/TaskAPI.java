package moppaapis;

import classes.Task;
import exceptions.InvalidData;
import exceptions.TaskNotFound;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    	if (tasks.size() == 0) {
			throw new InvalidData("There"
			+ " are not tasks created yet").except();
		}
    	
    	for (Task task : tasks) {
    		JsonObject value = Json.createObjectBuilder()
                    .add("taskID", task.getTaskID())
                    .add("taskValue", task.getTaskValue())
                    .add("result", task.getTaskResult())
                    .add("taskState", task.getTaskState())
                    .add("creatorUsername", task.getCreatorUsername())
                    .build();
            tasks.add((Task) value);
            return Response.status(C200).entity(task).build();
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
    	
        double taskID = object.getInt("taskID");
        int taskValue = object.getInt("taskValue");
        double result = object.getInt("result");
        String taskState = object.getString("taskState");
        String creatorUsername = object.getString("creatorUsername");
        
        if (taskValue < 0 || taskValue > MAX_VALUE) {
    		throw new InvalidData("The value cannot be "
    		+ "negative or greater than 100").except();
        }

        Task newTask = new Task(taskID, taskValue, result,
        taskState, creatorUsername);
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
    	boolean found = false;
    	for (Task task : tasks) {
    		if (task.getCreatorUsername().equals(username)) {
    			found = true;
    			JsonObject value = Json.createObjectBuilder()
                        .add("taskID", task.getTaskID())
                        .add("taskValue", task.getTaskValue())
                        .add("result", task.getTaskResult())
                        .add("taskState", task.getTaskState())
                        .add("creatorUsername", task.getCreatorUsername())
                        .build();
                return Response.status(C200).entity(value).build();
    		}
    		
    	}
    	if (found) {
			throw new TaskNotFound("There aren't tasks assigned to "
    		+ username).except();
		}
    	
        return Response.status(C200).entity(""
        + " All your tasks have been displayed").build();
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
	public final Response findTaskByStateInJSON(final String taskState) {
    	boolean found = false;
    	for (Task task : tasks) {
    		if (task.getTaskState().equals(taskState)) {
    			found = true;
    			JsonObject value = Json.createObjectBuilder()
                        .add("taskID", task.getTaskID())
                        .add("taskValue", task.getTaskValue())
                        .add("result", task.getTaskResult())
                        .add("result", task.getTaskState())
                        .build();
                return Response.status(C200).entity(value).build();
    		}
    		if (found) {
				throw new TaskNotFound("There aren't"
				+ " tasks with the state " + taskState).except();
			}
    	}
        return Response.status(C200).build();
    }
    
    
    
}