package webTestSuites;

import autoFramework.AutoTestBase;
import jdk.jfr.Description;
import listeners.MyInvokedMethodListener;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import webTestFramework.SeleniumControl;
@Listeners(MyInvokedMethodListener.class)
public class PodiumTestSuite extends AutoTestBase {

    @BeforeMethod
    public void TestSetUp()
    {
        pages.InitWebDriver();
    }

    @AfterMethod
    public void TestTearDown()
    {
        pages.Quit();
        ResetSteps();
    }

    @Test (groups = {"smokeTest"})
    @Description("Test to switch to correct iframes and click on Podium icon.")
    public void TestClickPodiumButton() throws Exception
    {
        Step("Go to Podium Demo Website");
            pages.GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Verify on site by finding podium bubble");
            pages.podiumBubble.VerifyPodiumBubbleExists();
            Info("Found Podium bubble");

        Step("Switch to Podium bubble iframe");
            pages.Sleep(1);
            pages.podiumBubble.GoToPodiumBubbleFrame();

        Step("Click on Podium Bubble");
            pages.podiumBubble.ClickOnPodiumButton();
            Info("Currently within Podium bubble");

        Step("Switch to Podium modal iframe");
            pages.podiumBubble.GoToPodiumModalFrame();

        Step("Verify on Podium Modal");
            pages.podiumBubble.VerifyOnModal();
            Info("Podium modal is visible");
    }

    @Test (groups = {"regressionTest"})
    @Description("Test to click on first location found in Location modal.")
    public void TestSelectFirstLocation() throws Exception
    {
        Step("Go to Podium Website");
            pages.GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
            pages.Sleep(1);
            pages.podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            pages.podiumModal.VerifyLocationSearchBar();
            Info("Podium modal open");

        Step("Click on first location in location list");
            pages.podiumModal.SelectFirstLocation();

        Step("Verify on message modal");
            pages.podiumModal.VerifyNameTextInputExists();
            Info("Within message modal as name text input was found.");
    }

    @Test (groups = {"regressionTest"})
    @Description("Test to confirm correct location is being clicked in the modal")
    public void TestScoreboardOremLocationExists() throws Exception
    {
        String location = "Scoreboard Sports - Orem";

        Step("Go to Podium Website");
            pages.GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
            pages.Sleep(1);
            pages.podiumBubble.JumpToPodiumModal();

        Step(String.format("Verify %s is in location list", location));
        // TODO: refactor into a private object
            SeleniumControl locationBtn = new SeleniumControl(By.xpath(String.format("//*[text()= \"%s\"]", location)));
            locationBtn.IsVisible(5);
            Info(String.format("%s is in the modal", location));

        Step(String.format("Click on %s", location));
            locationBtn.Click(5);

        Step(String.format("Verify %s opened up", location));
            pages.podiumModal.VerifyCorrectLocationOpened(location);
            Info(String.format("%s opened up", location));
    }

    @Test (groups = {"smokeTest"})
    @Description("Test to input data in all 3 fields of message modal")
    public void TestInputMessageData() throws Exception
    {
        String name = "Art";
            String telephone = "7777777777";
            String message = "Hello QA Tester";

        Step("Go to Podium Website");
            pages.GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
            pages.Sleep(1);
            pages.podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            pages.podiumModal.VerifyLocationSearchBar();
            Info("Podium modal open");

        Step("Click on first location in location list");
            pages.podiumModal.SelectFirstLocation();

        Step("Input name in Name text input");
            pages.podiumModal.SetTextInNameInput(name, 5, null);
            pages.Sleep(1);

        Step("Verify text was put into name input");
            pages.podiumModal.VerifyNameCheckmarkExists();
            Info("Message has text input");

        Step("Input phone number into mobile phone text input");
            pages.podiumModal.SetMobileNumberInput(telephone, 5, null);
            pages.Sleep(1);

        Step("Verify phone number was put into input");
            pages.podiumModal.VerifyMobileNumberCheckmarkExists();

        Step("Input message in message text input");
            pages.podiumModal.SetMessageInput(message, 5, null);
            pages.Sleep(1);

        Step("Verify message has input");
            pages.podiumModal.VerifyMessageHasInput();
            Info("Message has text input");

        Step("Verify all inputs have data");
            pages.podiumModal.VerifyAllInputsComplete();
            Info("All inputs filled out and send button is valid");
    }

    @Test (groups = {"regressionTest"})
    @Description("Test to confirm subject and terms are opened in a new tab.")
    public void TestClickSubjectTerms() throws Exception
    {
        Step("Go to Podium Website");
            pages.GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
            pages.Sleep(1);
            pages.podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
            pages.podiumModal.VerifyLocationSearchBar();
            Info("Podium modal open");

        Step("Click on 'use is subject to terms'");
            pages.podiumModal.ClickOnTermsButton();

        Step("Switch to Terms and Service page");
            pages.switchToNewlyOpenTab();

        Step("Verify on Terms and Service page by clicking on 'Terms of Service'");
            pages.podiumModal.VerifyOnSubjectTermsPage();
            Info("Clicked on 'Terms of Service'");
    }

    @Test (groups = {"regressionTest"})
    @Description("Test to prove there is a bug with the return arrow in the message modal.")
    public void TestReturnButtonDoesNotWork() throws Exception
    {
        String message = "There is a bug with the return arrow on the message widget!";

        Step("Go to Podium Website");
        pages.GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
        pages.Sleep(1);
        pages.podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
        pages.podiumModal.VerifyLocationSearchBar();
        Info("Podium modal open");

        Step("Click on first location in location list");
        pages.podiumModal.SelectFirstLocation();

        Step("Verify on message modal");
        pages.podiumModal.VerifyNameTextInputExists();
        Info("Within message modal as name text input was found.");

        Step("Click on return arrow");
        pages.podiumModal.ClickOnReturnArrowBtn();

        Step("Verify message modal is still open by inputting text into message input");
        pages.podiumModal.SetMessageInput(message, 5, null);
        pages.Sleep(1);

        Step("Verify text was inputted into message input");
        pages.podiumModal.VerifyMessageHasInput();
        Info("Message has text input");
    }

    @Test (groups = {"smokeTest"})
    @Description("Test to prove location searchbar works.")
    public void TestLocationSearchBar() throws Exception
    {
        String bountifulZIP = "84043";
        String bountifulStreet = "E Main St";
        String bountifulCity = "Lehi";
        String bountifulBuildingNum = "1402";
        String bountifulName = "Bountiful";

        Step("Go to Podium Website");
        pages.GoToURL("https://demo.podium.tools/qa-webchat-lorw/");

        Step("Go immediately to Podium modal");
        pages.Sleep(1);
        pages.podiumBubble.JumpToPodiumModal();

        Step("Verify on Podium modal");
        pages.podiumModal.VerifyLocationSearchBar();
        Info("Podium modal open");

        Step("Input Bountiful ZIP code into searchbar");
        pages.podiumModal.SetLocationSearchBarText(bountifulZIP);
        pages.Sleep(1);

        Step("Verify first location in list is Bountiful");
        pages.podiumModal.VerifySearchBarInputCorrect(bountifulZIP);
        Info("Correctly searched for Bountiful ZIP");

        Step("Input Bountiful name into searchbar");
        pages.podiumModal.SetLocationSearchBarText(bountifulName);
        pages.Sleep(1);

        Step("Verify first location in list is Bountiful");
        pages.podiumModal.VerifySearchBarInputCorrect(bountifulName);
        Info("Correctly searched for Bountiful name");

        Step("Input Bountiful building number into searchbar");
        pages.podiumModal.SetLocationSearchBarText(bountifulBuildingNum);
        pages.Sleep(1);

        Step("Verify first location in list is Bountiful");
        pages.podiumModal.VerifySearchBarInputCorrect(bountifulBuildingNum);
        Info("Correctly searched for Bountiful building name");

        Step("Input Bountiful street name into searchbar");
        pages.podiumModal.SetLocationSearchBarText(bountifulStreet);
        pages.Sleep(1);

        Step("Verify first location in list is Bountiful");
        pages.podiumModal.VerifySearchBarInputCorrect(bountifulStreet);
        Info("Correctly searched for Bountiful street name");

        Step("Input Bountiful city into searchbar");
        pages.podiumModal.SetLocationSearchBarText(bountifulCity);
        pages.Sleep(1);

        Step("Verify first location in list in Bountiful");
        pages.podiumModal.VerifySearchBarInputCorrect(bountifulCity);
        Info("Correctly searched for Bountiful city");
    }
}
