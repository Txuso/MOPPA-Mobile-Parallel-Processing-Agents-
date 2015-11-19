package cassandradb;

import com.datastax.driver.mapping.Mapper;

import classes.MoppaUser;

/**
 * 
 * @author Mario Measic-Gavran
 * This class is used for creating a new User 
 * and inserting a record into CassandraDB.
 */

public class CassandraUserDAO implements UserDAO {

	public final int insertUser(final String username, final String password) {
				
		try {
			MoppaUser user = new MoppaUser(username, password); //check
			Mapper<MoppaUser> mapper = CassandraDAOFactory
			                           .getMappingManager()
			                           .mapper(MoppaUser.class);
			mapper.save(user);
		} catch (Exception e) {
			//Logger			
		  }
		return 1; //check
	}

}
