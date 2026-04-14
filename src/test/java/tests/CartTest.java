package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.cartpage.*;
import utils.ConfigReader;

import java.time.Duration;

/**
 * Cart Test Suite
 * Covers: language switching, navigation, footer links, empty-cart state,
 * login flow, logged-in cart interactions, add products, and cart operations.
 */
public class CartTest {

    private WebDriver driver;

    // ── Page Objects ──────────────────────────────────────────────────────
    private CartNavigation  cartNav;
    private CartOperations  cartOps;
    private CartProduct     cartProd;
    private EmptyCart       emptyCart;
    private FooterCart      footer;
    private LanguageCart    lang;
    private LoggedInCartPage loggedInCart;
    private LoginCart       login;

    // ── Setup & Teardown ──────────────────────────────────────────────────

    @BeforeClass
    public void setup() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.getmarketspace.com/cart");

        cartNav      = new CartNavigation(driver);
        cartOps      = new CartOperations(driver);
        cartProd     = new CartProduct(driver);
        emptyCart    = new EmptyCart(driver);
        footer       = new FooterCart(driver);
        lang         = new LanguageCart(driver);
        loggedInCart = new LoggedInCartPage(driver);
        login        = new LoginCart(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ── Test Cases ────────────────────────────────────────────────────────

    @Test(priority = 1,
          description = "Verify the language switcher toggles site language correctly")
    public void testLanguageSwitching() throws InterruptedException {
        lang.testLanguages();
    }

    @Test(priority = 2,
          description = "Verify all main navigation buttons are clickable and functional")
    public void testNavigationButtons() {
        cartNav.testNavigation();
    }

    @Test(priority = 3,
          description = "Verify footer links are present and navigatable")
    public void testFooterLinks() {
        footer.testFooter();
    }

    @Test(priority = 4,
          description = "Verify the empty-cart state is displayed correctly for a guest user")
    public void testEmptyCartFunctionality() {
        emptyCart.testEmptyCart();
    }

    @Test(priority = 5,
          description = "Verify a user can log in from the cart page")
    public void testLoginFunctionality() throws InterruptedException {
        // Credentials come from config.properties — no hardcoded values
        login.login(ConfigReader.getEmail(), ConfigReader.getPassword());
    }

    @Test(priority = 6,
          description = "Verify the cart page displays correctly for a logged-in user")
    public void testLoggedInCartFunctionality() throws InterruptedException {
        loggedInCart.testLoggedInCart();
    }

    @Test(priority = 7,
          description = "Verify products can be added to the cart")
    public void testAddProducts() throws InterruptedException {
        cartProd.addProductsToCart();
    }

    @Test(priority = 8,
          description = "Verify quantity changes and removal work correctly in the cart")
    public void testCartOperations() throws InterruptedException {
        cartOps.testCartOperations();
    }
}