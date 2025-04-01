package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.WebdriverUtility;

public class CreateProductsPage {
	
	WebDriver driver;
	
	
	public CreateProductsPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="productName")
	private WebElement prodName;
	
	@FindBy(name="productCategory")
	private WebElement prodCategory;
	
	@FindBy(name="quantity")
	private WebElement quant;
	
	@FindBy(name="price")
	private WebElement price;
	
	@FindBy(name="vendorId")
	private WebElement vendorId;
	
	@FindBy(xpath="//button[text()='Add']")
	private WebElement addBtn;
	
	
	

	public WebElement getProdName() {
		return prodName;
	}

	public WebElement getProdCategory() {
		return prodCategory;
	}

	public WebElement getQuant() {
		return quant;
	}

	public WebElement getPrice() {
		return price;
	}

	public WebElement getVendorId() {
		return vendorId;
	}

	public WebElement getAddBtn() {
		return addBtn;
	}
	
	
	
	public void addProduct(String productName, String category,String Quantity, String priz, String vendor)
	{
		prodName.sendKeys(productName);
		WebdriverUtility webutil = new WebdriverUtility();
		webutil.select(category, prodCategory);
		quant.clear();
		quant.sendKeys(Quantity);
		price.clear();
		price.sendKeys(priz);
		webutil.select(vendor, vendorId);
		addBtn.click();
		
		
	}
	

}
