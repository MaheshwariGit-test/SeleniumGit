package cucumber.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Appliaction.TestComponent.BaseTest;
import Application.PageObjects.CartPage;
import Application.PageObjects.CheckOutPage;
import Application.PageObjects.ConformationPage;
import Application.PageObjects.LandingPage;
import Application.PageObjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefination extends BaseTest{

	public LandingPage landingPage; //if we give global name then we can use on other method also as each page is rerurn another page
	public ProductCatalog productCatalog;
	@Given("I landed on Ecommerce page")//giving life to driver by inisializing
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage=landingApplication();
	}
	
	//instead of {string} like this we can use regexlike (.+) "^User enters (.+) and (.+)$"
	@Given("Logged in with userName {string} and passWord {string}")
	public void Logged_in_with_userName_and_passWord(String userName,String passWord){
		productCatalog=landingPage.loginApplication(userName,passWord);
	}
	
	@When("Add the product {string} to cart")
	public void Add_the_product_to_cart(String productName) {
		List<WebElement> list=productCatalog.getProductList();
	    productCatalog.addToCart(productName);
	}
	//here we can give either when or and if we want to use when or then we can use based on previous steps
	@When("Checkout Product {string}  and submit the order")
	public void Checkout_Product_and_submit_the_order(String productName) throws Throwable {
		 CartPage cartPage=productCatalog.goToCart();
		   	Boolean match=cartPage.VerifyCartProductList(productName);
			Assert.assertTrue(match);
			//check out
			CheckOutPage checkOutPage=cartPage.goToCheckOut();
			String countryName="ind";
			checkOutPage.getCountry(countryName);
			//select autosuggetion full drop down and get india
			checkOutPage.getSelectedcountry();
			Thread.sleep(8000);
	}
	
	@Then("Conformation message is displayed on conformation page")
	public void Conformation_message_is_displayed_on_conformation_page() {
		ConformationPage conformPage= new ConformationPage(driver);
		System.out.println(conformPage.getConformPage());
		driver.close();
	}
	
	@Then("{string} message is displayed on LandingPage")
	public  void Login_message_is_displayed_on_LandingPage(String message) {
		  Assert.assertEquals(landingPage.getErrorMessage(),message);
		  driver.close();
	}
}
