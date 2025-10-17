package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import base.BaseTest;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUpTest() {
        // Open login page before each test
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, description = "Verify that user cannot login with invalid credentials")
    public void testInvalidLogin() {
        loginPage.enterEmail("fakeuser123@gmail.com");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLogin();

        // Verify error message (specific to LambdaTest playground)
        Assert.assertTrue(loginPage.isWarningDisplayed(), "❌ Warning message not displayed for invalid login!");
        System.out.println("✅ Invalid login test passed — warning message appeared as expected.");
    }

    @Test(priority = 2, description = "Verify that user can login with valid credentials")
    public void testValidLogin() {
        // Use your actual registered LambdaTest Playground credentials
        loginPage.enterEmail("actualemail@gmail.com");
        loginPage.enterPassword("aaa123");
        loginPage.clickLogin();

        // Verify successful login — page title or account heading
        String expectedTitle = "My Account";
        String actualTitle = driver.getTitle();

        Assert.assertTrue(actualTitle.contains(expectedTitle), 
            "❌ Login failed — user not redirected to 'My Account' page!");
        System.out.println("✅ Valid login test passed — user successfully logged in.");
    }
}
