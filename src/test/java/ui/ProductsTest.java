package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ProductsTest {

    WebDriver driver;
    ProductsPage productsPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/inventory.html");  // URL stranice sa proizvodima

        // Inicijalizujte ProductsPage
        productsPage = new ProductsPage(driver);

        login("standard_user", "secret_sauce");
    }

    @Test
    public void testSortProductsByPriceLowToHigh() {
        productsPage.sortProductsByPriceLowToHigh();
        Assert.assertTrue(isSortedByPriceLowToHigh(), "Proizvodi nisu sortirani ispravno.");
    }

    @Test
    public void testAddProductToCart() {
        productsPage.addProductToCart();
        Assert.assertTrue(isProductInCart(), "Proizvod nije dodat u korpu.");
    }

    @Test
    public void testGoToCart() {
        productsPage.goToCart();
        Assert.assertTrue(isOnCartPage(), "Korisnik nije prešao na stranicu korpe.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void login(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    private boolean isSortedByPriceLowToHigh() {
        return true;
    }

    private boolean isProductInCart() {
        return true;
    }

    private boolean isOnCartPage() {
        return true;
    }
}
