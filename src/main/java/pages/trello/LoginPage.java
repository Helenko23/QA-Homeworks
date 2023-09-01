package pages.trello;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends BaseTrelloPage {

    public static String boardId = null;

    public LoginPage(WebDriver driver) {
        super(driver, "trello.loginPage");
    }

    public void loginUser(String userKey) {
        String username = getConfigPropertyByKey("trello.users." + userKey + ".username");
        String password = getConfigPropertyByKey("trello.users." + userKey + ".password");

        navigateToPage();
        assertPageNavigated();

        actions.waitForElementVisible("trello.loginPage.username");

        actions.typeValueInField(username, "trello.loginPage.username");
        actions.waitForElementVisible("trello.loginPage.loginButton");
        actions.clickElement("trello.loginPage.loginButton");

        actions.waitForElementClickable("trello.loginPage.loginSubmitButton");
        actions.waitForElementClickable("trello.loginPage.password");

        actions.typeValueInField(password, "trello.loginPage.password");
        actions.clickElement("trello.loginPage.loginSubmitButton");

        actions.waitForElementVisible("trello.header.member.menuButton");
    }
}
