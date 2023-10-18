package rahulshettyacademy.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;

public class BasePage {

    // Instance variables for WebDriver, WebDriverWait, and Actions
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    @FindBy(css = "div[class*='toast']")
    private WebElement toastMessage;

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "userPassword")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    // Assuming there's a profile icon or some element visible after successful login
    @FindBy(css = ".fa-home")
    private WebElement homeIcon;

    @FindBy(css = "button[routerlink='/dashboard/myorders']")
    private WebElement ordersButton;

    // Constructor to initialize the web elements, WebDriver, WebDriverWait, and Actions
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public String getToastMessage() {
        waitForToastMsg();
        return toastMessage.getText();
    }

    public void waitForToastMsg() {
        wait.until(ExpectedConditions.visibilityOf(toastMessage));
    }

    public void launchApplication(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    // Check if the user is logged in by validating the visibility of the profile icon
    public boolean isUserLoggedIn() {
        try {
            wait.until(ExpectedConditions.visibilityOf(homeIcon));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOrdersButton() {
        wait.until(ExpectedConditions.visibilityOf(ordersButton));
        ordersButton.click();
    }

    private static final String LANDING_PAGE_URL = "https://rahulshettyacademy.com/client/";

    public void goTo() {
        driver.get(LANDING_PAGE_URL);
    }
}
