package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;
    Random random = new Random();
    Set<String> selectedProducts = new HashSet<>();

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By productLinks = By.cssSelector(".card-title a");
    By addToCartBtn = By.linkText("Add to cart");
    By homeLink = By.id("nava");

    // ✅ Truly random: 1, 2 or 3 products per iteration
        public int addRandomProductsToCart() {

        int productsToAdd = random.nextInt(3) + 1;

        for (int i = 0; i < productsToAdd; i++) {

            List<WebElement> products =
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productLinks));

            WebElement chosen;
            String name;
            int guard = 0;

            do {
                chosen = products.get(random.nextInt(products.size()));
                name = chosen.getText();
                guard++;
            } while (selectedProducts.contains(name) && guard < 5);

            selectedProducts.add(name);
            chosen.click();

            // ✅ INSERT HERE
            System.out.println("Product added: " + name);

            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

            try {
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.accept();
            } catch (Exception ignored) {}

            wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
        }

        return productsToAdd;} // 🔴 THIS WAS MISSING
    }