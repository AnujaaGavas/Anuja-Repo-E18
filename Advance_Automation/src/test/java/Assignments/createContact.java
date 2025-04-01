package Assignments;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jspecify.annotations.Nullable;
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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebdriverUtility;
import ObjectRepository.CampaignPage;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateCampaignPage;
import ObjectRepository.CreateContactsPage;
import ObjectRepository.CreateProductsPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;
import ObjectRepository.ProductPage;

public class createContact {
	
	

	
	@Test(groups= {"SmokeTest"})
	public void createContactTest()throws IOException, InterruptedException 
	{

		
		PropertiesFileUtility propUtil=new PropertiesFileUtility();
		
		//String BROWSER=browser;
		String BROWSER = propUtil.readingDataFromPropFile("browser");
		String URL = propUtil.readingDataFromPropFile("url");
		String UN = propUtil.readingDataFromPropFile("username");
		String PWD = propUtil.readingDataFromPropFile("password");
		
		JavaUtility jUtil=new JavaUtility();
		int randomNum = jUtil.getRandomNum(2000);
		
		ExcelFileUtility exUtil=new ExcelFileUtility();
		String Campaign = exUtil.readFromExcelFileUtility("DDT", 1, 2)+randomNum;
		String targetSize = exUtil.readFromExcelFileUtility("DDT", 1, 3);
		String organization = exUtil.readFromExcelFileUtility("Contact", 1, 2)+randomNum;
		String title = exUtil.readFromExcelFileUtility("Contact", 1, 3);
		String contactName = exUtil.readFromExcelFileUtility("Contact", 1, 4)+randomNum;
		String mobile = exUtil.readFromExcelFileUtility("Contact", 1, 5);
        
		WebDriver driver=null;
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.login(UN, PWD);
		
		DashboardPage dp=new DashboardPage(driver);
		Thread.sleep(2000);
		dp.getCompaignsLink().click();
		
		CampaignPage cp=new CampaignPage(driver);
		cp.getCreateCampaignBtn().click();
		
		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		ccp.createCampaignWithmandatoryFields(Campaign, targetSize);
		Thread.sleep(3000);
		
		WebElement contactLink = dp.getContactsLink();
		WebdriverUtility Wutil=new WebdriverUtility();
		Wutil.waitForElementToBeClickable(driver, contactLink,20);
		contactLink.click();
		
		Thread.sleep(5000);
		ContactsPage ccp1=new ContactsPage(driver);
		WebElement createContactBtn = ccp1.getCreateContactBtn();
		Wutil.waitForElementToBeClickable(driver, createContactBtn,20);
	    createContactBtn.click();
		
		CreateContactsPage cct=new CreateContactsPage(driver);
		cct.createContactWithCampaign(organization, title, contactName, mobile, "create-contact","selectCampaign", Campaign);
		
		Thread.sleep(5000);
		 
       String ConfirmationMsg = ccp1.getConfMsg().getText();
       
       
        if(ConfirmationMsg.contains(contactName))
        {
        	Reporter.log("Contact added Successfully",true);
        }
        else
        {
        	Reporter.log("Contact not added",true);
        }
       Thread.sleep(5000);
       
       
       dp.logout();
       driver.quit();
	
	}
	
	
	//@Parameters("browser")
	@Test(groups= {"SystemTest"})
	public void createProductTest() throws IOException, InterruptedException 
	{

		PropertiesFileUtility propUtil=new PropertiesFileUtility();
		
		//String BROWSER=browser;
		String BROWSER = propUtil.readingDataFromPropFile("browser");
		String URL = propUtil.readingDataFromPropFile("url");
		String UN = propUtil.readingDataFromPropFile("username");
		String PWD = propUtil.readingDataFromPropFile("password");
		
		JavaUtility jUtil=new JavaUtility();
		int randomNum = jUtil.getRandomNum(2000);
		
		ExcelFileUtility excel = new ExcelFileUtility();
		String Product =excel.readFromExcelFileUtility("Product", 1, 2)+randomNum;
		String Category =excel.readFromExcelFileUtility("Product", 1, 3);
		String Quantity =excel.readFromExcelFileUtility("Product", 1, 4);
		String PricePU =excel.readFromExcelFileUtility("Product", 1, 5);
		String Vendor =excel.readFromExcelFileUtility("Product", 1, 6);
		
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		
		LoginPage lp=new LoginPage(driver);
		lp.login(UN, PWD);
		
		DashboardPage dp=new DashboardPage(driver);
		Thread.sleep(2000);
		dp.getProductsLink().click();
		
		ProductPage pp=new ProductPage(driver);
		pp.getAddProduct().click();
		
		CreateProductsPage cpp = new CreateProductsPage(driver);
		cpp.addProduct(Product, Category, Quantity, PricePU, Vendor);
		
		
		 dp.logout();
		 
	     driver.quit();
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	

	
	
	
		@Test
		public void loginTest2() throws InterruptedException, IOException {
			
			
			PropertiesFileUtility propUtil=new PropertiesFileUtility();
			String BROWSER = propUtil.readingDataFromPropFile("browser");
		
			String URL = propUtil.readingDataFromPropFile("url");
			String UN = propUtil.readingDataFromPropFile("username");
			String PWD = propUtil.readingDataFromPropFile("password");
			
			String expectedURL="http://49.249.28.218:8098/dashboard";
			//Launching the browser
			WebDriver driver=null;
			/*
			 * ChromeOptions Coption=new ChromeOptions(); FirefoxOptions Foption=new
			 * FirefoxOptions(); EdgeOptions Eoption=new EdgeOptions();
			 * Coption.addArguments("--headless"); Foption.addArguments("--headless");
			 * Eoption.addArguments("--headless");
			 */
			if(BROWSER.equalsIgnoreCase("chrome"))
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
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			//navigating to ninza CRM
			driver.get(URL);
			//enter the username and password
			LoginPage lp=new LoginPage(driver);
			lp.login(UN, PWD);
			Thread.sleep(2000);
			//verification of dashboard
			String actualURL=driver.getCurrentUrl();
			/*
			 * if(actualURL.equals(expectedURL)) { Reporter.log("Validation is pass",true);
			 * } else { Reporter.log("validation is failed",true); }
			 */
			//logout
			
			Assert.assertEquals(actualURL, expectedURL,"Validation failed");
			DashboardPage dp=new DashboardPage(driver);
			dp.logout();
	        //close the browser
	        driver.quit();
		}

	
	}


