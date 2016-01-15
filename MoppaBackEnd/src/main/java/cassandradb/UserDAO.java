package cassandradb;

import classes.MoppaUser;

import com.datastax.driver.mapping.Result;

/**
 * 
 * @author Mario Measic-Gavran
 * This interface defines methods for User.
 */

public interface UserDAO {
	
	boolean insertUser(String userName, String password);
	Result<MoppaUser> checkIfUserExists (String userName);
	Result<MoppaUser> loginUser(String userName, String password);
}
