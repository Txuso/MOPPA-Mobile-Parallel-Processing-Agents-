package moppaapis;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import classes.MoppaUser;
import exceptions.InvalidData;
import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author Txuso
 *
 */
@Path("/moppa/v1/user")
@Api(value = "/user", description = "I am an User!")
public class UserAPI {
	
	/**
	 * tasks here we store all the Moppa Users.
	 */
    private ArrayList<MoppaUser> users = new ArrayList<MoppaUser>();
	
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
	 * @param input the information in order to create an user
	 * @return it returns a message warning that 
	 * the user has been created correctly
	 * @throws MoppaException
	 */
    @POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Say Hello World", notes = "Anything Else?")
	@ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in Server")})
	public final Response createUserInJSON(final String input) {
    	JsonReader jsonReader = Json.createReader(new StringReader(input));
   	 	JsonObject object = jsonReader.readObject();
   	 	jsonReader.close();
   	 	
   	 	if (object.getString("username").isEmpty()
   	 			|| object.getString("password").isEmpty()) {
			throw new InvalidData("The username or the password cannot be null")
			.except();
		}
   	 	
   	 	for (MoppaUser user: users) {
   	 		if (user.getUsername().equals(object.getString("username"))) {
   	 			throw new InvalidData("The username already exists").except();
   	 		}
   	 	}
   	 	
        return Response.status(C200).
        	   entity("The user has been created").build();
    }
    
    
}