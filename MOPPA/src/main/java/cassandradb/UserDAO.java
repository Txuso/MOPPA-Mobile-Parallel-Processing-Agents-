package cassandradb;

public interface UserDAO {
	
	public int insertUser(String username, String password);
}
