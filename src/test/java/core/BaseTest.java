package core;

import org.example.BrowserTypes;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String EXPECTED_URL = "https://www.saucedemo.com/inventory.html";
    public static final String EXPECTED_CART_URL= "https://www.saucedemo.com/cart.html";
    public static String username = "standard_user";
    public static String password = "secret_sauce";
    public static WebDriver driver;
    public static WebDriverWait wait;

    @AfterEach
    public void afterTest(){
        //Close driver
        driver.close();
    }

    protected static WebDriver startBrowser(BrowserTypes browserType) {
        //Setup Browser
        switch (browserType){
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions);
        }
        return null;
    }

    protected static void authenticateWithUser(String username, String pass) {
        WebElement usernameInput = driver.findElement(By.xpath("//input[@data-test='username']"));
        usernameInput.sendKeys(username);

        WebElement password = driver.findElement(By.xpath("//input[@data-test='password']"));
        password.sendKeys(pass);

        WebElement loginButton = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        loginButton.click();

        WebElement inventoryPageTitle = driver.findElement(By.xpath("//div[@class='app_logo']"));
        wait.until(ExpectedConditions.visibilityOf(inventoryPageTitle));
    }

    protected WebElement getProductByTitle(String title){
        return driver.findElement(By.xpath(String.format("//div[@class='inventory_item' and descendant::div[text()='%s']]", title)));
    }

    protected static void fillShippingDetails(String firstName, String lastName, String zip) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(zip);
    }

}