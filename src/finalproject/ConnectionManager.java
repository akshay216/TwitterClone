package finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is the connection manager class which returns the object of connection
 * type all the DAO classes are using this class to create connection
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class ConnectionManager {
	Connection con;

	public Connection getConnection() {

		try {
			String url = "jdbc:mysql://localhost:3306/test";
			String dbUser = "root";
			String dbPassword = "root";
			// jdbc drivers
			Class.forName("com.mysql.jdbc.Driver");
			try {
				// get a connection to the database
				con = DriverManager.getConnection(url, dbUser, dbPassword);

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException cnfe) {
			cnfe.getStackTrace();
		}

		return con;// returns connection type
	}
}