package com.tutorialninja_testcases;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utility.Utility;

import cm.tutorialninja.qa.pages.AccountPage;
import cm.tutorialninja.qa.pages.HomePage;
import cm.tutorialninja.qa.pages.LoginPage;

public class LoginTest extends Base {
	//because it will use all over the tcs,clickOnLogin() returning loginPage obj
	LoginPage loginPage;
	/*if we are creating Base class constructor we have use super()
	 * 	so the method of parent class method will be automatically called */
	
	public LoginTest()
	{
		super();
	}
	//if we are not using public ,will get nullpointer exception,to use in my listerner in takescreenshot()
	public WebDriver driver;
	@BeforeMethod
	public void setUp() throws IOException
	{
		//if we are not creating base class constructor,we can call this method
		//loadPropertiesFile();
		driver=browserIntializeAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		
		//homePage.clickOnMyAccount();
		//loginPage = homePage.clickOnLogin();
		//combing both the 2 above lines
		loginPage=homePage.navigateToLoginPage();
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.linkText("Login")).click();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyLoginWithValidCredential() throws InterruptedException {
		
		//LoginPage loginPage=new LoginPage(driver);
		//removing LoginPage because,declaring globally 
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		AccountPage accountPage = loginPage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@name='email']")).sendKeys(prop.getProperty("validEmail"));
		//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@type='submit']")).click();
		// verify logged in
		//AccountPage accountPage=new AccountPage(driver);
		//WebElement afterlogintext = driver.findElement(By.linkText("Edit your account information"));
		//Assert.assertTrue(afterlogintext.isDisplayed());
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());


	}
	@Test(priority = 6,dataProvider="supplyTestDataFromExcelData")
	public void verifyLoginWithValidCredentialfromDataProvider(String email,String password)  {
		//LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		//driver.findElement(By.id("input-password")).sendKeys(password);
		//driver.findElement(By.xpath("//input[@type='submit']")).click();
		// verify logged in
		//WebElement afterlogintext = driver.findElement(By.linkText("Edit your account information"));
		AccountPage accountPage=new AccountPage(driver);
		
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit Your Account Information is not displayed");
		//Thread.sleep(5000);

	}
	@DataProvider
	public Object[][] supplyTestData()
	{
		Object[][] data= {{"nikhilkumargiri02@gmail.com","Nikhil@398"},
				{"amotooricap1@gmail.com","12345"},{"amotooricap3@gmail.com","12345"}};
		return data;
	}
	@DataProvider
	public Object[][] supplyTestDataFromExcelData()
	{
		Object[][] data1=Utility.getTestDataFromExcel("Login");
		return data1;
	}
	
	
	@Test(priority = 2)
	public void verifyLoginWithInValidCredential()
	// after 5 or 6 run it will failed because of security reason(like ATM pin)
	// so we will be using email+time stamp to generate invalid mail id
	{
		// adding the generateTimeStamp() so it will be generate new invalid mail id
//		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Utility.generateEmailTimeStamp());
//		driver.findElement(By.id("input-password")).sendKeys(protestdata.getProperty("invalidPassword"));
//		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAddress(Utility.generateEmailTimeStamp());
		loginPage.enterPassword(protestdata.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		String actualmsg = loginPage.retrieveEmailPasswordWarningMessage();
		String expectedmsg = protestdata.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualmsg.contains(expectedmsg),"Expected warning message is not displayed");
	}
	@Test(priority=3)
	public void verifyLoginWithInvalidUsernamevalidPassword() {
		{
			// adding the generateTimeStamp() so it will be generate new invalid mail id
			//driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Utility.generateEmailTimeStamp());
			//driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
			//driver.findElement(By.xpath("//input[@type='submit']")).click();
			//LoginPage loginPage=new LoginPage(driver);
			loginPage.enterEmailAddress(Utility.generateEmailTimeStamp());
			loginPage.enterPassword(prop.getProperty("validPassword"));
			loginPage.clickOnLoginButton();
			String actualmsg = loginPage.retrieveEmailPasswordWarningMessage();
			String expectedmsg = protestdata.getProperty("emailPasswordNoMatchWarning");
			Assert.assertTrue(actualmsg.contains(expectedmsg),"Expected warning message is not displayed");
		}
	}

	@Test(priority=4)
	public void verifyLoginWithValidUsernameInvalidPassword() {
		{
			// adding the generateTimeStamp() so it will be generate new invalid mail id
			//driver.findElement(By.xpath("//input[@name='email']")).sendKeys(prop.getProperty("validEmail"));
			//driver.findElement(By.id("input-password")).sendKeys(protestdata.getProperty("invalidPassword"));
			//driver.findElement(By.xpath("//input[@type='submit']")).click();
			//LoginPage loginPage=new LoginPage(driver);
			loginPage.enterEmailAddress(prop.getProperty("validEmail"));
			loginPage.enterPassword(protestdata.getProperty("invalidPassword"));
			loginPage.clickOnLoginButton();
//			String actualmsg = driver
//					.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']"))
//					.getText();
//			String expectedmsg = protestdata.getProperty("emailPasswordNoMatchWarning");
//			Assert.assertEquals(actualmsg, expectedmsg);
			String actualmsg = loginPage.retrieveEmailPasswordWarningMessage();
			String expectedmsg = protestdata.getProperty("emailPasswordNoMatchWarning");
			Assert.assertTrue(actualmsg.contains(expectedmsg),"Expected warning message is not displayed");
		
		}
	}

	@Test(priority=5)
	public void verifyLoginWithOutCredential() {
		{
			// adding the generateTimeStamp() so it will be generate new invalid mail id
//			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("");
//			driver.findElement(By.id("input-password")).sendKeys("");
//			driver.findElement(By.xpath("//input[@type='submit']")).click();
			//LoginPage loginPage=new LoginPage(driver);
			//no required to send empty value in email and password field
			loginPage.clickOnLoginButton();
//			String actualmsg = driver
//					.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']"))
//					.getText();
//			String expectedmsg = protestdata.getProperty("emailPasswordNoMatchWarning");
//			Assert.assertEquals(actualmsg, expectedmsg);
			String actualmsg = loginPage.retrieveEmailPasswordWarningMessage();
			String expectedmsg = protestdata.getProperty("emailPasswordNoMatchWarning");
			Assert.assertTrue(actualmsg.contains(expectedmsg),"Expected warning message is not displayed");
		
		}
	}
}
