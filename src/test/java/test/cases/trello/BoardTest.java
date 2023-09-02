package test.cases.trello;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.*;
import org.openqa.selenium.WebElement;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;
import org.junit.After;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardTest extends BaseTest {

    public BoardPage boardPage;
    public BoardsPage boardsPage;


    @Test
    public void createBoardWhenCreateBoardClicked() {

        login();

        boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.createBoard();

        boardPage = new BoardPage(actions.getDriver());
        boardPage.assertListExists("To Do");
        boardPage.assertBoardIsNotEmpty("trello.boardName");

        boardPage.deleteBoard();
    }


    @Test
    public void createNewCardInExistingBoardWhenCreateCardClicked() {

        login();

        boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.createBoard();

        UserActions actions = new UserActions();
        actions.waitForElementClickable("trello.allBoards.button");
        actions.clickElement("trello.allBoards.button");

        boardsPage.clickOnBoard("My First Board");

        boardPage = new BoardPage(actions.getDriver());
        boardPage.addCardToList("To Do", "What are you waiting for");

        String cardName = Utils.getUIMappingByKey("trello.board.firstCard");
        //boardPage.assertCardExists(cardName);

        boardPage.deleteBoard();
    }


    @Ignore
    @Test
    public void moveCardBetweenStatesWhenDragAndDropIsUsed() {

    }

    @Test
    public void deleteBoardWhenDeleteButtonIsClicked() {
        login();

        boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.createBoard();

        boardPage = new BoardPage(actions.getDriver());
        boardPage.deleteBoard();
        boardPage.assertBoardIsEmpty();

    }


}
