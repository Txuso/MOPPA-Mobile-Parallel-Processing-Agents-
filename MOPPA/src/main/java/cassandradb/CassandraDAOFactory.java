package cassandradb;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

/**
 * 
 * @author Mario Measic-Gavran
 * This class serves as a provider for connection details
 * to CassandraDB and creating instances of DAO objects.
 */

public class CassandraDAOFactory extends DAOFactory {
	
  private static final String CLUSTERNAME = "moppa";
  private static final String USERNAME = "cassandra"; 
                       // Have to put it in properties file
  private static final String PASSWORD = "cassandrapassword";
  private static final String CONTACTPOINT = "127.0.0.1";
  private static final int PORT = 9042;
  private static final Cluster CLUSTER = Cluster.builder()
                                         .addContactPoint(CONTACTPOINT)
                                         .withPort(PORT)
                                         .withCredentials(USERNAME, PASSWORD)
                                         .build();
	private static final Session SESSION = CLUSTER.connect(CLUSTERNAME);
	private static final MappingManager MANAGER = new MappingManager(SESSION);
	
	public static Session getConnection() {
		return SESSION;
	}
	
	public static MappingManager getMappingManager() {
		return MANAGER;
	}
	
	public static void closeConnection(final Session session) {
		if (!SESSION.isClosed()) {
			
		  SESSION.close();
		}
		else {
		  // Do nothing, needs review
		}
	}
	
	@Override
	public final CassandraTaskDAO getTaskDAO() {
		
		return new CassandraTaskDAO();
	}
	
	@Override
	public final CassandraUserDAO getUserDAO() {
		
		return new CassandraUserDAO();
	}

}
