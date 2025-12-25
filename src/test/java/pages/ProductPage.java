package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Random random = new Random();

    // Demoblaze locators (nava is correct DOM id, not a typo)
    private final By productLinks = By.cssSelector(".card-title a");
    private final By addToCartBtn = By.linkText("Add to cart");
    private final By homeLink = By.id("nava");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Adds 1 or 2 random products to cart in a stable way
     */
    public void addRandomProductsToCart() {

        int productsToAdd = random.nextInt(2) + 1;
        System.out.println("🛒 Products to add in this iteration: " + productsToAdd);

        for (int i = 1; i <= productsToAdd; i++) {

            // Always re-fetch fresh elements (prevents stale element issues)
            List<WebElement> products = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(productLinks)
            );

            int index = random.nextInt(products.size());
            WebElement selectedProduct = products.get(index);
            String productName = selectedProduct.getText();

            System.out.println("➡️ Selecting product: " + productName);

            selectedProduct.click();

            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

            try {
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.accept();
            } catch (Exception ignored) {}

            System.out.println("✅ Product added to cart: " + productName);

            wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productLinks));
        }
    }
}
