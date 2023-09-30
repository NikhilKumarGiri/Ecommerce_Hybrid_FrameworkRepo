package com.tutorialninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialninja.qa.utility.Utility;

public class Base {
	public WebDriver driver;
	public ChromeOptions option;
	public Properties prop;
	public Properties protestdata;

	//Declaring Base() constructor 
	public Base() {
		prop = new Properties();
		//System.getProperty("user.dir") -Project path
		File proFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(proFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		protestdata=new Properties();
		File dataFile=new File(System.getProperty("user.dir")
				+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream fisdata=new FileInputStream(dataFile);
			protestdata.load(fisdata);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public WebDriver browserIntializeAndOpenApplication(String browserName) {
		{

			if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if(browserName.equalsIgnoreCase("headless"))
			{
				option.addArguments("headless");
				driver=new ChromeDriver(option);
			}
			
			else {
				System.out.println("Browser is not supported");
			}
			driver.manage().window().maximize();
			/*removing the hard code value of 10
			 * and 5 so we can not change from Base class
			 */
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utility.IMPLICIT_WAIT_TIME));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility.PAGE_LOAD_TIME));
			driver.get(prop.getProperty("url"));
			return driver;
		}

		
	}

}
