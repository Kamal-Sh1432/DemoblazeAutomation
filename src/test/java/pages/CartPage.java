package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By cartLink = By.id("cartur");
    By placeOrderBtn = By.xpath("//button[text()='Place Order']");

    // Order form fields
    By name = By.id("name");
    By country = By.id("country");
    By city = By.id("city");
    By card = By.id("card");
    By month = By.id("month");
    By year = By.id("year");
    By purchaseBtn = By.xpath("//button[text()='Purchase']");
    By confirmOkBtn = By.xpath("//button[text()='OK']");

    public void goToCart() throws InterruptedException {
        driver.findElement(cartLink).click();
        Thread.sleep(3000);
    }

    public void placeOrder(
            String customerName,
            String countryName,
            String cityName,
            String cardNumber,
            String monthValue,
            String yearValue) throws InterruptedException {

        driver.findElement(placeOrderBtn).click();
        Thread.sleep(2000);

        driver.findElement(name).sendKeys(customerName);
        driver.findElement(country).sendKeys(countryName);
        driver.findElement(city).sendKeys(cityName);
        driver.findElement(card).sendKeys(cardNumber);
        driver.findElement(month).sendKeys(monthValue);
        driver.findElement(year).sendKeys(yearValue);

        driver.findElement(purchaseBtn).click();
        Thread.sleep(2000);

        driver.findElement(confirmOkBtn).click();
    }

}
