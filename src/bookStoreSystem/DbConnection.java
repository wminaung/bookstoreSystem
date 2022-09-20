package bookStoreSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DbConnection {

	// connect java with MYsql database
	protected static Connection initializeDatabase() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // register mysql driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");
			System.out.println("Connected successfully bookstore DB");


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;

	}

	
}

