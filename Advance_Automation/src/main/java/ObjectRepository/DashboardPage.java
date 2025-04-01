package ObjectRepository;

import java.awt.event.ActionEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage 
{
	WebDriver driver;
	
	
	
	public DashboardPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText ="Campaigns")
	private WebElement compaignsLink;
	
	@FindBy(linkText ="Contacts")
	private WebElement contactsLink;
	
	@FindBy(linkText ="Products")
	private WebElement productsLink;
	
	@FindBy(xpath="//*[name()='svg' and @role='img']")
	private WebElement profileIcon;
	
	@FindBy(xpath="//div[text()='Logout ']")
	private WebElement logout;



	public WebElement getCompaignsLink() 
	{
		return compaignsLink;
	}

	public WebElement getContactsLink() 
	{
		return contactsLink;
	}

	public WebElement getProductsLink() 
	{
		return productsLink;
	}

	public WebElement getProfileIcon() 
	{
		return profileIcon;
	}

	public WebElement getLogout() 
	{
		return logout;
	}
	
	
	public void logout() 
	{
		profileIcon.click();
		Actions a = new Actions(driver);
		a.moveToElement(logout).click();
		
	}

}
