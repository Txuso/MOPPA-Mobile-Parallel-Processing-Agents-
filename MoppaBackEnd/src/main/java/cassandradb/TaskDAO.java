package cassandradb;

import java.util.UUID;

import com.datastax.driver.mapping.Result;

import classes.Task;

/**
 * 
 * @author Mario Measic-Gavran
 * This interface defines methods for Task.
 */
public interface TaskDAO {
	
	boolean insertTask(UUID uuid, String username, int problem);
	Result<Task> findTasksbyUsername(String username);
	Result<Task> findTasksbyState(String username, String state);
}
