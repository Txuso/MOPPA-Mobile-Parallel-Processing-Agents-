package classes;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.Table;

/**
	 * 
	 * @author Txuso
	 * This class is related to the MoppaUsers
	 */

@Table(keyspace = "moppa", name = "users",
readConsistency = "QUORUM",
writeConsistency = "QUORUM",
caseSensitiveKeyspace = false,
caseSensitiveTable = false)

public class MoppaUser {
	
	public MoppaUser(final String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Moppa user's username.
	 */
	private String username;
	
	/**
	 * Moppa user's password.
	 */
	private String password;
	
	/**
	 * 
	 * @return it returns the username
	 */
	public final String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @param user the username that is going to be changed
	 */
	public final void setUsername(final String user) {
		this.username = user;
	}
	
	/**
	 * 
	 * @return it returns the password of the chosen user
	 */
	public final String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param newPassword the password that is going to be changed
	 */
	public final void setPassword(final String newPassword) {
		this.password = newPassword;
	}
	
}
