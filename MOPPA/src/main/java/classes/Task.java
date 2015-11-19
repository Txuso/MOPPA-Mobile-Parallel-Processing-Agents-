package classes;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.Table;

/**
 * 
 * @author Txuso
 *	The class related to the tasks
 * @author Mario Measic-Gavran
 *  Modified 18.11.2015 - Mapping
 */

@Table(keyspace = "moppa", name = "tasks",
readConsistency = "QUORUM",
writeConsistency = "QUORUM",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)

public class Task {
	
	
	private UUID taskid;

	private int problem;

	private String result;

	private String state;

	private String username;
	
	public Task() {
	    
	}
	
	public Task(final UUID uuid, 
	            final String newCreatorUsername, final int newTaskValue,
	            final String newTaskResult, final String newTaskState) {
		super();
		this.taskid = uuid;
		this.problem = newTaskValue;
		this.result = newTaskResult;
		this.state = newTaskState;
		this.username = newCreatorUsername;
	}

	public final UUID getTaskid() {
		return taskid;
	}

	public final void setTaskid(final UUID newTaskId) {
		this.taskid = newTaskId;
	}

	public final int getProblem() {
		return problem;
	}

	public final void setProblem(final int newProblem) {
		this.problem = newProblem;
	}

	public final String getResult() {
		return result;
	}

	public final void setResult(final String newResult) {
		this.result = newResult;
	}

	public final String getState() {
		return state;
	}

	public final void setState(final String newState) {
		this.state = newState;
	}

	public final String getUsername() {
		return username;
	}

	public final void setUsername(final String newUsername) {
		this.username = newUsername;
	}
	
	
}