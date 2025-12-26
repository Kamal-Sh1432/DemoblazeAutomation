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

    private final By productLinks = By.cssSelector(".card-title a");
    private final By addToCartBtn = By.linkText("Add to cart");
    private final By homeLink = By.id("nava");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public int addRandomProductsToCart() {

        int productsToAdd = random.nextInt(3) + 1;
        System.out.println("🛒 Products to add: " + productsToAdd);

        for (int i = 0; i < productsToAdd; i++) {

            // 🔁 ALWAYS re-fetch elements per iteration (CI safe)
            List<WebElement> products = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(productLinks)
            );

            if (products.isEmpty()) {
                throw new RuntimeException("❌ No products visible on home page");
            }

            int index = random.nextInt(products.size());
            products.get(index).click();

            // ✅ Wait & click Add to Cart
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

            // ✅ Handle alert safely
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();

            // ✅ Go back home
            wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();

            // ✅ CRITICAL: ensure home page is fully ready before next loop
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productLinks));

            System.out.println("✅ Product added successfully");
        }

        return productsToAdd;
    }
}
