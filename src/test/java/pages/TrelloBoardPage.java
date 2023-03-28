package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import webTestFramework.SeleniumControl;

public class TrelloBoardPage extends UIBase {
    private final SeleniumControl inputCardTitleText = new SeleniumControl(By.xpath("//*[@class= 'list-card-composer-textarea js-card-title']"));

    private final SeleniumControl addCardBtn = new SeleniumControl(By.xpath("//*[contains(@value, 'Add card')]"));

    private final SeleniumControl menuBtn = new SeleniumControl(By.xpath("//*[@class='show-sidebar-button-react-root']"));

    private final SeleniumControl moreInMenuBtn = new SeleniumControl(By.xpath("//*[@class='board-menu-navigation-item-link js-open-more']"));

    private final SeleniumControl archiveInMoreBtn = new SeleniumControl(By.xpath("//*[@class='board-menu-navigation-item-link js-open-archive']"));

    private final SeleniumControl noCardsArchived = new SeleniumControl(By.xpath("//*[@class='empty-list js-empty-message']"));

    private final SeleniumControl sdetBoard = new SeleniumControl(By.xpath("//*[@class='js-board-editing-target board-header-btn-text']"));

    public TrelloCardPage trelloCardPage;

    public TrelloBoardPage()
    {
        trelloCardPage = new TrelloCardPage();
    }

    /** Add card to list. Args 'listName' should be a list that exists on the page, args
     * 'cardTitle' will be card title. */
    public void AddCardToList(String listName, String cardTitle) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[2]//*[text()='Add a card']", listName);
        SeleniumControl list = new SeleniumControl(By.xpath(xpath));
        list.Click(5);

        inputCardTitleText.SetText(cardTitle, 5, false);

        addCardBtn.Click(5);
        Info(String.format("Added '%s' to '%s'", cardTitle, listName));
    }

    /** Open specific card 'cardTitle' in list 'listName.' */
    public void OpenCard(String cardTitle, String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        SeleniumControl newCard = new SeleniumControl(By.xpath(xpath));

        for (int attempt = 0; attempt < 10; attempt++)
        {
            try {
                newCard.IsVisible(5);
                break;
            } catch (WebDriverException e)
            {

            }
        }

        newCard.Click(5);
        Info(String.format("Opened '%s' in '%s'", cardTitle, listName));
    }

    /** Delete specific card 'cardTitle' in list 'listName.' */
    public void DeleteCard(String cardTitle, String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        SeleniumControl newCard = new SeleniumControl(By.xpath(xpath));
        newCard.Click(5);

        trelloCardPage.DeleteCard();
    }

    /** Return card, if found in list. */
    public SeleniumControl ReturnCardInList(String cardTitle, String listName)
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        return new SeleniumControl(By.xpath(xpath));
    }

    /** Return menu button found on top right of page. */
    public SeleniumControl MenuButton()
    {
        return menuBtn;
    }

    /** Returns more in menu option; must be used after clicking on menu button- MenuButton() */
    public SeleniumControl MoreInMenuOption()
    {
        return moreInMenuBtn;
    }

    /** Returns archived items option; must be used after clicking on more in menu option- MoreInMenuOption() */
    public SeleniumControl ArchivedItemsOption()
    {
        return archiveInMoreBtn;
    }

    /** Returns no archived cards xpath; must be used after archived items option- ArchivedItemsOption() */
    public SeleniumControl NoArchivedCards()
    {
        return noCardsArchived;
    }

    /** Click on dropdown actions button on the top right of the list if the list exists. */
    private void DropDownActions(String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div//*[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']", listName);
        SeleniumControl dropDownListBtn = new SeleniumControl(By.xpath(xpath));
        dropDownListBtn.Click(5);
    }

    /** Click on dropdown actions button on the top right of the list (if list exists) and add card. */
    public void DropDownActionsAddCard(String cardTitle, String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div//*[@class='list-header-extras-menu js-open-list-menu icon-sm icon-overflow-menu-horizontal']", listName);
        SeleniumControl dropDownListBtn = new SeleniumControl(By.xpath(xpath));
        dropDownListBtn.Click(5);

        SeleniumControl addCard = new SeleniumControl(By.xpath("//*[@class='js-add-card']"));
        addCard.Click(5);

        inputCardTitleText.SetText(cardTitle, 5, false);

        addCardBtn.Click(5);
        Info(String.format("Added '%s' to '%s'", cardTitle, listName));
    }

    /** Return Selenium Control from board, if xpath is found. */
    public SeleniumControl ReturnSDETBoardPage()
    {
        for (int attempt = 0; attempt < 10; attempt++)
        {
            try {
                sdetBoard.IsVisible(5);
                break;
            } catch (WebDriverException e)
            {

            }
        }
        return sdetBoard;
    }

    /** Returns Selenium Control of all cards found in list. */
    public SeleniumControl ReturnCardsInList(String listName)
    {
        String xpathChild = String.format("//*[text()='%s']/parent::div/parent::div//*[@class='list-cards u-fancy-scrollbar u-clearfix js-list-cards js-sortable ui-sortable']//child::*", listName);
        return new SeleniumControl(By.xpath(xpathChild));
    }
}
