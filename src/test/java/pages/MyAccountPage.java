package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage {
    private WebDriver driver;
    private By myAccountHeader = By.xpath("//h2[contains(text(),'My Account')]");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // Wait for My Account header to appear (returns true if displayed within timeout)
    public boolean isPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountHeader));
            return driver.findElement(myAccountHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }
}

