package library_system;

import java.sql.Connection;
import java.sql.DriverManager;

public class sqlcon {
	
	public static void main( String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/library_system"; //define the local host port and database name
		String username = "root";  //sql server username
		String password = "newrootpw"; //sql server password
		
		try {
			
			Connection connection = DriverManager.getConnection(url,username,password); //connect with the database
			System.out.println("Connect to the database");
			
		} catch (Exception e) {
			
			System.out.println("Oops ERROR !");
			e.printStackTrace(); //show the error message
			// TODO: handle exception
		}
		
		
	}
	
}
