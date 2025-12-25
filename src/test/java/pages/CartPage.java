package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public void placeOrder(
            String customerName,
            String countryName,
            String cityName,
            String cardNumber,
            String monthValue,
            String yearValue) {

        // Click Place Order
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();

        // Fill order form
        wait.until(ExpectedConditions.visibilityOfElementLocated(name))
                .sendKeys(customerName);
        driver.findElement(country).sendKeys(countryName);
        driver.findElement(city).sendKeys(cityName);
        driver.findElement(card).sendKeys(cardNumber);
        driver.findElement(month).sendKeys(monthValue);
        driver.findElement(year).sendKeys(yearValue);

        // Click Purchase
        driver.findElement(purchaseBtn).click();

        // Wait for confirmation popup (SweetAlert) and click OK
        wait.until(ExpectedConditions.elementToBeClickable(confirmOkBtn)).click();

        // 🔑 MOST IMPORTANT LINE:
        // Wait until confirmation popup is completely gone
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".sweet-alert")
        ));

        // 🔑 Ensure navbar is usable again
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("logout2")
        ));
    }

}
