package rahulshettyacademy.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import rahulshettyacademy.tests.ECommerceTest;

import java.io.File;
import java.io.IOException;

public class CustomListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            takeScreenshot(driver, result.getName());
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    public void takeScreenshot(WebDriver driver, String testName) {
        File screenshotDirectory = new File("./screenshots");
        if (!screenshotDirectory.exists()) {
            boolean dirCreated = screenshotDirectory.mkdir();
            if (!dirCreated) {
                System.err.println("Failed to create screenshots directory");
                return;  // Exit the method if directory wasn't created
            }
        }

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotDirectory, testName + ".png");
        try {
            FileHandler.copy(srcFile, destFile);
            System.out.println("Screenshot saved at " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }

}
