package test.cases.bing;

import pages.bing.BingHomePage;
import pages.bing.BingResultsPage;

import com.telerikacademy.testframework.Utils;
import org.junit.Test;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BingTests extends BaseTest {

    @Test
    public void searchInputVisible_when_homePageNavigated() {

        BingHomePage home = new BingHomePage(actions.getDriver());

        home.navigateToPage();

        home.assertSearchInputVisible();
    }

    @Test
    public void searchResultsVisible_when_termIsSearched() {

        BingHomePage home = new BingHomePage(actions.getDriver());
        home.navigateToPage();

        home.searchAndSubmit(getUIMappingByKey("bing.homePage.searchText"));

        BingResultsPage results = new BingResultsPage(actions.getDriver());
        results.assertResultIsPresent(getUIMappingByKey("bing.resultsPage.firstResultTitle"));
    }
}
