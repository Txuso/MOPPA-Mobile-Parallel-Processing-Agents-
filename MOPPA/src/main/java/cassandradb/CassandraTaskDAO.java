package cassandradb;

import java.util.UUID;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.Result;

import classes.Task;
import utils.GeneratorUUID;
public class CassandraTaskDAO implements TaskDAO {
	
	public CassandraTaskDAO() {
		
	}
	
	public UUID insertTask (String username, int problem) {
		
		UUID uuid = GeneratorUUID.generateTaskID(); //can we use it directly in method call?
		try {
			Task task = new Task(uuid, username, problem, "", "Waiting"); // check
			Mapper<Task> mapper = CassandraDAOFactory.getMappingManager().mapper(Task.class);
			mapper.save(task);
		}
		catch (Exception e) {
			//Logger, log4j suggestion
		}
		return uuid;
	}
	
	public Result<Task> findTasksbyUsername (String username) {
		
		Session connection = CassandraDAOFactory.getConnection();
		Result<Task> tasks = null;
		try {
			PreparedStatement stmt = connection.prepare("SELECT * FROM tasks WHERE username = ?);");
			BoundStatement bound_stmt = new BoundStatement(stmt);
			
			ResultSet results = connection.execute(bound_stmt.bind(username));
			Mapper<Task> mapper = CassandraDAOFactory.getMappingManager().mapper(Task.class);
			tasks = mapper.map(results);
		}
		catch (Exception e) {
			//Logger
		}
		return tasks;
	}
	
	public Result<Task> findTasksbyState (String username, String state) {
		
		Session connection = CassandraDAOFactory.getConnection();
		Result<Task> tasks = null;
		try {
			PreparedStatement stmt = connection.prepare("SELECT * FROM tasks WHERE username = ? AND state = ?);");
			BoundStatement bound_stmt = new BoundStatement(stmt);
			
			ResultSet results = connection.execute(bound_stmt.bind(username, state));
			Mapper<Task> mapper = CassandraDAOFactory.getMappingManager().mapper(Task.class);
			tasks = mapper.map(results);
		}
		catch (Exception e) {
			//Logger
		}
		return tasks;
	}

}
