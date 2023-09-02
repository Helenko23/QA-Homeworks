package pages.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BoardPage extends BaseTrelloPage {

    public BoardPage(WebDriver driver) {
        super(driver, "trello.boardPage");
    }


    public void addCardToList(String listName, String cardName) {
        actions.waitForElementPresent("trello.add.card.addButton", listName);

        actions.clickElement("trello.add.card.addButton", listName);

        actions.typeValueInField(cardName, "trello.card.inputField", listName);
        //actions.waitForElementVisible("trello.card.inputField");
        actions.waitForElementClickable("trello.card.inputField");
        actions.pressEnter("trello.card.inputField");
    }

    public void moveCardToList(String cardName, String listName) {
    }

    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void deleteBoard() {

        actions.waitForElementClickable("trello.board.menuButton");
        actions.clickElement("trello.board.menuButton");

        actions.waitForElementClickable("trello.menu.closeBoard.button");
        actions.clickElement("trello.menu.closeBoard.button");

        actions.waitForElementClickable("trello.board.submitButton");
        actions.clickElement("trello.board.submitButton");

    }

    public void assertCardExists(String cardName) {
        actions.waitForElementPresent("trello.boardPage.cardByName", cardName);
    }

    public void assertBoardIsNotEmpty(String boardName) {
        actions.waitForElementClickable("trello.boardPage.RecentProject");
        actions.clickElement("trello.boardPage.RecentProject");
        actions.waitForElementPresent("trello.header.create.ProjectView", boardName);
    }
    public void assertBoardIsEmpty() {
        actions.waitForElementVisible("trello.board.icon");
        actions.clickElement("trello.board.icon");
        actions.waitForElementVisible("trello.empty.board");
        actions.clickElement("trello.empty.board");

    }
    public void assertCardMoved(String listName,String cardName ) {
        actions.waitForElementPresent("trello.boardPage.assertCardMovedToNewList", listName, cardName);
    }

}
