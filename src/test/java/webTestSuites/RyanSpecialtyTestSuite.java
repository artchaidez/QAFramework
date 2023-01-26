package webTestSuites;

import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import listeners.TestBaseListener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import webTestFramework.SeleniumControl;

import java.util.ArrayList;

@Listeners(TestBaseListener.class)
public class RyanSpecialtyTestSuite extends AutoTestBase {
    private String email = null;
    private String password = null;

    private String trelloURL = "https://trello.com/b/0QEdvItb/rt-sdet";
    @BeforeMethod
    public void TestSetUp()
    {
        email = System.getenv("trelloEmail");
        password = System.getenv("trelloPassword");
        pages.InitWebDriver();
    }

    @AfterMethod
    public void TestTearDown()
    {
        pages.Quit();
    }

    @Test()
    @TestInfo(description = "Scenario 1: Create a card in the To Do list")
    public void TestCreateNewCardInTodo() throws Exception
    {
        String cardTitle = "new card";
        String listNameTodo = "To Do";
        String cardDescription = "this is a new card";

        Step("Go to Trello Page");
            pages.GoToURL(trelloURL);

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.ReturnSDETBoardPage().IsVisible(5);
            Info("On SDET Board");

        Step(String.format("Add a new card titled '%s' to list '%s'", cardTitle, listNameTodo));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameTodo, cardTitle);

        Step("Verify card was made by opening it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listNameTodo);

        Step(String.format("In the card modal, add the description: %s", cardDescription));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.SetDescriptionText(cardDescription);

        Step("Verify the description is correct");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.FindDescription(cardDescription).IsVisible(5);
            Info("Description matches");

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();

    }

    @Test
    @TestInfo(description = "Scenario 2: Create a card in the To Do list and move it into the Working list")
    public void TestMoveCardIntoWorkingColumn() throws Exception
    {
        String listNameTodo = "To Do";
        String cardTitle = "new card";
        String listNameWorking = "Working";

        Step("Go to Trello Page");
            pages.GoToURL(trelloURL);

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.ReturnSDETBoardPage().IsVisible(5);
            Info("On SDET Board");

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listNameTodo));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameTodo, cardTitle);

        Step(String.format("Verify '%s' was created by opening the card", cardTitle));
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listNameTodo);

        Step(String.format("Under Actions, click on Move and move the card to list '%s'", listNameWorking));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.MoveCardToColumnUnderAction(listNameWorking);

        Step(String.format("Verify card was moved to list '%s'", listNameWorking));
            SeleniumControl card = pages.trelloSignInPage.trelloBoardPage.ReturnCardInColumn(cardTitle, listNameWorking);
            card.IsVisible(5);
            Info(String.format("Card '%s' is in list '%s'", cardTitle, listNameWorking));

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();

    }

    @Test
    @TestInfo(description = "Scenario 3: Create a card and add a checklist with two items in it")
    public void TestAddCheckListWithItems() throws Exception
    {
        String cardTitle = "new card";
        String listName = "Working";
        String checklistTitle = "check list";
        ArrayList<String> checkListItems = new ArrayList<>();
        checkListItems.add("Item 1");
        checkListItems.add("Item 2");

        Step("Go to Trello Page");
            pages.GoToURL(trelloURL);

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.ReturnSDETBoardPage().IsVisible(5);
            Info("On SDET Board");

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listName));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listName, cardTitle);

        Step("Verify the card was created by opening it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listName);

        Step(String.format("Create a checklist named: '%s' and its items", checklistTitle));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CreateNewCheckList(checklistTitle, checkListItems);

        Step("Verify checklist is correct");
            for (int i = 0; i < checkListItems.size(); i++) {
                String xpath = String.format("//*[@class='checklist-items-list js-checklist-items-list js-no-higher-edits ui-sortable']//*[text()='%s']", checkListItems.get(i));
                SeleniumControl checkList = new SeleniumControl(By.xpath(xpath));
                Assert.assertTrue(checkList.IsVisible(5));
            }
            Info("Checklist verified");

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();
    }

    @Test
    @TestInfo(description = "Scenario 4: Create a card with a checklist, complete the checklist, " +
            "and verify the checklist was completed")
    public void TestCompleteToDoItemsChecklist() throws Exception
    {
        String cardTitle = "new card";
        String listName = "Working";
        String checklistTitle = "check list";
        ArrayList<String> checkListItems = new ArrayList<>();
        checkListItems.add("Item 1");
        checkListItems.add("Item 2");

        Step("Go to Trello Page");
            pages.GoToURL(trelloURL);

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.ReturnSDETBoardPage().IsVisible(5);
            Info("On SDET Board");

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listName));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listName, cardTitle);

        Step("Verify the card was created by opening it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listName);

        Step(String.format("Create a checklist named: '%s' and its items", checklistTitle));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CreateNewCheckList(checklistTitle, checkListItems);

        Step("Complete the checklist by clicking on the checkboxes");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.CompleteCheckList(checkListItems);

        Step("Verify checklist is complete");
            String percentage = pages.trelloSignInPage.trelloBoardPage.trelloCardPage.ReturnChecklistCompletionPercentage();
            Assert.assertEquals(percentage, "100%");
            Info("Checklist complete");

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();
    }

    @Test
    @TestInfo(description = "Scenario 5: Create a card in Working list and move it into the Done list")
    public void TestMoveCardIntoDoneColumn() throws Exception
    {
        String cardTitle = "new card";
        String listNameWorking = "Working";
        String listNameDone = "Done";

        Step("Go to Trello Page");
            pages.GoToURL(trelloURL);

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.ReturnSDETBoardPage().IsVisible(5);
            Info("On SDET Board");

        Step(String.format("Add '%s' card to list '%s'", cardTitle, listNameWorking));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameWorking, cardTitle);

        Step("Verify the card was created by opening it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle, listNameWorking);

        Step(String.format("Click on 'in list Working' at the header and move to list '%s'", listNameDone));
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.MoveCardToColumnUsingInList(listNameDone);

        Step(String.format("Verify card is in list '%s'", listNameDone));
            SeleniumControl card = pages.trelloSignInPage.trelloBoardPage.ReturnCardInColumn(cardTitle, listNameDone);
            card.IsVisible(5);
            Info(String.format("Card '%s' is in list '%s'", cardTitle, listNameDone));

        Step("Delete card in modal");
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();
    }

    @Test
    @TestInfo(description = "Testing card can be deleted within card")
    public void TestDeleteCardInCard() throws Exception
    {
        String cardTitle = "my card";
        String listNameTodo = "To Do";

        Step("Go to Trello Page");
            pages.GoToURL(trelloURL);

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.ReturnSDETBoardPage().IsVisible(5);
            Info("On SDET Board");

        Step(String.format("Add a new card titled '%s' to list '%s'", cardTitle, listNameTodo));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameTodo, cardTitle);

        Step("Verify card was made by deleting it");
            pages.trelloSignInPage.trelloBoardPage.DeleteCard(cardTitle, listNameTodo);

        Step("Verify card is no longer in list");
            boolean noCards = false;
            SeleniumControl cards = pages.trelloSignInPage.trelloBoardPage.ReturnCardsInList(listNameTodo);

            try {
                // Look for children in the list
                // If there are no children, it should be caught and log there are no cards
                cards.IsVisible(5);

            } catch (Exception e) {
                noCards = true;
            }

            if (noCards) {
                Info(String.format("'%s' has no cards", listNameTodo));
            } else {
                // If code hits here, the list has children and the card was not properly deleted
                Info(String.format("FAILED: There is a card in '%s'", listNameTodo));
                Assert.fail();
            }

        Step("Verify card was not archived only and properly deleted");
            /* BUG: Sometimes Menu is open. Does not create issues interacting with List, but does when trying to
             * check archive. Try/catch is to take proper steps to correctly check archive regardless of this bug.*/
            try
            {
                pages.trelloSignInPage.trelloBoardPage.MoreInMenuOption().IsVisible(5);

            } catch(Exception e) {
                Info("Open Menu");
                pages.trelloSignInPage.trelloBoardPage.MenuButton().Click(5);
            }

            pages.trelloSignInPage.trelloBoardPage.MoreInMenuOption().Click(5);
            pages.trelloSignInPage.trelloBoardPage.ArchivedItemsOption().Click(5);
            pages.trelloSignInPage.trelloBoardPage.NoArchivedCards().IsVisible(5);
            Info("No cards archived");
    }

    @Test
    @TestInfo(description = "Verify card can be deleted on board")
    public void TestDeleteCardOnBoard() throws Exception
    {
        String cardTitle = "my card";
        String listNameTodo = "To Do";

        Step("Go to Trello Page");
            pages.GoToURL(trelloURL);

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.ReturnSDETBoardPage().IsVisible(5);
            Info("On SDET Board");

        Step(String.format("Add a new card titled '%s' to list '%s'", cardTitle, listNameTodo));
            pages.trelloSignInPage.trelloBoardPage.AddCardToColumn(listNameTodo, cardTitle);

        Step("Verify card was made by deleting it");
            pages.trelloSignInPage.trelloBoardPage.OpenCard(cardTitle,listNameTodo);
            pages.trelloSignInPage.trelloBoardPage.trelloCardPage.DeleteCard();

            Step("Verify card is no longer in list");
            boolean noCards = false;
            SeleniumControl cards = pages.trelloSignInPage.trelloBoardPage.ReturnCardsInList(listNameTodo);

            try {
                // Look for children in the list
                // If there are no children, it should be caught and log there are no cards
                cards.IsVisible(5);

            } catch (Exception e) {
                noCards = true;
            }

            if (noCards) {
                Info(String.format("'%s' has no cards", listNameTodo));
            } else {
                // If code hits here, the list has children and the card was not properly deleted
                Info(String.format("FAILED: There is a card in '%s'", listNameTodo));
                Assert.fail();
            }

        Step("Verify card was not archived only and properly deleted");
            /* BUG: Sometimes Menu is open. Does not create issues interacting with List, but does when trying to
             * check archive. Try/catch is to take proper steps to correctly check archive regardless of this bug.*/
            try
            {
                pages.trelloSignInPage.trelloBoardPage.MoreInMenuOption().IsVisible(5);

            } catch(Exception e) {
                Info("Open Menu");
                pages.trelloSignInPage.trelloBoardPage.MenuButton().Click(5);
            }

            pages.trelloSignInPage.trelloBoardPage.MoreInMenuOption().Click(5);
            pages.trelloSignInPage.trelloBoardPage.ArchivedItemsOption().Click(5);
            pages.trelloSignInPage.trelloBoardPage.NoArchivedCards().IsVisible(5);
            Info("No cards archived");
    }

    @Test
    @TestInfo(description = "Provide a second way to add a card to a list")
    public void TestDropDownAddCardOption() throws Exception
    {
        String cardTitle = "my card";
        String listNameTodo = "To Do";

        Step("Go to Trello Page");
            pages.GoToURL(trelloURL);

        Step("Log into Trello");
            pages.trelloSignInPage.LogIntoAccountOnMainPage(email, password);

        Step("Verify RT SDET board is open");
            pages.trelloSignInPage.trelloBoardPage.ReturnSDETBoardPage().IsVisible(5);
            Info("On SDET Board");

        Step("Add card using List actions dropdown");
            pages.trelloSignInPage.trelloBoardPage.DropDownActionsAddCard(cardTitle, listNameTodo);

        Step("Verify card was made");
            SeleniumControl card = pages.trelloSignInPage.trelloBoardPage.ReturnCardInColumn(cardTitle, listNameTodo);
            card.IsVisible(5);
            Info(String.format("Card '%s' is in list '%s'", cardTitle, listNameTodo));

        Step("Delete card");
            pages.trelloSignInPage.trelloBoardPage.DeleteCard(cardTitle, listNameTodo);
    }
}
