package test;

import javax.ws.rs.core.Response;
import org.junit.*;

import moppaapis.TaskAPI;

import static org.junit.Assert.*;

/**
 * The class <code>TaskAPITest</code> contains tests for the class <code>{@link TaskAPI}</code>.
 *
 * @generatedBy CodePro at 11/18/15 6:14 PM
 * @author Txuso
 * @version $Revision: 1.0 $
 */
public class TaskAPITest {
	/**
	 * Run the TaskAPI() constructor test.
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testTaskAPI_1()
		throws Exception {
		TaskAPI result = new TaskAPI();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the Response createTaskInJSON(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testCreateTaskInJSON_1()
		throws Exception {
		TaskAPI fixture = new TaskAPI();
		String input = "{\"taskID\": 1234,\"taskValue\": 200,\"result\": 12345,"
				+ " \"taskState\": \"doing\",\"creatorUsername\": \"adb\"}";

		Response result = fixture.createTaskInJSON(input);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    javax.json.stream.JsonParsingException: Invalid token=EOF at (line no=1, column no=0, offset=-1). Expected tokens are: [CURLYOPEN, SQUAREOPEN]
		//       at org.glassfish.json.JsonParserImpl.parsingException(JsonParserImpl.java:238)
		//       at org.glassfish.json.JsonParserImpl.access$1200(JsonParserImpl.java:61)
		//       at org.glassfish.json.JsonParserImpl$NoneContext.getNextEvent(JsonParserImpl.java:232)
		//       at org.glassfish.json.JsonParserImpl$StateIterator.next(JsonParserImpl.java:172)
		//       at org.glassfish.json.JsonParserImpl.next(JsonParserImpl.java:149)
		//       at org.glassfish.json.JsonReaderImpl.readObject(JsonReaderImpl.java:101)
		//       at moppaapis.TaskAPI.createTaskInJSON(TaskAPI.java:100)
		assertNotNull(result);
	}

	/**
	 * Run the Response createTaskInJSON(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testCreateTaskInJSON_2()
		throws Exception {
		TaskAPI fixture = new TaskAPI();
		String input = "";

		Response result = fixture.createTaskInJSON(input);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    javax.json.stream.JsonParsingException: Invalid token=EOF at (line no=1, column no=0, offset=-1). Expected tokens are: [CURLYOPEN, SQUAREOPEN]
		//       at org.glassfish.json.JsonParserImpl.parsingException(JsonParserImpl.java:238)
		//       at org.glassfish.json.JsonParserImpl.access$1200(JsonParserImpl.java:61)
		//       at org.glassfish.json.JsonParserImpl$NoneContext.getNextEvent(JsonParserImpl.java:232)
		//       at org.glassfish.json.JsonParserImpl$StateIterator.next(JsonParserImpl.java:172)
		//       at org.glassfish.json.JsonParserImpl.next(JsonParserImpl.java:149)
		//       at org.glassfish.json.JsonReaderImpl.readObject(JsonReaderImpl.java:101)
		//       at moppaapis.TaskAPI.createTaskInJSON(TaskAPI.java:100)
		assertNotNull(result);
	}

	/**
	 * Run the Response createTaskInJSON(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testCreateTaskInJSON_3()
		throws Exception {
		TaskAPI fixture = new TaskAPI();
		String input = "";

		Response result = fixture.createTaskInJSON(input);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    javax.json.stream.JsonParsingException: Invalid token=EOF at (line no=1, column no=0, offset=-1). Expected tokens are: [CURLYOPEN, SQUAREOPEN]
		//       at org.glassfish.json.JsonParserImpl.parsingException(JsonParserImpl.java:238)
		//       at org.glassfish.json.JsonParserImpl.access$1200(JsonParserImpl.java:61)
		//       at org.glassfish.json.JsonParserImpl$NoneContext.getNextEvent(JsonParserImpl.java:232)
		//       at org.glassfish.json.JsonParserImpl$StateIterator.next(JsonParserImpl.java:172)
		//       at org.glassfish.json.JsonParserImpl.next(JsonParserImpl.java:149)
		//       at org.glassfish.json.JsonReaderImpl.readObject(JsonReaderImpl.java:101)
		//       at moppaapis.TaskAPI.createTaskInJSON(TaskAPI.java:100)
		assertNotNull(result);
	}

	/**
	 * Run the Response findTaskByStateInJSON(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testFindTaskByStateInJSON_1()
		throws Exception {
		TaskAPI fixture = new TaskAPI();
		String taskState = "";

		Response result = fixture.findTaskByStateInJSON(taskState);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:613)
		//       at moppaapis.TaskAPI.findTaskByStateInJSON(TaskAPI.java:186)
		assertNotNull(result);
	}

	/**
	 * Run the Response findTaskByStateInJSON(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testFindTaskByStateInJSON_2()
		throws Exception {
		TaskAPI fixture = new TaskAPI();
		String taskState = "";

		Response result = fixture.findTaskByStateInJSON(taskState);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:613)
		//       at moppaapis.TaskAPI.findTaskByStateInJSON(TaskAPI.java:186)
		assertNotNull(result);
	}

	/**
	 * Run the Response findTaskByStateInJSON(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testFindTaskByStateInJSON_3()
		throws Exception {
		TaskAPI fixture = new TaskAPI();
		String taskState = "";

		Response result = fixture.findTaskByStateInJSON(taskState);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:613)
		//       at moppaapis.TaskAPI.findTaskByStateInJSON(TaskAPI.java:186)
		assertNotNull(result);
	}

	/**
	 * Run the Response findTaskByStateInJSON(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testFindTaskByStateInJSON_4()
		throws Exception {
		TaskAPI fixture = new TaskAPI();
		String taskState = "";

		Response result = fixture.findTaskByStateInJSON(taskState);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:613)
		//       at moppaapis.TaskAPI.findTaskByStateInJSON(TaskAPI.java:186)
		assertNotNull(result);
	}

	/**
	 * Run the Response findTaskByUsernameInJSON(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testFindTaskByUsernameInJSON_1()
		throws Exception {
		TaskAPI fixture = new TaskAPI();
		String username = "";

		Response result = fixture.findTaskByUsernameInJSON(username);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:613)
		//       at moppaapis.TaskAPI.findTaskByUsernameInJSON(TaskAPI.java:153)
		assertNotNull(result);
	}

	/**
	 * Run the Response findTaskByUsernameInJSON(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testFindTaskByUsernameInJSON_2()
		throws Exception {
		TaskAPI fixture = new TaskAPI();
		String username = "";

		Response result = fixture.findTaskByUsernameInJSON(username);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:613)
		//       at moppaapis.TaskAPI.findTaskByUsernameInJSON(TaskAPI.java:153)
		assertNotNull(result);
	}

	/**
	 * Run the Response findTaskByUsernameInJSON(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testFindTaskByUsernameInJSON_3()
		throws Exception {
		TaskAPI fixture = new TaskAPI();
		String username = "";

		Response result = fixture.findTaskByUsernameInJSON(username);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:613)
		//       at moppaapis.TaskAPI.findTaskByUsernameInJSON(TaskAPI.java:153)
		assertNotNull(result);
	}

	/**
	 * Run the Response getAllTasks() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testGetAllTasks_1()
		throws Exception {
		TaskAPI fixture = new TaskAPI();

		Response result = fixture.getAllTasks();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:590)
		//       at exceptions.AbstractException.responseBuilder(AbstractException.java:86)
		//       at exceptions.AbstractException.build(AbstractException.java:94)
		//       at exceptions.AbstractException.except(AbstractException.java:102)
		//       at moppaapis.TaskAPI.getAllTasks(TaskAPI.java:67)
		assertNotNull(result);
	}

	/**
	 * Run the Response getAllTasks() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testGetAllTasks_2()
		throws Exception {
		TaskAPI fixture = new TaskAPI();

		Response result = fixture.getAllTasks();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:590)
		//       at exceptions.AbstractException.responseBuilder(AbstractException.java:86)
		//       at exceptions.AbstractException.build(AbstractException.java:94)
		//       at exceptions.AbstractException.except(AbstractException.java:102)
		//       at moppaapis.TaskAPI.getAllTasks(TaskAPI.java:67)
		assertNotNull(result);
	}

	/**
	 * Run the Response getAllTasks() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	@Test
	public void testGetAllTasks_3()
		throws Exception {
		TaskAPI fixture = new TaskAPI();

		Response result = fixture.getAllTasks();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class to jar:file:/Users/Txuso/.m2/repository/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar!/javax/ws/rs/ext/RuntimeDelegate.class
		//       at javax.ws.rs.ext.RuntimeDelegate.findDelegate(RuntimeDelegate.java:146)
		//       at javax.ws.rs.ext.RuntimeDelegate.getInstance(RuntimeDelegate.java:120)
		//       at javax.ws.rs.core.Response$ResponseBuilder.newInstance(Response.java:848)
		//       at javax.ws.rs.core.Response.status(Response.java:590)
		//       at exceptions.AbstractException.responseBuilder(AbstractException.java:86)
		//       at exceptions.AbstractException.build(AbstractException.java:94)
		//       at exceptions.AbstractException.except(AbstractException.java:102)
		//       at moppaapis.TaskAPI.getAllTasks(TaskAPI.java:67)
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 11/18/15 6:14 PM
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
	 * @generatedBy CodePro at 11/18/15 6:14 PM
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
	 * @generatedBy CodePro at 11/18/15 6:14 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TaskAPITest.class);
	}
}