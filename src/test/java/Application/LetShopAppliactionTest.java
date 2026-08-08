package Application;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Appliaction.TestComponent.BaseTest;
import Application.PageObjects.CartPage;
import Application.PageObjects.CheckOutPage;
import Application.PageObjects.ConformationPage;
import Application.PageObjects.ProductCatalog;

public class LetShopAppliactionTest extends BaseTest
{
	@Test
	public void developBranch() {
		System.out.println("making changes to develop branch");
	}
	@Test
	public void errorValidation() throws IOException {
		 
	  landingPage.loginApplication("ishika@gmail.com","Dummy@12356");//calling from LandingPage Object
	  Assert.assertEquals(landingPage.getErrorMessage(),"correct email or password.");
	}
	
	 @Test
	  public void submitApplication() throws IOException {
		  String productName ="ZARA COAT 3";
		  String countryName="ind";
		
   ProductCatalog productCatalog=landingPage.loginApplication("suguki@gmail.com","Dummy@123");//calling from LandingPage Object

   List<WebElement> list=productCatalog.getProductList();
   productCatalog.getProductNames(productName);
	
   //get the name of the product tht u want to add to cart
   productCatalog.addToCart(productName);
	
	
   CartPage cartPage=productCatalog.goToCart();
  	Boolean match=cartPage.VerifyCartProductList(productName);
	Assert.assertTrue(match);
	
	//click on CheckOut
	CheckOutPage checkOutPage=cartPage.goToCheckOut();
	checkOutPage.getCountry(countryName);
	
	//select autosuggetion full drop down and get india
	checkOutPage.getSelectedcountry();
	
	//clcik on PlaceOrder
	//checkOutPage.placeOrder();
	
	//conforamation Page
	ConformationPage conformPage=checkOutPage.placeOrder();
	System.out.println(conformPage.getConformPage());
}

}
