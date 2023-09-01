package pages.trello;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public abstract class BaseTrelloPage extends BasePage {

    public BaseTrelloPage(WebDriver driver, String pageUrlKey) {
        super(driver, pageUrlKey);
    }
}
