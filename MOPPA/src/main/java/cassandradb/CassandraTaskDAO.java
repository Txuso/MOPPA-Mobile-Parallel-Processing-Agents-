package cassandradb;

import java.util.UUID;

import com.datastax.driver.core.*;
public class CassandraTaskDAO implements TaskDAO {
	
	public CassandraTaskDAO() {
		
	}
	
	public UUID insertTask (String username, int problem) {
		
		Session connection = CassandraDAOFactory.createConnection();
		UUID uuid = java.util.UUID.randomUUID();
		
		try {
			PreparedStatement stmt = connection.prepare("INSERT INTO tasks (taskid, username, problem, result, state) VALUES (?, ?, ?, ?, ?);");
			BoundStatement bound_stmt = new BoundStatement(stmt);
			
			connection.execute(bound_stmt.bind(uuid, username, problem, null, "Waiting"));
		}
		
		catch (Exception e) {
			//Logger
		}
		
		finally {
			CassandraDAOFactory.closeConnection(connection);
		}

		return uuid;
	}
	
	public ResultSet findTasksbyUsername (String username) {
		
		Session connection = CassandraDAOFactory.createConnection();
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
		
		Session connection = CassandraDAOFactory.createConnection();
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
