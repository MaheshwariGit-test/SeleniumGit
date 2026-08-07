package Application.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Application.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
  WebDriver driver;
  public CartPage(WebDriver driver) {
	  super(driver);
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
  //veriry cart product
  @FindBy(xpath="//div[@class='cartSection'] /h3")
  List<WebElement> cartProducts;

 // private By cartProduct=By.cssSelector(".mb-3 b"); required when we are writing wait stmt
   public  Boolean VerifyCartProductList(String ProductName) {
	    // waitForTheElementToAppear(cartProduct);
	     Boolean match=cartProducts.stream().anyMatch(present->present.getText().equalsIgnoreCase(ProductName));
	       return match;
   }
   
 //click on CheckOut
   @FindBy(xpath="//button[contains(text(),'Checkout')]")
   WebElement checkOut;
   public CheckOutPage goToCheckOut() {
	   hiddenElementInJavaScript(checkOut);
	   checkOut.click();
	   return new CheckOutPage(driver);
   }
}