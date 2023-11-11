package rahulshettyacademy.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.testng.Assert;
import rahulshettyacademy.pageobjects.*;
import rahulshettyacademy.util.DriverManager;

public class ProductSteps {

    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OrdersPage ordersPage;

    @Before
    public void setUp() {
        DriverManager.refreshDriver(); // This will ensure you have a fresh driver for each scenario

        productPage = new ProductPage(DriverManager.getDriver());
        cartPage = new CartPage(DriverManager.getDriver());
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        ordersPage = new OrdersPage(DriverManager.getDriver());

        productPage.goTo();
    }

    public ProductSteps() {
        // Constructor body can be left empty since initialization is now done in the setUp method
    }

    @Given("^I launch the application with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void i_launch_application(String email, String password) {
        productPage.launchApplication(email, password);
    }

    @Then("^I should be logged in$")
    public void i_should_be_logged_in() {
        Assert.assertTrue(productPage.isUserLoggedIn(), "User is not logged in!");
    }

    @And("^I add the product \"([^\"]*)\" to the cart$")
    public void i_add_the_product_to_the_cart(String productName) {
        productPage.addProductToCartByName(productName);
    }

    @Then("^I should see a toast message \"([^\"]*)\"$")
    public void i_should_see_a_toast_message(String expectedToast) {
        String actualToast = productPage.getToastMessage();
        Assert.assertEquals(actualToast, expectedToast);
    }

    @When("^I navigate to the cart$")
    public void i_navigate_to_the_cart() {
        cartPage.navigateToCart();
    }

    @Then("^I should see the product \"([^\"]*)\" in the cart$")
    public void i_should_see_the_product_in_the_cart(String productName) {
        boolean isProductPresent = cartPage.isProductPresentInCart(productName);
        Assert.assertTrue(isProductPresent, productName + " not found in the cart.");
    }

    @When("^I proceed to checkout$")
    public void i_proceed_to_checkout() {
        cartPage.clickCheckoutButton();
    }

    @And("^I select the country \"([^\"]*)\"$")
    public void i_select_country(String country) {
        checkoutPage.selectCountry(country);
    }

    @Then("^I should see a confirmation message \"([^\"]*)\"$")
    public void i_should_see_a_confirmation_message(String expectedConfirmation) {
        String actualConfirmation = checkoutPage.confirmOrder();
        Assert.assertEquals(actualConfirmation, expectedConfirmation);
    }

    @And("^I click on the Orders button$")
    public void i_click_orders_button() {
        ordersPage.clickOrdersButton();
    }

    @Then("^I should see the product \"([^\"]*)\" in the orders$")
    public void i_should_see_product_in_orders(String productName) {
        boolean isProductNamePresent = ordersPage.isProductNamePresent(productName);
        Assert.assertTrue(isProductNamePresent, productName + " not found in the orders.");
    }

    @After
    public void tearDown() {
        if(productPage.isUserLoggedIn()) {
            productPage.logout();
        }
        DriverManager.quitDriver();
    }

}

