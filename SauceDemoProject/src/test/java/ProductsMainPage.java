import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsMainPage extends PageBase {
    private By menuButtonLocator = By.xpath("//button[@id='react-burger-menu-btn']");
    private By shoppingCartLocator = By.xpath("//div[@id='shopping_cart_container']");
    private By productContainer = By.xpath("//div[@id='inventory_container']");
    private By backpackAddToCartBtn = By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']");
    private By cartQuantityBadge = By.xpath("//*[@class='shopping_cart_badge' and @data-test='shopping-cart-badge']");

    private WebDriver driver;
    public ProductsMainPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step ("Main page elements after logging are visible")
    public boolean LoginUserMainPageIsDisplayed() {
        return driver.findElement(shoppingCartLocator).isDisplayed();
    }
    @Step ("Add to cart backpack item")
    public void addToCartBackpackItem() {
        driver.findElement(backpackAddToCartBtn).click();
    }
    public int getCartQuantityInBadge () {
        String quantityText = driver.findElement(cartQuantityBadge).getText();
        return Integer.parseInt(quantityText);
    }
}
