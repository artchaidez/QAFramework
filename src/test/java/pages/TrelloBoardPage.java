package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
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

    public void AddCardToColumn(String listName, String cardTitle) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[2]//*[text()='Add a card']", listName);
        SeleniumControl list = new SeleniumControl(By.xpath(xpath));
        list.Click(5);

        inputCardTitleText.SetText(cardTitle, 5, false);

        addCardBtn.Click(5);
    }

    public void OpenCard(String cardTitle, String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        SeleniumControl newCard = new SeleniumControl(By.xpath(xpath));
        newCard.Click(5);
    }

    public void DeleteCard(String cardTitle, String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        SeleniumControl newCard = new SeleniumControl(By.xpath(xpath));
        newCard.Click(5);

        trelloCardPage.DeleteCard();
    }


    public SeleniumControl ReturnCardInColumn(String cardTitle, String listName)
    {
        String xpath = String.format("//*[text()='%s']/parent::div/following-sibling::div[1]//*[text()='%s']", listName, cardTitle);
        return new SeleniumControl(By.xpath(xpath));
    }

    public SeleniumControl MenuButton()
    {
        return menuBtn;
    }

    public SeleniumControl MoreInMenuOption()
    {
        return moreInMenuBtn;
    }

    public SeleniumControl ArchivedItemsOption()
    {
        return archiveInMoreBtn;
    }

    public SeleniumControl NoArchivedCards()
    {
        return noCardsArchived;
    }

    private void DropDownActions(String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div//*[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']", listName);
        SeleniumControl dropDownListBtn = new SeleniumControl(By.xpath(xpath));
        dropDownListBtn.Click(5);
    }

    public void DropDownActionsAddCard(String cardTitle, String listName) throws Exception
    {
        String xpath = String.format("//*[text()='%s']/parent::div//*[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']", listName);
        SeleniumControl dropDownListBtn = new SeleniumControl(By.xpath(xpath));
        dropDownListBtn.Click(5);

        SeleniumControl addCard = new SeleniumControl(By.xpath("//*[@class='js-add-card']"));
        addCard.Click(5);

        inputCardTitleText.SetText(cardTitle, 5, false);

        addCardBtn.Click(5);
    }

    public SeleniumControl ReturnSDETBoardPage()
    {
        return sdetBoard;
    }

    public SeleniumControl ReturnCardsInList(String listName)
    {
        String xpathChild = String.format("//*[text()='%s']/parent::div/parent::div//*[@class='list-cards u-fancy-scrollbar u-clearfix js-list-cards js-sortable ui-sortable']//child::*", listName);
        return new SeleniumControl(By.xpath(xpathChild));
    }
}
