package rahulshettyacademy.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import rahulshettyacademy.data.JsonUtil;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.util.DriverManager;
import rahulshettyacademy.pageobjects.ProductPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.CartPage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ECommerceTest {

    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OrdersPage ordersPage;


    @BeforeMethod
    public void setup() throws IOException {
        // Initialize Page Objects
        WebDriver driver = DriverManager.getDriver();
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        ordersPage =  new OrdersPage(driver);
        HashMap<String, String> defaultLogin = (HashMap<String, String>) productDataProvider()[0][0];
        productPage.goTo();
        productPage.launchApplication(defaultLogin.get("email"), defaultLogin.get("password"));
        Assert.assertTrue(productPage.isUserLoggedIn(), "User is not logged in!");
    }


    @Test(dataProvider = "productData", groups = { "Purchase" })
    public void testAddProductToCart(HashMap<String, String> data) {
        productPage.addProductToCartByName(data.get("productName"));
        String toastMsg = productPage.getToastMessage();
        Assert.assertEquals(toastMsg, "Product Added To Cart", "Unexpected toast message!");

        cartPage.navigateToCart();
        boolean isProductPresent = cartPage.isProductPresentInCart(data.get("productName"));
        Assert.assertTrue(isProductPresent, "'" + data.get("productName") + "' not found in the cart.");
        cartPage.clickCheckoutButton();

        checkoutPage.selectCountry(data.get("country"));
        String confirmationMessage = checkoutPage.confirmOrder();
        Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.", "Unexpected confirmation message!");
        String toastMessage = productPage.getToastMessage();
        Assert.assertEquals(toastMessage, "Order Placed Successfully", "Unexpected toast message!");

        ordersPage.clickOrdersButton();
        boolean isProductNamePresent = ordersPage.isProductNamePresent(data.get("productName"));
        Assert.assertTrue(isProductNamePresent, "'Products' not found in the orders.");
    }

//    @Test(dependsOnMethods = {"testAddProductToCart"}, dataProvider = "productData")
//    public void testProductNameInOrders(HashMap<String, String> data) {
//        ordersPage.clickOrdersButton();
//        boolean isProductNamePresent = ordersPage.isProductNamePresent(data.get("productName"));
//        Assert.assertTrue(isProductNamePresent, "'Products' not found in the orders.");
//    }

    @DataProvider(name = "productData")
    public Object[][] productDataProvider() throws IOException {
        List<HashMap<String, String>> dataList = JsonUtil.readJsonFileToMap("src/test/java/rahulshettyacademy/data/data.json");
        Object[][] dataArray = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i][0] = dataList.get(i);
        }
        return dataArray;
    }

//    @DataProvider(name = "productData")
//    public Object[][] productDataProvider() {
//        HashMap<String, String> data1 = new HashMap<>();
//        data1.put("email", "phil@hotmail.com");
//        data1.put("password", "Klm9000!");
//        data1.put("productName", "adidas original");
//        data1.put("country", "South Africa");
//
//        HashMap<String, String> data2 = new HashMap<>();
//        data2.put("email", "shonj@hotmail.com");
//        data2.put("password", "Qradar.1");
//        data2.put("productName", "iphone 13 pro");
//        data2.put("country", "United States");
//
//        HashMap<String, String> data3 = new HashMap<>();
//        data3.put("email", "phil@hotmail.com");
//        data3.put("password", "Klm9000!");
//        data3.put("productName", "zara coat 3");
//        data3.put("country", "India");
//
//        return new Object[][]{
//                {data1},
//                {data2},
//                {data3}
//        };
//    }

//    @DataProvider(name = "productData")
//    public Object[][] productDataProvider() {
//        return new Object[][] {
//                {"phil@hotmail.com", "Klm9000!", "adidas original", "South Africa"},
//                {"shonj@hotmail.com", "Qradar.1", "iphone 13 pro", "United States"},
//                {"phil@hotmail.com", "Klm9000!", "zara coat 3", "India"},
//        };
//    }


    @AfterMethod
    public void tearDown() {
        // Close the browser and terminate WebDriver session
        DriverManager.quitDriver();
    }
}
