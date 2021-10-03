package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    WebDriver driver;

    @Test(description = "check that user can login", retryAnalyzer = Retry.class)
    public void validLogin() {
        //ОТКРЫТЬ
        loginPage.open();
        //ЗАЛОГИНИТЬСЯ
        loginPage.login("standard_user", "secret_sauce");
        //ПРОВЕРКА, ПРОДУКТОВ
        String title = productsPage.getHeader();
        assertEquals(title, "PRODUCTS", "Wrong page is opened");
    }

    @Test(description = "locked user can't login", retryAnalyzer = Retry.class)
    public void lockedUser() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertEquals(loginPage.getError(), "Epic sadface: Sorry, this user has been locked out.",
                "Wrong error message");
    }

}
