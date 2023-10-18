
package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrdersPage extends BasePage {

    @FindBy(css = ".cartWrap")
    private WebElement cartWrap;

    @FindBy(css = ".table tbody tr td:nth-child(3)")
    private List<WebElement> yourOrdersName;

    @FindBy(css = ".table tbody tr td:nth-child(3)")
    private WebElement firstElementInNameColumn;


    public OrdersPage(WebDriver driver) {
        super(driver);
    }


    public boolean isProductNamePresent(String productName) {
        wait.until(ExpectedConditions.visibilityOf(firstElementInNameColumn));
        return yourOrdersName.stream()
                .anyMatch(item -> item.getText().equalsIgnoreCase(productName));
    }
}
