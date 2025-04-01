package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage 
{
	
	WebDriver driver;

	public ProductPage(WebDriver driver) 
	{
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	
	}
	
	
	@FindBy(xpath="//select[@class='form-control']")
	private WebElement searchBy;
	
	@FindBy(xpath="//input[@class='form-control' and @placeholder='Search by product Id']")
	private WebElement searchByProdId;
	
	@FindBy(xpath="//input[@class='form-control' and @placeholder='Search by product Name']")
	private WebElement searchByProdName;
	
	@FindBy(xpath="//span[text()='Add Product']")
	private WebElement addProduct;
	
	



	public WebElement getSearchBy() {
		return searchBy;
	}

	public WebElement getSearchByProdId() {
		return searchByProdId;
	}

	public WebElement getSearchByProdName() {
		return searchByProdName;
	}

	public WebElement getAddProduct() {
		return addProduct;
	}
	
	
	
	
	
	
	

}
