package GenericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

public class DatabaseUtility 
{
	Connection conn;
	
	public void getDBConnection(String url,String uname,String pwd)
	{
		try 
		{
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, uname, pwd);
			
		}
		
		catch(Exception e)
		{
			System.out.println("Connection not established");
		}
	}
	
	
	public void closeConnection()
	{
		try
		{
			conn.close();
		}
		
		catch(Exception e)
		{
			
		}
	}
	
	
	public ResultSet executeQuery(String query)
	{
		ResultSet result = null;
		try
		{
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery(query);
		}
		
		catch(Exception e)
		{
			
		}
		
		return result;
	}
	
	public int executeNonSelectQuery(String query)
	{
		int result=0;
		try
		{
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		}
		
		catch(Exception e)
		{
			
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
