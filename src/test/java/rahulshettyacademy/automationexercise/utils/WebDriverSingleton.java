package rahulshettyacademy.automationexercise.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverSingleton {
    private static WebDriver driver;
    private static WebDriverWait wait;

    private WebDriverSingleton() {
        // Prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return driver;
    }

    public static WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null; // Ensure wait is also set to null
        }
    }
}
