package tests;

import base.BaseTest;
import data.TestData;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;


public class DemoblazeTest extends BaseTest {

    @Test(dataProvider = "purchaseData", dataProviderClass = TestData.class)
    public void endToEndPurchaseTest(
            String username,
            String password,
            String customerName,
            String country,
            String city,
            String card,
            String month,
            String year
    ) throws InterruptedException {

        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        ProductPage product = new ProductPage(driver);
        CartPage cart = new CartPage(driver);

        // Login
        home.clickLogin();
        login.login(username, password);
        Thread.sleep(3000);

        // 🔹 Random category & random multiple products

        String category = home.selectRandomCategoryWithName();
        test.info("Selected random category: " + category);

        int count = product.addRandomProductsToCart();
        test.info("Added " + count + " product(s) to cart");

        // Cart & Purchase
        cart.goToCart();
        cart.placeOrder(customerName, country, city, card, month, year);

        // Logout
        home.logout();
    }
}

