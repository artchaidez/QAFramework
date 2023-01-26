package webTestSuites;

import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import listeners.TestBaseListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestBaseListener.class)
public class PodiumTestSuite extends AutoTestBase {

    private String podiumURL = "https://demo.podium.tools/qa-webchat-lorw/";
    @BeforeMethod
    public void TestSetUp()
    {
        pages.InitWebDriver();
    }

    @AfterMethod
    public void TestTearDown()
    {
        pages.Quit();
    }

    @Test ()
    @TestInfo(description = "Test switching to correct iframes and click on Podium icon.", level = "Smoke")
    public void TestClickPodiumButton() throws Exception
    {
        Step("Go to Podium Demo Website");
            pages.GoToURL(podiumURL);

        Step("Verify on site by finding podium bubble");
            pages.podiumBubble.PodiumBubble().IsVisible(5);
            Info("Found Podium bubble");

        Step("Switch to Podium bubble iframe");
            pages.podiumBubble.GoToPodiumBubbleFrame();

        Step("Click on Podium Bubble");
            pages.podiumBubble.ClickOnPodiumButton();

        Step("Switch to Podium modal iframe");
            pages.podiumBubble.GoToPodiumModalFrame();

        Step("Verify on Podium Modal");
            pages.podiumModal.Modal().Click(5);
            Info("Podium modal is visible");
    }

    @Test ()
    @TestInfo(description = "Test clicking on first location found in Location modal.")
    public void TestSelectFirstLocation() throws Exception
    {
        Step("Go to Podium Website");
            pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            pages.podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            pages.podiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal is open");

        Step("Click on first location in location list");
            pages.podiumModal.SelectFirstLocation();

        Step("Verify on message modal");
            pages.podiumModal.NameTextInput().IsVisible(5);
            Info("Within message modal as name text input was found.");
    }

    @Test ()
    @TestInfo(description = "Test confirming correct location is being clicked in the modal")
    public void TestScoreboardOremLocationExists() throws Exception
    {
        String location = "Scoreboard Sports - Orem";

        Step("Go to Podium Website");
            pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            pages.podiumBubble.JumpToPodiumModal();

        Step(String.format("Verify %s is in location list", location));
            pages.podiumModal.FindByLocation(location).IsVisible(5);
            Info(String.format("'%s' is in the modal", location));

        Step(String.format("Click on %s", location));
            pages.podiumModal.FindByLocation(location).Click(5);

        Step(String.format("Verify %s opened up", location));
            String returnedLocation = pages.podiumModal.GetLocationInMessageModal();
            Assert.assertEquals(returnedLocation, location);
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
            pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            pages.podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            pages.podiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal open");

        Step("Click on first location in location list");
            pages.podiumModal.SelectFirstLocation();

        Step("Input name in Name text input");
            pages.podiumModal.SetTextInNameInput(name, 10, null);

        Step("Verify text was put into name input");
            pages.podiumModal.NameCheckmark().IsVisible(5);
            Info("Message has text input");

        Step("Input phone number into mobile phone text input");
            pages.podiumModal.SetMobileNumberInput(telephone, 10, null);

        Step("Verify phone number was put into input");
            pages.podiumModal.MobileNumberCheckmark().IsVisible(5);
            Info("Mobile number has text input");

        Step("Input message in message text input");
            pages.podiumModal.SetMessageInput(message, 10, null);

        Step("Verify message has input");
            String emptyMessageIndicator = "M 50 0 A 50 50 0 0 1 50 0";
            String messageIndicator = pages.podiumModal.MessageInputIndicator();
            Assert.assertNotEquals(messageIndicator, emptyMessageIndicator);
            Info("Message has text input");

        Step("Verify all inputs have data");
            pages.podiumModal.SendButton().IsVisible(5);
            Info("All inputs filled out and send button is valid");
    }

    @Test ()
    @TestInfo(description = "Test confirming subject and terms are opened in a new tab.")
    public void TestClickSubjectTerms() throws Exception
    {
        Step("Go to Podium Website");
            pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            pages.podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            pages.podiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal open");

        Step("Click on 'use is subject to terms'");
            pages.podiumModal.ClickOnTermsButton();

        Step("Switch to Terms and Service page");
            pages.switchToNewlyOpenTab();

        Step("Verify on Terms and Service page by clicking on 'Terms of Service'");
            pages.podiumTermsOfService.TermsOfService().Click(5);
            Info("Clicked on 'Terms of Service'");
    }

    @Test ()
    @TestInfo(description = "Test proving there is a bug with the return arrow in the message modal.")
    public void TestReturnButtonDoesNotWork() throws Exception
    {
        String message = "There is a bug with the return arrow on the message widget!";

        Step("Go to Podium Website");
            pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            pages.podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            pages.podiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal open");

        Step("Click on first location in location list");
            pages.podiumModal.SelectFirstLocation();

        Step("Verify on message modal");
            pages.podiumModal.NameTextInput().IsVisible(5);
            Info("Within message modal as name text input was found.");

        Step("Click on return arrow");
            pages.podiumModal.ClickOnReturnArrowBtn();

        Step("Verify message modal is still open by inputting text into message input");
            pages.podiumModal.SetMessageInput(message, 10, null);

        Step("Verify text was inputted into message input");
            String emptyMessageIndicator = "M 50 0 A 50 50 0 0 1 50 0";
            String messageIndicator = pages.podiumModal.MessageInputIndicator();
            Assert.assertNotEquals(messageIndicator, emptyMessageIndicator);
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
            pages.GoToURL(podiumURL);

        Step("Go immediately to Podium modal");
            pages.podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            pages.podiumModal.LocationSearchBar().IsVisible(5);
            Info("Podium modal open");

        Step(String.format("Input ZIP code '%s' into searchbar", bountifulZIP));
            pages.podiumModal.SetLocationSearchBarText(bountifulZIP);
            pages.Sleep(1);

        Step(String.format("Verify first location in list is '%s' ZIP code", bountifulName));
            returnedLocation = pages.podiumModal.GetFirstLocationText();
            Assert.assertTrue(returnedLocation.contains(bountifulZIP));
            Info(String.format("Correctly searched for %s ZIP code", bountifulZIP));

        Step(String.format("Input '%s' into searchbar", bountifulName));
            pages.podiumModal.SetLocationSearchBarText(bountifulName);
            pages.Sleep(1);

        Step(String.format("Verify first location in list is '%s'", bountifulName));
            returnedLocation = pages.podiumModal.GetFirstLocationText();
            Assert.assertTrue(returnedLocation.contains(bountifulName));
            Info(String.format("Correctly searched for %s", bountifulName));

        Step(String.format("Input building number '%s' into searchbar", bountifulBuildingNum));
            pages.podiumModal.SetLocationSearchBarText(bountifulBuildingNum);
            pages.Sleep(1);

        Step(String.format("Verify first location in list is building number '%s'", bountifulName));
            returnedLocation = pages.podiumModal.GetFirstLocationText();
            Assert.assertTrue(returnedLocation.contains(bountifulBuildingNum));
            Info(String.format("Correctly searched for %s", bountifulBuildingNum));

        Step(String.format("Input street name '%s' into searchbar", bountifulStreet));
            pages.podiumModal.SetLocationSearchBarText(bountifulStreet);
            pages.Sleep(1);

        Step(String.format("Verify first location in list is street name '%s'", bountifulName));
            returnedLocation = pages.podiumModal.GetFirstLocationText();
            Assert.assertTrue(returnedLocation.contains(bountifulStreet));
            Info(String.format("Correctly searched for %s", bountifulStreet));

        Step(String.format("Input city '%s' into searchbar", bountifulCity));
            pages.podiumModal.SetLocationSearchBarText(bountifulCity);
            pages.Sleep(1);

        Step(String.format("Verify first location in list is city name '%s'", bountifulName));
            returnedLocation = pages.podiumModal.GetFirstLocationText();
            Assert.assertTrue(returnedLocation.contains(bountifulCity));
            Info(String.format("Correctly searched for %s", bountifulCity));
    }
}
