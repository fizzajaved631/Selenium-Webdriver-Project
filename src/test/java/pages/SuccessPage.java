package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessPage {
    WebDriver driver;

    By successHeading = By.xpath("//h1[text()='Your Account Has Been Created!']");

    public SuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnSuccessPage() {
        return driver.getCurrentUrl().contains("account/success");
    }

    public String getSuccessMessage() {
        return driver.findElement(successHeading).getText();
    }
}
