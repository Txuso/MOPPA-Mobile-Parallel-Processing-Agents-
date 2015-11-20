package moppaapis.test;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import moppaapis.TaskAPI;

import static org.junit.Assert.assertNotNull;

/**
 * The class <code>TaskAPITest</code> 
 * contains tests for the class <code>{@link TaskAPI}</code>.
 *
 * @author Txuso
 * @version $Revision: 1.0 $
 */
public class TaskAPITest {
  /**
   * Run the TaskAPI() constructor test.
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testTaskAPICreation() {
    TaskAPI result = new TaskAPI();
    assertNotNull(result);
    // add additional test code here
  }

  /**
   * Run the Response createTaskInJSON(String) method test.
   *
   * @throws Exception if the taskValue is wrong
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testCreateTaskInJSONCorrectInput()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    String input = "{\"taskID\": 1234,\"taskValue\": 10,\"result\":"
    + " \"12345\", \"taskState\": \"doing\",\"creatorUsername\": "
    + "\"adb\"}";

    Response result = fixture.createTaskInJSON(input);

    assertNotNull(result);
  }

  /**
   * Run the Response createTaskInJSON(String) method test.
   *
   * @throws Exception launched because the taskValue is negative
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testCreateTaskInJSONWithNegativeValue()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    String input = "{\"taskID\": 1234,\"taskValue\": -5,\"result\":"
        + " \"12345\", \"taskState\": \"doing\",\"creatorUsername\": "
        + "\"adb\"}";
    
    Response result = fixture.createTaskInJSON(input);

    assertNotNull(result);
  }

  /**
   * Run the Response createTaskInJSON(String) method test.
   *
   * @throws Exception is launched because the taskValue is > 100
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testCreateTaskInJSONWithValueHigherThan100()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    String input = "{\"taskID\": 1234,\"taskValue\": 1000,\"result\":"
        + " \"12345\", \"taskState\": \"doing\",\"creatorUsername\": "
        + "\"adb\"}";

    Response result = fixture.createTaskInJSON(input);

    assertNotNull(result);
  }

  /**
   * Run the Response findTaskByStateInJSON(String) method test.
   *
   * 
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testFindTaskByStateInJSONCorrect() {
    TaskAPI fixture = new TaskAPI();
    String taskState = "{Waiting}";
    String username = "Mario";
    Response result = fixture.findTaskByStateInJSON(username, taskState);
    assertNotNull(result);
  }

  /**
   * Run the Response findTaskByStateInJSON(String) method test.
   *
   * @throws Exception it is launched because there aren't tasks 
   * with the input state
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testFindTaskByStateInJSONNoTaskWithThatState()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    String username = "Mario";
    String taskState = "In process";
    Response result = fixture.findTaskByStateInJSON(username, taskState);
    assertNotNull(result);
  }


  /**
   * Run the Response findTaskByUsernameInJSON(String) method test.
   *
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testFindTaskByUsernameInJSONCorrectInput() {
    TaskAPI fixture = new TaskAPI();
    String username = "{Txuso}";
    Response result = fixture.findTaskByUsernameInJSON(username);
    assertNotNull(result);
  }

  /**
   * Run the Response findTaskByUsernameInJSON(String) method test.
   *
   * @throws Exception it is launched because there are not results
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testFindTaskByUsernameInJSONNotResults()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    String username = "{Rodolfo}";
    Response result = fixture.findTaskByUsernameInJSON(username);
    assertNotNull(result);
  }

  /**
   * Run the Response getAllTasks() method test.
   *
   * @throws Exception is launched if there aren't tasks created yet
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testGetAllTasksCorrect()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    Response result = fixture.getAllTasks();
    assertNotNull(result);
  }

  /**
   * Run the Response getAllTasks() method test.
   *
   * @throws Exception is launched if there aren't tasks created yet
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  @Test
  public final void testGetAllTasksNoTasks()
    throws Exception {
    TaskAPI fixture = new TaskAPI();
    Response result = fixture.getAllTasks();
    assertNotNull(result);
  }

  /**
   * Perform pre-test initialization.
   *
   * @throws Exception
   *         if the initialization fails for some reason
   *
   * @generatedBy CodePro at 11/19/15 12:20 PM
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
   * @generatedBy CodePro at 11/19/15 12:20 PM
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
   * @generatedBy CodePro at 11/19/15 12:20 PM
   */
  public static void main(final String[] args) {
    new org.junit.runner.JUnitCore().run(TaskAPITest.class);
  }
}
