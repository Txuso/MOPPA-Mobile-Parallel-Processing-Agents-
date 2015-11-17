package cassandradb;

public abstract class DAOFactory {
	
	public abstract TaskDAO getTaskDAO();
	//public abstract UserDAO getUserDAO();
	
	// @17.11.2015 Author: Mario Measic-Gavran
	// Possible to add more data sources
	// We are going to use Cassandra
	public static DAOFactory getDAOFactory() {
		
		return new CassandraDAOFactory();
	}
	


}
