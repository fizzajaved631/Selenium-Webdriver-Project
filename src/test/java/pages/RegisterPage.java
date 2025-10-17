package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    // Locators
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By privacyPolicy = By.name("agree");
    private By continueBtn = By.xpath("//input[@value='Continue']");
    private By warningMsg = By.cssSelector(".alert-danger");
    private By successMsg = By.xpath("//h1[text()='Your Account Has Been Created!']");

    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterFirstName(String fname) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(fname);
    }

    public void enterLastName(String lname) {
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lname);
    }

    public void enterEmail(String mail) {
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(mail);
    }

    public void enterTelephone(String phone) {
        driver.findElement(telephone).clear();
        driver.findElement(telephone).sendKeys(phone);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
    }

    public void confirmPassword(String pass) {
        driver.findElement(confirmPassword).clear();
        driver.findElement(confirmPassword).sendKeys(pass);
    }

    public void selectPrivacyPolicy() {
        driver.findElement(privacyPolicy).click();
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    public String getWarningMessage() {
        return driver.findElement(warningMsg).getText();
    }

    public boolean isRegistrationSuccessful() {
        return driver.findElements(successMsg).size() > 0;
    }
}
