package test.cases.trello;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.*;
import org.openqa.selenium.WebElement;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardTest extends BaseTest {

    //public String boardId;


    @Test
    public void createBoardWhenCreateBoardClicked() {

        login();

        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.createBoard();

        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.assertListExists("To Do");

        boardPage.deleteBoard();
    }

    @Test
    public void createNewCardInExistingBoardWhenCreateCardClicked() {

        login();

        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.clickOnBoard("My First Board");

        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.addCardToList("To Do", "What are you waiting for?");


    }


    @Ignore
    @Test
    public void moveCardBetweenStatesWhenDragAndDropIsUsed() {
//         ListModel responseListFrom = trelloApi.createList(createdBoard.id, "ListNameAutoFrom");
//         ListModel responseListTo = trelloApi.createList(createdBoard.id, "ListNameAutoTo");
//         trelloApi.createCard(responseListFrom.id, "CardNameAuto");
//
//         actions.dragAndDropElement("trello.boardPage.cardByName", "trello.boardPage.listByName");
    }



    @Ignore
    @Test
    public void deleteBoardWhenDeleteButtonIsClicked() {
        //API: Create a board
        //API: Delete board

    }



}
