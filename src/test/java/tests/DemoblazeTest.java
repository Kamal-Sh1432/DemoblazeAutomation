package tests;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import base.BaseTest;
import org.testng.annotations.Test;
import data.TestData;
public class DemoblazeTest extends BaseTest {

    @Test(
            dataProvider = "purchaseData",
            dataProviderClass = TestData.class
    )
    public void endToEndPurchaseTest(
            String username,
            String password,
            String name,
            String country,
            String city,
            String card,
            String month,
            String year) {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Login
        homePage.clickLogin();
        loginPage.login(username, password);

        // Random category + random products
        homePage.selectRandomCategory();
        productPage.addRandomProductsToCart();

        // Cart & purchase
        cartPage.goToCart();
        cartPage.placeOrder(name, country, city, card, month, year);

        // Logout (JS based – stable)
        homePage.logout();
    }

}