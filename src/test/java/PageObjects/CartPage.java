package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private final String url = "https://academybugs.com/my-cart/";
    private final WebDriver driver;
    private final WebDriverWait wait;

    // locators
    private final By updateInputField = By.className("ec_quantity");
    private final By updateButton = By.className("ec_cartitem_update_button");
    private final By deleteButton = By.className("ec_cartitem_delete");
    private final By returnToStoreButton = By.xpath("//a[@class='ec_cart_empty_button academy-bug']");
    private String expectedReturnToStoreButtonText = "RETURN TO STORE";

    public CartPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public void updateQuantity(int quantity){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(updateInputField));
        element.clear();
        element.sendKeys(String.valueOf(quantity));
        driver.findElement(updateButton).click();
    }

    public String getUpdateFieldValue(){
        System.out.println(driver.findElement(updateInputField).getText());
        return driver.findElement(updateInputField).getAttribute("value");
    }

    public void removeProduct(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
        element.click();
    }

    public WebElement getReturnToStoreButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(returnToStoreButton));
    }

    public String getExpectedReturnToStoreButtonText() {
        return expectedReturnToStoreButtonText;
    }
}
