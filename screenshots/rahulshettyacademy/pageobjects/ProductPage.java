package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(css = ".mb-3")
    private List<WebElement> allProducts;

    @FindBy(css = ".btn-custom > label")
    private WebElement customButtonLabel;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> getProducts() {
        // Always fetch fresh products when this method is called
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
            // Log or print the exception if you want
            System.out.println("Failed to find the product element for name " + productName + ". " + e.getMessage());
        }

        return product;
    }

    public void addProductToCartByName(String productName) {
        WebElement product = getProductByName(productName);
        if(product != null) {
            product.findElement(By.xpath(".//button[contains(.,'Add To Cart')]")).click();
            // Wait for the text "1" to be present in the specified element
            wait.until(ExpectedConditions.textToBePresentInElement(customButtonLabel, "1"));

        } else {
            System.out.println("Product with name " + productName + " not found.");
        }
    }
}
