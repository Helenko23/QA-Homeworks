package pages.bing;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class BingHomePage extends BasePage {

    public BingHomePage(WebDriver driver) {
        super(driver, "bing.homePage");
    }

    public void enterSearchTerm(String term) {
        actions.typeValueInField(term, "bing.homePage.searchInput");
    }

    public void searchAndSubmit(String term) {
        enterSearchTerm(term);
        actions.clickElement("bing.homePage.searchButton");
    }

    public void assertSearchInputVisible() {
        actions.assertElementPresent("bing.homePage.searchInput");
    }
}
