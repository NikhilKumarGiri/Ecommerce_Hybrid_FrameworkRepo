package com.tutorialninja.qa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	/*Declaring static because we dont have to create object,final because we cant 
	change the value in other class when we will implement this variable */
	public static final int IMPLICIT_WAIT_TIME=15;
	public static final int PAGE_LOAD_TIME=10;
	
	public static String generateEmailTimeStamp() {
		Date date = new Date();
		String email="nikhil";
		return email+date.toString().replace(" ", "_").replace(":", "_")+"@gmail.com";
	}
	public static String generateEmailwithnumber()
	{
		int number=(int) ((Math.random())*10000000);
		return "nikhilkumargiri"+number;
	}
	public static Object[][] getTestDataFromExcel(String sheetName)
	{
		File excelFile=new File(System.getProperty("user.dir")
				+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\TutorialNinjaTestdata.xlsx");
		XSSFWorkbook workbook=null;
		try {
		FileInputStream fis=new FileInputStream(excelFile);
		workbook=new XSSFWorkbook(fis);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		XSSFSheet sheet=workbook.getSheet(sheetName);
		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();
		Object [][]data=new Object[rows][cols];
		for (int i=0;i<rows;i++)
		{
			XSSFRow row=sheet.getRow(i+1);
			for(int j=0;j<cols;j++)
			{
				XSSFCell cell=row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				case BLANK:
					break;
				case ERROR:
					break;
				case FORMULA:
					break;
				case _NONE:
					break;
				default:
					break;
					
				}
			}
		}
		return data;
	}
	public static String captureScreenshot(WebDriver driver,String testName)
	{
		File scrScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotpath=System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".png";
		try {
			//to copy the screenshot
			FileHandler.copy(scrScreenshot, new File(destinationScreenshotpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationScreenshotpath;
	}
	
}
