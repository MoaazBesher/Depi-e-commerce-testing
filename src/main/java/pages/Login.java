package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {
    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void testLoginWithValidCredentials(String email, String password) throws InterruptedException {
        WebElement loginButton = driver.findElement(By.className("label-login"));
        loginButton.click();

        WebElement emailField = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement submitButton = driver.findElement(By.className("btn-login"));
        submitButton.click();

        Thread.sleep(2000);
    }

    public void testLoginWithInvalidCredentials() throws InterruptedException {
        // Implementation for invalid credentials
    }

    public void testForgotPasswordFlow() throws InterruptedException {
        // Implementation for forgot password
    }
}