package ui;

import com.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/v1/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testErrorMessage() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("invalid_user");
        passwordField.sendKeys("wrong_password");
        loginButton.click();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.matches(".*Username and password do not match.*"));
    }

    @Test
    public void testLogin_case1() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        //oba pogresna
        usernameField.sendKeys("invalid_user");
        passwordField.sendKeys("wrong_password");
        loginButton.click();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.matches(".*Username and password do not match.*"));
    }

    @Test
    public void testLogin_case2() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        //validan username, pogresan password
        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("wrong_password");
        loginButton.click();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.matches(".*Username and password do not match.*"));
    }

    @Test
    public void testLogin_case3() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        //pogresan username, validan password
        usernameField.sendKeys("user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.matches(".*Username and password do not match.*"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
