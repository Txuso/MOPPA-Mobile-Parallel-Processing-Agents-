package cassandradb;

import java.util.UUID;

import com.datastax.driver.core.ResultSet;

public interface TaskDAO {
	
	public UUID insertTask (String username, int problem);
	//public ResultSet findTasksbyUsername (String username);
	//public ResultSet findTasksbyState (String username, String state);
}
