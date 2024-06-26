import org.openqa.selenium.WebDriver;

public class PageBase {
    protected boolean isDisplayed(WebDriver driver, String expectedTitle) {
        return driver.getTitle().equals(expectedTitle);
    }
}
