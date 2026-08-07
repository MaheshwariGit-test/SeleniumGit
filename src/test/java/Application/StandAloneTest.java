package Application;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Appliaction.TestComponent.BaseTest;
import Application.PageObjects.CartPage;
import Application.PageObjects.CheckOutPage;
import Application.PageObjects.ConformationPage;
import Application.PageObjects.LandingPage;
import Application.PageObjects.ProductCatalog;


public class StandAloneTest extends BaseTest {
   public static void main(String[] args) {
	  String productName ="ZARA COAT 3";
	  String countryName="ind";
	   //login page
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\anger\\SeleniumDriver\\chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//	driver.get("https://rahulshettyacademy.com/client/#/auth/login");
//	driver.findElement(By.id("userEmail")).sendKeys("suguki@gmail.com");
//	driver.findElement(By.id("userPassword")).sendKeys("Dummy@123");
//	driver.findElement(By.id("login")).click();
	LandingPage login=new LandingPage(driver);
	login.gotoURL();
	login.loginApplication("suguki@gmail.com","Dummy@123");//calling from LandingPage Object
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3 b")));
//	List<WebElement> list=driver.findElements(By.cssSelector(".mb-3 b"));
//	for(WebElement iteam:list) {
//		String iteamName=iteam.getText();
//		System.out.println(iteamName);
//		if(iteamName.contains("ZARA")) {
//			driver.findElement(By.xpath("//button[contains(@class,\"btn w-10 rounded\")]")).click();
//			System.out.println("Zara Product addded to cart");
//		}
//	}
	//or//getting all the product names and what product is we need is present 
//	WebElement prod=list.stream().filter(product->
//	product.getText().contains("ZARA")).findFirst().orElse(null);
	ProductCatalog prods=new ProductCatalog(driver);
	//List<WebElement> list=prods.getProductList();
	prods.getProductNames(productName);
	
    //add product to cart
//	prod.findElement(By.xpath("(//button[contains(@class,'btn w-10 rounded')])[2]")).click();
//	System.out.println(prod.getText());
	prods.addToCart(productName);
	
	//visibility of the toast
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
//	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("toast-container")));
	
	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='btn btn-custom'])[3]")));
	//open cart or go to the cart
	//driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
	prods.goToCart();
	
	//what we have added is present or not if present retrn true
//	List<WebElement> cart=driver.findElements(By.xpath("//div[@class='cartSection'] /h3"));
//	Boolean match=cart.stream().anyMatch(present->present.getText().equals("ZARA COAT 3"));
//	Assert.assertTrue(match);
	CartPage cartPage=new CartPage(driver);
	Boolean match=cartPage.VerifyCartProductList(productName);
	Assert.assertTrue(match);
	
	//click on CheckOut
//	WebElement ele = driver.findElement(By.xpath("//button[contains(text(),'Checkout')and@class='btn btn-primary']"));
//	JavascriptExecutor js=(JavascriptExecutor) driver;
//	js.executeScript("arguments[0].scrollIntoView(true);", ele);
//	wait.until(ExpectedConditions.elementToBeClickable(ele));
//	ele.click();
//	cartPage.goToCheckOut();
	
	//click on country and enter ind
//	WebElement ref=driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
//	Actions a=new Actions(driver);
//	a.sendKeys(ref,"ind").perform();
	CheckOutPage checkOutPage=cartPage.goToCheckOut();
	checkOutPage.getCountry(countryName);
	
	//select autosuggetion full drop down and get india
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//	js.executeScript("arguments[0].click()", driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")));
//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@class,'ta-item')])[2]")));
	checkOutPage.getSelectedcountry();
	
	//clcik on PlaceOrder
//	WebElement ref2=driver.findElement(By.xpath("//a[normalize-space(text())='Place Order']//i"));
//	js.executeScript("arguments[0].click()", ref2);
//	wait.until(ExpectedConditions.elementToBeClickable(ref2));
	//ref2.click();//this click not required as we have java click
	checkOutPage.placeOrder();
	
	//conforamation Page
	ConformationPage conformPage=checkOutPage.placeOrder();
	System.out.println(conformPage.getConformPage());
	
}
}
