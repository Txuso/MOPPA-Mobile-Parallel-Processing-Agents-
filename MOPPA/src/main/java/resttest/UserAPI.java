package resttest;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import classes.MoppaUser;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/moppa/v1/user")
@Api(value = "/user", description = "I am an User!")
public class UserAPI {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response getUser() {
        JsonObject value = Json.createObjectBuilder()
                .add("username", "Mikel")
                .add("password", "1234")
                .build();
        return Response.status(200).entity(value).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Say Hello World",notes = "Anything Else?")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response createUserInJSON(MoppaUser user) {
        JsonObject value = Json.createObjectBuilder()
                .add("username", user.getUsername())
                .add("taskValue", user.getPassword())                
                .build();
        return Response.status(200).entity(value).build();
    }
    
    
}