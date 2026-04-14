package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

/**
 * End-to-End Checkout Flow Test
 * Covers: add to cart -> personal info -> address -> payment -> place order.
 * Uses EdgeDriver — no System.setProperty needed (Selenium 4 auto-detects).
 */
public class CheckoutTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // ── Setup & Teardown ──────────────────────────────────────────────────

    @BeforeMethod
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.setAcceptInsecureCerts(true);
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.navigate().to("https://demo.getmarketspace.com/cart");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ── Test Cases ────────────────────────────────────────────────────────

    @Test(description = "Verify the complete checkout flow from cart to order confirmation")
    public void testFullCheckoutFlow() throws InterruptedException {

        // ── Step 1: Add product and go to cart ──
        CartPage cartPage = new CartPage(driver);
        cartPage.addProductsToCart();
        Thread.sleep(2000);
        cartPage.ClickCheckout();
        Thread.sleep(10000);

        // ── Step 2: Personal Info page ──
        personalInfoPage personalInfo = new personalInfoPage(driver);
        personalInfo.enterPersonalInfo("amina allam", "ali@example.com", "ali@example.com");

        // ── Step 3: Address page ──
        AddressPage addressPage = new AddressPage(driver);
        addressPage.enterAddress("123 Main St", "Cairo", "12345", "Egypt", "Nas City");

        WebElement dropdown = driver.findElement(By.id("billingCountry"));
        Select select = new Select(dropdown);
        select.getFirstSelectedOption().getText();

        addressPage.toggleSameAddress(true);
        Assert.assertTrue(
                addressPage.isSameAddressSelected(),
                "Same address checkbox should be checked after toggle"
        );

        // ── Step 4: Payment page ──
        PaymentPage paymentPage = new PaymentPage(driver);
        addressPage.toggleSameAddress(true);
        Assert.assertTrue(
                addressPage.isSameAddressSelected(),
                "Same address checkbox should still be checked"
        );
        paymentPage.enterAllCardDetails("Ali Hassan", "4242 4242 4242 4242", "12/26", "123");
        driver.switchTo().defaultContent();

        // ── Step 5: Order Summary ──
        OrderSummaryPage summary = new OrderSummaryPage(driver);
        summary.selectShippingOptionButton();
        summary.writeNoteToSeller("Please deliver quickly.");
        summary.acceptTermsAndConditions();

        Assert.assertTrue(
                summary.isPlaceOrderButtonEnabled(),
                "Place Order button should be enabled after all required fields are filled"
        );

        summary.placeOrder();

        WebDriverWait orderWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        orderWait.until(ExpectedConditions.urlContains("/checkout/success/"));
    }
}
