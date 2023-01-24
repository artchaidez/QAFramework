package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import webTestFramework.SeleniumControl;

public class PodiumModal extends UIBase {

    // Objects are private final
    private final SeleniumControl modal = new SeleniumControl(By.xpath("//*[contains(@class, 'SearchInput')]"));

    private final SeleniumControl firstLocation = new SeleniumControl(By.xpath("//*[@class = 'LocationContainer StaggerFadeIn3 LocationContainer--desktop']"));

    private final SeleniumControl nameTextInput = new SeleniumControl(By.xpath("//*[@id= 'Name']"));

    private final SeleniumControl nameCheckMark = new SeleniumControl(By.xpath("//*[@class='TextInput']//*[@class='checkmark']"));

    private final SeleniumControl mobileNumberTextInput = new SeleniumControl(By.xpath("//*[@id= 'Mobile Phone']"));

    private final SeleniumControl mobileNumberCheckmark = new SeleniumControl(By.xpath("//*[@class='TextInput TextInput--tel']//*[@class='checkmark']"));

    private final SeleniumControl messageTextInput = new SeleniumControl(By.xpath("//*[@id= 'Message']"));

    private final SeleniumControl sendButtonValid = new SeleniumControl(By.xpath("//*[@class= 'SendButton SendButton--valid']"));

    private final SeleniumControl subjectTermsLink = new SeleniumControl(By.className("terms"));

    private final SeleniumControl returnArrowBtn = new SeleniumControl((By.xpath("//*[@class='SendSmsPage__ArrowIcon']")));

    private final SeleniumControl locationText = new SeleniumControl(By.xpath("//*[@class='SendSmsPage__CurrentLocationName']"));

    private final SeleniumControl locationSearchBar = new SeleniumControl(By.xpath("//*[@name='Search Locations']"));

    private final SeleniumControl messageCharCount = new SeleniumControl(By.xpath("//*[contains(@stroke, '#3074dc')]"));

    private final SeleniumControl clearLocationSearchBar = new SeleniumControl(By.xpath("//*[@class='SearchInput__Reset']"));

    // Methods are public
    public void SelectFirstLocation() throws Exception
    {
        firstLocation.Click(5);
        Info("Selected first location in modal");
    }

    public String GetFirstLocationText() {
        return firstLocation.getText();
    }

    public void SearchBarInput(String data) {
        Assert.assertTrue(GetFirstLocationText().contains(data));
    }

    public SeleniumControl NameTextInput()
    {
        return nameTextInput;
    }

    public void SetTextInNameInput(String data, int Max_Retries, Boolean escape) throws Exception
    {
        nameTextInput.SetText(data, Max_Retries, escape);
    }

    public SeleniumControl NameCheckmark()
    {
        return nameCheckMark;
    }

    public void SetMobileNumberInput(String data, int Max_Retries, Boolean escape) throws Exception
    {
        mobileNumberTextInput.SetText(data, Max_Retries, escape);
    }

    public SeleniumControl MobileNumberCheckmark()
    {
        return mobileNumberCheckmark;
    }

    public void SetMessageInput(String data, int Max_Retries, Boolean escape) throws Exception
    {
        messageTextInput.SetText(data, Max_Retries, escape);
    }

    public String MessageInputIndicator()
    {
        return messageCharCount.getAttribute("d");
    }

    public SeleniumControl SendButton()
    {
        return sendButtonValid;
    }

    public void ClickOnTermsButton() throws Exception
    {
        subjectTermsLink.Click(5);
        Info("Clicked on 'use is subject to terms'");
    }

    /** Click on arrow on message modal to return to location list modal */
    public void ClickOnReturnArrowBtn() throws Exception
    {
        returnArrowBtn.Click(5);
        Info("Clicked on return arrow button");
    }

    public String GetLocationInMessageModal()
    {
        return locationText.getText();
    }

    public SeleniumControl LocationSearchBar()
    {
        return locationSearchBar;
    }

    public void SetLocationSearchBarText(String zipAddress) throws Exception
    {
        if (clearLocationSearchBar.IsVisible(10))
            ClickClearSearchBar();
        locationSearchBar.SetText(zipAddress, 10, null);
    }

    public void ClickClearSearchBar() throws Exception
    {
        clearLocationSearchBar.Click(5);
    }

    public SeleniumControl FindByLocation(String location)
    {
        return new SeleniumControl(By.xpath(String.format("//*[text()= \"%s\"]", location)));
    }

    /** Looks for searchbar within iframe podium-modal.  */
    public SeleniumControl Modal()
    {
        return modal;
    }

}
