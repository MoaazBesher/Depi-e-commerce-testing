package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.productPage.Categories;
import pages.productPage.Photo;
import pages.productPage.ProductInfo;
import pages.productPage.ProductSummary;
import pages.productPage.Seller;

import java.time.Duration;
import java.util.List;

public class ProductTest {

    private WebDriver driver;
    private Categories category;
    private Photo photo;
    private ProductInfo productInfo;
    private ProductSummary productSummary;
    private Seller seller;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.setAcceptInsecureCerts(true);
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demo.getmarketspace.com/product/242/rocking-chair");

        category = new Categories(driver);
        photo = new Photo(driver);
        productInfo = new ProductInfo(driver);
        productSummary = new ProductSummary(driver);
        seller = new Seller(driver);
    }

    // Photo Tests
    @Test
    public void verifyProductDetailsDisplayed() {
        Assert.assertTrue(photo.isProductImageDisplayed(), "Product image not displayed!");
    }

    @Test
    public void testImageCount() {
        int count = photo.getImageCount();
        Assert.assertTrue(count >= 2, "Images should be at least 2");
    }

    @Test
    public void testImagesHaveBackgroundImage() {
        List<WebElement> images = photo.getAllImages();
        for (WebElement image : images) {
            Assert.assertTrue(photo.imageHasBackground(image), "Image should have background-image style");
        }
    }

    @Test
    public void testNavigationButtons() throws InterruptedException {
        photo.clickNext();
        Thread.sleep(1000);
        photo.clickPrev();
        Thread.sleep(1000);
    }

    // Categories Tests
    @Test
    public void verifyIsSearchDisplayed() {
        Assert.assertTrue(Categories.isSearchDisplayed(), "Search not displayed!");
    }

    @Test
    public void verifyIsChairsDisplayed() {
        Assert.assertTrue(Categories.isChairDisplayed(), "Chairs not displayed!");
    }

    @Test
    public void testChairsNavigation() {
        category.clickOnChairs("Chairs");
        Assert.assertTrue(category.isChairsPageLoaded("chairs"), "Chairs page not loaded correctly");
        driver.navigate().back();
    }

    @Test
    public void verifyModernDisplayed() {
        Assert.assertTrue(Categories.isModernDisplayed(), "Modern not displayed!");
    }

    @Test
    public void testModernNavigation() {
        category.clickOnModern("Modern");
        Assert.assertTrue(category.isModernPageLoaded("modern"), "Modern page not loaded correctly");
        driver.navigate().back();
    }

    @Test
    public void verifySoftFurnishingsDisplayed() {
        Assert.assertTrue(Categories.isSoftFurnishingsDisplayed(), "Soft Furnishings not displayed!");
    }

    @Test
    public void testSoftFurnishingsNavigation() {
        category.clickOnSoftFurnishings("Soft Furnishings");
        Assert.assertTrue(category.isSoftFurnishingsPageLoaded("soft-furnishings"), "Soft Furnishings page not loaded correctly");
        driver.navigate().back();
    }

    @Test
    public void verifyTablesDisplayed() {
        Assert.assertTrue(Categories.isTablesDisplayed(), "Tables not displayed!");
    }

    @Test
    public void testTablesNavigation() {
        category.clickOnTables("Tables");
        Assert.assertTrue(category.isTablesPageLoaded("tables"), "Tables page not loaded correctly");
        driver.navigate().back();
    }

    @Test
    public void verifyWoodDisplayed() {
        Assert.assertTrue(Categories.isWoodDisplayed(), "Wood not displayed!");
    }

    @Test
    public void testWoodNavigation() {
        category.clickOnWood("Wood");
        Assert.assertTrue(category.isWoodPageLoaded("wood"), "Wood page not loaded correctly");
        driver.navigate().back();
    }

    // ProductInfo Tests
    @Test
    public void verifyProductNameDisplayed() {
        Assert.assertTrue(ProductInfo.isNameDisplayed(), "Product name not displayed!");
    }

    @Test
    public void verifySocialCountDisplayed() {
        Assert.assertTrue(ProductInfo.isSocialCountDisplayed(), "Social count not displayed!");
    }

    @Test
    public void verifyRatingBoxDisplayed() {
        Assert.assertTrue(ProductInfo.isRatingBoxDisplayed(), "Rating box not displayed!");
    }

    @Test
    public void verifyProductTagListDisplayed() {
        Assert.assertTrue(ProductInfo.isProductTagListDisplayed(), "Product tag list not displayed!");
    }

    @Test
    public void testProductTagNavigation() {
        productInfo.clickOnProductTagList("Chair");
        Assert.assertTrue(productInfo.isProductTagPageLoaded("chair"), "Product tag page not loaded correctly");
        driver.navigate().back();
    }

    // ProductSummary Tests
    @Test
    public void verifyPriceDisplayed() {
        String price = productSummary.getPriceText();
        Assert.assertFalse(price.isEmpty(), "Price not displayed!");
    }

    @Test
    public void verifyDescriptionDisplayed() {
        String description = productSummary.getDescriptionText();
        Assert.assertFalse(description.isEmpty(), "Description not displayed!");
    }

    @Test
    public void testQuantityInput() {
        productSummary.setQuantity("3");
        Assert.assertEquals(productSummary.getQuantityValue(), "3", "Quantity input not working correctly");
    }

    @Test
    public void verifyAddToCartEnabled() {
        Assert.assertTrue(productSummary.isAddToCartEnabled(), "Add to cart button not enabled!");
    }

    // Seller Tests
    @Test
    public void verifySellerSectionDisplayed() {
        Assert.assertTrue(seller.isSellerSectionDisplayed(), "Seller section not displayed!");
    }

    @Test
    public void verifyShopNameDisplayed() {
        String shopName = seller.getShopName();
        Assert.assertFalse(shopName.isEmpty(), "Shop name not displayed!");
    }

    @Test
    public void verifyShopImageDisplayed() {
        Assert.assertTrue(seller.isShopImageDisplayed(), "Shop image not displayed!");
    }

    @Test
    public void verifyContactButtonDisplayed() {
        Assert.assertTrue(seller.isContactButtonDisplayed(), "Contact button not displayed!");
    }

    @Test
    public void verifyShopDescriptionDisplayed() {
        String description = seller.getShopDescription();
        Assert.assertFalse(description.isEmpty(), "Shop description not displayed!");
    }

    @Test
    public void verifySellerRatingBoxDisplayed() {
        Assert.assertTrue(Seller.isRatingBoxDisplayed(), "Seller rating box not displayed!");
    }

    @Test
    public void verifyShippingReturnInfoDisplayed() {
        Assert.assertTrue(seller.getShippingReturnInfo(), "Shipping and return info not displayed!");
    }

    @Test
    public void verifyMoreFromSellerSectionVisible() {
        Assert.assertTrue(seller.isSectionVisible(), "More from seller section not visible!");
    }

    @Test
    public void verifyMoreFromSellerProductsCount() {
        int count = seller.getNumberOfProducts();
        Assert.assertTrue(count > 0, "No products found in 'More from this seller' section");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}