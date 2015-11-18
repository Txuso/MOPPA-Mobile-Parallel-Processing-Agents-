package cassandradb;

import java.util.UUID;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;

import classes.Task;
public class CassandraTaskDAO implements TaskDAO {
	
	public CassandraTaskDAO() {
		
	}
	
	public UUID insertTask (String username, int problem) {
		
		Session connection = CassandraDAOFactory.getConnection();
		UUID uuid = java.util.UUID.randomUUID();
		
		try {
			Task task = new Task(uuid, username, problem, username, username); //popraviti
			Mapper<Task> mapper = CassandraDAOFactory.getMappingManager().mapper(Task.class);
			mapper.save(task);
		}
		
		catch (Exception e) {
			//Logger
		}
		
		finally {
			CassandraDAOFactory.closeConnection(connection);
		}

		return uuid;
		
		//CHANGE THE METHODS BELOW ACCORDING TO THE FIRST ONE
	}
	
	public ResultSet findTasksbyUsername (String username) {
		
		Session connection = CassandraDAOFactory.getConnection();
		ResultSet results = null;
		try {
			PreparedStatement stmt = connection.prepare("SELECT * FROM tasks WHERE username = ?);");
			BoundStatement bound_stmt = new BoundStatement(stmt);
			
			results = connection.execute(bound_stmt.bind(username));
		}
		
		catch (Exception e) {
			//Logger
		}
		
		finally {
			CassandraDAOFactory.closeConnection(connection);
		}
		
		return results;
	}
	
	public ResultSet findTasksbyState (String username, String state) {
		
		Session connection = CassandraDAOFactory.getConnection();
		ResultSet results = null;
		try {
			PreparedStatement stmt = connection.prepare("SELECT * FROM tasks WHERE username = ? AND state = ?);");
			BoundStatement bound_stmt = new BoundStatement(stmt);
			
			results = connection.execute(bound_stmt.bind(username, state));
		}
		
		catch (Exception e) {
			//Logger
		}
		
		finally {
			CassandraDAOFactory.closeConnection(connection);
		}
		
		return results;
	}

}
