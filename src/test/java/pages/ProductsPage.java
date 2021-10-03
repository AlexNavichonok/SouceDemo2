package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage{

    private static final By TITLE = By.cssSelector("[class=title]");
    public static final String BASE_PRODUCTONPRODUCTPAGE_LOCATOR = "//*[contains(text(), '%s')]//ancestor::*[contains" +
            "(@class, 'inventory_item_";
    String productLocator = "//*[contains(text(),'%s')]/ancestor::*[contains(@class,'inventory_item')]//button";
    String productPriceLocator = BASE_PRODUCTONPRODUCTPAGE_LOCATOR + "description')]//descendant::*[contains(@class, " +
            "'inventory_item_price')]";
    String productDescriptionLocator = BASE_PRODUCTONPRODUCTPAGE_LOCATOR + "label')]/child::*[contains(@class, " +
            "'inventory_item_desc')]";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "inventory.html");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(TITLE));
    }

    public String getHeader() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }

    public void addToCart(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(productLocator, product))));
        driver.findElement(By.xpath(String.format(productLocator, product))).click();
    }

    public void removeFromCart(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(productLocator, product))));
        driver.findElement(By.xpath(String.format(productLocator, product))).click();
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
