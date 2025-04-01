package Assignments;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebdriverUtility;
import io.opentelemetry.sdk.metrics.data.Data;

public class Comp_Date_Test {

	public static void main(String[] args) throws InterruptedException, IOException 
	{

		WebDriver driver = null;
		
		

		PropertiesFileUtility PropFileUtils = new PropertiesFileUtility();
		
	
		
		String BROWSER = PropFileUtils.readingDataFromPropFile("browser");
		String URL = PropFileUtils.readingDataFromPropFile("url");
		String UN = PropFileUtils.readingDataFromPropFile("username");
		String PWD = PropFileUtils.readingDataFromPropFile("password");
		
		
		
		
		JavaUtility jutil = new JavaUtility();
		
		int randomNum = jutil.getRandomNum(2000);
		
		ExcelFileUtility exutil = new ExcelFileUtility();
		
		
		String Comp_Name = exutil.readFromExcelFileUtility("DDT",1,2)+randomNum;
		String Target_Size = exutil.readFromExcelFileUtility("DDT",1,3);
		
	
		String closedate =jutil.generateReqDate(30);
		
		
		
		
		
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		
		else if (BROWSER.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		
		else
		{
			driver = new ChromeDriver();
		}
		
		
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(UN);
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		Thread.sleep(2000);
		
		
		
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
		driver.findElement(By.xpath("//input[@name='campaignName']")).sendKeys(Comp_Name);
		driver.findElement(By.xpath("//input[@name='targetSize']")).sendKeys(Target_Size);
		driver.findElement(By.xpath("//input[@name='expectedCloseDate']")).sendKeys(closedate);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		Thread.sleep(1000);
		
		
		String confmsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		
		if (confmsg.contains("Anuja"))
		{
			System.out.println("Added successfully");
		}
		
		else
		{
			System.out.println("Not added");
		}
		
		
		/*
		 * WebElement popup =
		 * driver.findElement(By.xpath("//div[contains(text(),'Successfully Added')]"));
		 * 
		 * 
		 * if (popup.isDisplayed())
		 * 
		 * { System.out.println("Confirmation pop-up displayd-- Looks good"); }
		 * 
		 * else { System.out.println("Popup not displayed -- something went wrong"); }
		 */
		
		Thread.sleep(7000);
		
		//Actions a = new Actions(driver);

		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon-container']"));
		
		//a.moveToElement(icon).perform();
		
		WebdriverUtility webutil= new WebdriverUtility();
		webutil.actionMoveToElement(driver, icon);
		
		driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
		

		
		driver.quit();
		
		
		
		
		
		
		
		
		
		
	}

}
