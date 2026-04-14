package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Navigation {
    private WebDriver driver;

    public Navigation(WebDriver driver) {
        this.driver = driver;
    }

    public void testSocialLoginNavigation() throws InterruptedException {
        WebElement loginButton = driver.findElement(By.className("label-login"));
        loginButton.click();

        WebElement fbButton = driver.findElement(By.className("facebook-text"));
        fbButton.click();

        driver.navigate().back();
    }

    public void testBasicNavigation() throws InterruptedException {
        // Implementation for basic navigation
    }
}