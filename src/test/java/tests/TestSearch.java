package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchHome;

public class TestSearch extends TestBase {
    SearchHome Home;

     @BeforeMethod
    public void setHome()
     {
        Home = new SearchHome(driver);
    }

     @Test
    public void TestsHomeWithValidData() throws InterruptedException {
        Home.FillSearch("chair");
        Thread.sleep(2000);
        Home.FillCategory("Chairs");
        Thread.sleep(2000);
        Home.FillLocation("  ");
        Home.GoClick();
        Thread.sleep(5000);
    }

}
