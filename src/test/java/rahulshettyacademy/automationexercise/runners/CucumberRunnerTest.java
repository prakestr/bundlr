package rahulshettyacademy.automationexercise.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import rahulshettyacademy.automationexercise.utils.WebDriverSingleton;

@CucumberOptions(
        features = "src/test/java/rahulshettyacademy/automationexercise/features",  // Path to your feature file
        glue = "rahulshettyacademy.automationexercise.stepdefinitions",  // Path to your step definition file
        plugin = {"pretty", "html:target/cucumber-reports.html"}  // Plugins to generate reports
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

    @AfterClass
    public static void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}