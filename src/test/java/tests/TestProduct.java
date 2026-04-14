package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductHome;

public class TestProduct extends TestBase {
    private ProductHome Home;


    @BeforeMethod
    public void setHome() {
        Home = new ProductHome(driver);
    }

    @Test
    public void TestsHomeWithValidData() throws InterruptedException {

        Home.productClick();
        driver.navigate().back();
        Thread.sleep(1000);

        Home.chairsProductClick();
        driver.navigate().back();
        Thread.sleep(1000);

        Home.sellersClick();
        driver.navigate().back();
        Thread.sleep(1000);

//        Home.latestClick();
//        driver.navigate().back();

        Home.returnToHome();
        driver.navigate().back();

    }
}
