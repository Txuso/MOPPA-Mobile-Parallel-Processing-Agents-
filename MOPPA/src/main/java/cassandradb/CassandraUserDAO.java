package cassandradb;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

public class CassandraUserDAO implements UserDAO {

	public int insertUser(String username, String password) {
		
		Session connection = CassandraDAOFactory.createConnection();
		
		try {
			PreparedStatement stmt = connection.prepare("INSERT INTO users (username, password) VALUES (?, ?);");
			BoundStatement bound_stmt = new BoundStatement(stmt);
			
			connection.execute(bound_stmt.bind(username, password));
		}
		
		catch (Exception e) {
			//Logger			
		}
		
		finally {
			CassandraDAOFactory.closeConnection(connection);
		}
		
		return 1;
	}

}
