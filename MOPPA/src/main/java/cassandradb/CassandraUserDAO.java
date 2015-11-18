package cassandradb;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;

import classes.MoppaUser;
import classes.Task;

public class CassandraUserDAO implements UserDAO {

	public int insertUser(String username, String password) {
				
		try {
			MoppaUser user = new MoppaUser(username, password); //check
			Mapper<MoppaUser> mapper = CassandraDAOFactory.getMappingManager().mapper(MoppaUser.class);
			mapper.save(user);
		}
		
		catch (Exception e) {
			//Logger			
		}
		return 1; //check
	}

}
