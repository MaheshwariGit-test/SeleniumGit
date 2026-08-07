package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//features="src/test/java/cucumber" give feature file path here and in glue give stepdefination class path 
//by defailt our result in cucumber come in the form of encoded form (not readable) so ,we have attribute called
//monochrome which will give result in cucumber
//if want to generate reports in html then we have to pull that plugin is like key and value pairs key is html and value is 
//target foldername where we want the report to create(give the folder name it will create fiolder and then html report in it (all plugin inforamtion is in the form of key value pair)
@CucumberOptions(features="src/test/java/cucumber",glue="cucumber.stepDefinations",tags="@ErrorValidation", 
monochrome=true, plugin= {"html:target/Cucumber-reports/Cucumber.html"})
public class TesNGTestRunner extends AbstractTestNGCucumberTests{

}
