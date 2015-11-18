package cassandradb;

import com.datastax.driver.core.*;

public class CassandraDAOFactory extends DAOFactory {
	
	private static final String clusterName = "moppa";
	private static final String username = "cassandra";
	private static final String password = "cassandrapassword";
	private static final String contactPoint = "127.0.0.1";
	private static final int port = 9042;
	
	public static Session createConnection() {
		
		Cluster cluster = Cluster.builder().addContactPoint(contactPoint).withPort(port).withCredentials(username, password).build();
		Session session = cluster.connect(clusterName);
		return session;
	}
	
	public static void closeConnection(Session session) {
		if (!session.isClosed()) {
			
			session.close();
		}
	}
	
	public TaskDAO getTaskDAO() {
		
		return new CassandraTaskDAO();
	}
	
	public UserDAO getUserDAO() {
		
		return new CassandraUserDAO();
	}

}
