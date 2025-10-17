package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    private WebDriver driver;

    // ✅ Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Locators
    private By searchBox = By.name("search");
    private By searchButton = By.cssSelector("button[type='button'][class*='btn-default']");
    private By firstProduct = By.cssSelector(".product-thumb h4 a");
    private By addToCartButton = By.id("button-cart");
    private By successAlert = By.cssSelector(".alert-success");

    // ✅ Actions
    public void searchProduct(String productName) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void openFirstProduct() {
        driver.findElement(firstProduct).click();
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getSuccessMessage() {
        WebElement alert = driver.findElement(successAlert);
        return alert.getText();
    }
}
