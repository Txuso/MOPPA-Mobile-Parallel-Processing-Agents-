package test;

import static org.junit.Assert.*;
import org.junit.Test;

import exception.MoppaException;
import resttest.TaskAPI;
import javax.ws.rs.core.Response;


public class TaskApiTest {
	
	TaskAPI taskApi = new TaskAPI();
	
	@Test
	public void testCreateTask() throws MoppaException {
		String input = "{taskID: 1234,taskValue: 10,result: 12345}";
		Response an = taskApi.createTaskInJSON(input);
		assertEquals(an, "Error");
		
	}

}
