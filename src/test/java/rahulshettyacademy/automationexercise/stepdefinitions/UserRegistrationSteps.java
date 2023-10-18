package rahulshettyacademy.automationexercise.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import rahulshettyacademy.automationexercise.pageobjects.UserRegistrationPage;
import rahulshettyacademy.automationexercise.utils.WebDriverSingleton;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;

public class UserRegistrationSteps {
    private UserRegistrationPage userRegistrationPage;
    private WebDriverWait wait;

    @Given("I navigate to the main page")
    public void iNavigateToTheMainPage() {
        userRegistrationPage = new UserRegistrationPage(WebDriverSingleton.getDriver());
        WebDriverSingleton.getDriver().get("https://demo.nopcommerce.com/");
        wait = new WebDriverWait(WebDriverSingleton.getDriver(), Duration.ofSeconds(10));
        WebDriverSingleton.getDriver().navigate().refresh();
    }

    @When("I click on the Register link")
    public void iClickOnTheRegisterLink() {
        WebElement registerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='ico-register']")));
        registerLink.click();
    }

    @When("I fill in the registration form with valid data")
    public void iFillInTheRegistrationFormWithValidData() {
        HashMap<String, String> userData = new HashMap<>();
        userData.put("gender", "Male");
        userData.put("firstName", "John");
        userData.put("lastName", "Smith");
        userData.put("day", "15");
        userData.put("month", "March");
        userData.put("year", "2021");
        userData.put("email", "john.smith@hotmail.com");
        userData.put("company", "XYZ");
        userData.put("password", "klm9000");
        userData.put("confPassword", "klm9000");
        userRegistrationPage.fillForm(userData);
        userRegistrationPage.clickRegisterButton();
    }

    @Then("I should see the \"Your registration completed\" message")
    public void iShouldSeeTheRegistrationCompletedMessage() {
        boolean isDisplayed = userRegistrationPage.isRegistrationCompletedDisplayed();
        Assert.assertTrue(isDisplayed, "Expected 'Your registration completed' message is not displayed");
    }

    @And("I should be able to click the Continue button")
    public void iShouldBeAbleToClickTheContinueButton() {
        userRegistrationPage.clickContinueButton();
    }
}
