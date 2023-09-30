package com.tutorialninja.qa.listener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utility.ExtentReporter;
import com.tutorialninja.qa.utility.Utility;

public class MyListener implements ITestListener{
	
//select the class name -go to  -override method
	//to invoke these method we have to add <listener> in testng.xml file 
	//only in testng.xml file it will invoke not in individual test classes
	ExtentReports extentReport ;
	ExtentTest extentTest;
	String testName;
	public void onStart(ITestContext context) {
		//calling from utility package
		extentReport = ExtentReporter.generateExtentReport();
		System.out.println("Execution of Project Tests started");
		
	}
	
	
	public void onTestStart(ITestResult result) {
		//result.getName() return the test name
		//System.out.println(result.getName() +"started executing");
		testName=result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO,testName+"started executing" );
		
	}

	public void onTestSuccess(ITestResult result) {
	//System.out.println(result.getName()+"got successfully passed");
		extentTest.log(Status.PASS,testName+"got successfully passed" );

	}

	public void onTestFailure(ITestResult result) {

		WebDriver driver=null;
		
		try {
			//driver is not in the local so we have to get the driver by using below line
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		File scrScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String destinationScreenshotpath=System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".png";
//		try {
//			//to copy the screenshot
//			FileHandler.copy(scrScreenshot, new File(destinationScreenshotpath));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//calling the screenshot method from utility package
		String destinationScreenshotpath=Utility.captureScreenshot(driver, testName);
		//to add the ss in extent report
		extentTest.addScreenCaptureFromPath(destinationScreenshotpath);
		//all the exception will print
				//System.out.println(result.getThrowable());
		extentTest.log(Status.INFO,result.getThrowable());
		//		System.out.println(result.getName()+"got failed");
		extentTest.log(Status.FAIL,result.getName()+"got failed");
		
		


	}

	public void onTestSkipped(ITestResult result) {
	//	System.out.println(result.getThrowable());
	extentTest.log(Status.INFO,result.getThrowable());
	//System.out.println(result.getName() +"got skipped");
	extentTest.log(Status.SKIP,result.getName() +"got skipped");


	}


	public void onFinish(ITestContext context) {
		//if we dont use report will not add to the extent report
		extentReport.flush();
		String pathExtentreport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport=new File(pathExtentreport);
		//automatically it will generate extent report after execution
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
