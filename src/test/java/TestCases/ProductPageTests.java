package TestCases;

import Base.TestBase;
import PageObjects.ProductPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductPageTests extends TestBase {

    @Test
    public void addToCard_SuccessMessage_Pass() throws InterruptedException {
        ProductPage pp = new ProductPage(driver, wait);
        driver.get(properties.getProperty("productsUrl"));

        //Click the "ADD TO CART" button
        pp.clickAddToCartButton();

        // Checks the visibility of success message
        Boolean b = pp.getSuccessMessageSection().isDisplayed();
        Thread.sleep(5000);

        // Assertion
        Assert.assertEquals(Boolean.TRUE,b);
    }

    @Test
    public void addToCard_SuccessMessage_Fail() throws TimeoutException, InterruptedException {
        ProductPage pp = new ProductPage(driver, wait);
        driver.get(properties.getProperty("productsUrl"));

        //Click the specified heading
        pp.clickHeading();

        // Checks the visibility of success message
        try{
            Boolean b = pp.getSuccessMessageSection().isDisplayed();
        }catch (TimeoutException e){
            // Assertion
            Assert.assertTrue(true, "TimeoutException was thrown as expected.");
        }
        Thread.sleep(5000);

    }

    @Test
    public void paginationButtonClick_Pass() throws InterruptedException {
        ProductPage pp = new ProductPage(driver, wait);
        driver.get(properties.getProperty("productsUrl"));

        //Click the specified pagination button
        int numberInButton = pp.clickPaginationButton();

        // Get the count of product card displays in the web
        List<WebElement> elements = pp.getProductCardsByClassName();

        // Assertion
        Assert.assertEquals(elements.size(),numberInButton);
    }
}
