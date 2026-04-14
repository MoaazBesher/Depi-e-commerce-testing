package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for the Payment step in the checkout flow.
 * Handles Stripe iFrame switching for card number, expiry, and CVC fields.
 */
public class PaymentPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ── Public API ────────────────────────────────────────────────────────

    public void enterCardHolderName(String name) {
        WebElement nameField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("paymentName")));
        nameField.sendKeys(name);
    }

    public void enterCardNumber(String cardNumber) {
        switchToFrameAndType(
                By.cssSelector("iframe[src*='componentName=cardNumber']"),
                By.name("cardnumber"),
                cardNumber
        );
    }

    public void enterCardExpiry(String expiryDate) {
        switchToFrameAndType(
                By.cssSelector("iframe[src*='componentName=cardExpiry']"),
                By.name("exp-date"),
                expiryDate
        );
    }

    public void enterCardCVC(String cvc) {
        switchToFrameAndType(
                By.cssSelector("iframe[src*='componentName=cardCvc']"),
                By.name("cvc"),
                cvc
        );
    }

    /** Convenience method — fills all four card fields in sequence. */
    public void enterAllCardDetails(String name, String number, String expiry, String cvc) {
        enterCardHolderName(name);
        enterCardNumber(number);
        enterCardExpiry(expiry);
        enterCardCVC(cvc);
    }

    // ── Private helpers ───────────────────────────────────────────────────

    private void switchToFrameAndType(By frameLocator, By inputLocator, String value) {
        WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(frameLocator));
        driver.switchTo().frame(frame);
        wait.until(ExpectedConditions.presenceOfElementLocated(inputLocator)).sendKeys(value);
        driver.switchTo().defaultContent();
    }
}
