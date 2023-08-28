package sausedemotests;

import core.BaseTest;
import dev.failsafe.internal.util.Assert;
import org.example.BrowserTypes;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsTests extends BaseTest {

    public static final String ERROR_MESSAGE_ITEMS_COUNT = "Items count not as expected";
    public static final String ERROR_MESSAGE_WRONG_TITLE = "Item title not as expected";
    public static final String ERROR_TOTAL_PRICE = "Items total price not as expected";
    public static final String ORDER_IN_NOT_COMPLETED = "Order in not completed!";
    public static final String CART_IS_NOT_EMPTY = "Shopping cart is not empty";
    public static final String CART_SUCCESS_MESSAGE = "Shopping cart is empty.";
    public static String username = "standard_user";
    public static String password = "secret_sauce";

    public int expectedCountProducts = 2;

//    @BeforeAll
//    public static void beforeAllTests(){
//        driver = startBrowser(BrowserTypes.CHROME);
//
//        //Configure wait
//        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//        //Navigate to Google.com
//        driver.get("https://www.saucedemo.com/");
//
//        authenticateWithUser(username, password);
//    }

    @BeforeEach
    public void beforeEachTest(){
        driver = startBrowser(BrowserTypes.CHROME);

        //Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Navigate to Google.com
        driver.get("https://www.saucedemo.com/");

        authenticateWithUser(username, password);
    }


    @Test
    public void productAddedToShoppingCart_when_addToCart(){
        String backpackTitle = "Sauce Labs Backpack";
        String tShirtTitle = "Sauce Labs Bolt T-Shirt";

        WebElement backpack = getProductByTitle(backpackTitle);
        backpack.findElement(By.className("btn_inventory")).click();

        var tShirt = getProductByTitle(tShirtTitle);
        tShirt.findElement(By.className("btn_inventory")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        //Assert the shopping cart is displayed
        String expectedURL = "https://www.saucedemo.com/cart.html";
        Assertions.assertEquals(expectedURL, driver.getCurrentUrl(), "User is not in the shopping cart");

        //Assert Items and Totals
        var items = driver.findElements(By.className("inventory_item_name"));

        Assertions.assertEquals(expectedCountProducts, items.size(), ERROR_MESSAGE_ITEMS_COUNT);
        Assertions.assertEquals(backpackTitle, items.get(0).getText(), ERROR_MESSAGE_WRONG_TITLE);
        Assertions.assertEquals(tShirtTitle, items.get(1).getText(), ERROR_MESSAGE_WRONG_TITLE);

        System.out.printf("Products '%s' and '%s' successfully added to cart.%n", backpackTitle, tShirtTitle);

    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation(){
        String backpackTitle = "Sauce Labs Backpack";
        String shirtTitle = "Sauce Labs Bolt T-Shirt";

        WebElement backpack = getProductByTitle(backpackTitle);
        backpack.findElement(By.className("btn_inventory")).click();

        var tShirt = getProductByTitle(shirtTitle);
        tShirt.findElement(By.className("btn_inventory")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        //Assert Items and Totals
        driver.findElement(By.id("checkout")).click();

        //Fill in form
        fillShippingDetails("FirstName", "LastName", "ZipCode");

        driver.findElement(By.id("continue")).click();

        var items = driver.findElements(By.className("inventory_item_name"));
        Assertions.assertEquals(expectedCountProducts, items.size(), ERROR_MESSAGE_ITEMS_COUNT);

        var total = driver.findElement(By.className("summary_total_label")).getText();
        double expectedPrice = 29.99 + 15.99 + 3.68;
        String expectedTotal = String.format("Total: $%.2f", expectedPrice);

        Assertions.assertEquals(expectedCountProducts, items.size(), ERROR_MESSAGE_ITEMS_COUNT);
        Assertions.assertEquals(backpackTitle, items.get(0).getText(), ERROR_MESSAGE_WRONG_TITLE);
        Assertions.assertEquals(shirtTitle, items.get(1).getText(), ERROR_MESSAGE_WRONG_TITLE);
        Assertions.assertEquals(expectedTotal, total, ERROR_TOTAL_PRICE);
    }

    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm(){
        //Add Backpack and T-shirt to shopping cart
        String backpackTitle = "Sauce Labs Backpack";
        String tShirtTitle = "Sauce Labs Bolt T-Shirt";

        WebElement backpack = getProductByTitle(backpackTitle);
        backpack.findElement(By.className("btn_inventory")).click();

        var tShirt = getProductByTitle(tShirtTitle);
        tShirt.findElement(By.className("btn_inventory")).click();

        //Click on shopping Cart
        driver.findElement(By.className("shopping_cart_link")).click();

        //Fill Contact Details
        driver.findElement(By.id("checkout")).click();

        fillShippingDetails("FirstName", "LastName", "ZipCode");

        //Complete Order
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        String checkout = driver.findElement(By.className("title")).getText();
        Assertions.assertEquals("Checkout: Complete!", checkout, ORDER_IN_NOT_COMPLETED);

        //Assert Items removed from Shopping Cart
        driver.findElement(By.id("back-to-products")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        // Assert that the shopping cart is empty
        var cartItems = driver.findElements(By.className("inventory_item_name"));
        Assertions.assertEquals(0, cartItems.size(), CART_IS_NOT_EMPTY);

        System.out.println(CART_SUCCESS_MESSAGE);
        driver.findElement(By.id("continue-shopping")).click();
        System.out.println("The page has been reset.");


//        driver.findElement(By.id("react-burger-menu-btn")).click();
//
//        //Simulate mouse hover over the logout link
//        WebElement logoutLink = driver.findElement(By.id("reset_sidebar_link"));
//        Actions actions = new Actions(driver);
//        wait.until(ExpectedConditions.visibilityOf(logoutLink));
//        actions.moveToElement(logoutLink).perform();
//
//        // Click the logout link after the hover action
//        logoutLink.click();
//        driver.findElement(By.className("shopping_cart_link")).click();
//        driver.findElement(By.id("continue-shopping")).click();





    }
}