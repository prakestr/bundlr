package rahulshettyacademy.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/rahulshettyacademy/features",  // Path to your feature file
        glue = "rahulshettyacademy.stepdefinitions",  // Path to your step definition file
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-pretty", "json:target/cucumber-reports/CucumberTestReport.json"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // This class should be empty
}
