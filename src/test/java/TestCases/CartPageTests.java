package TestCases;

import Base.TestBase;
import PageObjects.CartPage;
import PageObjects.HomePage;
import PageObjects.ProductPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CartPageTests extends TestBase {
    @DataProvider(name = "updateQuantities")
    public Object[][] testQuantities() {
        return new Object[][]{
                {2},
                {3},
                {10},
        };
    }

    @Test(dataProvider = "updateQuantities")
    public void updateQuantityInCart_Pass(int quantity) throws InterruptedException {
        ProductPage pp = new ProductPage(driver,wait);
        CartPage cp = new CartPage(driver,wait);
        driver.get(properties.getProperty("productsUrl"));

        // Add an item to cart
        pp.clickAddToCartButton();
        Thread.sleep(10000);

        driver.get(properties.getProperty("cartUrl"));

        // update quantity
        cp.updateQuantity(quantity);
        Thread.sleep(10000);

        int output =  Integer.parseInt(cp.getUpdateFieldValue());

        Assert.assertEquals(output,quantity);
    }

    @Test
    public void clearCart_Pass() throws InterruptedException {
        ProductPage pp = new ProductPage(driver,wait);
        CartPage cp = new CartPage(driver,wait);
        driver.get(properties.getProperty("productsUrl"));

        // Add an item to cart
        pp.clickAddToCartButton();
        Thread.sleep(10000);

        driver.get(properties.getProperty("cartUrl"));

        cp.removeProduct();
        WebElement element = cp.getReturnToStoreButton();
        Assert.assertEquals(element.getText(),cp.getExpectedReturnToStoreButtonText());

    }
}


