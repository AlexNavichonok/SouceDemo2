package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage{

    private static final By FIRSTNAME = By.id("first-name");
    private static final By LASTNAME = By.id("last-name");
    private static final By ZIPCODE = By.cssSelector("[data-test='postalCode']");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By FINISH_BUTTON = By.id("finish");
    public static final By CONFIRMATION_MESSAGE = By.className("complete-text");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "checkout-step-one.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_BUTTON));
    }

    public void confirmOrder(String firstName, String lastName, String zipCode) {
        driver.findElement(FIRSTNAME).sendKeys(firstName);
        driver.findElement(LASTNAME).sendKeys(lastName);
        driver.findElement(ZIPCODE).sendKeys(zipCode);
        driver.findElement(CONTINUE_BUTTON).click();
        driver.findElement(FINISH_BUTTON).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(FINISH_BUTTON));
    }

    public String getSuccessfulOrder() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONFIRMATION_MESSAGE));
        return driver.findElement(CONFIRMATION_MESSAGE).getText();
    }

}
