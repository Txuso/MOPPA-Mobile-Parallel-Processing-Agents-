package classes;

public class Task {
	private double taskID;
	private double taskValue;
	private double taskResult;
	private String taskState;
	private String creatorUsername;
	
	public Task(double taskID, double taskValue, double taskResult, String taskState, String creatorUsername) {
		super();
		this.taskID = taskID;
		this.taskValue = taskValue;
		this.taskResult = taskResult;
		this.taskState = taskState;
		this.creatorUsername = creatorUsername;
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
	public double getTaskValue() {
		return taskValue;
	}
	public void setTaskValue(double taskValue) {
		this.taskValue = taskValue;
	}
	public double getTaskResult() {
		return taskResult;
	}
	public void setTaskResult(double taskResult) {
		this.taskResult = taskResult;
	}

}
