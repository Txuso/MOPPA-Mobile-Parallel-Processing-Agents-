package classes;

/**
 * 
 * @author Txuso
 *	The class related to the tasks
 */
public class Task {
	
	/**
	 * the id of the tasks.
	 */
	private double taskID;
	
	/**
	 * 
	 * the value of the task that is going to be calculated.
	 */
	private int taskValue;
	
	/**
	 * The result of the operation.
	 */
	private double taskResult;
	
	/**
	 * The state of the task {Waiting, In process, Finished}.
	 */
	private String taskState;
	
	/**
	 * the task creator username.
	 */
	private String creatorUsername;
	
	/**
	 * 
	 * @param newTaskID the id of the tasks.
	 * @param newTaskValue the value of the task that is going to be calculated.
	 * @param newTaskResult The result of the operation.
	 * @param newTaskState The state of the task 
	 * {Waiting, In process, Finished}.
	 * @param newCreatorUsername the task creator username.
	 */
	public Task(final double newTaskID, final int newTaskValue,
	final double newTaskResult, final String newTaskState, 
	final String newCreatorUsername) {
		super();
		this.taskID = newTaskID;
		this.taskValue = newTaskValue;
		this.taskResult = newTaskResult;
		this.taskState = newTaskState;
		this.creatorUsername = newCreatorUsername;
	}
	
	public String getCreatorUsername() {
		return creatorUsername;
	}
	public void setCreatorUsername(String creatorUsername) {
		this.creatorUsername = creatorUsername;
	}
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	
	public double getTaskID() {
		return taskID;
	}
	public void setTaskID(double taskID) {
		this.taskID = taskID;
	}
	public int getTaskValue() {
		return taskValue;
	}
	public void setTaskValue(int taskValue) {
		this.taskValue = taskValue;
	}
	public double getTaskResult() {
		return taskResult;
	}
	public void setTaskResult(double taskResult) {
		this.taskResult = taskResult;
	}

}
