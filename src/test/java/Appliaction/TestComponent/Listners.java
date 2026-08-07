package Appliaction.TestComponent;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Application.resources.ExtentReportsNG;

public class Listners implements ITestListener {
    ExtentTest test;
    WebDriver driver;
	ExtentReports extentReports=ExtentReportsNG.getReportsObject();
	ThreadLocal<ExtentTest> reports=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test=extentReports.createTest(result.getMethod().getMethodName());
		reports.set(test);
		//ITestListener.super.onTestStart(result);
	}
	@Override
	public void onTestFailure(ITestResult result) {
		reports.get().fail(result.getThrowable()); // to get datails abouyt test fail we use thid throwable method
		//giving control of drivder from class
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			String imagepath=TakingScreenshot.getScreenshotmethods(result.getMethod().getMethodName(),driver);
			//test.addScreenCaptureFromPath(imagepath, result.getMethod().getMethodName());// befre making thread safe
			reports.get().addScreenCaptureFromPath(imagepath, result.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		reports.get().log(Status.PASS, "Testcase Passed");
		//ITestListener.super.onTestSuccess(result);
	}
	public void onMethodSuccess(ITestResult result) {
		reports.get().log(Status.PASS, "Testcase Passed");
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			String imagepath=TakingScreenshot.getScreenshotmethods(result.getMethod().getMethodName(),driver);
			reports.get().addScreenCaptureFromPath(imagepath, result.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		reports.get().log(Status.SKIP, "Skipped");
		//ITestListener.super.onTestSkipped(result);
	}
	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}
}
