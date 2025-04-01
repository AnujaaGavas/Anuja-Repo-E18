package GenericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverUtility 
{
	
	public void waitForElementPresent(WebDriver driver,WebElement element, long secs)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitForElementToBeClickable(WebDriver driver,WebElement element, long secs)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secs));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public void switchToWindow(WebDriver driver,String partialUrl)
	{
		
		Set<String> allWindowIds = driver.getWindowHandles();
		
		for(String window:allWindowIds)
		{
			driver.switchTo().window(window);
			String Url = driver.getCurrentUrl();
			
			if(Url.contains(partialUrl))
			{
				break;
			}
			
		
		}
	}
	
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	
	public void switchToFrame(WebDriver driver,String nameid)
	{
		driver.switchTo().frame(nameid);
	}
	
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void select(WebElement element,String value)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	
	public void select(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	public void select(String visibleText,WebElement element)
	{
		Select sel=new Select(element);
		sel.selectByValue(visibleText);
	}
	
	
	public void actionMoveToElement(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();	
	
	}
	
	public void actionMoveToElementAndClick(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();	
	
	}
	
	public void actionDoubleClick(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();	
	
	}
	
	public void actionRightClick(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.contextClick(element).perform();	
	
	}
	
	public void takingScreenshotOfWebpage(WebDriver driver,String fileName) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/"+fileName+".png");
		FileHandler.copy(temp, dest);
		
	
	}
	
	public void takingScreenshotOfWebElement(WebElement element,String fileName) throws IOException
	{
		
		File temp = element.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/"+fileName+".png");
		FileHandler.copy(temp, dest);
		
	
	}
	
	
}
