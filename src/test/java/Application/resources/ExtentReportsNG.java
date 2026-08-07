package Application.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
   
	ExtentReports extentReport;
	public  static ExtentReports getReportsObject() {
//		ExtentSparkReporter reporter=new ExtentSparkReporter(System.getProperty("user.dir"+"\\reportName\\index.html"));
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Testing");
		reporter.config().setDocumentTitle("Testing Reports");
		ExtentReports extentReport=new ExtentReports();
		extentReport.attachReporter( reporter);
		extentReport.setSystemInfo("Tester", "Maheshwari");
		//extentReport.createTest(filePath);	
		return extentReport;
	}
}
	