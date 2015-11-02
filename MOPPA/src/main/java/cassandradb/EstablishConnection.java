package cassandradb;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

public class EstablishConnection {

		private String clusterName = "moppa";
		private String username = "cassandra";
		private String password = "cassandrapassword";
		
		private Cluster cluster;
		private Session session;

		public void openConnection() {
			cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9042).withCredentials(username, password).build();
			session = cluster.connect(clusterName);
		}
		
		public void closeConnection() {
			cluster.close();
		}
		
		public void setClusterName(String clusterName) {
			this.clusterName = clusterName;
		}
		
		public void setUsername(String username) {
			this.username = username;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public ResultSet executeQuery (String query) { /* Bad query, test purpose only */
			ResultSet rs;
			rs = session.execute(query);
		    return rs;
		}

	}
