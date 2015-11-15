package resttest;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import classes.Task;
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
    
    @GET
    @Path("/getTask")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response getAllTasks() {
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
    
    public Response createTaskInJSON(String input) {
    	JsonReader jsonReader = Json.createReader(new StringReader(input));
    	JsonObject object = jsonReader.readObject();
    	jsonReader.close();
    	
        double taskID = Double.parseDouble(object.getString("taskID"));
        double taskValue = Double.parseDouble(object.getString("taskValue"));
        double result = Double.parseDouble(object.getString("result"));
        String taskState = object.getString("taskState");
        String creatorUsername = object.getString("creatorUsername");
        
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
    
    public Response findTaskByUsernameInJSON(String username) {

    	for (Task task : tasks){
    		if (task.getCreatorUsername().equals(username)){
    			JsonObject value = Json.createObjectBuilder()
                        .add("taskID", task.getTaskID())
                        .add("taskValue", task.getTaskValue())
                        .add("result", task.getTaskResult())
                        .add("result", task.getTaskState())
                        .build();
                tasks.add((Task)value);
                return Response.status(200).entity(value).build();
    		}
    	}
        return Response.status(200).build();
    }
    
    @POST
    @Path("/findTaskByTaskID")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Say Hello World",notes = "Anything Else?")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    
    public Response findTaskByTaskIDInJSON(String taskID) {
    	boolean found = false;
    	int i = 0;
    	while (found || i < tasks.size()){
    		
    		if (tasks.get(i).getCreatorUsername().equals(taskID)){
    			JsonObject value = Json.createObjectBuilder()
                        .add("taskID", tasks.get(i).getTaskID())
                        .add("taskValue", tasks.get(i).getTaskValue())
                        .add("result", tasks.get(i).getTaskResult())
                        .add("result", tasks.get(i).getTaskState())
                        .build();
                tasks.add((Task)value);
        		i++;
        		found = true;
                return Response.status(200).entity(value).build();
    		}
    	}
        return Response.status(200).build();
    }
    
    
}