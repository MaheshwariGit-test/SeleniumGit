package Application;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Appliaction.TestComponent.BaseTest;
import Appliaction.TestComponent.TakingScreenshot;
import Application.PageObjects.CartPage;
import Application.PageObjects.CheckOutPage;
import Application.PageObjects.ConformationPage;
import Application.PageObjects.OrderPage;
import Application.PageObjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
	//String productName ="ZARA COAT 3";
	  String countryName="ind";
	   //login page
	  @Test(dataProvider = "getData" ,groups = "Purchase")
	  public void submitApplication(HashMap<String, String> input) throws InterruptedException, IOException {
		  
		// LandingPage landingPage= landingApplication();
	//LandingPage login=new LandingPage(driver);
	//login.gotoURL();
	ProductCatalog productCatalog=landingPage.loginApplication(input.get("email"),input.get("password"));//calling from LandingPage Object

	//or//getting all the product names and what product is we need is present 
	//=new ProductCatalog(driver);
    List<WebElement> list=productCatalog.getProductList();
    //productCatalog.getProductNames(input.get("productName"));
	
    //get the name of the product tht u want to add to cart
    productCatalog.addToCart(input.get("productName"));
	
	//visibility of the toast and invisibility in add to cartpage
	//prods.goToCart();
	//what we have added is present or not if present retrn true
    CartPage cartPage=productCatalog.goToCart();
   	Boolean match=cartPage.VerifyCartProductList(input.get("productName"));
	Assert.assertTrue(match);
	
	//click on CheckOut
	CheckOutPage checkOutPage=cartPage.goToCheckOut();
	checkOutPage.getCountry(countryName);
	
	//select autosuggetion full drop down and get india
	checkOutPage.getSelectedcountry();
	
	//clcik on PlaceOrder
	//checkOutPage.placeOrder();
	
	//conforamation Page
	Thread.sleep(8000);
	ConformationPage conformPage= new ConformationPage(driver);
	System.out.println(conformPage.getConformPage());
	
	// Take screenshot when needed not working
//	String screenshotPath = TakingScreenshot.getScreenshotmethods("SubmitOrder",driver);
//	System.out.println("Screenshot saved at: "+screenshotPath);
}
	  
//	  @Test
//	  public void method() throws IOException {
//		  
//	  }
//		
	  
	  @Test(dependsOnMethods = {"submitApplication"})
	  public void orderHistroy() {
		  
		 ProductCatalog productCatalog=landingPage.loginApplication("suguki@gmail.com","Dummy@123");//calling from LandingPage Object
         OrderPage orderpage=productCatalog.orderHistroy();
		  Boolean match=orderpage.VerifyOrderProductList("ZARA COAT 3");
		  Assert.assertEquals(match, true);
	  }
	  @DataProvider
	  public Object[][] getData() throws Throwable {
		 List<HashMap<String,String>>  data= readJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\Application\\Data\\PurchaseOrder.json");
	    return new Object[][] {{data.get(0)}, {data.get(1)}};
	  }
	  
	  
//	  @DataProvider
//	  public Object[][] getData() {
//		HashMap<Object,Object> map=new HashMap<Object, Object>();
//	    map.put("email", "suguki@gmail.com");
//	    map.put("password", "Dummy@123");
//	    map.put("productName", "ZARA COAT 3");
//	    HashMap<Object,Object> map2=new HashMap<Object, Object>();
//	    map2.put("email", "advith@gmail.com");
//	    map2.put("password", "Advith@123");
//	    map2.put("productName", "ADIDAS ORIGINAL");
//	    return new Object[][] {{map}, {map2}};
//	  }
//	  public Object[][] getData() {
//			 return new Object[][] {{map}, {map2}};
//		  }

}
