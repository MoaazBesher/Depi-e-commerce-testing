package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for the Billing/Shipping Address step in checkout.
 */
public class AddressPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ── Locators ──────────────────────────────────────────────────────────

    private final By streetField          = By.id("billingStreet1");
    private final By cityField            = By.id("billingCity");
    private final By zipField             = By.id("billingPostal");
    private final By countryDropdown      = By.id("billingCountry");
    private final By provinceInputField   = By.id("billingProvinceInput");
    private final By provinceSelectField  = By.id("billingProvinceSelect");
    private final By sameAddressCheckbox  = By.id("useSameAddresses");

    // ── Actions ───────────────────────────────────────────────────────────

    public void enterAddress(String street, String city, String zip,
                             String country, String province) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(streetField)).clear();
        driver.findElement(streetField).sendKeys(street);

        driver.findElement(cityField).clear();
        driver.findElement(cityField).sendKeys(city);

        driver.findElement(zipField).clear();
        driver.findElement(zipField).sendKeys(zip);

        // Select country from dropdown
        WebElement countryEl = wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
        new Select(countryEl).selectByVisibleText(country);

        // Province: try dropdown first, fall back to free-text input
        try {
            WebElement provinceSelect = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(provinceSelectField));
            new Select(provinceSelect).selectByVisibleText(province);
            System.out.println("Selected province from dropdown: " + province);
        } catch (TimeoutException e) {
            WebElement provinceInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(provinceInputField));
            provinceInput.clear();
            provinceInput.sendKeys(province);
            System.out.println("Entered province manually: " + province);
        }
    }

    // ── Same-address checkbox ─────────────────────────────────────────────

    public boolean isSameAddressSelected() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(sameAddressCheckbox))
                   .isSelected();
    }

    public void toggleSameAddress(boolean shouldBeChecked) {
        WebElement cb = wait.until(ExpectedConditions.elementToBeClickable(sameAddressCheckbox));

        boolean needsChange = (shouldBeChecked && !cb.isSelected())
                           || (!shouldBeChecked && cb.isSelected());
        if (needsChange) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", cb);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cb);
            wait.until(d -> cb.isSelected() == shouldBeChecked);
        }
    }
}
