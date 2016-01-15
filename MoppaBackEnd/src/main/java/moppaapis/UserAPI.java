package moppaapis;

import com.datastax.driver.mapping.Result;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import cassandradb.CassandraDAOFactory;
import cassandradb.UserDAO;
import classes.MoppaUser;
import classes.Task;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * 
 * @author Txuso
 *
 */
@Path("/moppa/v1/user")
@Api(value = "/user", description = "Feel free to handle the users.")
public class UserAPI {
		
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
    @Path("/createUser")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Let's create some users.", notes = "What else do you need?")
	@ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK."),
        @ApiResponse(code = C500, message = "Something wrong in the Server.")})
	public final Response createUserInJSON(final String input) {
    	JsonReader jsonReader = Json.createReader(new StringReader(input));
   	 	JsonObject jsonObject = jsonReader.readObject();
   	 	jsonReader.close();
   	 	
   	 	CassandraDAOFactory factory = new CassandraDAOFactory();
   	 	
   	 	try {
   	 	  
        String userName = jsonObject.getString("userName").trim();
        String password = jsonObject.getString("password").trim();
        
        if (userName.isEmpty() || password.isEmpty()) {
          JsonObject payload = Json.createObjectBuilder()
              .add("message", "Provide data to create an user")
              .build();
          return Response.status(Status.NOT_ACCEPTABLE)
              .entity(payload)
              .build();
        }
        
   	 	  UserDAO user = factory.getUserDAO();
   	 	  
   	 	  Result<MoppaUser> userCheck = user.checkIfUserExists(userName);
   	 	  
        if (!userCheck.isExhausted()) {
          JsonObject payload = Json.createObjectBuilder()
              .add("userName", userName)
              .add("message", "User exists, choose another username")
              .build();
          return Response.status(Status.NOT_FOUND)
              .entity(payload)
              .build();
        }
          
   	 	  boolean success = user.insertUser(userName, password);
   	 	  if (!success) {
          JsonObject payload = Json.createObjectBuilder()
              .add("userName", userName)
              .add("message", "User has not been created, internal error")
              .build();
   	 	    return Response.status(Status.NOT_FOUND)
              .entity(payload)
              .build();
   	 	  } else {
          JsonObject payload = Json.createObjectBuilder()
              .add("userName", userName)
              .add("message", "User has been created")
              .build();
     	 	  return Response.status(C200)
              .entity(payload)
              .build();
   	 	  }  
   	 	
   	 	} catch (Exception e) {
        //Logging @ Service Layer
   	 	  
   	 	} finally {
   	 	  factory.closeConnection();
   	 	}
    JsonObject payload = Json.createObjectBuilder()
        .add("message", "User has not been created. Please provide correct parameters or contact the administrator")
        .build();
    return Response.status(Status.NOT_FOUND)
        .entity(payload)
        .build();
    }
    
    @POST
    @Path("/loginUser")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Login user service.", notes = "What more than login do you need?")
  @ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in the Server.")})
  public final Response loginUser(final String input) {
      JsonReader jsonReader = Json.createReader(new StringReader(input));
      JsonObject jsonObject = jsonReader.readObject();
      jsonReader.close();
      
      CassandraDAOFactory factory = new CassandraDAOFactory();
      
      try {
        
        String userName = jsonObject.getString("userName").trim();
        String password = jsonObject.getString("password").trim();
        
        if (userName.isEmpty() || password.isEmpty()) {
          JsonObject payload = Json.createObjectBuilder()
              .add("message", "Provide credentials in order to login")
              .build();
          return Response.status(Status.NOT_ACCEPTABLE)
              .entity(payload)
              .build();
        }
        
        UserDAO user = factory.getUserDAO();
        Result<MoppaUser> login = user.loginUser(userName, password);
        if (login.isExhausted()) {
          JsonObject payload = Json.createObjectBuilder()
              .add("userName", userName)
              .add("message", "Invalid credentials")
              .build();
          return Response.status(Status.NOT_FOUND)
              .entity(payload)
              .build();
        } else {
          JsonObject payload = Json.createObjectBuilder()
              .add("userName", userName)
              .add("message", "Login successful")
              .build();
          return Response.status(C200)
              .entity(payload)
              .build();
        }  
      
      } catch (Exception e) {
        //Logging @ Service Layer
        
      } finally {
        factory.closeConnection();
      }
    JsonObject payload = Json.createObjectBuilder()
        .add("message", "Login failed. Please provide correct parameters or contact the administrator.")
        .build();
    return Response.status(Status.NOT_FOUND)
        .entity(payload)
        .build();
    }    
}