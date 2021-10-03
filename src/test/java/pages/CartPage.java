package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    public static final By CHECKOUT_BUTTON = By.id("checkout");
    public static final String BASE_PRODUCTINCART_LOCATOR = "//*[contains(text(),'%s')]//ancestor::*[contains(@class, " +
            "'cart_item_label')]//descendant::*[contains(@class, ";
    String productPriceLocator = BASE_PRODUCTINCART_LOCATOR + "'inventory_item_price')]";
    String productDescriptionLocator = BASE_PRODUCTINCART_LOCATOR + "'inventory_item_desc')]";
    String productDisplay = "//*[contains(@class, 'inventory_item_name')]//self::*[contains(text(), '%s')]";


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "cart.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
    }

    public boolean isProductDisplayed(String product) {
        return driver.findElement(By.xpath(String.format(productDisplay, product))).isDisplayed();
    }

    public String getPrice(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(productPriceLocator, product))));
        return driver.findElement(By.xpath(String.format(productPriceLocator, product))).getText();
    }

    public String getProductDescription(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(productDescriptionLocator, product))));
        return driver.findElement(By.xpath(String.format(productDescriptionLocator, product))).getText();
    }

}
