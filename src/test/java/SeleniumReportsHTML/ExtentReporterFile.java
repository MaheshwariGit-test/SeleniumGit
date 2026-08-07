package SeleniumReportsHTML;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterFile {
	
	ExtentReports report;
	@BeforeMethod
	public  void configure() {
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reportorHelper=new ExtentSparkReporter(path);
		reportorHelper.config().setReportName("Web Automation Results");
		reportorHelper.config().setDocumentTitle("Alluri Report");
		
		report=new ExtentReports();
		report.attachReporter(reportorHelper);
		report.setSystemInfo("Tester", "Maheshwari");
		
		}
   @Test
	public void executeTest() {
	   ExtentTest extent=report.createTest("ExtentReportsFile");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anger\\SeleniumDriver\\chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://rahulshettyacademy.com/");
		System.out.println(driver.getTitle());
		extent.fail("Reports not matching");//failing the scripts Explicitly
		extent.addScreenCaptureFromBase64String("Screenshot");
		driver.close();
		report.flush();
		
	}
}
