package tests;

import base.BaseTest;
import pages.RegisterPage;
import pages.SuccessPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class RegisterTest extends BaseTest {

    @Test(priority = 1)
    public void TC_REG_01_VerifyExistingEmail() {
        RegisterPage reg = new RegisterPage(driver);
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        driver.navigate().refresh();

        reg.enterFirstName("FirstName");
        reg.enterLastName("LastName");
        reg.enterEmail(" existingemail@gmail.com"); // existing email
        reg.enterTelephone("01234567");
        reg.enterPassword("Test@123");
        reg.confirmPassword("Test@123");

        // Accept privacy policy
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.name("agree")));

        reg.clickContinue();

        String msg = reg.getWarningMessage();
        Assert.assertTrue(msg.contains("Warning: E-Mail Address is already registered!"),
                "Expected warning for existing email not displayed.");
        System.out.println("✅ Existing email warning appeared successfully.");
    }

    @Test(priority = 2)
    public void TC_REG_02_VerifyInvalidEmailFormat() {
        RegisterPage reg = new RegisterPage(driver);
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        driver.navigate().refresh();

        reg.enterFirstName("FirstName");
        reg.enterLastName("LastName");
        reg.enterEmail(" emailexample.com"); // Invalid email format
        reg.enterTelephone("01234567");
        reg.enterPassword("Test@123");
        reg.confirmPassword("Test@123");

     // Accept privacy policy
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.name("agree")));

        reg.clickContinue();


        WebElement continueBtn = driver.findElement(By.xpath("//input[@value='Continue']"));
        js.executeScript("arguments[0].click();", continueBtn);

        String validationMsg = driver.findElement(By.id("input-email")).getAttribute("validationMessage");
        System.out.println("📩 Email validation message: " + validationMsg);

        Assert.assertTrue(validationMsg.toLowerCase().contains("@") || validationMsg.toLowerCase().contains("include"),
                "Expected email format warning not displayed.");
        System.out.println("✅ Invalid email format test passed.");
    }

    @Test(priority = 3)
    public void TC_REG_03_WithoutAcceptingPrivacyPolicy() {
        RegisterPage reg = new RegisterPage(driver);
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        driver.navigate().refresh();

        reg.enterFirstName("firstname");
        reg.enterLastName("lastname");
        reg.enterEmail("email999@example.com");
        reg.enterTelephone("01234567");
        reg.enterPassword("Test@123");
        reg.confirmPassword("Test@123");

        // Not checking privacy checkbox
        reg.clickContinue();

        String msg = reg.getWarningMessage();
        Assert.assertTrue(msg.contains("Warning: You must agree to the Privacy Policy!"),
                "Expected privacy policy warning not displayed.");
        System.out.println("✅ Privacy policy warning displayed correctly.");
    }

    @Test(priority = 4)
    public void TC_REG_04_ValidRegistration() {
        RegisterPage reg = new RegisterPage(driver);
        SuccessPage success = new SuccessPage(driver);

        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        driver.navigate().refresh();

        // Generate unique email each time
        String uniqueEmail = "fizza" + System.currentTimeMillis() + "@example.com";

        reg.enterFirstName("FirstName");
        reg.enterLastName("LastName");
        reg.enterEmail("youruniqueemail@gmail.com");
        reg.enterTelephone("01234567");
        reg.enterPassword("Test@123");
        reg.confirmPassword("Test@123");

        // Accept privacy policy using JS
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.name("agree")));

        // Click continue
        WebElement continueBtn = driver.findElement(By.xpath("//input[@value='Continue']"));
        js.executeScript("arguments[0].click();", continueBtn);

        // Wait for success page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("account/success"));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("🔹 Redirected URL: " + currentUrl);

        if (currentUrl.contains("account/success")) {
            String successMessage = success.getSuccessMessage();
            System.out.println("✅ Registration successful!");
            System.out.println("🎉 Success page message: " + successMessage);

            Assert.assertTrue(successMessage.contains("Your Account Has Been Created!"),
                    "Success page did not display expected message!");
        } else {
            // Handle failure
            List<WebElement> warnings = driver.findElements(By.cssSelector(".alert-danger"));
            if (!warnings.isEmpty()) {
                System.out.println("⚠️ Warning message: " + warnings.get(0).getText());
            } else {
                System.out.println("❌ Registration failed with no visible warning.");
            }
            Assert.fail("Registration test failed — user not redirected to success page.");
        }
    }
}
