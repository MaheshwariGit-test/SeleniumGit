package Application.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Application.PageObjects.CartPage;
import Application.PageObjects.OrderPage;

public class AbstractComponent {
   
	WebDriver driver; 
	WebDriverWait wait;
    JavascriptExecutor js;
	
   public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		js = (JavascriptExecutor) driver; 
	}

   public void waitForTheElementToAppear(By product) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(product));
   }
   public void waitForElementToDiapper(By product) {
	   wait.until(ExpectedConditions.visibilityOfElementLocated(product));
   }
   
   @FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
   WebElement headerCart;
   public CartPage goToCart() {
	   headerCart.click();
	   CartPage cartPage = new CartPage(driver);
	    return cartPage;
   }
   
   public void hiddenElementInJavaScript(WebElement checkOut) {
	js.executeScript("arguments[0].scrollIntoView(true);", checkOut);
	wait.until(ExpectedConditions.elementToBeClickable(checkOut));
   }
   
   public void hiddenElementToclick(WebElement clickElement) {
		js.executeScript("arguments[0].click()", clickElement);
		wait.until(ExpectedConditions.elementToBeClickable(clickElement));
   }
   
   @FindBy(xpath="(//button[contains(@class,'btn-custom')])[2]")
   WebElement OrderHeader;
   public OrderPage orderHistroy() {
	   OrderHeader.click();
	   OrderPage orderPage = new OrderPage(driver);
	    return orderPage;
   }

}
