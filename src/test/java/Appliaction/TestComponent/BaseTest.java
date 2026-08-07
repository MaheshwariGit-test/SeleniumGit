package Appliaction.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Application.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
   public WebDriver driver;
   public LandingPage landingPage;
   
   public WebDriver initializeBrowser() throws IOException {
	   
	   Properties prop=new Properties(); 
//	   FileInputStream fis=new FileInputStream("C:\\Users\\anger\\eclipse-workspace\\FrameWork\\src\\test\\java\\Application\\resources\\GlobalResources.properties");
	   FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Application\\resources\\GlobalResources.properties");
	   prop.load(fis);
	   String browserName=System.getProperty("browserName")!=null? System.getProperty("browserName") : prop.getProperty("browserName");
	   //String browserName=prop.getProperty("browserName");
	   if (browserName.contains("chrome")) {
		   ChromeOptions options=new ChromeOptions();
		   //System.setProperty("webdriver.chrome.driver", "C:\\Users\\anger\\SeleniumDriver\\chromedriver.exe");
		   WebDriverManager.chromedriver().setup();
		   if(browserName.contains("headless")) {
			   options.addArguments("--headless=new"); 
		        options.addArguments("--window-size=1440,900");
		   }
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
	   }
	   else if(browserName.equalsIgnoreCase("edge")) {
		  // System.setProperty("webdriver.chrome.driver", "C:\\Users\\anger\\SeleniumDriver\\edgedriver.exe");
		   WebDriverManager.edgedriver().setup();
		   driver=new EdgeDriver();
	   }
	   else if(browserName.equalsIgnoreCase("firefox")) {
		   //System.setProperty("webdriver.chrome.driver", "C:\\Users\\anger\\SeleniumDriver\\firefoxdriver.exe");
		   WebDriverManager.firefoxdriver().setup();
		   driver=new FirefoxDriver();
	   }
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	   return driver;
   }
//   returns List of HashMap
   public List<HashMap<String,String>> readJsonData(String filePath) throws Throwable {
//		FileInputStream fis=new FileInputStream("C:\\Users\\anger\\eclipse-workspace\\FrameWork\\src\\test\\java\\Application\\Data\\PurchaseOrder.json");
		
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}
   
//   Screenshot creted seperate class
//   public void getScreanshot(String testCaseName) throws IOException {
//		  TakesScreenshot screenshot=(TakesScreenshot)driver;
//		  File temp=screenshot.getScreenshotAs(OutputType.FILE);
//		  File perm=new File(System.getProperty("user.dir")+"reports"+testCaseName +".png");
//		  FileUtils.copyFile(temp, perm); 
//	  }
   
   //Open landing page/link
   @BeforeMethod(groups="Purchase")
     public LandingPage landingApplication() throws IOException {
    	 driver=initializeBrowser();
    	 landingPage=new LandingPage(driver);
    	 landingPage.gotoURL();
    	 return landingPage;
     }
   @AfterMethod(groups="Purchase")
    public void tearDown() {
	   driver.close();
   }
}
