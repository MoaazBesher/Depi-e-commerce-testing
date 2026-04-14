package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUp {
    private WebDriver driver;

    public SignUp(WebDriver driver) {
        this.driver = driver;
    }

    public void testSignUpFlow() throws InterruptedException {
        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='/account/create']"));
        signUpButton.click();

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("test@example.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Test@1234");

        WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
        confirmPasswordField.sendKeys("Test@1234");

        WebElement createAccountButton = driver.findElement(By.xpath("//button[contains(text(),'Create account')]"));
        createAccountButton.click();

        Thread.sleep(2000);
    }
}