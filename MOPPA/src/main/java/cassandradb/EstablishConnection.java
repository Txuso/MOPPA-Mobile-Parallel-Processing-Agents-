package cassandradb;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class EstablishConnection {

		private static String clusterName = "moppa";
		private static String username = "cassandra";
		private static String password = "cassandrapassword";

		public static void main(String[] args) {
			Cluster cluster;
			Session session;

			cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9042).withCredentials(username, password).build();
			session = cluster.connect(clusterName);

			/*session.execute("INSERT INTO performer (name, born, country, died, founded, style, type) VALUES ('Mario', 1992, 'Croatia', null, 2014, 'HipHop', null)");*/

			ResultSet results = session.execute("SELECT * FROM users where username = 'mario'");
			for (Row row : results) {
				System.out.format("%s %s", row.getString("username"), row.getString("password"));
			}
			cluster.close();

		}

	}
