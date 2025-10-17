package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddToCartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By firstProduct = By.cssSelector("#entry_218387 a");
    private By addToCartButton = By.cssSelector("button.btn-cart, button.button-cart, button.btn.btn-secondary");
    private By sizeDropdown = By.cssSelector("select[id^='input-option']");
    private By successMessage = By.cssSelector(".alert-success");

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void scrollToProducts() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1200);");
        System.out.println("✅ Scrolled down to products section.");
    }

    public void openFirstProduct() {
        try {
            WebElement product = wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
            product.click();
            System.out.println("✅ Clicked first product.");
            Thread.sleep(2000); // allow product page to load
        } catch (Exception e) {
            System.out.println("❌ Could not click product: " + e.getMessage());
        }
    }

    public void addProductToCart() {
        try {
            // Scroll down completely to bring Add to Cart into view
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000);

            // Handle dropdown if exists
            try {
                WebElement dropdown = driver.findElement(sizeDropdown);
                if (dropdown.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].value='1';", dropdown);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", dropdown);
                    System.out.println("✅ Size dropdown found and handled.");
                }
            } catch (NoSuchElementException ex) {
                System.out.println("⚠️ No size dropdown found, skipping.");
            }

            // Wait for Add to Cart button and click it
            WebElement addBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);
            Thread.sleep(1000);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
            System.out.println("✅ Clicked Add to Cart button.");

        } catch (Exception e) {
            System.out.println("❌ Add to Cart failed: " + e.getMessage());
        }
    }

    public boolean isProductAddedSuccessfully() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            boolean visible = driver.findElement(successMessage).isDisplayed();
            System.out.println("✅ Success message visible: " + visible);
            return visible;
        } catch (TimeoutException e) {
            System.out.println("❌ Success message not found.");
            return false;
        }
    }
}
