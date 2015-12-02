package moppaapis;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import cassandradb.CassandraDAOFactory;
import cassandradb.UserDAO;
import classes.MoppaUser;
import exceptions.InvalidData;
import redis.clients.jedis.Jedis;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
 * @author Mario Measic-Gavran
 *
 */
@Path("/moppa/v1/mobiletask")
@Api(value = "/mobiletask", description = "I am a Mobile Task!")
public class MobileTaskAPI {
  
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
    @Path("/getTask")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Get task for Mobile", notes = "Anything Else?")
  @ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in Server")})
  public final Response getTask(final String input) {
      JsonReader jsonReader = Json.createReader(new StringReader(input));
      JsonObject object = jsonReader.readObject();
      jsonReader.close();
      
      if (object.getString("phoneName").isEmpty()) {
            return Response.status(Status.NOT_FOUND)
                .entity("You have to provide phone data."
                + "Please contact the administrator.")
                .build();
      }
      
      try {
        Jedis jedis = new Jedis();
        String taskRedis = jedis.lpop("tasks");
        
        JsonReader readTask = Json
                              .createReader(new StringReader(taskRedis));
        JsonObject taskRedisJSON = readTask.readObject();
        jsonReader.close();
        
        return Response.status(C200)
            .entity("Task " + taskRedisJSON
            .getString("taskId") 
            + " has been taken from the Redis queue"
            + " and sent for processing.")
            .build();
        
      } catch (Exception e) {
        
        
      } finally {

      }
      
      return Response.status(Status.NOT_FOUND)
          .entity("There are no tasks to process. "
          + "Please wait few seconds and try again.")
          .build();
  
    }    
}