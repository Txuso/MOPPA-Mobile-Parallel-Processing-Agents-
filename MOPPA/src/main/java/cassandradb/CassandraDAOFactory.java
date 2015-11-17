package cassandradb;

import com.datastax.driver.core.*;

public class CassandraDAOFactory extends DAOFactory {
	
	public static final String clusterName = "moppa";
	public static final String username = "cassandra";
	public static final String password = "cassandrapassword";
	public static final String contactPoint = "127.0.0.1";
	public static final int port = 9042;
	
	public static Session createConnection() {
		
		Cluster cluster = Cluster.builder().addContactPoint(contactPoint).withPort(port).withCredentials(username, password).build();
		Session session = cluster.connect(clusterName);
		return session;
	}
	
	public TaskDAO getTaskDAO() {
		
		return new CassandraTaskDAO();
	}
	
	/*public UserDAO getUserDAO() {
		
		return new CassandraUserDAO();
	}*/

}
