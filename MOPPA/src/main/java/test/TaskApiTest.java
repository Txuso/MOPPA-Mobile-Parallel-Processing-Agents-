package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import moppaapis.TaskAPI;
import javax.ws.rs.core.Response;

/**
 * 
 * @author Txuso
 * this class contains all the JUNIT tests carried out to test MOPPA API
 */
public class TaskApiTest {
	/**
	 * The task API that is going to be tested.
	 */
	private TaskAPI taskApi = new TaskAPI();
	
	/**
	 * Task creation JUnit test.
	 */
	@Test
	public final void testCreateTask() {
		String input = "{\"taskID\": 1234,\"taskValue\": 123,\"result\": 12345,"
		+ " \"taskState\": \"doing\",\"creatorUsername\": \"adb\"}";
		Response an = taskApi.createTaskInJSON(input);
		assertEquals("Error", an);
		
	}

}
