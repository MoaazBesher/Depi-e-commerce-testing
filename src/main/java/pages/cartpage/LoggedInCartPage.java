package pages.cartpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoggedInCartPage {
    private WebDriver driver;

    public LoggedInCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void testLoggedInCart() throws InterruptedException {
        System.out.println("\n⏳Starting logged in cart test...");
        WebElement accountName = driver.findElement(By.className("user-display-name"));
        System.out.println("1.Account on header");

        testAccountOption(accountName, By.linkText("My Account"), "My Account Page");
        testAccountOption(accountName, By.linkText("Messages"), "Messages Page");
        testAccountOption(accountName, By.linkText("My Purchases"), "My Purchases Page");
        testAccountOption(accountName, By.linkText("Log Out"), "Log Out");

        System.out.println("🔒Logged out successfully!");
        Thread.sleep(5000);
    }

    private void testAccountOption(WebElement accountName,
                                   By optionLocator, String optionName) {
        accountName.click();
        System.out.println("\n\t" + optionName);
        WebElement option = driver.findElement(optionLocator);
        option.click();
        System.out.println("\t🔔Went to page: " + driver.getCurrentUrl());
        driver.navigate().back();
    }
}