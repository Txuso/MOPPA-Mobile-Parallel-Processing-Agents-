package test;

import org.junit.*;
import static org.junit.Assert.*;
import com.datastax.driver.core.ResultSet;

import cassandradb.EstablishConnection;

/**
 * The class <code>EstablishConnectionTest</code> contains tests for the class <code>{@link EstablishConnection}</code>.
 *
 * @generatedBy CodePro at 11/18/15 6:13 PM
 * @author Txuso
 * @version $Revision: 1.0 $
 */
public class EstablishConnectionTest {
	/**
	 * Run the EstablishConnection() constructor test.
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@Test
	public void testEstablishConnection_1()
		throws Exception {
		EstablishConnection result = new EstablishConnection();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the void closeConnection() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@Test
	public void testCloseConnection_1()
		throws Exception {
		EstablishConnection fixture = new EstablishConnection();
		fixture.setUsername("");
		fixture.setPassword("");
		fixture.setClusterName("");

		fixture.closeConnection();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at cassandradb.EstablishConnection.closeConnection(EstablishConnection.java:38)
	}

	/**
	 * Run the ResultSet executeQuery(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@Test
	public void testExecuteQuery_1()
		throws Exception {
		EstablishConnection fixture = new EstablishConnection();
		fixture.setUsername("");
		fixture.setPassword("");
		fixture.setClusterName("");
		String query = "";

		ResultSet result = fixture.executeQuery(query);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at cassandradb.EstablishConnection.executeQuery(EstablishConnection.java:55)
		assertNotNull(result);
	}

	/**
	 * Run the void openConnection() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@Test
	public void testOpenConnection_1()
		throws Exception {
		EstablishConnection fixture = new EstablishConnection();
		fixture.setUsername("");
		fixture.setPassword("");
		fixture.setClusterName("");

		fixture.openConnection();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    com.datastax.driver.core.exceptions.NoHostAvailableException: All host(s) tried for query failed (tried: /127.0.0.1:9042 (com.datastax.driver.core.TransportException: [/127.0.0.1:9042] Cannot connect))
		//       at com.datastax.driver.core.ControlConnection.reconnectInternal(ControlConnection.java:227)
		//       at com.datastax.driver.core.ControlConnection.connect(ControlConnection.java:86)
		//       at com.datastax.driver.core.Cluster$Manager.init(Cluster.java:1409)
		//       at com.datastax.driver.core.Cluster.init(Cluster.java:160)
		//       at com.datastax.driver.core.Cluster.connectAsync(Cluster.java:338)
		//       at com.datastax.driver.core.Cluster.connect(Cluster.java:283)
		//       at cassandradb.EstablishConnection.openConnection(EstablishConnection.java:34)
	}

	/**
	 * Run the void setClusterName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@Test
	public void testSetClusterName_1()
		throws Exception {
		EstablishConnection fixture = new EstablishConnection();
		fixture.setUsername("");
		fixture.setPassword("");
		fixture.setClusterName("");
		String clusterName = "";

		fixture.setClusterName(clusterName);

		// add additional test code here
	}

	/**
	 * Run the void setPassword(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@Test
	public void testSetPassword_1()
		throws Exception {
		EstablishConnection fixture = new EstablishConnection();
		fixture.setUsername("");
		fixture.setPassword("");
		fixture.setClusterName("");
		String password = "";

		fixture.setPassword(password);

		// add additional test code here
	}

	/**
	 * Run the void setUsername(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@Test
	public void testSetUsername_1()
		throws Exception {
		EstablishConnection fixture = new EstablishConnection();
		fixture.setUsername("");
		fixture.setPassword("");
		fixture.setClusterName("");
		String username = "";

		fixture.setUsername(username);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(EstablishConnectionTest.class);
	}
}