package pages.bing;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class BingResultsPage extends BasePage {

    public BingResultsPage(WebDriver driver) {
        super(driver, "bing.homePage");
    }

    public void assertResultIsPresent(String resultTitle) {
        actions.assertElementPresent("bing.resultsPage.firstResult");
        actions.assertElementAttribute("bing.resultsPage.firstResult", "innerText", resultTitle);
    }
}
