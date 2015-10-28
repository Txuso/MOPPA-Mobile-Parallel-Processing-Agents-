package resttest;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/moppa/va/task")
@Api(value = "/task", description = "I am a task!")
public class TaskAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Say Hello World",notes = "Anything Else?")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response showTask() {
        JsonObject value = Json.createObjectBuilder()
                .add("taskID", "1234")
                .add("taskValue", "10!")
                .add("result", "3628800")
                .build();
        return Response.status(200).entity(value).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Say Hello World",notes = "Anything Else?")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response createTaskInJSON(Task task) {
        JsonObject value = Json.createObjectBuilder()
                .add("taskID", task.getTaskID())
                .add("taskValue", task.getTaskValue())
                .add("result", task.getTaskResult())
                .build();
   
        return Response.status(200).entity(value).build();
    }
    
    
}