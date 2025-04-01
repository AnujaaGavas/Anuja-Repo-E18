package genericBaseClassUtility;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import GenericUtility.DatabaseUtility;
import GenericUtility.PropertiesFileUtility;

public class BaseClass 
{
	
	public WebDriver driver = null;
	DatabaseUtility dbutil = new DatabaseUtility();
	PropertiesFileUtility pro = new PropertiesFileUtility();
	public static WebDriver sdriver=null;
	
	@BeforeSuite
	public void beforeSuite() throws SQLException
	{
		System.out.println("Established database connection");
		dbutil.getDBConnection("jdbc:mysql://localhost:3306/Ninza_E18", "root", "root");
	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("Pre configuration Setup");
	}
	
	
	@BeforeClass
	public void beforeClass() throws IOException
	{
		System.out.println("Launch the browser");
		String BROWSER = pro.readingDataFromPropFile("browser");
		if(BROWSER.equalsIgnoreCase("chrome")
				)
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	

}
