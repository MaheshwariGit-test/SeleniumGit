package Application.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Application.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }
	  //veriry cart product
	  @FindBy(xpath="//tbody/tr/td[2]")
	  List<WebElement> orderList;

	 // private By cartProduct=By.cssSelector(".mb-3 b"); required when we are writing wait stmt
	   public  Boolean VerifyOrderProductList(String ProductName) {
		    // waitForTheElementToAppear(cartProduct);
		     Boolean match=orderList.stream().anyMatch(present->present.getText().equalsIgnoreCase(ProductName));
		       return match;
	   }
}
