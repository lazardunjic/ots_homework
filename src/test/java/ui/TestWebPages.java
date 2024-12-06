package ui;

import com.pages.LoginPage;
import com.pages.ProductsPage;
import com.pages.TopMenu;
import com.utils.WebDriverFabric;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestWebPages {

    private static final Logger log = LogManager.getLogger(TestWebPages.class);

    private WebDriver driver = null;
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    @Parameters({"browser"})
    @BeforeMethod
    public void startBrowser(String browser){

        driver = WebDriverFabric.startBrowser(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){

        WebDriverFabric.closeBrowser(driver);
    }


    @Test
    public void testValidLogin(){

        log.info("************* Test Started *************");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeOnUsernameFieldName(USERNAME);

        loginPage.typeOnPasswordField(PASSWORD);

        loginPage.clickOnLoginButton();

        assertTrue(loginPage.getCurrentUrl().contains(ProductsPage.INVENTORY_URL),
                "URL is not changed. User is not logged in!");

        TopMenu topMenu = new TopMenu(driver);

        topMenu.clickTopMenu();

        topMenu.clickLogoutButton();

        assertTrue(loginPage.isLoginButtonVisible(),
                "Login button is not visible on a page");

        log.info("************* Test Ended *************");
    }


}
