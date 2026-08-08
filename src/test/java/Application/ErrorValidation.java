package Application;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Appliaction.TestComponent.BaseTest;
import Appliaction.TestComponent.RetryRunning;

public class ErrorValidation extends BaseTest{
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = RetryRunning.class)
	public void submitApplication() throws IOException {
		 
	  landingPage.loginApplication("suguki@gmail.com","Dummy@12356");//calling from LandingPage Object
	  Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
	  
	  System.out.println("Error msg Incorrect email or password");
	  System.out.println("Error msg Incorrect email or password");
	  System.out.println("Error msg Incorrect email or password");
	}
}
