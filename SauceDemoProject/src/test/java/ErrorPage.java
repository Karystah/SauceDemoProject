import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorPage extends PageBase {
    private By errorBoxLocator = By.xpath("//h3[@data-test='error']");
    private By errorTextLocator = By.cssSelector("div h3");
    private By loginPageFormLocator = By.xpath("//div[@id='login_button_container']/div[@class='login-box']");
    private By errorStateOfUsernameFieldLocator = By.xpath("//input[@class='input_error form_input error' and @id='user-name']");
    private By errorStateOfPasswordFieldLocator = By.xpath("//input[@class=input_error form_input error' and @id='password']");
    private By svgIconOfInvalidInputLocator = By.xpath("//*[local-name()='svg' and @data-icon='times-circle']");
    private By errorMessageConatainerLocator = By.xpath("//*[@class='error-message-container']");
    private By closingErrorButtonLocator = By.xpath("//*[@class='error-button']");

    private WebDriver driver;
    public ErrorPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMissedFiledErrorText() {
        return driver.findElement(errorTextLocator).getText();
    }
    public boolean errorMessageIsVisible() {
        return driver.findElement(errorBoxLocator).isDisplayed();
    }
    public boolean loginFormIsDisplayed() {
        return driver.findElement(loginPageFormLocator).isDisplayed();
    }
    public boolean svgErrorIconIsVisible() {
        return driver.findElement(svgIconOfInvalidInputLocator).isDisplayed();
    }
    public boolean errorMessageContainerIsEmpty() {
        try {
            driver.findElement(errorBoxLocator);
            return false;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return true;
        }
    }

}
