package cm.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	@FindBy(xpath="//a[contains(text(),'HP')]")
	private WebElement validateHpProduct;
	
	@FindBy(xpath="//p[contains(text(),'no product that matches')]")
	private WebElement noProductMatchMessage;
	
	public SearchPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public boolean verifyHPproductisDiplayed()
	{
		boolean verifyproductname=validateHpProduct.isDisplayed();
		return verifyproductname;
	}
	public String retreiveNoProductMathMessage()
	{
		String noproductMatch=noProductMatchMessage.getText();
		return noproductMatch;
	}
}
