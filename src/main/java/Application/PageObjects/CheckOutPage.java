package Application.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Application.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
       PageFactory.initElements(driver, this);
	}
	//click on countrySearch and type ind
	   @FindBy(xpath="//input[@placeholder='Select Country']")
	   WebElement countrySearch;
	   public String getCountry(String countryName) {
		   countrySearch.click();
		   Actions a=new Actions(driver);
			a.sendKeys(countrySearch,countryName).perform();
			return countryName;
	   }
	   
	 //select AutoSiggestion dropdown and then from that select india  
	   private By allCountries=By.cssSelector(".ta-results");
	   @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	   WebElement country;
	   public void getSelectedcountry() {
		   waitForTheElementToAppear(allCountries);
		   country.click();
		   //hiddenElementToclick(country);
	   }
	   
	 //PalceOrder by clciking the PalceOrder button
	   @FindBy(xpath="//a[normalize-space(text())='Place Order']//i")
	   WebElement placeOrder;
	   private By placeOrderwait=By.xpath("//a[normalize-space(text())='Place Order']");
	   public ConformationPage placeOrder() {
		   waitForTheElementToAppear(placeOrderwait);
		   //hiddenElementToclick(placeOrder);
		   placeOrder.click();
		    return new ConformationPage(driver);
	   }
}
