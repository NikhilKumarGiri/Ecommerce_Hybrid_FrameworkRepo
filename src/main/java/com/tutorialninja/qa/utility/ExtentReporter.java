package com.tutorialninja.qa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateExtentReport(){
		//add Extent report dependency
		ExtentReports extentReport= new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Test Automation Result");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		Properties config=new Properties();
		File configPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
		try {
			FileInputStream fisConfigProp=new FileInputStream(configPropFile);
			config.load(fisConfigProp);
		    }
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Appilcation Url", config.getProperty("url"));
		extentReport.setSystemInfo("Appilcation Url", config.getProperty("browserName"));
		extentReport.setSystemInfo("Appilcation Url", config.getProperty("validEmail"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java version", System.getProperty("java.version"));

		return extentReport;

	}
}
