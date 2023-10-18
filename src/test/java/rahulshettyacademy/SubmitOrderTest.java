//package rahulshettyacademy;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import rahulshettyacademy.pageobjects.LandingPage;
//import rahulshettyacademy.pageobjects.ProductCatalog;
//
//import java.time.Duration;
//import java.util.List;
//
//import static org.testng.AssertJUnit.assertEquals;
//
//
//public class SubmitOrderTest {
//    public static void main(String[] args) {
//        // Setup ChromeDriver using WebDriverManager
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        Actions actions = new Actions(driver);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        driver.manage().window().maximize();
//
//        // create page objects
//        LandingPage landingpage = new LandingPage(driver);
//        ProductCatalog productCatalog = new ProductCatalog(driver);
//
//        // Go to landing page
//        landingpage.goTO();
//
//        // Log into application
//        landingpage.loginApplication("phil@hotmail.com", "Klm9000!");
//
//        // Get Product List
//        List<WebElement> products = productCatalog.getProductList();
//
//
//
//        // Use Java streams to find the WebElement with the text 'ZARA COAT 3'
//
//        if (zaraCoat3 != null) {
//            // Click the 'Add To Cart' button of the ZARA COAT 3 product
//            WebElement addToCartButton = zaraCoat3.findElement(By.xpath(".//button[contains(.,'Add To Cart')]"));
//            addToCartButton.click();
//
//            // Wait for the toast to appear
//            WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container .toast-message")));
//
//            // Assert that the toast's text is as expected
//            Assert.assertEquals(toastMessage.getText(), "Product Added To Cart", "Toast message does not match!");
//
//            // Wait for the cart to update (You can wait for a specific condition if needed)
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fa-shopping-cart")));
//
//            // Wait for the cart label to be updated
//            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".btn-custom > label"), "1"));
//
//            // Get the cart label element
//            WebElement cartLabel = driver.findElement(By.cssSelector(".btn-custom > label"));
//
//            // Verify if the cart contains '1' item
//            String cartCount = cartLabel.getText();
//            Assert.assertEquals(cartCount, "1", "Cart count does not match!");
//
//        } else {
//            Assert.fail("Product 'ZARA COAT 3' not found.");
//        }
//        // Click cart button
//        driver.findElement(By.xpath("//button[@class='btn btn-custom' and contains(., 'Cart')]")).click();
//
//        // Wait for the cart page to load
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartWrap")));
//
//        // Fetch all items in the cart
//        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartWrap .items h3"));
//
//        // Use Java streams to check if 'ZARA COAT 3' is present among the items
//        boolean isZaraCoat3Present = cartItems.stream()
//                .anyMatch(item -> item.getText().equalsIgnoreCase("ZARA COAT 3"));
//
//        // Assert that 'ZARA COAT 3' is present in the cart
//        Assert.assertTrue(isZaraCoat3Present, "'ZARA COAT 3' not found in the cart.");
//
//        // Click Checkout
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class, 'totalRow')]//button[text()='Checkout']")));
//        WebElement checkoutBtn = driver.findElement(By.xpath("//li[contains(@class, 'totalRow')]//button[text()='Checkout']"));
//        js.executeScript("arguments[0].click();", checkoutBtn);
//
//        // Find the input element by its placeholder and type "United States".
//        WebElement countryInput = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
//        actions.moveToElement(countryInput).click().sendKeys("United States").perform();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//
//        // Wait for the dropdown to appear and for the "United States" option to be visible.
//        WebElement unitedStatesOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(., 'United States')]")));
//
//        // Click on the "United States" option.
//        unitedStatesOption.click();
//
//        // Click the "Place Order" button
//        WebElement element = driver.findElement(By.cssSelector("a.btnn.action__submit.ng-star-inserted"));
//        js.executeScript("arguments[0].click();", element);
//
//        // Wait for the confirmation message to appear and verify its text
//        WebElement confirmationText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.hero-primary")));
//
//        Assert.assertEquals("THANKYOU FOR THE ORDER.", confirmationText.getText().trim());
//
//        // Extract the label history (the order ID in this case)
//        WebElement orderIDLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label.ng-star-inserted")));
//        String orderID = orderIDLabel.getText().replace("|", "").trim();
//        System.out.println("Order ID: " + orderID);
//
//        // Locate the element that contains the toast message
//        WebElement toastMessageElement = driver.findElement(By.cssSelector("#toast-container .toast-title"));
//
//        // Retrieve the text from the toast message element
//        String toastMessage = toastMessageElement.getText();
//
//        // Use an assertion to check that the text matches "Order Placed Successfully"
//        assertEquals("Order Placed Successfully", toastMessage);
//
//        // Close the driver
//        driver.quit();
//
//    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
