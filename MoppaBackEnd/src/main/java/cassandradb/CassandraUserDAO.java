package cassandradb;

import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

import classes.MoppaUser;
import classes.Task;

/**
 * 
 * @author Mario Measic-Gavran
 * This class is used for creating a new User 
 * and inserting a record into CassandraDB.
 */

public class CassandraUserDAO implements UserDAO {
  
  Session session;
  MappingManager manager;
  
  public CassandraUserDAO(Session session, MappingManager manager) {
    this.session = session;
    this.manager = manager;
  }

	public final boolean insertUser(final String userName, final String password) {
				
		try {
		  MoppaUser user = new MoppaUser(userName, password);
			
			Mapper<MoppaUser> mapper = manager
			                           .mapper(MoppaUser.class);
			mapper.save(user);
		} catch (Exception e) {
      //LoggingHandler.writeErrorToLog(e);	
		  return false;
		  }
		
		return true; //Check
	}
	
	public final Result<MoppaUser> loginUser(final String userName, final String password) {
	  
    Result<MoppaUser> user = null;
    try {
      PreparedStatement stmt = session.prepare("SELECT * "
          + "FROM users WHERE username = ? AND password = ? ALLOW FILTERING;");
      BoundStatement boundStmt = new BoundStatement(stmt);
      ResultSet results = session
                          .execute(boundStmt.bind(userName, password));
      Mapper<MoppaUser> mapper = manager.mapper(MoppaUser.class);
      user = mapper.map(results);

    } catch (Exception e) {
      //LoggingHandler.writeErrorToLog(e);
      System.out.println(e.toString());
    }
    return user;
	}
	
	 public final Result<MoppaUser> checkIfUserExists(final String userName) {
	    
	    Result<MoppaUser> user = null;
	    try {
	      PreparedStatement stmt = session.prepare("SELECT * "
	          + "FROM users WHERE username = ? ALLOW FILTERING;");
	      BoundStatement boundStmt = new BoundStatement(stmt);
	      ResultSet results = session
	                          .execute(boundStmt.bind(userName));
	      Mapper<MoppaUser> mapper = manager.mapper(MoppaUser.class);
	      user = mapper.map(results);

	    } catch (Exception e) {
	      //LoggingHandler.writeErrorToLog(e);
	      System.out.println(e.toString());
	    }
	    return user;
	    
	  }
}
