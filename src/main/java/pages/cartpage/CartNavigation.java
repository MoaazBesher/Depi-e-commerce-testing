package pages.cartpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartNavigation {
    private WebDriver driver;

    public CartNavigation(WebDriver driver) {
        this.driver = driver;
    }

    public void testNavigation() {
        System.out.println("\n⏳Starting Nav bar test...");
        testNavigationButton(By.className("label-support"), "1.support Btn");
        testNavigationButton(By.className("fa-shopping-cart"), "2.cart Btn");
        testNavigationButton(By.className("label-login"), "3.login Btn");
        testNavigationButton(By.className("signup-item"), "4.Signup Btn");
        System.out.println("✅Nav bar completed successfully\n");
    }

    private void testNavigationButton(By locator, String buttonName) {
        WebElement button = driver.findElement(locator);
        button.click();
        System.out.println(buttonName + ":");
        System.out.println("\t🔔Went to page: " + driver.getCurrentUrl());
        driver.navigate().back();
        System.out.println("\n⏪Back to: " + driver.getCurrentUrl() + "\n");
    }
}