package Application.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Application.AbstractComponents.AbstractComponent;

public class ConformationPage extends AbstractComponent {
	
	WebDriver driver;
   public ConformationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

   @FindBy(xpath="//h1[normalize-space(text())='Thankyou for the order.']")
   WebElement conformPage;
   public String getConformPage() {
	   return conformPage.getText();
   }
}
