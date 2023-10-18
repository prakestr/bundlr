package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {

    // Define WebElements
    @FindBy(xpath = "//button[@class='btn btn-primary' and text()='Buy Now']")
    private WebElement buyNowButton;

    @FindBy(css = "h1.hero-primary")
    private WebElement confirmationText;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Methods related to actions on the CheckoutPage

    public void clickBuyNow() {
        wait.until(ExpectedConditions.visibilityOf(buyNowButton)).click();
    }


    @FindBy(xpath = "//a[contains(@class, 'btnn') and contains(text(), 'Place Order')]")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    private WebElement countryInput;


    public void selectCountry(String country) {
        actions.moveToElement(countryInput).click().sendKeys(country).perform();

        By countryOption = By.xpath("//button[contains(@class, 'list-group-item') and contains(., '" + country + "')]");
        WebElement countryToSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(countryOption));
        countryToSelect.click();
    }

    public String confirmOrder() {
        js.executeScript("arguments[0].click();", placeOrderButton);
        wait.until(ExpectedConditions.visibilityOf(getConfirmationText()));
        return getConfirmationText().getText().trim();
    }

    public WebElement getConfirmationText() {
        return confirmationText;
    }

}
