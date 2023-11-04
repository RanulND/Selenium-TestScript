package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // XPaths
    private final By addToCartButtonXpath = By.xpath("//a[@id='ec_add_to_cart_5']");
    private final By heading =  By.xpath("//h6[contains(text(),'Explore a practice test site that has 25 real bugs')]");
    private final By successMessageSectionXpath = By.xpath("//div[@class='ec_product_added_to_cart']");
    private final By paginationButtonXpath = By.xpath("//a[normalize-space()='50']");
    private final By productCardClassName = By.className("ec_product_li");
    private final By productCardLink = By.xpath("//div[@id='ec_product_image_effect_4481370']//a[@class='ec_image_link_cover']");


    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickAddToCartButton() {
        driver.findElement(addToCartButtonXpath).click();
    }

    public void clickHeading() {
        driver.findElement(heading).click();
    }

    public int clickPaginationButton(){
        driver.findElement(paginationButtonXpath).click();
        String paginationValue = driver.findElement(paginationButtonXpath).getText();
        return Integer.parseInt(paginationValue);
    }

    public WebElement getSuccessMessageSection(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageSectionXpath));
    }

    public List<WebElement> getProductCardsByClassName() {
        return driver.findElements(productCardClassName);
    }

    public void clickProductCardLink() {
         driver.findElement(productCardLink).click();
    }
}
