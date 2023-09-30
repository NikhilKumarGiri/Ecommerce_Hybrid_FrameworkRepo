package cm.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	//object
	@FindBy(xpath="//input[@name='email']")
	private WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passWordField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[text()='Warning: No match for E-Mail Address and/or Password.']")
	private WebElement emailPasswordNotMatching;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//action
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	public void enterPassword(String passwordText)
	{
		passWordField.sendKeys(passwordText);
	}
	public AccountPage clickOnLoginButton()
	{
		loginButton.click();
		return new AccountPage (driver);
	}
	public String retrieveEmailPasswordWarningMessage()
	{
		String emailPasswordwarningText=emailPasswordNotMatching.getText();
		return emailPasswordwarningText;
	}

}
