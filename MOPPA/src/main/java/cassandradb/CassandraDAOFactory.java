package cassandradb;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

public class CassandraDAOFactory extends DAOFactory {
	
  private static final String clusterName = "moppa";
  private static final String username = "cassandra";
  private static final String password = "cassandrapassword";
  private static final String contactPoint = "127.0.0.1";
  private static final int port = 9042;
  private static Cluster cluster = Cluster.builder().addContactPoint(contactPoint).withPort(port).withCredentials(username, password).build();
	private static Session session = cluster.connect(clusterName);
	private static MappingManager manager = new MappingManager(session);
	
	public static Session getConnection() {
		return session;
	}
	
	public static MappingManager getMappingManager() {
		return manager;
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
