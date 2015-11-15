package resttest;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import exception.MoppaException;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/moppa/v1/user")
@Api(value = "/user", description = "I am an User!")
public class UserAPI {
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Say Hello World",notes = "Anything Else?")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Something wrong in Server")})
    public Response createUserInJSON(String input) throws MoppaException {
    	JsonReader jsonReader = Json.createReader(new StringReader(input));
   	 	JsonObject object = jsonReader.readObject();
   	 	jsonReader.close();
   	 	
   	 	if (object.getString("username").isEmpty() || object.getString("password").isEmpty())
    		throw new MoppaException("The username or the password cannot be null");
   	 	
        JsonObject value = Json.createObjectBuilder()
                .add("username", object.get("username"))
                .add("password", object.get("password"))                
                .build();
        return Response.status(200).entity("The user has been created").build();
    }
    
    
}