package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * Base class for simple home-page tests (TestCart, TestProduct, TestSearch).
 * Opens a fresh Edge browser before each test method and closes it after.
 */
public class TestBase {

    public static WebDriver driver;

    @BeforeMethod
    public void OpenBrowser() {
        driver = new EdgeDriver();
        driver.get("https://demo.getmarketspace.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void CloseBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
