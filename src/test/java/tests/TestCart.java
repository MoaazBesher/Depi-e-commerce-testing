package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartHome;

public class TestCart extends TestBase {
    CartHome Home;

    @BeforeMethod

    public void setHome() {
        Home = new CartHome(driver);
    }

    @Test
    public void TestsHomeWithValidData() throws InterruptedException {

        Home.clickCart();
        Thread.sleep(1000);

        Home.clickSupport();
        Thread.sleep(1000);

        Home.clickLogin();
        Thread.sleep(1000);

        driver.navigate().back();
        Thread.sleep(1000);

        Home.clickSignUp();
        driver.navigate().back();
        Thread.sleep(1000);
        driver.navigate().back();

        driver.navigate().back();
        Thread.sleep(3000);
    }

}
