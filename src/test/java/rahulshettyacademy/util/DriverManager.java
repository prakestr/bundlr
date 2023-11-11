package rahulshettyacademy.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static WebDriver driver;

    private DriverManager() { }

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            // Set Chrome to run headlessly
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.setBinary("/path/to/chrome"); // Replace with the path to the Chrome binary
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);

            // If you wish to switch back to GUI mode, comment out the above and uncomment below
            // driver = new ChromeDriver();
            // driver.manage().window().maximize();
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
        if (driver != null) {
            driver.quit();
        }
        driver = null; // Set it to null so that getDriver() will reinitialize it
    }
}
