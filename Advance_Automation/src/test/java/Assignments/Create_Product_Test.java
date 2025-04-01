package Assignments;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import ObjectRepository.CreateProductsPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;
import ObjectRepository.ProductPage;

public class Create_Product_Test {

	public static void main(String[] args) throws IOException, InterruptedException 
	{

		PropertiesFileUtility propUtil=new PropertiesFileUtility();
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
		dp.getProductsLink().click();
		
		ProductPage pp=new ProductPage(driver);
		pp.getAddProduct().click();
		
		CreateProductsPage cpp = new CreateProductsPage(driver);
		cpp.addProduct(Product, Category, Quantity, PricePU, Vendor);
		
		
		 dp.logout();
		 
	     driver.quit();
		
		
		
		
		
		
		
		
		
		
		
	}

}
