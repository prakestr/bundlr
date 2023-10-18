package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ProductPage extends BasePage {

    private static final Logger logger = Logger.getLogger(ProductPage.class.getName());


    @FindBy(css = ".mb-3")
    private List<WebElement> allProducts;

    @FindBy(css = ".btn-custom > label")
    private WebElement customButtonLabel;

    @FindBy(css = "button.btn.btn-custom i.fa.fa-sign-out")
    private WebElement logoutButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    private List<WebElement> getProducts() {
        return allProducts;
    }

    public WebElement getProductByName(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        WebElement product = null;
        List<WebElement> products = getProducts();
        try {
            product = products.stream()
                    .filter(p -> p.findElement(By.cssSelector("h5 b")).getText().equalsIgnoreCase(productName))
                    .findFirst()
                    .orElse(null);
        } catch (NoSuchElementException e) {
            logger.log(Level.SEVERE, "Failed to find the product element for name: " + productName, e);
        }
        return product;
    }

    public void addProductToCartByName(String productName) {
        WebElement product = getProductByName(productName);
        if(product != null) {
            product.findElement(By.xpath(".//button[contains(.,'Add To Cart')]")).click();
            wait.until(ExpectedConditions.textToBePresentInElement(customButtonLabel, "1"));
        } else {
            logger.log(Level.WARNING, "Product with name " + productName + " not found.");

        }
    }
}
