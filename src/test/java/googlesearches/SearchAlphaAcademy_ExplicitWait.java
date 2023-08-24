package googlesearches;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchAlphaAcademy_ExplicitWait {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private final String searchText = "Telerik Academy Alpha";
    private final String searchResult = "IT Career Start in 6 Months - Telerik Academy Alpha";
    private final String errorMessage = "Search result not found";

    @BeforeAll
    public static void driverSetUp() {
        //Setup browser
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterAll
    public static void driverClose() {
        driver.close();
    }

    @Test
    public void resultFound_when_SearchTermProvided_onBing() {
        //Navigate to bing.com
        driver.get("https://bing.com");

        //Agree to consent
        WebElement consentButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='bnp_btn_accept']")));
        consentButton.click();

        //Type text in search field
        WebElement searchField = driver.findElement(By.xpath("//input[@id='sb_form_q']"));
        searchField.sendKeys(searchText);

        //Click search button
        WebElement searchIconButton = driver.findElement(By.xpath("//label[@id='search_icon']"));
        searchIconButton.click();

        //Assert result found
        WebElement firstResult = driver.findElement(By.xpath("//ol[@id='b_results']/li[1]//h2/a"));
        Assertions.assertEquals(searchResult, firstResult.getText(), errorMessage);

    }

    @Test
    public void resultFound_when_SearchTermProvided_onGoogle() {
        //Navigate to bing.com
        driver.get("https://google.com");

        //Agree to consent
        WebElement consentButton = driver.findElement(By.xpath("//button[@id='L2AGLb']"));
        consentButton.click();

        //Type text in search field
        WebElement searchField = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
        searchField.sendKeys(searchText);

        //Click search button
        WebElement searchButton = driver.findElement(By.xpath("(//input[@type='submit' and @name='btnK'])[1]"));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();

        //Assert result found
        WebElement firstResult = driver.findElement(By.xpath("(//a/h3)[1]"));

        Assertions.assertEquals(searchResult, firstResult.getText(), errorMessage);

    }


}
