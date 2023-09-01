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
        // Wait for the "Add a new card" button to be clickable
        actions.waitForElementPresent("trello.add.card.addButton", listName);

        // Click on the "Add a new card" button for the specified list
        actions.clickElement("trello.add.card.addButton", listName);

        // Input the card name and press Enter
        actions.typeValueInField(cardName, "trello.card.inputField", listName);
        actions.pressEnter("trello.card.inputField");
    }

    public void moveCardToList(String cardName, String listName) {
    }

    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void deleteBoard() {

        actions.hoverElement("trello.board.menuButton2");
        actions.waitForElementPresent("trello.board.menuButton2");
        actions.clickElement("trello.board.menuButton2");

        actions.hoverElement("trello.menu.closeBoard.button2");
        actions.clickElement("trello.menu.closeBoard.button2");


        WebElement closeButton = driver.findElement(By.className("js-confirm"));
        Actions actions = new Actions(driver);
        actions.moveToElement(closeButton).perform();
        closeButton.click();

        String deleteButtonXPath = "//button[@data-testid='close-board-delete-board-button']";
        WebElement deleteButton = driver.findElement(By.xpath(deleteButtonXPath));
        actions.moveToElement(deleteButton).perform();
        deleteButton.click();

    }

}
