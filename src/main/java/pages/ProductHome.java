package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductHome extends HomeBase {

    public ProductHome (WebDriver driver) {
        super(driver);
    }

    private By ProductLocator = By.cssSelector("div[class =\"promo-inner\"]");
    private By ChairsProductLocator = By.xpath("//*[@id=\"promotions-block\"]/div/div/div[1]/div/a/div/div/div");
    private By SellersLocator = By.xpath("//*[@id=\"seller-list\"]/div/div/div/ul/li[1]/div/div");
    // private By LatestLocator = By.cssSelector("div[class =\"tile-image-placeholder height-3x4 lazyloaded\"]");
    private By HomeLocator = By.xpath("/html");



    public void returnToHome() {
        ClickButton(HomeLocator);
    }

    public void productClick() {
        ClickButton(ProductLocator);
        returnToHome();
    }

    public void chairsProductClick() {
        ClickButton(ChairsProductLocator);
        returnToHome();
    }

    public void sellersClick() {
        ClickButton(SellersLocator);
        returnToHome();
    }

//    public void latestClick() {
//        ClickButton(LatestLocator);
//        returnToHome();
//    }
}
