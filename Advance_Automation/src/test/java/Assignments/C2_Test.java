package Assignments;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.tracing.Propagator;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebdriverUtility;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;

public class C2_Test {
	
	@Parameters("browser")
    @Test(groups= {"Integration"})
	public void loginTest(String browser) throws InterruptedException, IOException 
	{

		WebDriver driver = null;
		
		PropertiesFileUtility PropFileUtils = new PropertiesFileUtility();
		
	
		String BROWSER=browser;
		//String BROWSER = PropFileUtils.readingDataFromPropFile("browser");
		String URL = PropFileUtils.readingDataFromPropFile("url");
		String UN = PropFileUtils.readingDataFromPropFile("username");
		String PWD = PropFileUtils.readingDataFromPropFile("password");
		
		
		
		
		
		
		/*
		 * driver.findElement(By.xpath("//input[@id='username']")).sendKeys(UN);
		 * driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys(PWD);
		 * driver.findElement(By.xpath("//button[@type='submit']")).click();
		 */
		
		
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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		Thread.sleep(2000);
		
		LoginPage lp = new LoginPage(driver);
		lp.login(UN, PWD);
	
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
		driver.findElement(By.xpath("//input[@name='campaignName']")).sendKeys("Anuja Test campaign2");
		driver.findElement(By.xpath("//input[@name='targetSize']")).sendKeys("18");
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
		Thread.sleep(1000);
		
		
		String confmsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		/*
		 * if (confmsg.contains("Anuja")) { Reporter.log("Added successfully",true); }
		 * 
		 * else { Reporter.log("Not added",true); }
		 */
		
		
		
		
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
		
		DashboardPage dp = new DashboardPage(driver);
		dp.logout();
		
		/*
		 * Actions a = new Actions(driver);
		 * 
		 * WebElement icon =
		 * driver.findElement(By.xpath("//div[@class='user-icon-container']"));
		 * 
		 * a.moveToElement(icon).perform();
		 * 
		 * driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
		 */
		
		driver.quit();
		
		
	}
    
    

	@Parameters("browser")
    @Test(groups= {"Integration"})
    public void createCampaignTest(String browser) throws IOException, InterruptedException 
	
	{


		PropertiesFileUtility PropFileUtils = new PropertiesFileUtility();
		
	
		String BROWSER=browser;
		//String BROWSER = PropFileUtils.readingDataFromPropFile("browser");
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
      	
        	Reporter.log("Contact added Successfully",true);
      
        }
      
        else
        {
      	
        	Reporter.log("Contact not added",true);
      
        }
      
     
        Thread.sleep(5000);
        
       
     
     
        driver.findElement(By.xpath("//*[name()='svg' and @role='img']")).click();
     
        WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
     
        
        

        WebdriverUtility webutils = new WebdriverUtility();
        
        webutils.actionMoveToElementAndClick(driver, logout);
     
        driver.quit();
	
	}


}
