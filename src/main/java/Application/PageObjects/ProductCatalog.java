package Application.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Application.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
  WebDriver driver;
  public ProductCatalog(WebDriver driver) {
	  super(driver);
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
  @FindBy(css=".mb-3")
  List<WebElement> list;

  private By products=By.cssSelector(".mb-3");//created so we can give this in wait method
   public List<WebElement> getProductList() {
	     waitForTheElementToAppear(products);
	       return list;
   }
   //checking the product that we need to add to cart
  public WebElement getProductNames(String productName) {
	  WebElement prod=getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
  }
  
  //add to cart and visibility of toast
  private By addToCart=By.cssSelector(".btn.w-10.rounded");
  private By appearToast=By.xpath("//div[contains(@aria-label,'Product Added To Cart')]");
//  private By appearToast=By.cssSelector(".ngx-toastr.toast-success");
  private By disaperToast=By.xpath("//div[contains(@aria-label,'Product Added To Cart')]");
  
  public void addToCart(String productName) {
	  WebElement prod=getProductNames(productName);
	  //here we cannot create PageFactory as we are creating prod.findElement, in pagefactory we use driver.findelements
	  prod.findElement(addToCart).click();
	  System.out.println(prod.getText());
	  //System.out.println(prod.findElement(addToCart).getText());//Add To Cart
		waitForTheElementToAppear(appearToast);
		waitForElementToDiapper(disaperToast);
  }
}