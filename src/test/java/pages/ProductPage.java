package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;   // ✅ THIS WAS MISSING
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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public int addRandomProductsToCart() {

        int productsToAdd = random.nextInt(3) + 1;
        System.out.println("🛒 Products to add: " + productsToAdd);

        for (int i = 0; i < productsToAdd; i++) {

            boolean added = false;
            int attempts = 0;

            while (!added && attempts < 2) {
                try {
                    attempts++;

                    // 🔑 Fetch product list ONCE
                    List<WebElement> products = wait.until(
                            ExpectedConditions.presenceOfAllElementsLocatedBy(productLinks)
                    );

                    if (products.size() == 0) {
                        throw new RuntimeException("No products found on page");
                    }

                    int index = random.nextInt(products.size());
                    products.get(index).click();

                    // Wait for product page
                    wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

                    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                    alert.accept();

                    wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productLinks));

                    added = true;
                    System.out.println("✅ Product added successfully");

                } catch (org.openqa.selenium.StaleElementReferenceException |
                         IndexOutOfBoundsException e) {

                    System.out.println("⚠️ DOM changed, retrying once...");
                }
            }

            if (!added) {
                throw new RuntimeException("❌ Failed to add product after retry");
            }
        }

        return productsToAdd;
    }

}
