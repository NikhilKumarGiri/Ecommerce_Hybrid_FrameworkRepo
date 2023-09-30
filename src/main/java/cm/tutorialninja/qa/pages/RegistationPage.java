package cm.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistationPage {
	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameFeild;

	@FindBy(id = "input-lastname")
	private WebElement lastNameFeild;

	@FindBy(id = "input-email")
	private WebElement emailAddressFeild;

	@FindBy(id = "input-telephone")
	private WebElement telephoneFeild;

	@FindBy(id = "input-password")
	private WebElement passwordFeild;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordFeild;

	@FindBy(name = "agree")
	private WebElement privacyPolicyFeild;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@value='0']")
	private WebElement subscribeOptionNo;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privaryPolicyWarningtext;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarningtext;

	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarningtext;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarningtext;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarningtext;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarningText;
	
	public RegistationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstNameText) {
		firstNameFeild.sendKeys(firstNameText);
	}

	public void enterLastName(String lasttNameText) {
		lastNameFeild.sendKeys(lasttNameText);
	}

	public void enterEmailAddress(String emailAddress) {
		emailAddressFeild.sendKeys(emailAddress);
	}

	public void enterTelephoneNo(String telephone) {
		telephoneFeild.sendKeys(telephone);
	}

	public void enterPassword(String password) {
		passwordFeild.sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordFeild.sendKeys(confirmPassword);
	}

	public void clickOnprivacyPolicyFeild() {
		privacyPolicyFeild.click();
	}

	public AccountSuccessPage clickOncontinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void clickOnsubscribeOptionNo() {
		subscribeOptionNo.click();
	}
	public String retreiveduplicateEmailWarningText() {
		String duplicateEmailwarning=duplicateEmailWarning.getText();
		return duplicateEmailwarning;
	}
	public String retreiveprivaryPolicyWarning()
	{
		String privacyPolicyWarning=privaryPolicyWarningtext.getText();
		return privacyPolicyWarning;
	}
	public String retreiveFirstNameWarning()
	{
		String firstNameWarning=firstNameWarningtext.getText();
		return firstNameWarning;
	}
	public String retreiveLastNameWarning()
	{
		String lastNameWarning=lastNameWarningtext.getText();
		return lastNameWarning;
	}
	public String retreiveEmailWarning()
	{
		String emailWarning=emailWarningtext.getText();
		return emailWarning;
	}
	public String retreiveTelephoneWarning()
	{
		String telephoneWarning=telephoneWarningtext.getText();
		return telephoneWarning;
	}
	public String retreivePasswordWarning()
	{
		String passwordWarning=passwordWarningText.getText();
		return passwordWarning;
	}
}
