import com.beust.ah.A;
import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.SoftAsserts;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Selenide.*;

public class SauceDemoProjectTest {
    public static final String RED = "rgba(226,35,26)";
    public static final String GREEN = "rgba(61,220,145)";
    private WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

//    @BeforeMethod
//    public void setup() {
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setBrowserName(CHROME);
//        caps.setPlatform(Platform.WINDOWS);
//
////        Configuration.remote = "http://192.168.0.100:4444/wd/hub";
////        Configuration.browserCapabilities = caps;
//
//        Configuration.pageLoadTimeout = 5000;
//        Configuration.browserSize = "1920x1080";
//        Configuration.baseUrl = "https://www.saucedemo.com/";
//        Configuration.assertionMode = AssertionMode.SOFT;
//
//        open(Configuration.baseUrl);
//    }
//
//    @AfterMethod
//    public void teardown(ITestResult testResult) {
//        if(testResult.getStatus() == ITestResult.FAILURE) {
//            File screenshot = Selenide.screenshot(OutputType.FILE);
//            try {
//                Allure.addAttachment(testResult.getMethod().getMethodName(), new FileInputStream(screenshot));
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        closeWebDriver();
//    }


    @Description("The attempt to login with empty Username field")
    @Test
    public void emptyUsernameErrorTest() {
        String expectedEmptyUsernameErrorText = "Epic sadface: Username is required";
        LoginPage loginPage = new LoginPage(driver);
        ErrorPage errorPage = new ErrorPage(driver);
        loginPage.loginWithoutUsername("secret_sauce");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(errorPage.getMissedFiledErrorText(), expectedEmptyUsernameErrorText);
        softAssert.assertTrue(errorPage.errorMessageIsVisible());
        softAssert.assertAll();
    }
    @Test
    public void emptyPasswordErrorTest() {
        String expectedEmptyPasswordErrorText = "Epic sadface: Password is required";
        LoginPage loginPage = new LoginPage(driver);
        ErrorPage errorPage = new ErrorPage(driver);
        loginPage.loginWithoutPassword("standard_user");
        Assert.assertEquals(errorPage.getMissedFiledErrorText(), expectedEmptyPasswordErrorText);

    }
    @Test
    public void emptyUsernamePasswordErrorTest() {
        String expectedEmptyUsernamePasswordErrorText = "Epic sadface: Username is required";
        LoginPage loginPage = new LoginPage(driver);
        ErrorPage errorPage = new ErrorPage(driver);
        loginPage.loginWithoutUsernamePassword();
        Assert.assertEquals(errorPage.getMissedFiledErrorText(), expectedEmptyUsernamePasswordErrorText);
    }
    @Test
    public void lockedOutUserLoginTest() {
        String expectedLockedOutUserErrorText = "Epic sadface: Sorry, this user has been locked out.";
        LoginPage loginPage = new LoginPage(driver);
        ErrorPage errorPage = new ErrorPage(driver);
        loginPage.successfulLogin("locked_out_user", "secret_sauce");
        Assert.assertEquals(errorPage.getMissedFiledErrorText(), expectedLockedOutUserErrorText);
    }
    @Test
    public void svgErrorIconIsVisibleTest() {
        LoginPage loginPage = new LoginPage(driver);
        ErrorPage errorPage = new ErrorPage(driver);
        loginPage.successfulLogin("invalid_user", "invalid_password");
        Assert.assertTrue(errorPage.svgErrorIconIsVisible());

    }
//Need to improve this test
    @Test
    public void closingErrorFormState() {
        LoginPage loginPage = new LoginPage(driver);
        ErrorPage errorPage = new ErrorPage(driver);
        loginPage.hidingErrorMessage("invalid_user", "invalid_password");
        Assert.assertTrue(errorPage.errorMessageContainerIsEmpty());

    }
    @Test
    public void loginFormIsVisibleTest() {
        ErrorPage errorPage = new ErrorPage(driver);
        Assert.assertTrue(errorPage.loginFormIsDisplayed());

    }
    @Test
    public void standardUserLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsMainPage productsMainPage = new ProductsMainPage(driver);
        loginPage.successfulLogin("standard_user", "secret_sauce");
        productsMainPage.LoginUserMainPageIsDisplayed();
        Assert.assertTrue(productsMainPage.LoginUserMainPageIsDisplayed());

    }
    @Test
    public void openShoppingCartTest() {
        String expectedCartPageTitle = "Your Cart";
        LoginPage loginPage = new LoginPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        loginPage.successfulLogin("standard_user", "secret_sauce");
        shoppingCartPage.openUserCart();
        Assert.assertEquals(expectedCartPageTitle, shoppingCartPage.getCartPageTitle());
    }
    @Test
    public void returnToMainPageTest() {
        LoginPage loginPage = new LoginPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        ProductsMainPage productsMainPage = new ProductsMainPage(driver);
        loginPage.successfulLogin("standard_user", "secret_sauce");
        shoppingCartPage.openUserCart();
        shoppingCartPage.clickOnBackCartButton();
        Assert.assertTrue(productsMainPage.LoginUserMainPageIsDisplayed());
    }
    @Test
    public void addToCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsMainPage productsMainPage = new ProductsMainPage(driver);
        loginPage.successfulLogin("standard_user", "secret_sauce");
        productsMainPage.addToCartBackpackItem();
        int cartQuantity = productsMainPage.getCartQuantityInBadge();
        Assert.assertEquals(cartQuantity, 1, "Cart quantity should be 1 after adding one item.");
    }
    @Test
    public void emptyCartTest(){
        LoginPage loginPage = new LoginPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        loginPage.successfulLogin("standard_user", "secret_sauce");
        shoppingCartPage.openUserCart();
        boolean isCartItemPresent = shoppingCartPage.isCartItemPresent();
        Assert.assertFalse(isCartItemPresent);
    }
    @Test
    public void cartItemPresenceTest() {
        LoginPage loginPage = new LoginPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        ProductsMainPage productsMainPage = new ProductsMainPage(driver);
        loginPage.successfulLogin("standard_user", "secret_sauce");
        productsMainPage.addToCartBackpackItem();
        shoppingCartPage.openUserCart();
        boolean isProductItemAddedToCart = shoppingCartPage.isProductItemAddedToCart();
        Assert.assertTrue(isProductItemAddedToCart);
    }
    @Test
    public void removeItemFromCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        ProductsMainPage productsMainPage = new ProductsMainPage(driver);
        loginPage.successfulLogin("standard_user", "secret_sauce");
        productsMainPage.addToCartBackpackItem();
        shoppingCartPage.openUserCart();
        shoppingCartPage.deleteItemFromCart();
        boolean isCartItemPresent = shoppingCartPage.isCartItemPresent();
        Assert.assertFalse(isCartItemPresent);
    }
    @Test
    public void checkingItemInfoInCartTest() {

    }
}
