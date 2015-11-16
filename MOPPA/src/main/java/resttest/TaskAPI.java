package resttest;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import classes.Task;
import exception.MoppaException;

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

@Path("/moppa/v1/task")
@Api(value = "/task", description = "I am a task!")
public class TaskAPI {
	
	static ArrayList<Task> tasks = new ArrayList<Task>();
	public final int MAX_VALUE = 100;

    
    @GET
    @Path("/getTask")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response getAllTasks() throws MoppaException {
    	if (tasks.size() == 0){
    		throw new MoppaException("There are not tasks created yet");
    	}
    	for (Task task : tasks){
    		JsonObject value = Json.createObjectBuilder()
                    .add("taskID", task.getTaskID())
                    .add("taskValue", task.getTaskValue())
                    .add("result", task.getTaskResult())
                    .add("result", task.getTaskState())
                    .build();
            tasks.add((Task)value);
            return Response.status(200).entity(task).build();
    	}
        return Response.status(200).entity("All your tasks have been displayed").build();

    }
    
    @POST
    @Path("/createTask")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Say Hello World",notes = "Anything Else?")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    
    public Response createTaskInJSON(String input) throws MoppaException {
    	
    	JsonReader jsonReader = Json.createReader(new StringReader(input));
    	JsonObject object = jsonReader.readObject();
    	jsonReader.close();
    	
    	if (object.getString("taskValue").isEmpty()){
    		throw new MoppaException("The task value cannot be null");
    	}
    		
    	if (object.getString("taskValue").matches("^[a-zA-Z]*$")){
    		throw new MoppaException("The value cannot contain letters or special characters");
    	}	
    	
        double taskID = Double.parseDouble(object.getString("taskID"));
        int taskValue = Integer.parseInt(object.getString("taskValue"));
        double result = Double.parseDouble(object.getString("result"));
        String taskState = object.getString("taskState");
        String creatorUsername = object.getString("creatorUsername");
        
        if (taskValue < 0 || taskValue > MAX_VALUE){
    		throw new MoppaException("The value cannot be negative or greater than 100");
        }

        Task newTask = new Task(taskID, taskValue, result, taskState, creatorUsername);
        tasks.add(newTask);
		
        return Response.status(200).entity("Your task has been uploaded correctly").build();

    }
    
    @POST
    @Path("/findTaskByUsername")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Say Hello World",notes = "Anything Else?")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    
    public Response findTaskByUsernameInJSON(String username) throws MoppaException {
    	boolean found = false;
    	for (Task task : tasks){
    		if (task.getCreatorUsername().equals(username)){
    			found = true;
    			JsonObject value = Json.createObjectBuilder()
                        .add("taskID", task.getTaskID())
                        .add("taskValue", task.getTaskValue())
                        .add("result", task.getTaskResult())
                        .add("result", task.getTaskState())
                        .build();
                return Response.status(200).entity(value).build();
    		}
    		if (found)
        		throw new MoppaException("There aren't tasks assigned to " + username);
    	}
        return Response.status(200).build();
    }
    
    @POST
    @Path("/findTaskByTaskState")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Say Hello World",notes = "Anything Else?")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    
    public Response findTaskByStateInJSON(String taskState) throws MoppaException {
    	boolean found = false;
    	for (Task task : tasks){
    		if (task.getTaskState().equals(taskState)){
    			found = true;
    			JsonObject value = Json.createObjectBuilder()
                        .add("taskID", task.getTaskID())
                        .add("taskValue", task.getTaskValue())
                        .add("result", task.getTaskResult())
                        .add("result", task.getTaskState())
                        .build();
                return Response.status(200).entity(value).build();
    		}
    		if (found)
        		throw new MoppaException("There aren't tasks with the state " + taskState);
    	}
        return Response.status(200).build();
    }
    
    
    
}