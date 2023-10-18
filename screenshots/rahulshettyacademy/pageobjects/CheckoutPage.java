package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//li[contains(@class, 'totalRow')]//button")
    private WebElement checkoutBtn;

    @FindBy(css = "[placeholder='Select Country']")
    private WebElement countryInput;

    @FindBy(css = "a.btnn.action__submit.ng-star-inserted")
    private WebElement placeOrderBtn;

    @FindBy(css = "h1.hero-primary")
    WebElement confirmationText;

    @FindBy(css = "label.ng-star-inserted")
    private WebElement orderIDLabel;

    @FindBy(css = "div[class*='toast']")
    private WebElement toastMessageElement;

    // Constructor to initialize the web elements, the driver, and other utilities
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void proceedToCheckout() {
        // Wait for the button to become visible
        wait.until(ExpectedConditions.visibilityOf(checkoutBtn));

        // Click the button
        js.executeScript("arguments[0].click();", checkoutBtn);
    }

    public void selectCountry(String countryName) {
        actions.moveToElement(countryInput).click().sendKeys(countryName).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        WebElement countryOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(., '" + countryName + "')]")));
        countryOption.click();
    }

    public String confirmOrder() {
        js.executeScript("arguments[0].click();", placeOrderBtn);
        wait.until(ExpectedConditions.visibilityOf(getConfirmationText()));
        return getConfirmationText().getText().trim();
    }

    public WebElement getConfirmationText() {
        return confirmationText;
    }

    public String getOrderID() {
        wait.until(ExpectedConditions.visibilityOf(orderIDLabel));
        return orderIDLabel.getText().replace("|", "").trim();
    }

    public String getToastMessage() {
        return toastMessageElement.getText();
    }
}
