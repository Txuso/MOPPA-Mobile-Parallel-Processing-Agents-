package moppaapis;

import com.datastax.driver.mapping.Result;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import cassandradb.CassandraDAOFactory;
import cassandradb.CassandraTaskDAO;
import classes.Task;
import redis.clients.jedis.Jedis;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONObject;


/**
 * 
 * @author Mario Measic-Gavran
 *
 */
@Path("/moppa/v1/mobiletask")
@Api(value = "/mobiletask", description = "Feel free to try this feature, it's awesome.")
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
  @SuppressWarnings("resource")
	@POST
  @Path("/getTask")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Feel free to take some tasks.", notes = "What more do you want?")
  @ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK."),
        @ApiResponse(code = C500, message = "Something wrong in the Server.")})
  public final Response getTask(final String input) {
      JsonReader jsonReader = Json.createReader(new StringReader(input));
      JsonObject jsonObject = jsonReader.readObject();
      jsonReader.close();
      
      String phoneName = jsonObject.getString("phoneName").trim();
      
      if (phoneName.isEmpty()) {
        JsonObject payload = Json.createObjectBuilder()
            .add("message", "Provide phone data.")
            .build();
        return Response.status(Status.NOT_FOUND)
            .entity(payload)
            .build();
      }
      
      try {
        Jedis jedis = new Jedis();
        String taskRedis = jedis.lpop("tasks");
        
        JsonReader readTask = Json
                              .createReader(new StringReader(taskRedis));
        JsonObject jsonObjectTask = readTask.readObject();
        jsonReader.close();
        
        String taskID = jsonObjectTask.getString("taskID");
        String taskValue = jsonObjectTask.getString("taskValue");
        
        JsonObject value = Json.createObjectBuilder()
            .add("taskID", taskID)
            .add("taskValue", taskValue)
            .build();
                
        return Response.status(C200)
            .entity(value)
            .build();

      } catch (Exception e) {
        //Logging
      }
      JsonObject payload = Json.createObjectBuilder()
          .add("message", "No tasks to process. Please wait few seconds and try again.")
          .build();
      return Response.status(Status.NOT_FOUND)
          .entity(payload)
          .build();
    }   
    
  @POST
  @Path("/returnTask")
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Return me some calculations you have done.", notes = "I don't need anything, just calculations.")
  @ApiResponses(value = {
        @ApiResponse(code = C200, message = "OK"),
        @ApiResponse(code = C500, message = "Something wrong in the Server.")})
  public final Response returnTask(final String input) {
      JsonReader jsonReader = Json.createReader(new StringReader(input));
      JsonObject jsonObject = jsonReader.readObject();
      jsonReader.close();
      
      String phoneName = jsonObject.getString("phoneName").trim();
      UUID taskID = java.util.UUID.fromString(jsonObject.getString("taskID").trim());
      String taskResult = jsonObject.getString("taskResult").trim();
 
      if (phoneName.isEmpty()
          || taskID.toString().isEmpty()
          || taskResult.isEmpty()) {
        JsonObject payload = Json.createObjectBuilder()
            .add("message", "Some of the important data is missing.")
            .build();
        return Response.status(Status.NOT_FOUND)
            .entity(payload)
            .build();
      }
      
      CassandraDAOFactory factory = new CassandraDAOFactory();
      
      try {
        CassandraTaskDAO task = factory.getTaskDAO();
        
        boolean success = task.updateTask(taskID, taskResult);
        
        if (success) {
          JsonObject payload = Json.createObjectBuilder()
              .add("taskID", taskID.toString())
              .add("message", "Task received")
              .build();
          return Response.status(C200).entity(payload).build();
        } else {
          JsonObject payload = Json.createObjectBuilder()
          .add("message", "Task not updated. Contact the administrator.")
          .build();
          return Response.status(Status.NOT_ACCEPTABLE)
            .entity(payload)
            .build();
        }
      } catch (Exception e) {
        //Log
    
      } finally {
       factory.closeConnection();
      }
      JsonObject payload = Json.createObjectBuilder()
      .add("message", "Task not updated. Contact the administrator.")
      .build();
      return Response.status(Status.NOT_FOUND)
          .entity(payload)
          .build();
    }
}