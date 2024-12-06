package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    private WebDriver driver;

    @FindBy(css = ".product_sort_container")
    public WebElement sortDropdown;

    @FindBy(id = "reset_sidebar_link")
    public WebElement resetAppStateOption;

    @FindBy(css = ".btn_primary.btn_inventory")
    public WebElement addToCartButton;

    @FindBy(css = ".shopping_cart_container")
    public WebElement cartIcon;

    @FindBy(css = ".btn_action.checkout_button")
    public WebElement checkoutButton;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sortProductsByPriceLowToHigh() {
        sortDropdown.click();
        WebElement sortByPriceOption = driver.findElement(By.cssSelector("option[value='lohi']"));
        sortByPriceOption.click();
    }

    public void resetAppState() {
        resetAppStateOption.click();
    }

    public void addProductToCart() {
        addToCartButton.click();
    }

    public void goToCart() {
        cartIcon.click();
    }

    public void selectProduct(String productName) {
        WebElement product = driver.findElement(By.xpath("//div[text()='" + productName + "']"));
        product.click();
    }

    public void addToCart() {
        WebElement addToCartButton = driver.findElement(By.cssSelector(".btn_primary.btn_inventory"));
        addToCartButton.click();
    }

    public void checkout() {
        checkoutButton.click();
    }

    public void enterShippingInformation(String firstName, String lastName, String postalCode) {
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        WebElement continueButton = driver.findElement(By.cssSelector(".btn_primary.cart_button"));

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
        continueButton.click();
    }

    public String getConfirmationMessage() {
        WebElement confirmationMessage = driver.findElement(By.className("complete-header"));
        return confirmationMessage.getText();
    }

    public void confirmOrder() {
        WebElement finishButton = driver.findElement(By.cssSelector(".btn_action.cart_button"));
        finishButton.click();
    }

    public String getCartItemName() {
        WebElement cartItem = driver.findElement(By.className("inventory_item_name"));
        return cartItem.getText();
    }
}
