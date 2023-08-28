package sausedemotests;

import core.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTests extends BaseTest {

    public static final String USER_IS_NOT_LOGGED_IN = "User is not logged in";

    @BeforeAll
    public static void beforeAllTests() {
        driver = startBrowser(BrowserTypes.CHROME);

        //Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        //Navigate to Google.com
        driver.get(BASE_URL);
    }

    @Test
    public void userAuthenticated_when_validCredentialsProvided() {
        authenticateWithUser(username, password);

        //Assert that the URL has changed to the inventory page, indicating successful login
        Assertions.assertEquals(EXPECTED_URL, driver.getCurrentUrl(), USER_IS_NOT_LOGGED_IN);

        //Assert that Products element is present, indicating successful login
        By headerContainerXpath = By.xpath("//*[@id='header_container']/div[2]/span");
        WebElement headerContainer = driver.findElement(headerContainerXpath);
        Assertions.assertNotNull(headerContainer, USER_IS_NOT_LOGGED_IN);

        //Print a success message
        System.out.printf("%nUser with username '%s' is logged in successfully.%n", username);

    }
}
