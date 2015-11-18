package cassandradb;

import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Result;

import classes.Task;


public interface TaskDAO {
	
	public UUID insertTask (String username, int problem);
	public Result<Task> findTasksbyUsername (String username);
	public Result<Task> findTasksbyState (String username, String state);
}
