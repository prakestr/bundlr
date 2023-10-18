package rahulshettyacademy.util;

import org.openqa.selenium.WebDriver;

public class TestContext {

    private WebDriver driver;

    public TestContext() {
        this.driver = DriverManager.getDriver();
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void refreshDriver() {
        DriverManager.refreshDriver();
        this.driver = DriverManager.getDriver();
    }
}

