package moppaapis;
import cassandradb.EstablishConnection;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/login")
public class Authentication {

    @POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("username") String username, 
                                     @FormParam("password") String password) {

        try {

            // Authenticate the user using the credentials provided
        	// Needs more functionality, handling if we can't authenticate user, front-end interaction
            authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }

    private void authenticate(String username, String password) throws Exception {
    	EstablishConnection connection = new EstablishConnection();
        connection.openConnection();
        connection.executeQuery("SELECT * FROM users WHERE username = " + username);
        
    }

    private String issueToken(String username) {
		return "1";
		// Have to consult with Rodolfo how to create token, in Java or from db
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    }
}