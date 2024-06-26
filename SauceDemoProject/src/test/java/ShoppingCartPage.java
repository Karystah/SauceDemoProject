import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

public class ShoppingCartPage extends PageBase{
    private WebDriver driver;
    private By shoppingCartButtonLocator = By.xpath("//*[@class='shopping_cart_link']");
    private By shoppingCartTitleLocator = By.xpath("//*[@class='title' and @data-test='title']");
    private By backCartButtonLocator = By.xpath("//*[@id='continue-shopping']");
    private By cartItemLocator = By.xpath("//*[@class='cart_item']");
    private By removeItemFromCartLocator = By.xpath("//*[@class='btn btn_secondary btn_small cart_button']");
    private By cartItemNameLocator = By.xpath("//*[@class='inventory_item_name']");
    private By cartItemDescriptionLocator = By.xpath("//*[@class='inventory_item_desc']");
    private By cartItemPriceLocator = By.xpath("//*[@class='inventory_item_price']");
    private By cartItemQuantityLocator = By.xpath("//*[@class='cart_quantity']");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step ("Attempt to open shopping cart")
    public void openUserCart() {
        driver.findElement(shoppingCartButtonLocator).click();
    }
    public String getCartPageTitle() {
        return driver.findElement(shoppingCartTitleLocator).getText();
    }
    @Step ("Attempt to return to the Main Page by clicking on Continue Shopping button")
    public void clickOnBackCartButton() {
        driver.findElement(backCartButtonLocator).click();
    }
    @Step ("Checking if cart item is present")
    public boolean isCartItemPresent() {
        return !driver.findElements(cartItemLocator).isEmpty();
    }
    @Step ("Checking if item is added to cart")
    public boolean isProductItemAddedToCart() {
        return driver.findElement(cartItemLocator).isDisplayed();
    }
    @Step ("Removing Item from cart")
    public void deleteItemFromCart() {
        driver.findElement(removeItemFromCartLocator).click();
    }
    @Step ("Item Description is shown in shopping cart")
    public boolean isDescShownInCart() {
        return !driver.findElements(cartItemDescriptionLocator).isEmpty();
    }
    @Step ("Item Name is shown in shopping cart")
    public boolean isNameShownInCart() {
        return !driver.findElements(cartItemNameLocator).isEmpty();
    }
    @Step ("Item Price is shown in shopping cart")
    public boolean isPriceShownInCart() {
        return !driver.findElements(cartItemPriceLocator).isEmpty();
    }
    @Step ("Item Quantity is shown in shopping cart")
    public boolean isQuantityShownInCart() {
        return !driver.findElements(cartItemQuantityLocator).isEmpty();
    }
    @Step ("Checking the item Name in cart")
    public String getItemNameInCart (){
        return driver.findElement(cartItemNameLocator).getText();
    }
    @Step ("Checking the item description in cart")
    public String getItemDescription() {
        return driver.findElement(cartItemDescriptionLocator).getText();
    }
    @Step ("Checking the item price in cart")
    public String getItemPrice() {
        return driver.findElement(cartItemLocator).getText();
    }
    

}
