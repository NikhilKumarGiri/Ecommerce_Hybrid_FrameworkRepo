package com.tutorialninja_testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utility.Utility;

import cm.tutorialninja.qa.pages.AccountSuccessPage;
import cm.tutorialninja.qa.pages.HomePage;
import cm.tutorialninja.qa.pages.RegistationPage;

public class RegisterTest extends Base {
	RegistationPage registerPage;
	AccountSuccessPage accountSuccessPage;
	public WebDriver driver;

	public RegisterTest()
	{
		super();
	}

	@BeforeMethod
	public void setUp()
	{
		driver=browserIntializeAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.xpath("(//a[text()=\"Register\"])[1]")).click();
		registerPage = homePage.selectRegisterOption();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Test(priority=1)
	public void verifyRegisterAccountWithMandatoryFeild()
	{
		//RegistationPage registerPage=new RegistationPage(driver);
		registerPage.enterFirstName(protestdata.getProperty("firstName"));
		//driver.findElement(By.id("input-firstname")).sendKeys(protestdata.getProperty("firstName"));
		registerPage.enterLastName(protestdata.getProperty("lastName"));
		//driver.findElement(By.id("input-lastname")).sendKeys(protestdata.getProperty("lastName"));
		registerPage.enterEmailAddress(Utility.generateEmailTimeStamp());
		//driver.findElement(By.id("input-email")).sendKeys(Utility.generateEmailTimeStamp());
		registerPage.enterTelephoneNo(protestdata.getProperty("telephoneNumber"));
		registerPage.enterPassword(protestdata.getProperty("validPassword"));
		registerPage.enterConfirmPassword(protestdata.getProperty("validPassword"));
		//driver.findElement(By.id("input-telephone")).sendKeys(protestdata.getProperty("telephoneNumber"));
		//driver.findElement(By.id("input-password")).sendKeys(protestdata.getProperty("validPassword"));
		//driver.findElement(By.id("input-confirm")).sendKeys(protestdata.getProperty("validPassword"));
		registerPage.clickOnprivacyPolicyFeild();
		//driver.findElement(By.name("agree")).click();
		accountSuccessPage = registerPage.clickOncontinueButton();
		//driver.findElement(By.xpath("//input[@type='submit']")).click();
		//AccountSuccessPage accountSuccessPage=new AccountSuccessPage(driver);
		
		String actualHeading=accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualHeading, protestdata.getProperty("accountSuccessfullyCreatedHedaing"),"Account page is not displayed");
	}
	@Test(priority=2)
	public void verifyRegisterAccountWithAllFeild() 
	{
		//RegistationPage registerPage=new RegistationPage(driver);
		registerPage.enterFirstName(protestdata.getProperty("firstName"));
		registerPage.enterLastName(protestdata.getProperty("lastName"));
		registerPage.enterEmailAddress(Utility.generateEmailTimeStamp());
		registerPage.enterTelephoneNo(protestdata.getProperty("telephoneNumber"));
		registerPage.enterPassword(protestdata.getProperty("validPassword"));
		registerPage.enterConfirmPassword(protestdata.getProperty("validPassword"));

//		driver.findElement(By.id("input-firstname")).sendKeys(protestdata.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(protestdata.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(Utility.generateEmailTimeStamp());
//		driver.findElement(By.id("input-telephone")).sendKeys(protestdata.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(protestdata.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(protestdata.getProperty("validPassword"));
//		driver.findElement(By.xpath("//input[@value='0']")).click();
		registerPage.clickOnsubscribeOptionNo();
		registerPage.clickOnprivacyPolicyFeild();
		accountSuccessPage=registerPage.clickOncontinueButton();

//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@type='submit']")).click();
//		String actualHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		//AccountSuccessPage accountSuccessPage=new AccountSuccessPage(driver);(written 82 line)
		String actualHeading=accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualHeading, "Your Account Has Been Created!","Account page is not displayed");
	}
	@Test(priority=3)
	public void verifyRegisterAccountWithExistingEmail()
	{
		
//		driver.findElement(By.id("input-firstname")).sendKeys(protestdata.getProperty("firstName"));
//		driver.findElement(By.id("input-lastname")).sendKeys(protestdata.getProperty("lastName"));
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//		driver.findElement(By.id("input-telephone")).sendKeys(protestdata.getProperty("telephoneNumber"));
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		//RegistationPage registerPage=new RegistationPage(driver);
		registerPage.enterFirstName(protestdata.getProperty("firstName"));
		registerPage.enterLastName(protestdata.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNo(protestdata.getProperty("telephoneNumber"));
		registerPage.enterPassword(protestdata.getProperty("validPassword"));
		registerPage.enterConfirmPassword(protestdata.getProperty("validPassword"));
		registerPage.clickOnsubscribeOptionNo();
		registerPage.clickOnprivacyPolicyFeild();
		registerPage.clickOncontinueButton();
//		driver.findElement(By.xpath("//input[@value='0']")).click();
//		driver.findElement(By.name("agree")).click();
//		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualWarning=registerPage.retreiveduplicateEmailWarningText();
		Assert.assertTrue(actualWarning.contains(protestdata.getProperty("duplicateEmailWarning")),"Warning message regaring duplicate email is not displayed");
	}
	@Test(priority=4)
	public void verifyRegisterAccountWithoutFillingAnyDetails()
	{
		
		//driver.findElement(By.xpath("//input[@type='submit']")).click();
		//RegistationPage registerPage=new RegistationPage(driver);
		registerPage.clickOncontinueButton();

		String actualprivaryPolicyWarning=registerPage.retreiveprivaryPolicyWarning();	
		Assert.assertEquals(actualprivaryPolicyWarning,protestdata.getProperty("privacyPolicyWarning"),"Privacy policy warning message is failed" );
		//String actualFirstNameWarning=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		String actualFirstNameWarning=registerPage.retreiveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning, protestdata.getProperty("firstNameWarning"),"First name message is not dispalyed");
		String actualLastNameWarning=registerPage.retreiveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning, protestdata.getProperty("lastNameWarning"),"Last name message is not dispalyed");
		String EmailWarning=registerPage.retreiveEmailWarning();
		Assert.assertEquals(EmailWarning, protestdata.getProperty("emailWarning"),"Email validation is not dispalyed");
		String telephoneWarning=registerPage.retreiveTelephoneWarning();
		Assert.assertEquals(telephoneWarning, protestdata.getProperty("telephoneWarning"),"Telephone warning message is not dispalyed");
		String passwordWarning=registerPage.retreivePasswordWarning();
		Assert.assertEquals(passwordWarning, protestdata.getProperty("passwordWarning"),"Password warning message is not dispalyed");

	}
}
