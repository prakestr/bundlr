package rahulshettyacademy.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rahulshettyacademy.automationexercise.pageobjects.UserRegistrationPage;
import rahulshettyacademy.automationexercise.utils.WebDriverSingleton;

import java.time.Duration;
import java.util.HashMap;

public class UserRegistrationPageTest {
    private UserRegistrationPage userRegistrationPage;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Initialize the page object
        userRegistrationPage = new UserRegistrationPage(WebDriverSingleton.getDriver());

        // Navigate to main page
        WebDriverSingleton.getDriver().get("https://demo.nopcommerce.com");

        // Initialize WebDriverWait
        wait = new WebDriverWait(WebDriverSingleton.getDriver(), Duration.ofSeconds(10));

        WebDriverSingleton.getDriver().navigate().refresh();

        // Check if "Register" link is present and click it
        WebElement registerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='ico-register']")));
        registerLink.click();

    }

    @Test
    public void userRegistrationTest() {
        HashMap<String, String> userData = new HashMap<>();
        userData.put("gender", "Male");
        userData.put("firstName", "John");
        userData.put("lastName", "Dude");
        userData.put("day", "15");
        userData.put("month", "March");
        userData.put("year", "2021");
        userData.put("email", "john.dude@hotmail.com");
        userData.put("company", "XYZ");
        userData.put("password", "klm9000");
        userData.put("confPassword", "klm9000");

        // Check if "FirstName" field is present
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));
        Assert.assertNotNull(firstNameField, "FirstName field is not present!");

        // Fill out and submit the form
        userRegistrationPage.fillForm(userData);
        userRegistrationPage.clickRegisterButton();
        boolean isDisplayed = userRegistrationPage.isRegistrationCompletedDisplayed();
        Assert.assertTrue(isDisplayed, "Expected 'Your registration completed' message is not displayed");
        userRegistrationPage.clickContinueButton();
    }

    @AfterClass
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}
