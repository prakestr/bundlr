package rahulshettyacademy.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class DriverManager {

    private static WebDriver driver;
    private static final String SELENIUM_HUB_URL = "http://localhost:4444/wd/hub"; // Pointing to the standalone server

    private DriverManager() { }

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            try {
                URL hubUrl = URI.create(SELENIUM_HUB_URL).toURL();
                driver = new RemoteWebDriver(hubUrl, capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException("The URL provided for the Selenium server is malformed", e);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void refreshDriver() {
        quitDriver();
        getDriver();
    }
}
