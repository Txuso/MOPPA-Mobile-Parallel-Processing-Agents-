package cassandradb;

import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.Result;

import classes.Task;

import java.util.UUID;

/**
 * 
 * @author Mario Measic-Gavran
 * This class is used for creating a new Task 
 * and inserting a record into CassandraDB.
 */

public class CassandraTaskDAO implements TaskDAO {
	
	public CassandraTaskDAO() {
		
	}
	
	public final UUID insertTask(final String username, final int problem) {
		
		UUID uuid = UUID.randomUUID(); //can we use it directly in method call?
		try {
			Task task = new Task(
			uuid, username, problem, "", "Waiting");
			
			Mapper<Task> mapper = CassandraDAOFactory
			                      .getMappingManager().mapper(Task.class);
			mapper.save(task);
		} catch (Exception e) {
			//LoggingHandler.writeErrorToLog(e);
		  }
		return uuid; // Returns newly created TaskID
	}
	
	public final Result<Task> findTasksbyUsername(final String username) {
		
		Session connection = CassandraDAOFactory.getConnection();
		Result<Task> tasks = null;
		try {
			PreparedStatement stmt = connection.prepare("SELECT * "
          + "FROM tasks WHERE username = ? ALLOW FILTERING;");
			BoundStatement boundStmt = new BoundStatement(stmt);
			
			ResultSet results   = connection.execute(boundStmt.bind(username));
			Mapper<Task> mapper = CassandraDAOFactory.getMappingManager()
			                      .mapper(Task.class);
			tasks = mapper.map(results);
			} catch (Exception e) {
	      //LoggingHandler.writeErrorToLog(e);
			  }
		return tasks;
	}
	
	public final Result<Task> findTasksbyState(final String username, 
	                                           final String state) {
		
		Session connection = CassandraDAOFactory.getConnection();
		Result<Task> tasks = null;
		try {
			PreparedStatement stmt = connection.prepare("SELECT * "
          + "FROM tasks WHERE username = ? AND state = ? ALLOW FILTERING;");
			BoundStatement boundStmt = new BoundStatement(stmt);
			
			ResultSet results = connection
			                    .execute(boundStmt.bind(username, state));
			Mapper<Task> mapper = CassandraDAOFactory
			                    .getMappingManager().mapper(Task.class);
			tasks = mapper.map(results);
		} catch (Exception e) {
      //LoggingHandler.writeErrorToLog(e);
		  }
		return tasks;
	}

}
