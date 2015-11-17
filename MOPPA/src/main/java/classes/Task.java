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
	
	/**
	 * 
	 * @return it return the creator username
	 */
	public final String getCreatorUsername() {
		return creatorUsername;
	}
	
	/**
	 * 
	 * @param newCreatorUsername the creator username
	 * that is going to be changed
	 */
	public final void setCreatorUsername(final String newCreatorUsername) {
		this.creatorUsername = newCreatorUsername;
	}
	/**
	 * 
	 * @return it returns the state of the user.
	 */
	public final String getTaskState() {
		return taskState;
	}
	/**
	 * 
	 * @param newTaskState the new state that is going
	 * to be assigned to the user
	 */
	public final void setTaskState(final String newTaskState) {
		this.taskState = newTaskState;
	}
	
	/**
	 * 
	 * @return it returns the task id
	 */
	public final double getTaskID() {
		return taskID;
	}
	
	/**
	 * 
	 * @param newTaskID the new task id that 
	 * is going to be assigned to the user
	 */
	public final void setTaskID(final double newTaskID) {
		this.taskID = newTaskID;
	}
	
	/**
	 * 
	 * @return it returns the value to calculate of the task
	 */
	public final int getTaskValue() {
		return taskValue;
	}
	
	/**
	 * 
	 * @param newTaskValue the value of the new task value
	 * that is going to be changed
	 */
	public final void setTaskValue(final int newTaskValue) {
		this.taskValue = newTaskValue;
	}
	
	/**
	 * 
	 * @return it returns the value of the task
	 */
	public final double getTaskResult() {
		return taskResult;
	}
	
	/**
	 * 
	 * @param newTaskResult the new task result that is going to be changed
	 */
	public final void setTaskResult(final double newTaskResult) {
		this.taskResult = newTaskResult;
	}

}
