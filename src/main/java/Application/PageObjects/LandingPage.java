package Application.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Application.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
  WebDriver driver;
  public LandingPage(WebDriver driver) {
	  super(driver);
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
  @FindBy(id="userEmail")
  private WebElement userEmail;

  @FindBy(id="userPassword")
  private WebElement password;

  @FindBy(id="login")
  private WebElement submit;

  public ProductCatalog loginApplication(String email, String password) {
      userEmail.sendKeys(email);
      this.password.sendKeys(password);
      submit.click();
      return new ProductCatalog(driver);
  }
  
  @FindBy(xpath="//div[normalize-space(text())='Incorrect email or password.']")
  private WebElement errorMessage;
  private By waitElement =By.xpath("//div[normalize-space(text())='Incorrect email or password.']");
  public String getErrorMessage() {
	  waitForTheElementToAppear(waitElement);
	  System.out.println(errorMessage.getText());
	  return errorMessage.getText();
  }
  public void gotoURL() {
	  driver.get("https://rahulshettyacademy.com/client/#/auth/login");
  }
}
