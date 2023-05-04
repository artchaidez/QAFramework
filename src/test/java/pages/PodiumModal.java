package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

public class PodiumModal extends UIBase {

    // Objects are private final
    private final SeleniumControl firstLocation = new SeleniumControl(By.xpath("//*[@class = 'LocationContainer StaggerFadeIn3 LocationContainer--desktop']"));

    private final SeleniumControl nameTextInput = new SeleniumControl(By.xpath("//*[@id= 'Name']"));

    private final SeleniumControl nameCheckMark = new SeleniumControl(By.xpath("//*[@class='TextInput']//*[@class='checkmark']"));

    private final SeleniumControl mobileNumberTextInput = new SeleniumControl(By.xpath("//*[@id= 'Mobile Phone']"));

    private final SeleniumControl mobileNumberCheckmark = new SeleniumControl(By.xpath("//*[@class='TextInput TextInput--tel']//*[@class='checkmark']"));

    private final SeleniumControl messageTextInput = new SeleniumControl(By.xpath("//*[@id= 'Message']"));

    private final SeleniumControl sendButtonValid = new SeleniumControl(By.xpath("//*[@class= 'SendButton SendButton--valid']"));

    private final SeleniumControl subjectTermsLink = new SeleniumControl(By.xpath("//*[text()='Use is subject to terms.']"));

    private final SeleniumControl returnArrowBtn = new SeleniumControl((By.xpath("//*[@class='SendSmsPage__ArrowIcon']")));

    private final SeleniumControl locationText = new SeleniumControl(By.xpath("//*[@class='SendSmsPage__CurrentLocationName']"));

    private final SeleniumControl locationSearchBar = new SeleniumControl(By.xpath("//*[@name='Search Locations']"));

    // There is no consistency which indicator appears. //*[contains(@stroke, '#3074dc')] is the circle indicator
    private final SeleniumControl messageCharCount = new SeleniumControl(By.xpath("//*[@class='message-char-count']"));

    private final SeleniumControl clearLocationSearchBar = new SeleniumControl(By.xpath("//*[@class='SearchInput__Reset']"));

    // Methods are public
    /** Clicks on first available location on list.  Must be on 'Select Location' modal.*/
    public void SelectFirstLocation() throws Exception
    {
        firstLocation.Click(5);
        Info("Selected first location in modal");
    }

    /** Returns name of first location on list.  Must be on 'Select Location' modal.*/
    public String GetFirstLocationText() {
        return firstLocation.getText();
    }

    /** Returns web element for 'Name'. Will be a text box. Must be on 'Send message' modal.*/
    public SeleniumControl NameTextInput()
    {
        return nameTextInput;
    }

    /** Set text for web element 'Name' to send a message. Must be on 'Send message' modal.*/
    public void SetTextInNameInput(String data, int Max_Retries, Boolean escape) throws Exception
    {
        nameTextInput.SetText(data, Max_Retries, escape);
    }

    /** Returns web element that is a checkmark. Appears next to text box 'Name'; will only
     * appear if there is inputted text. Must be on 'Send message' modal.*/
    public SeleniumControl NameCheckmark()
    {
        return nameCheckMark;
    }

    /** Set text for web element 'Mobile Number' to send a message. Must be on 'Send message' modal.*/
    public void SetMobileNumberInput(String data, int Max_Retries, Boolean escape) throws Exception
    {
        mobileNumberTextInput.SetText(data, Max_Retries, escape);
    }

    /** Returns web element that is a checkmark. Appears next to text box 'Mobile Number'; will only
     * appear if there is inputted text. Must be on 'Send message' modal.*/
    public SeleniumControl MobileNumberCheckmark()
    {
        return mobileNumberCheckmark;
    }

    /** Set text for web element 'Message' to send a message. Must be on 'Send message' modal.*/
    public void SetMessageInput(String data, int Max_Retries, Boolean escape) throws Exception
    {
        messageTextInput.SetText(data, Max_Retries, escape);
    }

    /** NOTE: Used in test where bug shows indicator '0/300' or circle indicator. Cannot be tested properly as
     * there is no consistency which indicator appears.
     * Returns web element of total character count. By default, an empty message will be 0 / 300
     * and will count the total characters in the message. Must be on 'Send message' modal.*/
    public String MessageInputIndicator()
    {
        return messageCharCount.getText();
        // return messageCharCount.getAttribute("d").replaceAll("[path()\"]", "");
    }

    /** Returns web element 'Send' button. Will only be clickable when 'Name', 'Mobile Number',
     * and 'Message' text boxes all have input. Must be on 'Send message' modal.*/
    public SeleniumControl SendButton()
    {
        return sendButtonValid;
    }

    /** Clicks on link that opens up 'Subject and Terms' in a new tab. Must be on 'Send message' modal. */
    public void ClickOnTermsButton() throws Exception
    {
        subjectTermsLink.Click(5);
        Info("Clicked on 'use is subject to terms'");
    }

    /** Click on arrow on message modal to return to location list modal. Must be on 'Send message' modal. */
    public void ClickOnReturnArrowBtn() throws Exception
    {
        returnArrowBtn.Click(5);
        Info("Clicked on return arrow button");
    }

    /** Returns the name of the location, if modal is open to send a message. Must be on 'Send message' modal. */
    public String GetLocationInMessageModal()
    {
        return locationText.getText();
    }

    /** Returns web element for the search bar. Must be on 'Select Location' modal.*/
    public SeleniumControl LocationSearchBar()
    {
        return locationSearchBar;
    }

    /** Set text for search bar. Must be in 'Select Location' modal.*/
    public void SetLocationSearchBarText(String zipAddress) throws Exception
    {
        if (clearLocationSearchBar.IsVisible(10))
            ClickClearSearchBar();
        locationSearchBar.SetText(zipAddress, 10, null);
    }

    /** Clears search bar text. Must be on 'Select Location' modal.*/
    public void ClickClearSearchBar() throws Exception
    {
        clearLocationSearchBar.Click(5);
    }

    /** Returns Selenium Control of location, if found. Must be on 'Select Location' modal.*/
    public SeleniumControl FindByLocation(String location)
    {
        return new SeleniumControl(By.xpath(String.format("//*[text()= \"%s\"]", location)));
    }

}
