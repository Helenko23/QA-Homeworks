package sausedemotests;

import core.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTests extends BaseTest {

    public static final String USER_IS_NOT_LOGGED_IN = "User is not logged in";
    public static String username = "standard_user";
    public static String password = "secret_sauce";


    @BeforeAll
    public static void beforeAllTests() {
        driver = startBrowser(BrowserTypes.CHROME);

        //Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Navigate to Google.com
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void userAuthenticated_when_validCredentialsProvided() {
        authenticateWithUser(username, password);

        //Assert that the URL has changed to the inventory page, indicating successful login
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assertions.assertEquals(expectedURL, driver.getCurrentUrl(), USER_IS_NOT_LOGGED_IN);

        //Assert that Products element is present, indicating successful login
        By headerContainerXpath = By.xpath("//*[@id='header_container']/div[2]/span");
        WebElement headerContainer = driver.findElement(headerContainerXpath);
        Assertions.assertNotNull(headerContainer, USER_IS_NOT_LOGGED_IN);

        //Print a success message
        System.out.printf("User with username '%s' is logged in successfully.%n", username);

    }

//    @Test
//    public void productAddedToShoppingCar_when_addToCart(){
//        // Add Backpack and T-shirt to shopping cart
//
//        // Click on shopping Cart
//
//        // Assert Items and Totals
//    }

//    @Test
//    public void userDetailsAdded_when_checkoutWithValidInformation(){
//        // Add Backpack and T-shirt to shopping cart
//
//        // Click on shopping Cart
//
//        // Fill Contact Details
//
//        // Assert Details in next step
//    }

//    @Test
//    public void orderCompleted_when_addProduct_and_checkout_withConfirm(){
//        // Add Backpack and T-shirt to shopping cart
//
//        // Click on shopping Cart
//
//        // Fill Contact Details
//
//        // Complete Order
//
//        // Assert Items removed from Shopping Cart
//    }
}
