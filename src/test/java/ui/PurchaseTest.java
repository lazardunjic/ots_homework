package ui;

import com.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class PurchaseTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1/");
        loginPage = new LoginPage(driver);
        productPage = new ProductsPage(driver);
    }

    @Test
    public void testPurchaseProduct() {
        loginPage.typeOnUsernameFieldName("standard_user");
        loginPage.typeOnPasswordField("secret_sauce");
        loginPage.clickOnLoginButton();

        productPage.selectProduct("Sauce Labs Backpack");

        productPage.addToCart();

        productPage.goToCart();
        String cartItemName = productPage.getCartItemName();
        Assert.assertEquals(cartItemName, "Sauce Labs Backpack");

        productPage.checkout();

        productPage.enterShippingInformation("Lazar", "Dunjic", "37230");

        productPage.confirmOrder();

        String confirmationMessage = productPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.contains("THANK YOU FOR YOUR ORDER"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
