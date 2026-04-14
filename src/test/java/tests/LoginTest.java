package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Login;

import java.time.Duration;

/**
 * Login Test Suite
 * Covers: valid login, and post-login navigation.
 * Uses EdgeDriver — no System.setProperty needed (Selenium 4 auto-detects).
 */
public class LoginTest {

    private WebDriver driver;
    private Login loginPage;

    // ── Setup & Teardown ──────────────────────────────────────────────────

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.setAcceptInsecureCerts(true);
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        // Start from home so the navbar login button is accessible
        driver.get("https://demo.getmarketspace.com/");
        loginPage = new Login(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ── Test Cases ────────────────────────────────────────────────────────

    @Test(priority = 1,
          description = "Verify that a user can log in with valid credentials and is redirected to the home page")
    public void testLoginWithValidCredentials() throws InterruptedException {
        loginPage.testLoginWithValidCredentials("moaazDepi@testing.com", "mo012345");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe("https://demo.getmarketspace.com/"));

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://demo.getmarketspace.com/",
                "After successful login the user should be redirected to the home page"
        );
    }

    @Test(priority = 2,
          description = "Verify that a logged-in user can navigate to the cart page")
    public void testNavigateToCartAfterLogin() {
        driver.get("https://demo.getmarketspace.com/cart");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/cart"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/cart"),
                "A logged-in user should be able to navigate to the cart page"
        );
    }
}