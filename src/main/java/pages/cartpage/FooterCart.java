package pages.cartpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterCart {
    private WebDriver driver;

    public FooterCart(WebDriver driver) {
        this.driver = driver;
    }

    public void testFooter() {
        System.out.println("\n⏳Starting Footer test...");
        testFooterLink(By.xpath("//*[@id=\"footer\"]/div/div/footer/div/div[2]/ul/li[2]"), "1.login Btn");
        testFooterLink(By.xpath("//*[@id=\"footer\"]/div/div/footer/div/div[2]/ul/li[3]/a"), "2.Signup Btn");
        testFooterLink(By.xpath("//*[@id=\"footer\"]/div/div/footer/div/div[1]/ul/li[2]/a"), "3.Search Btn");
        testFooterLink(By.xpath("//*[@id=\"footer\"]/div/div/footer/div/div[1]/ul/li[3]/a"), "4.Popular Btn");
        testFooterLink(By.xpath("//*[@id=\"footer\"]/div/div/footer/div/div[1]/ul/li[4]/a"), "5.Latest Btn");
        testFooterLink(By.xpath("//*[@id=\"footer\"]/div/div/footer/div/div[1]/ul/li[5]/a"), "6.Sellers Btn");
    }

    private void testFooterLink(By locator, String linkName) {
        System.out.println("\n" + linkName + ":");
        WebElement link = driver.findElement(locator);
        link.click();
        System.out.println("\t🔔Went to page: " + driver.getCurrentUrl());
        driver.navigate().back();
        System.out.println("\n⏪Back to: " + driver.getCurrentUrl() + "\n");
    }
}