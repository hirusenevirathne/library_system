package library_system;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class sqlConnection {
	
	Connection connection = null;
	
	public static Connection dbConnector() {
		
		String url = "jdbc:mysql://localhost:3306/library_system"; //define the local host port and database name
		String username = "root";  //sql server username
		String password = "newrootpw"; //sql server password
		
		try {
			
			Connection connection = DriverManager.getConnection(url,username,password); //connect with the database
			System.out.println("Connect to the database");
			return connection;
			
		} catch (Exception e) {
			
			System.out.println("Oops ERROR !");
			e.printStackTrace(); //show the error message
			JOptionPane.showMessageDialog(null, e);
			return null;
			// TODO: handle exception
		}
		
		
	}
	
}
