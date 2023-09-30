package com.tutorialninja_testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;

import cm.tutorialninja.qa.pages.HomePage;
import cm.tutorialninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	SearchPage searchPage;
	public WebDriver driver;
	//search is a constructor,super we are using because in parent class(base)
	public SearchTest()
	{
		super();
	}
	@BeforeMethod
	public void setUp()
	{
		driver=browserIntializeAndOpenApplication(prop.getProperty("browserName"));
	}

	@Test(priority=1)
	public void verifySearchWithValidProduct()
	{
		HomePage homePage=new HomePage(driver);
		homePage.enterProductInToSearchBoxFeild(protestdata.getProperty("validProductName"));
		searchPage = homePage.clickOnSearchButton();
		//driver.findElement(By.xpath("//div[@id='search']/input")).sendKeys(protestdata.getProperty("validProductName"));
		//driver.findElement(By.xpath("//div[@id='search']/input/../span")).click();
		//SearchPage searchPage=new SearchPage(driver);
		Assert.assertTrue(searchPage.verifyHPproductisDiplayed());
	}
	@Test(priority=2)
	public void verifySearchWithInValidProduct()
	{
//		driver.findElement(By.xpath("//div[@id='search']/input")).sendKeys(protestdata.getProperty("inValidproductName"));
//		driver.findElement(By.xpath("//div[@id='search']/input/../span")).click();
		HomePage homePage=new HomePage(driver);
		homePage.enterProductInToSearchBoxFeild(protestdata.getProperty("inValidproductName"));
		searchPage=homePage.clickOnSearchButton();
		//SearchPage searchPage=new SearchPage(driver);
		String actualResult=searchPage.retreiveNoProductMathMessage();
		Assert.assertEquals(actualResult, protestdata.getProperty("noProductTextInSearch"),
				"No product message in search result is not diaplyed");
	}
	@Test(priority=3)
	public void verifySearchWithOutProduct()
	{
		HomePage homePage=new HomePage(driver);
		homePage.enterProductInToSearchBoxFeild("");
		searchPage=homePage.clickOnSearchButton();
		//SearchPage searchPage=new SearchPage(driver);
		String actualResult=searchPage.retreiveNoProductMathMessage();
		Assert.assertEquals(actualResult,  protestdata.getProperty("noProductTextInSearch"),
				"No product message in search result is not diaplyed");
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.quit();
	}
}
