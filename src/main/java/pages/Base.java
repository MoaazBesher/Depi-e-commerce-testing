package pages;

import org.openqa.selenium.WebDriver;

/**
 * Base class for page objects that need a shared driver reference.
 * Provides a simple constructor injection pattern.
 */
public class Base {

    protected WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }
}