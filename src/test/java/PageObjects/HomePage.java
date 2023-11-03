package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String addToCartButtonXpath = "//a[@id='ec_add_to_cart_5']";
    private String findBugsLinkXpath =  "//a[@title='Find Bugs']";
    private String successMessageSectionXpath = "//div[@class='ec_product_added_to_cart']";
    private String paginationButtonXpath = "//a[normalize-space()='50']";
    private String productCardClassName = "ec_product_li";

    public HomePage(WebDriver driver, WebDriverWait wait){
        this.driver =  driver;
        this.wait = wait;
    }

    public void clickFindBugsLink(){
        driver.findElement(By.xpath(findBugsLinkXpath)).click();
    }
}
