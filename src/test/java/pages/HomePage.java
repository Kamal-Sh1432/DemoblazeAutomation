package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;
    Random random = new Random();

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By login = By.id("login2");
    By logout = By.id("logout2");
    By loginModal = By.id("logInModal");

    By phonesCategory = By.linkText("Phones");
    By laptopsCategory = By.linkText("Laptops");
    By monitorsCategory = By.linkText("Monitors");

    public void clickLogin() {
        driver.findElement(login).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }

    // ✅ Safe random category selection
    public void selectRandomCategory() {

        // Ensure login modal is gone
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginModal));

        By[] categories = {
                phonesCategory,
                laptopsCategory,
                monitorsCategory
        };

        By selected = categories[random.nextInt(categories.length)];

        wait.until(ExpectedConditions.elementToBeClickable(selected)).click();

        // Wait for product list refresh
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".card-title a")
        ));
    }

    public String selectRandomCategoryWithName() {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginModal));

        By[] categories = {phonesCategory, laptopsCategory, monitorsCategory};
        String[] categoryNames = {"Phones", "Laptops", "Monitors"};

        int index = random.nextInt(categories.length);

        wait.until(ExpectedConditions.elementToBeClickable(categories[index])).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".card-title a")));

        return categoryNames[index];
    }

}
