package TestCases;

import Base.TestBase;
import PageObjects.ProductPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Tests extends TestBase {
    @Test
    public void testing_01() throws TimeoutException {
        ProductPage pp = new ProductPage(driver, wait);
        driver.get(properties.getProperty("productsUrl"));
        pp.clickAddToCartButton();

        Boolean b = pp.getSuccessMessageSection().isDisplayed();

        Assert.assertEquals(Boolean.TRUE,b);
    }
}
