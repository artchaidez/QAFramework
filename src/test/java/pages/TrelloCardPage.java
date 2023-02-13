package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

import java.util.ArrayList;

public class TrelloCardPage extends UIBase {
    private final SeleniumControl archiveBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-archive-card']"));

    private final SeleniumControl deleteBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-delete-card negate']"));

    private final SeleniumControl confirmDeleteBtn = new SeleniumControl(By.xpath("//*[@class='js-confirm full nch-button nch-button--danger']"));

    private final SeleniumControl closeCard = new SeleniumControl(By.xpath("//*[@class= 'icon-md icon-close dialog-close-button js-close-window']"));

    private final SeleniumControl moveCardToNewList = new SeleniumControl(By.xpath("//*[@class='nch-button nch-button--primary wide js-submit']"));

    private final SeleniumControl underActionsMoveBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-move-card']"));

    private final SeleniumControl inHeaderMoveBtn = new SeleniumControl(By.xpath("//*[@class= 'js-open-move-from-header']"));

    private final SeleniumControl checkListBtn = new SeleniumControl(By.xpath("//*[@class='button-link js-add-checklist-menu']"));

    private final SeleniumControl checkListTitleText = new SeleniumControl(By.xpath("//*[@id='id-checklist']"));

    private final SeleniumControl addCheckListBtn = new SeleniumControl(By.xpath("//*[@class='nch-button nch-button--primary wide confirm js-add-checklist']"));

    private final SeleniumControl addItemToChecklistText = new SeleniumControl(By.xpath("//*[@class='edit field checklist-new-item-text js-new-checklist-item-input']"));

    private final SeleniumControl addItemToCheckListText = new SeleniumControl(By.xpath("//*[@class='nch-button nch-button--primary confirm mod-submit-edit js-add-checklist-item']"));

    private final SeleniumControl checkListCompletionPercentage = new SeleniumControl(By.xpath("//*[@class='checklist-progress-percentage js-checklist-progress-percent']"));

    private final SeleniumControl openCardDescription = new SeleniumControl(By.xpath("//*[@class='description-fake-text-area js-description-fake-text-area hide-on-edit js-edit-desc js-hide-with-draft']"));

    private final SeleniumControl editCardDescription = new SeleniumControl(By.xpath("//*[@role='textbox']"));
    private final SeleniumControl saveDescriptionBtn = new SeleniumControl(By.xpath("//*[@class= 'nch-button nch-button--primary confirm mod-submit-edit js-save-edit']"));

    private final SeleniumControl sdetBoard = new SeleniumControl(By.xpath("//*[@class='js-board-editing-target board-header-btn-text']"));

    /** Within a card, delete card. */
    public void DeleteCard() throws Exception
    {
        archiveBtn.Click(5);
        deleteBtn.Click(5);
        confirmDeleteBtn.Click(5);
        sdetBoard.IsVisible(5);
        Info("Card deleted");
    }

    /** Within a card, close card. */
    public void CloseCard() throws Exception
    {
        closeCard.Click(5);
        Info("Closed card");
    }

    /** Within a card, move the card to a different list. Used in MoveCardToListUnderAction() and
     * MoveCardListUnderAction. */
    private void MoveCardToList(String listName) throws Exception
    {
        String xpath = String.format("//*[@class='button-link setting form-grid-child form-grid-child-threequarters']//option[contains(.,'%s')]", listName);
        SeleniumControl workingOnMoveCardList = new SeleniumControl(By.xpath(xpath));
        workingOnMoveCardList.Click(5);

        moveCardToNewList.Click(5);
        Info(String.format("Moved card into '%s'", listName));
    }

    /** Within a card, move card to a new list using an Action. Same functionality as MoveCardToListUsingInList*/
    public void MoveCardToListUnderAction(String listName) throws Exception
    {
        underActionsMoveBtn.Click(5);
        MoveCardToList(listName);
    }

    /** Within a card, move card to a new list by clicking on current list name in the header (list
     * name will be underlined). Same functionality as MoveCardToListUnderAction*/
    public void MoveCardToListUsingInList(String listName) throws Exception
    {
        inHeaderMoveBtn.Click(5);
        MoveCardToList(listName);
    }

    /** Within a card, create a new checklist. */
    public void CreateNewCheckList(String checklistTitle, ArrayList<String> checkListItems) throws Exception
    {
        checkListBtn.Click(5);

        checkListTitleText.SetText(checklistTitle, 5, false);

        addCheckListBtn.Click(5);

        for (int i = 0; i < checkListItems.size(); i++)
        {
            addItemToChecklistText.SetText(checkListItems.get(i), 5, false);
            addItemToCheckListText.Click(5);
        }
        Info(String.format("Checklist '%s' created", checklistTitle));
    }

    /** Within a card, complete the checklist found in the card. */
    public void CompleteCheckList(ArrayList<String> checkListItems) throws Exception
    {
        for (int i = 0; i < checkListItems.size(); i++) {
            String xpath = String.format("//*[text()='%s']/ancestor::div[@class= 'checklist-item no-assignee no-due']//*[@data-testid='checklist-item-checkbox']", checkListItems.get(i));
            SeleniumControl checkList = new SeleniumControl(By.xpath(xpath));
            checkList.Click(5);
            Sleep(1);
        }
        Info("Checklist complete");

    }

    /** Within a card, set the description text. */
    public void SetDescriptionText(String cardDescription) throws Exception
    {

        try {
            editCardDescription.SetText(cardDescription, 5, false);
        } catch (Exception e)
        {
            openCardDescription.Click(5);
            editCardDescription.SetText(cardDescription,5,false);
        }

        Info("Card description set");
        SaveDescription();
    }

    /** Within a card, save the description text. This function is private and used
     *  within SetDescriptionText() */
    private void SaveDescription() throws Exception
    {
        saveDescriptionBtn.Click(5);
        Info("Card description saved");
    }

    /** Within a card, return Selenium Control of description of the card. */
    public SeleniumControl FindDescription(String cardDescription)
    {
        return new SeleniumControl(By.xpath(String.format("//*[text()='%s']", cardDescription)));
    }

    /** Within a card, return the completion percentage of the card's checklist. */
    public String ReturnChecklistCompletionPercentage()
    {
        return checkListCompletionPercentage.getText();
    }
}
