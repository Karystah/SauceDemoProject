import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {
    private By usernameInputLocator = By.xpath("//input[@id='user-name']");
    private By passwordInputLocator = By.xpath("//input[@id='password']");
    private By submitButtonLocator = By.xpath("//input[@id='login-button' and @class='submit-button btn_action']");

    private By loginPageFormLocator = By.xpath("//div[@id='login_button_container']/div[@class='login-box']");
    private By closingErrorButtonLocator = By.xpath("//*[@class='error-button']");


    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step ("Attempt to login without entering data in USERNAME field")
    public void loginWithoutUsername (String password) {
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(submitButtonLocator).click();
    }

    @Step ("Attempt tp login with empty PASSWORD field")
    public void loginWithoutPassword (String username) {
        driver.findElement(usernameInputLocator).sendKeys(username);
        driver.findElement(submitButtonLocator).click();
    }

    @Step ("Attempt to login with empty Username Password fields")
    public void loginWithoutUsernamePassword () {
        driver.findElement(submitButtonLocator).click();
    }

    @Step ("Successful login")
    public void successfulLogin (String username, String password) {
        driver.findElement(usernameInputLocator).sendKeys(username);
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(submitButtonLocator).click();
    }
    @Step ("Attempt to hide error message by clicking on CLOSE BUTTON")
    public void hidingErrorMessage (String username, String password) {
        driver.findElement(usernameInputLocator).sendKeys(username);
        driver.findElement(passwordInputLocator).sendKeys(password);
        driver.findElement(submitButtonLocator).click();
        driver.findElement(closingErrorButtonLocator).click();
    }

}
