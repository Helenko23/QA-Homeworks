package sausedemotests;

import core.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsTests extends BaseTest {

    public static final String ITEMS_COUNT_ERROR = "Items count not as expected";
    public static final String WRONG_TITLE_ERROR = "Item title not as expected";
    public static final String TOTAL_PRICE_ERROR = "Items total price not as expected";
    public static final String ORDER_IN_NOT_COMPLETED = "Order in not completed!";
    public static final String CART_IS_NOT_EMPTY = "Shopping cart is not empty";
    public static final String CART_SUCCESS_MESSAGE = "Shopping cart is empty.";
    public static final String PAGE_RESET_ERROR = "The page has not been reset";

    public static final String PAGE_RESET_SUCCESS = "The page has been reset.";

    public int expectedCountProducts = 2;

    @BeforeAll
    public static void beforeAllTests(){
        driver = startBrowser(BrowserTypes.CHROME);

        //Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Navigate to Google.com
        driver.get(BASE_URL);

        authenticateWithUser(username, password);
    }

    @AfterEach
    public void pageReset() {
        //Reset app State
        driver.findElement(By.id("react-burger-menu-btn")).click();

        WebElement resetLink = driver.findElement(By.id("reset_sidebar_link"));
        Actions actionReset = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(resetLink));
        actionReset.moveToElement(resetLink).perform();
        resetLink.click();

        //Go to main inventory page
        WebElement inventoryLink = driver.findElement(By.id("inventory_sidebar_link"));
        Actions actionInventory = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(inventoryLink));
        actionInventory.moveToElement(inventoryLink).perform();
        inventoryLink.click();

        Assertions.assertEquals(EXPECTED_URL, driver.getCurrentUrl(), PAGE_RESET_ERROR);
        System.out.println(PAGE_RESET_SUCCESS);
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
        Assertions.assertEquals(EXPECTED_CART_URL, driver.getCurrentUrl(), "User is not in the shopping cart");

        //Assert Items and Totals
        var items = driver.findElements(By.className("inventory_item_name"));

        Assertions.assertEquals(expectedCountProducts, items.size(), ITEMS_COUNT_ERROR);
        Assertions.assertEquals(backpackTitle, items.get(0).getText(), WRONG_TITLE_ERROR);
        Assertions.assertEquals(tShirtTitle, items.get(1).getText(), WRONG_TITLE_ERROR);

        System.out.printf("Products '%s' and '%s' successfully added to cart.%n", backpackTitle, tShirtTitle);

    }

    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation(){
        String backpackTitle = "Sauce Labs Backpack";
        String tShirtTitle = "Sauce Labs Bolt T-Shirt";

        WebElement backpack = getProductByTitle(backpackTitle);
        backpack.findElement(By.className("btn_inventory")).click();

        var tShirt = getProductByTitle(tShirtTitle);
        tShirt.findElement(By.className("btn_inventory")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        //Assert Items and Totals
        driver.findElement(By.id("checkout")).click();

        //Fill in form
        fillShippingDetails("FirstName", "LastName", "ZipCode");

        driver.findElement(By.id("continue")).click();

        var items = driver.findElements(By.className("inventory_item_name"));
        Assertions.assertEquals(expectedCountProducts, items.size(), ITEMS_COUNT_ERROR);

        var total = driver.findElement(By.className("summary_total_label")).getText();
        double expectedPrice = 29.99 + 15.99 + 3.68;
        String expectedTotal = String.format("Total: $%.2f", expectedPrice);

        Assertions.assertEquals(expectedCountProducts, items.size(), ITEMS_COUNT_ERROR);
        Assertions.assertEquals(backpackTitle, items.get(0).getText(), WRONG_TITLE_ERROR);
        Assertions.assertEquals(tShirtTitle, items.get(1).getText(), WRONG_TITLE_ERROR);
        Assertions.assertEquals(expectedTotal, total, TOTAL_PRICE_ERROR);

        System.out.printf("%nProducts '%s' and '%s' successfully added to the shopping cart.%n", backpackTitle, tShirtTitle);

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

        //Assert that the shopping cart is empty
        var cartItems = driver.findElements(By.className("inventory_item_name"));
        Assertions.assertEquals(0, cartItems.size(), CART_IS_NOT_EMPTY);

        System.out.println(CART_SUCCESS_MESSAGE);

//        //Alternative eay to reset the page to main inventory page
//        driver.findElement(By.id("continue-shopping")).click();
//        Assertions.assertEquals(EXPECTED_URL, driver.getCurrentUrl(), PAGE_RESET_ERROR);
//        System.out.println(PAGE_RESET_SUCCESS);

    }
}