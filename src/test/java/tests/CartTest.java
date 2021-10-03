package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @Test(description = "check that user can buy", retryAnalyzer = Retry.class)
    public void buyProduct() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        cartPage.open();
    }

    @Test(description = "check product's price in cart", retryAnalyzer = Retry.class)
    public void checkPriceInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        String productPriceOnProductPage = productsPage.getPrice("Sauce Labs Backpack");
        cartPage.open();
        String productPriceOnCartPage = cartPage.getPrice("Sauce Labs Backpack");
        assertEquals(productPriceOnCartPage, productPriceOnProductPage, "This price is incorrect");
    }

    @Test(description = "check that user can add two items in cart", retryAnalyzer = Retry.class)
    public void addTwoItemsInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        cartPage.open();
        assertTrue(cartPage.isProductDisplayed("Sauce Labs Bolt T-Shirt"));
        assertTrue(cartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
    }

    @Test(description = "check that user can make an order", retryAnalyzer = Retry.class)
    public void makeAnOrder() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        cartPage.open();
        checkoutPage.open();
        checkoutPage.confirmOrder("Ver", "K", "12345");
        assertEquals(checkoutPage.getSuccessfulOrder(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Your order is failed");
    }

    @Test(description = "check that problem user can't delete from cart", retryAnalyzer = Retry.class)
    public void problemUserDeletesFromCart() {
        loginPage.open();
        loginPage.login("problem_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.removeFromCart("Sauce Labs Backpack");
        cartPage.open();
        assertTrue(cartPage.isProductDisplayed("Sauce Labs Backpack"),
                "Problem user can delete an item from cart");
    }

    @Test(description = "check item description in cart", retryAnalyzer = Retry.class)
    public void checkItemDescriptionInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        String productOnProductPageDescription = productsPage.getProductDescription("Sauce Labs Backpack");
        cartPage.open();
        String productInCartDescription = cartPage.getProductDescription("Sauce Labs Backpack");
        assertEquals(productInCartDescription, productOnProductPageDescription, "The description is wrong");
    }

}
