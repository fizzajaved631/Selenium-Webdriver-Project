package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddToCartPage;

public class AddToCartTest extends BaseTest {

    private AddToCartPage addToCartPage;

    @BeforeMethod
    public void setUpTest() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
        addToCartPage = new AddToCartPage(driver);
    }

    @Test(priority = 1)
    public void verifyAddToCartFeature() throws InterruptedException {
        addToCartPage.scrollToProducts();
        addToCartPage.openFirstProduct();
        addToCartPage.addProductToCart();

        Assert.assertTrue(addToCartPage.isProductAddedSuccessfully(),
                "❌ Product was not added successfully!");

        System.out.println("✅ Product successfully added to the cart!");
        Thread.sleep(5000);
    }
}
