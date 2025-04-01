package Assignments;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.graphbuilder.math.func.RandFunction;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebdriverUtility;

public class Create_Campaign_Test 
{

	public static void main(String[] args) throws IOException, InterruptedException 
	
	{


		PropertiesFileUtility PropFileUtils = new PropertiesFileUtility();
		
	
		
		String BROWSER = PropFileUtils.readingDataFromPropFile("browser");
		String URL = PropFileUtils.readingDataFromPropFile("url");
		String USERNAME = PropFileUtils.readingDataFromPropFile("username");
		String PASSWORD = PropFileUtils.readingDataFromPropFile("password");
		
		
		JavaUtility jutil = new JavaUtility();
		int rand =jutil.getRandomNum(2000);
		
		
		ExcelFileUtility exutil = new ExcelFileUtility();
		String campaign = exutil.readFromExcelFileUtility("DDT",1,2)+rand;
		String target_size = exutil.readFromExcelFileUtility("DDT",1,3);
		
		String organization = exutil.readFromExcelFileUtility("Contact",1,2)+rand;
		String title = exutil.readFromExcelFileUtility("Contact",1,3);
		String contact_name = exutil.readFromExcelFileUtility("Contact",1,4);
		String mobile = exutil.readFromExcelFileUtility("Contact",1,5)+rand;
		
		
		WebDriver driver = null;
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver= new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver= new EdgeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			driver= new ChromeDriver();

		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
			
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
			
			
		Thread.sleep(2000);
		
		
			
			
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campaign);
		driver.findElement(By.name("targetSize")).sendKeys(target_size);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
			
			
		Thread.sleep(3000);
		
		WebdriverUtility wdutils = new WebdriverUtility();
			
		WebElement contactLink = driver.findElement(By.linkText("Contacts"));
		
		wdutils.waitForElementToBeClickable(driver, contactLink, 20);
		
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.elementToBeClickable(contactLink));
		contactLink.click();
		
		
		Thread.sleep(5000);
		
		WebElement createContactBtn = driver.findElement(By.xpath("//span[text()='Create Contact']"));
		
		wdutils.waitForElementPresent(driver, createContactBtn, 20);
		
		//WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.visibilityOf(createContactBtn));
		createContactBtn.click();
		
		
		driver.findElement(By.name("organizationName")).sendKeys(organization);
		driver.findElement(By.name("title")).sendKeys(title);
		driver.findElement(By.name("contactName")).sendKeys(contact_name);
		driver.findElement(By.name("mobile")).sendKeys(mobile);
		driver.findElement(By.xpath("//button[@type='button' and contains(@style,'white-space')]")).click();
		
		
	
		wdutils.switchToWindow(driver, "selectCampaign");
		
		
		/*
		 * Set<String> allWindowIds = driver.getWindowHandles(); for(String
		 * Window:allWindowIds) { driver.switchTo().window(Window); String actUrl =
		 * driver.getCurrentUrl(); if(actUrl.contains("selectCampaign")) { break; } }
		 */
		
		
		WebElement selectTypeDD = driver.findElement(By.id("search-criteria"));
		
		/*
		 * Select select1=new Select(selectTypeDD);
		 * select1.selectByValue("campaignName");
		 */
	    
	    wdutils.select(selectTypeDD, "campaignName");
	    
	    
	    driver.findElement(By.id("search-input")).sendKeys(campaign);
	    driver.findElement(By.xpath("//button[@class='select-btn']")).click();
	    
	    wdutils.switchToWindow(driver, "create-contact");
	    
	    
	    
		/*
		 * for(String Window:allWindowIds) { driver.switchTo().window(Window); String
		 * actUrl = driver.getCurrentUrl(); if(actUrl.contains("create-contact")) {
		 * break; } }
		 */
	    
	    
	    driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
	    
        Thread.sleep(3000);
        
     
        String ConfirmationMsg = driver.findElement(By.xpath("//div[text()='Contact "+contact_name+" Successfully Added']")).getText();
      
        if(ConfirmationMsg.contains(contact_name))
      
        {
      	
        	System.out.println("Contact added Successfully");
      
        }
      
        else
        {
      	
        	System.out.println("Contact not added");
      
        }
      
     
        Thread.sleep(5000);
        
       
     
     
        driver.findElement(By.xpath("//*[name()='svg' and @role='img']")).click();
     
        WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
     
        
        

        WebdriverUtility webutils = new WebdriverUtility();
        
        webutils.actionMoveToElementAndClick(driver, logout);
     
        driver.quit();
	
	}

	
}



	
	
	
	





		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		

				
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
