package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/";
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().
                        equals("complete");
            }
        };
    }

    public boolean isVisible(By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean isFound = driver.findElements(locator).size() > 0;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return isFound;
    }

    public boolean isVisible2(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    public boolean isVisible3(By locator) {
        boolean found;
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException exception) {
            found = false;
        }
        return found;
    }

}