package webTestSuites;

import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import listeners.BaseInvokedMethodListener;
import listeners.BaseTestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Listeners({BaseTestListener.class, BaseInvokedMethodListener.class})
public class PodiumTestSuite extends AutoTestBase {

    private String podiumURL = "https://demo.podium.tools/qa-webchat-lorw/";
    @BeforeMethod
    public void TestSetUp() throws MalformedURLException {
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

        Step(String.format("Verify %s is in location list", location));
            Pages.PodiumModal.FindByLocation(location).IsVisible(5);
            Info(String.format("'%s' is in the modal", location));

        Step(String.format("Click on %s", location));
            Pages.PodiumModal.FindByLocation(location).Click(5);

        Step(String.format("Verify %s opened up", location));
            String returnedLocation = Pages.PodiumModal.GetLocationInMessageModal();
            Verify.That(returnedLocation).Equals(location);
            Info(String.format("'%s' opened up", location));
    }

    @Test ()
    @TestInfo(description = "Test input data in all 3 fields of message modal", level = "Smoke")
    public void TestInputMessageData() throws Exception
    {
        String name = "Art";
            String telephone = "7777777777";
            String message = "Hello QA Tester";

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

        Step("Verify message has input");
            String emptyMessageIndicator = "M 50 0 A 50 50 0 0 1 50 0";
            String messageIndicator = Pages.PodiumModal.MessageInputIndicator();
            Verify.That(messageIndicator).DoesNotEqual(emptyMessageIndicator);
            Info("Message has text input");

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

    // TODO: Create new test; should test clicking on Return button correctly
    @Test (enabled = false, description = "This test was to show there was a bug on site and is now fixed.")
    @TestInfo(description = "Test proving there is a bug with the return arrow in the message modal.")
    public void TestReturnButtonDoesNotWork() throws Exception
    {
        String message = "There is a bug with the return arrow on the message widget!";

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

        Step("Verify text was inputted into message input");
            String emptyMessageIndicator = "M 50 0 A 50 50 0 0 1 50 0";
            String messageIndicator = Pages.PodiumModal.MessageInputIndicator();
            Verify.That(messageIndicator).DoesNotEqual(emptyMessageIndicator);
            Info("Message has text input");
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

        Step(String.format("Input ZIP code '%s' into searchbar", bountifulZIP));
            Pages.PodiumModal.SetLocationSearchBarText(bountifulZIP);
            Pages.Sleep(1);

        Step(String.format("Verify first location in list is '%s' ZIP code", bountifulZIP));
            returnedLocation = Pages.PodiumModal.GetFirstLocationText();
            Verify.That(returnedLocation.contains(bountifulZIP)).IsTrue();
            Info(String.format("Correctly searched for %s ZIP code", bountifulZIP));

        Step(String.format("Input '%s' into searchbar", bountifulName));
            Pages.PodiumModal.SetLocationSearchBarText(bountifulName);
            Pages.Sleep(1);

        Step(String.format("Verify first location in list is '%s'", bountifulName));
            returnedLocation = Pages.PodiumModal.GetFirstLocationText();
            Verify.That(returnedLocation.contains(bountifulName)).IsTrue();
            Info(String.format("Correctly searched for %s", bountifulName));

        Step(String.format("Input building number '%s' into searchbar", bountifulBuildingNum));
            Pages.PodiumModal.SetLocationSearchBarText(bountifulBuildingNum);
            Pages.Sleep(1);

        Step(String.format("Verify first location in list is building number '%s'", bountifulBuildingNum));
            returnedLocation = Pages.PodiumModal.GetFirstLocationText();
            Verify.That(returnedLocation.contains(bountifulBuildingNum)).IsTrue();
            Info(String.format("Correctly searched for %s", bountifulBuildingNum));

        Step(String.format("Input street name '%s' into searchbar", bountifulStreet));
            Pages.PodiumModal.SetLocationSearchBarText(bountifulStreet);
            Pages.Sleep(1);

        Step(String.format("Verify first location in list is street name '%s'", bountifulStreet));
            returnedLocation = Pages.PodiumModal.GetFirstLocationText();
            Verify.That(returnedLocation.contains(bountifulStreet)).IsTrue();
            Info(String.format("Correctly searched for %s", bountifulStreet));

        Step(String.format("Input city '%s' into searchbar", bountifulCity));
            Pages.PodiumModal.SetLocationSearchBarText(bountifulCity);
            Pages.Sleep(1);

        Step(String.format("Verify first location in list is city name '%s'", bountifulCity));
            returnedLocation = Pages.PodiumModal.GetFirstLocationText();
            Verify.That(returnedLocation.contains(bountifulCity)).IsTrue();
            Info(String.format("Correctly searched for %s", bountifulCity));
    }
}
