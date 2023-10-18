
package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    By buyNowButtonLocator = By.xpath("//button[@class='btn btn-primary' and text()='Buy Now']");

    @FindBy(css = "button.btn.btn-custom > label")
    private WebElement cartLabel;

    @FindBy(css = ".cartWrap .items h3")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//button[@class='btn btn-custom' and contains(., 'Cart')]")
    private WebElement cartButton;

    @FindBy(xpath = "//button[contains(@class,'btn-primary') and text()='Checkout']")
    private WebElement checkoutButton;


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToCart() {
        wait.until(ExpectedConditions.visibilityOf(cartLabel));
        cartButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(buyNowButtonLocator));
    }

    public boolean isProductPresentInCart(String productName) {
        return cartItems.stream()
                .anyMatch(item -> item.getText().equalsIgnoreCase(productName));
    }

    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.visibilityOf(checkoutButton));
        checkoutButton.click();
    }
}
