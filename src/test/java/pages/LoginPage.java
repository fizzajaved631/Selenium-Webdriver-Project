package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Locators (collected from the page)
    private By emailField = By.id("input-email");
    private By passwordField = By.id("input-password");
    private By loginButton = By.xpath("//input[@value='Login']");
    private By warningMessage = By.cssSelector(".alert-danger");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        WebElement e = driver.findElement(emailField);
        e.clear();
        e.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement e = driver.findElement(passwordField);
        e.clear();
        e.sendKeys(password);
    }

    // Click the login button (no assumption about success/failure)
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isWarningDisplayed() {
        return !driver.findElements(warningMessage).isEmpty() && driver.findElement(warningMessage).isDisplayed();
    }
}
