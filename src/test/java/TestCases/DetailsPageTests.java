package TestCases;

import Base.TestBase;
import PageObjects.ProductDetailsPage;
import PageObjects.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DetailsPageTests extends TestBase {

    @DataProvider(name = "selectOptionObject")
    public Object[][] selectOptions() {
        return new Object[][]{
                {"GBP", '£'},
                {"EUR", '€'},
                {"JPY", '¥'}
        };
    }

    @DataProvider(name = "searchQueries")
    public Object[][] searchQueries() {
        return new Object[][]{
                {"DNK Yellow Shoes", By.xpath("//h3[@class='test_title ec_product_title_type1']//a[@class='ec_image_link_cover'][normalize-space()='DNK Yellow Shoes']")},
                {"Blue Tshirt", By.xpath("//h3[@class='test_title ec_product_title_type1']//a[@class='ec_image_link_cover'][normalize-space()='Blue Tshirt']")}
        };
    }

    @DataProvider(name = "nonProductSearchQueries")
    public Object[][] nonProductSearchQueries() {
        return new Object[][]{
                {"test product 1", By.xpath("//div[@class='ec_products_no_results']")},
                {"test product 2", By.xpath("//div[@class='ec_products_no_results']")}
        };
    }

    @DataProvider(name = "commentDetails")
    public Object[][] commentDetails() {
        return new Object[][]{
                {"test comment 1", "test user 1", "testuser@gmail.com", "www.testweb.com"},
                {"test comment 2", "test user 2", "testuser2@gmail.com", "www.testweb2.com"}
        };
    }

    @Test(dataProvider = "selectOptionObject")
    public void changeCurrencyType_Pass(String selectedValue, char expectedCharacter) throws InterruptedException {
        ProductPage pp = new ProductPage(driver, wait);
        ProductDetailsPage pdp = new ProductDetailsPage(driver, wait);

        driver.get(properties.getProperty("productsUrl"));
        pp.clickProductCardLink();

        Select dropdown = new Select(pdp.getSelectElement());

        dropdown.selectByValue(selectedValue);
        Thread.sleep(5000);

        String actualOutput = pdp.getPriceSectionText();

        Assert.assertTrue(actualOutput.contains("£"), "Expecting '" + expectedCharacter + "' character is present in the price section " + actualOutput + " when user selects " + selectedValue);
        Thread.sleep(1000);
    }

    @Test(dataProvider = "searchQueries")
    public void searchProduct_Pass(String query, By xpath) throws InterruptedException {
        ProductPage pp = new ProductPage(driver, wait);
        ProductDetailsPage pdp = new ProductDetailsPage(driver, wait);

        driver.get(properties.getProperty("productsUrl"));
        pp.clickProductCardLink();

        pdp.enterSearchQuery(query);
        WebElement element = pdp.getProductNameSection(xpath);
        boolean isDisplayed = element.isDisplayed();
        Assert.assertTrue(isDisplayed);
        Thread.sleep(5000);
    }

    @Test(dataProvider = "nonProductSearchQueries")
    public void searchProduct_Fail(String query, By xpath) throws InterruptedException { // searching products with queries which are not associated with products
        ProductPage pp = new ProductPage(driver, wait);
        ProductDetailsPage pdp = new ProductDetailsPage(driver, wait);

        driver.get(properties.getProperty("productsUrl"));
        pp.clickProductCardLink();

        pdp.enterSearchQuery(query);
        WebElement element = pdp.getProductNameSection(xpath);
        boolean isDisplayed = element.isDisplayed();
        Assert.assertTrue(isDisplayed);
        Thread.sleep(5000);
    }

    @Test(dataProvider = "commentDetails")
    public void postComment(String comment, String name, String email, String website) throws InterruptedException {
        ProductPage pp = new ProductPage(driver, wait);
        ProductDetailsPage pdp = new ProductDetailsPage(driver, wait);
        driver.get(properties.getProperty("productsUrl"));
        pp.clickProductCardLink();

        pdp.postAComment(comment,name,email,website);
        Thread.sleep(5000);
    }
}
