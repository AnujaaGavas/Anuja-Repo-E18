package Assignments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DDT_by_Database_Delete {

	public static void main(String[] args) throws SQLException 
	{

		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ninza_E18", "root", "root");
		
		Statement stmt = conn.createStatement();
		
		int result = stmt.executeUpdate("delete from Ninza_CRM_Details where Browser='chrome'");
		

		System.out.println(result);
		
		if(result!=0)
		{
			System.out.println("data Updated");
		}
		else
		{
			System.out.println("Operation failed");
		}
		
		conn.close();

		
		
		
		
	}

}
