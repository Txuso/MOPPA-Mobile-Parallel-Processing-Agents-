package utils;

import java.util.UUID;

public class GeneratorUUID {
	
	public static UUID generateTaskID() {
		UUID taskid = java.util.UUID.randomUUID();
		return taskid;
	}

}
