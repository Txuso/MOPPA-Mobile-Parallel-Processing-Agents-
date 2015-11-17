package cassandradb;

import java.util.UUID;

import com.datastax.driver.core.*;
public class CassandraTaskDAO implements TaskDAO {
	
	public CassandraTaskDAO() {
		
	}
	
	public UUID insertTask (String username, int problem) {
		
		Session connection = CassandraDAOFactory.createConnection();
		UUID uuid = java.util.UUID.randomUUID();
		PreparedStatement stmt = connection.prepare("INSERT INTO tasks (taskid, username, problem, result, state) VALUES (?, ?, ?, ?, ?);");
		BoundStatement bound_stmt = new BoundStatement(stmt);
		
		connection.execute(bound_stmt.bind(uuid, username, problem, "", "Waiting"));
		return uuid;
	}

}
