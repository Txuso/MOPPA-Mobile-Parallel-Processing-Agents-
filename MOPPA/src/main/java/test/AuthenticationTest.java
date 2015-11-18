package test;

import javax.ws.rs.core.Response;
import org.junit.*;

import moppaapis.Authentication;

import static org.junit.Assert.*;

/**
 * The class <code>AuthenticationTest</code> contains tests for the class <code>{@link Authentication}</code>.
 *
 * @generatedBy CodePro at 11/18/15 6:13 PM
 * @author Txuso
 * @version $Revision: 1.0 $
 */
public class AuthenticationTest {
	/**
	 * Run the Response authenticateUser(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@Test
	public void testAuthenticateUser_1()
		throws Exception {
		Authentication fixture = new Authentication();
		String username = "";
		String password = "";

		Response result = fixture.authenticateUser(username, password);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:590)
		//       at javax.ws.rs.core.Response.status(Response.java:601)
		//       at moppaapis.Authentication.authenticateUser(Authentication.java:43)
		assertNotNull(result);
	}

	/**
	 * Run the Response authenticateUser(String,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:13 PM
	 */
	@Test
	public void testAuthenticateUser_2()
		throws Exception {
		Authentication fixture = new Authentication();
		String username = "";
		String password = "";

		Response result = fixture.authenticateUser(username, password);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:590)
		//       at javax.ws.rs.core.Response.status(Response.java:601)
		//       at moppaapis.Authentication.authenticateUser(Authentication.java:43)
		assertNotNull(result);
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
		new org.junit.runner.JUnitCore().run(AuthenticationTest.class);
	}
}