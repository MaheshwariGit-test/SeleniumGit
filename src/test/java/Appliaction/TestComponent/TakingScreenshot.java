package Appliaction.TestComponent;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakingScreenshot extends BaseTest{
	static WebDriver driver=null;//must be insitialized as it is static when we are calling it we are getting error
	public static String getScreenshotmethods(String testCaseName,WebDriver driver) {//here i am passing driver control
	   //driver=driver;
		TakesScreenshot screenshotLog=(TakesScreenshot)driver;
		File temp=screenshotLog.getScreenshotAs(OutputType.FILE);
		File Perm=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		 try {
			FileUtils.copyFile(temp, Perm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png"; //like this we will return path where file stored			 
	}	
}
