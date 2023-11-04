package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // locators
    By selectFieldXpath = By.xpath("//select[@id='ec_currency_conversion']");
    By priceSection = By.xpath("/html/body/div[3]/div/div/div[1]/main/article/div/section/div[1]/div[1]/div[1]/span");
    By searchInputField = By.xpath("//input[@name='ec_search']");
    By productNameSection = By.xpath("//h3[@class='test_title ec_product_title_type1']//a[@class='ec_image_link_cover'][normalize-space()='DNK Yellow Shoes']");
    By searchButton = By.xpath("//input[@value='Search']");
    By commentField = By.xpath("//textarea[@id='comment']");
    By nameField = By.xpath("//input[@id='author']");
    By emailField = By.xpath("//input[@id='email']");
    By websiteField = By.xpath("//input[@id='url']");
    By postCommentButton = By.xpath("//button[@id='academy-comment-submit']");

    public ProductDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement getSelectElement(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selectFieldXpath));
    }

    public String getPriceSectionText(){
        WebElement priceSectionElement = driver.findElement(priceSection);
        return priceSectionElement.getAttribute("innerHTML");
    }

    public void enterSearchQuery(String keys){
        driver.findElement(searchInputField).sendKeys(keys);
        driver.findElement(searchButton).click();
    }

    public WebElement getProductNameSection(By productNameSection){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productNameSection));
    }

    public void postAComment(String comment, String name, String email, String website){
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentField)).sendKeys(comment);
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(websiteField)).sendKeys(website);
        wait.until(ExpectedConditions.visibilityOfElementLocated(postCommentButton)).click();


    }
}
