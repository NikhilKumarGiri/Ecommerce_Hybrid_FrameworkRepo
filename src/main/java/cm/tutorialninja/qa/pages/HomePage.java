package cm.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	//web element are objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccoutDropDown;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(xpath="//a[text()='Register']")
	private WebElement registerOption;
	
	@FindBy(xpath="//div[@id='search']/input")
	private WebElement searchTextBoxFeild;
	
	@FindBy(xpath="//div[@id='search']/input/../span")
	private WebElement searchOption;

	//Constructor -to access the locator from outside,we r declaring them as private
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		/*as per page factory design pattern
		init element used to call automatically the element when HomePage
		constructor will call */
		PageFactory.initElements(driver, this);
	}
	
	//Actions methods
	public void clickOnMyAccount() {
		myAccoutDropDown.click();
	}
	public LoginPage clickOnLogin() {
		loginOption.click();
		/*creating the object of LoginPage ,so 
		LoginPage loginPage=new LoginPage(driver); this object creation
		 will be remove in test class */

		return new LoginPage(driver);
	}
	//combing above 2 methods
	public LoginPage navigateToLoginPage()
	{
		myAccoutDropDown.click();
		loginOption.click();
		return new LoginPage(driver);

	}
	public RegistationPage selectRegisterOption()
	{
		registerOption.click();
		return new RegistationPage(driver);
	}
	public void enterProductInToSearchBoxFeild(String productText)
	{
		searchTextBoxFeild.sendKeys(productText);
	}
	public SearchPage clickOnSearchButton()
	{
		searchOption.click();
		return new SearchPage(driver);
	}
	

}
