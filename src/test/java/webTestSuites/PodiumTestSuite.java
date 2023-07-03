package webTestSuites;

import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import listeners.BaseInvokedMethodListener;
import listeners.BaseTestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.MessageFormat;

@Listeners({BaseTestListener.class, BaseInvokedMethodListener.class})
public class PodiumTestSuite extends AutoTestBase {

    private String podiumURL = "https://demo.podium.tools/qa-webchat-lorw/";
    @BeforeMethod
    public void TestSetUp() {
        Pages.InitWebDriver();
    }

    @AfterMethod
    public void TestTearDown()
    {
        Pages.Quit();
    }

    @Test ()
    @TestInfo(description = "Test switching to correct iframes and click on Podium icon.", level = "Smoke")
    public void TestClickPodiumButton() throws Exception
    {
        Step("Go to Podium Demo Website");
            Pages.GoToURL(podiumURL);

        Step("Verify on site by finding podium bubble");
            Pages.PodiumBubble.PodiumBubble().IsVisible(5);
            Info("Found Podium bubble");

        Step("Switch to Podium bubble iframe");
            Pages.PodiumBubble.GoToPodiumBubbleFrame();

        Step("Click on Podium Bubble");
            Pages.PodiumBubble.ClickOnPodiumButton();

        Step("Switch to Podium modal iframe");
            Pages.PodiumBubble.GoToPodiumModalFrame();

        Step("Verify on Podium Modal by locating search bar");
            Pages.PodiumModal.LocationSearchBar().Click(5);
            Info("Podium modal is visible");
    }

    @Test ()
    @TestInfo(description = "Test clicking on first location found in Location modal.")
    public void TestSelectFirstLocation() throws Exception
    {
        Step("Go to Podium Website");
            Pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            Pages.PodiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            Pages.PodiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal is open");

        Step("Click on first location in location list");
            Pages.PodiumModal.SelectFirstLocation();

        Step("Verify on message modal");
            Pages.PodiumModal.NameTextInput().IsVisible(5);
            Info("Within message modal as name text input was found.");
    }

    @Test ()
    @TestInfo(description = "Test confirming correct location is being clicked in the modal")
    public void TestScoreboardOremLocationExists() throws Exception
    {
        String location = "Scoreboard Sports - Orem";

        Step("Go to Podium Website");
            Pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            Pages.PodiumBubble.JumpToPodiumModal();

        Step("Verify {0} is visible in location list", location);
            Pages.PodiumModal.FindByLocation(location).IsVisible(5);
            Info(MessageFormat.format("{0} is in the modal", location));

        Step("Click on {0}", location);
            Pages.PodiumModal.FindByLocation(location).Click(5);

        Step("Verify {0} opened up", location);
            String returnedLocation = Pages.PodiumModal.GetLocationInMessageModal();
            Verify.That(returnedLocation).Equals(location);
    }

    @Test()
    @TestInfo(description = "Test input data in all 3 fields of message modal", level = "Smoke")
    public void TestInputMessageData() throws Exception
    {
        String name = "Art";
        String telephone = "7777777777";
        String message = "Hello QA Tester";
        // String used for circle indicator
        //String emptyMessageIndicator = "M 50 0 A 50 50 0 0 1 50 0";
        // String used for '0/300' indicator
        String correctMessageIndicator = message.length() + " / 300";

        Step("Go to Podium Website");
            Pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            Pages.PodiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            Pages.PodiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal open");

        Step("Click on first location in location list");
            Pages.PodiumModal.SelectFirstLocation();

        Step("Input name in Name text input");
            Pages.PodiumModal.SetTextInNameInput(name, 10, null);

        Step("Verify text was put into name input");
            Pages.PodiumModal.NameCheckmark().IsVisible(5);
            Info("Message has text input");

        Step("Input phone number into mobile phone text input");
            Pages.PodiumModal.SetMobileNumberInput(telephone, 10, null);

        Step("Verify phone number was put into input");
            Pages.PodiumModal.MobileNumberCheckmark().IsVisible(5);
            Info("Mobile number has text input");

        Step("Input message in message text input");
            Pages.PodiumModal.SetMessageInput(message, 10, null);

        Step("Verify message has input of {0}", correctMessageIndicator);
            String messageIndicator = Pages.PodiumModal.MessageInputIndicator();
            Verify.That(messageIndicator).Equals(correctMessageIndicator);

        Step("Verify all inputs have data");
            Pages.PodiumModal.SendButton().IsVisible(5);
            Info("All inputs filled out and send button is valid");
    }

    @Test ()
    @TestInfo(description = "Test confirming subject and terms are opened in a new tab.")
    public void TestClickSubjectTerms() throws Exception
    {
        Step("Go to Podium Website");
            Pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            Pages.PodiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            Pages.PodiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal open");

        Step("Click on first location to open the message modal");
            Pages.PodiumModal.SelectFirstLocation();

        Step("Click on 'use is subject to terms'");
            Pages.PodiumModal.ClickOnTermsButton();

        Step("Switch to Terms and Service page");
            Pages.switchToNewlyOpenTab();

        Step("Verify on Terms and Service page by clicking on 'Terms of Service'");
            Pages.PodiumTermsOfService.TermsOfService().Click(5);
            Info("Clicked on 'Terms of Service'");
    }

    @Test (enabled = false, description = "Bug fixed.")
    @TestInfo(description = "Test proving there is a bug with the return arrow in the message modal.")
    public void TestReturnButtonDoesNotWork() throws Exception
    {
        String message = "Hello QA Tester";
        // String used for circle indicator
        // String emptyMessageIndicator = "M 50 0 A 50 50 0 0 1 50 0";
        // String used for '0/300' indicator
        String correctMessageIndicator = message.length() + " / 300";

        Step("Go to Podium Website");
            Pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            Pages.PodiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            Pages.PodiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal open");

        Step("Click on first location in location list");
            Pages.PodiumModal.SelectFirstLocation();

        Step("Verify on message modal");
            Pages.PodiumModal.NameTextInput().IsVisible(5);
            Info("Within message modal as name text input was found.");

        Step("Click on return arrow");
            Pages.PodiumModal.ClickOnReturnArrowBtn();

        Step("Verify message modal is still open by inputting text into message input");
            Pages.PodiumModal.SetMessageInput(message, 10, null);

        Step("Verify message has input of {0}", correctMessageIndicator);
            String messageIndicator = Pages.PodiumModal.MessageInputIndicator();
            Verify.That(messageIndicator).Equals(correctMessageIndicator);
    }

    @Test
    @TestInfo(description = "Test the return arrow in the message modal.")
    public void TestReturnButtonDoesWork() throws Exception
    {
        Step("Go to Podium Website");
            Pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            Pages.PodiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            Pages.PodiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal open");

        Step("Click on first location in location list");
            Pages.PodiumModal.SelectFirstLocation();

        Step("Verify on message modal");
            Pages.PodiumModal.NameTextInput().IsVisible(5);
            Info("Within message modal as name text input was found.");

        Step("Click on return arrow");
            Pages.PodiumModal.ClickOnReturnArrowBtn();

        Step("Verify on 'Select Location' modal by checking the location searchbar is visible");
            Pages.PodiumModal.LocationSearchBar().IsVisible(5);
            Info("Searchbar is visible");
    }

    @Test ()
    @TestInfo(description = "Test proving location searchbar works.", level = "Smoke")
    public void TestLocationSearchBar() throws Exception
    {
        String bountifulZIP = "84043";
        String bountifulStreet = "E Main St";
        String bountifulCity = "Lehi";
        String bountifulBuildingNum = "1402";
        String bountifulName = "Bountiful";
        String returnedLocation;

        Step("Go to Podium Website");
            Pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            Pages.PodiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            Pages.PodiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal open");

        Step("Input ZIP code {0} into searchbar", bountifulZIP);
            Pages.PodiumModal.SetLocationSearchBarText(bountifulZIP);
            Pages.Sleep(1);

        Step("Verify first location in list is {0} ZIP code", bountifulZIP);
            returnedLocation = Pages.PodiumModal.GetFirstLocationText();
            Verify.That(returnedLocation.contains(bountifulZIP)).IsTrue();

        Step("Input {0} into searchbar", bountifulName);
            Pages.PodiumModal.SetLocationSearchBarText(bountifulName);
            Pages.Sleep(1);

        Step("Verify first location in list is {0}", bountifulName);
            returnedLocation = Pages.PodiumModal.GetFirstLocationText();
            Verify.That(returnedLocation.contains(bountifulName)).IsTrue();

        Step("Input building number {0} into searchbar", bountifulBuildingNum);
            Pages.PodiumModal.SetLocationSearchBarText(bountifulBuildingNum);
            Pages.Sleep(1);

        Step("Verify first location in list is building number {0}", bountifulBuildingNum);
            returnedLocation = Pages.PodiumModal.GetFirstLocationText();
            Verify.That(returnedLocation.contains(bountifulBuildingNum)).IsTrue();

        Step("Input street name {0} into searchbar", bountifulStreet);
            Pages.PodiumModal.SetLocationSearchBarText(bountifulStreet);
            Pages.Sleep(1);

        Step("Verify first location in list is street name {0}", bountifulStreet);
            returnedLocation = Pages.PodiumModal.GetFirstLocationText();
            Verify.That(returnedLocation.contains(bountifulStreet)).IsTrue();

        Step("Input city {0} into searchbar", bountifulCity);
            Pages.PodiumModal.SetLocationSearchBarText(bountifulCity);
            Pages.Sleep(1);

        Step("Verify first location in list is city name {0}", bountifulCity);
            returnedLocation = Pages.PodiumModal.GetFirstLocationText();
            Verify.That(returnedLocation.contains(bountifulCity)).IsTrue();
    }
}
