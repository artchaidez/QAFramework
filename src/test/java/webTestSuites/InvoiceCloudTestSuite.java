package webTestSuites;

import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import listeners.BaseInvokedMethodListener;
import listeners.BaseTestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({BaseTestListener.class, BaseInvokedMethodListener.class})
public class InvoiceCloudTestSuite extends AutoTestBase {

    private String herokuURL = "https://the-internet.herokuapp.com/add_remove_elements/";

    @BeforeMethod
    public void TestSetUp()
    {
        Pages.InitWebDriver();
    }

    @AfterMethod
    public void TestTearDown()
    {
        Pages.Quit();
    }

    @Test()
    @TestInfo(description = "Add n number of elements, then assert n number of elements exist on the page.")
    public void TestAddElements() throws Exception
    {
        int addFiveElements = 5;

        Step("Go to Heroku URL");
            Pages.GoToURL(herokuURL);

        Step(String.format("Click on 'Add Element' %d times", addFiveElements));
            for (int steps = 0; steps < addFiveElements; steps++) {
                Pages.InvoiceCloudPage.AddElement();
            }

        Step(String.format("Confirm total delete buttons on page are %d", addFiveElements));
            int totalDeleteBtns = Pages.InvoiceCloudPage.TotalDeleteButtons();
            Verify.That(addFiveElements).Equals(totalDeleteBtns);
            Info(String.format("Confirmed total delete buttons are %d", addFiveElements));

    }

    @Test()
    @TestInfo(description = "Add n number of elements, then assert n - 1 number of elements exist on the page after clicking one delete button.")
    public void TestDeleteElement() throws Exception
    {
        int addFiveElements = 5;
        int fourElements = 4;

        Step("Go to Heroku URL");
            Pages.GoToURL(herokuURL);

        Step(String.format("Click on 'Add Element' %d times", addFiveElements));
            for (int steps = 0; steps < addFiveElements; steps++) {
                Pages.InvoiceCloudPage.AddElement();
            }

        Step(String.format("Confirm total delete buttons on page are %d", addFiveElements));
            int totalDeleteBtns = Pages.InvoiceCloudPage.TotalDeleteButtons();
            Verify.That(addFiveElements).Equals(totalDeleteBtns);
            Info(String.format("Confirmed total delete buttons are %d", addFiveElements));

        Step("Delete one element");
            Pages.InvoiceCloudPage.DeleteButton();
            Info("Deleted one element on the page");

        Step(String.format("Confirm there are now %d delete buttons", fourElements));
            totalDeleteBtns =  Pages.InvoiceCloudPage.TotalDeleteButtons();
            Verify.That(fourElements).Equals(totalDeleteBtns);
            Info(String.format("Confirmed total delete buttons are %d", fourElements));
    }

    @Test()
    @TestInfo(description = "Click on Elemental Selenium link and confirm new tab is opened")
    public void TestClickElementalSelenium() throws Exception
    {
        Step("Go to Heroku URL");
            Pages.GoToURL(herokuURL);

        Step("Click on Elemental Selenium link");
            Pages.InvoiceCloudPage.ClickOnElementalSelenium();

        Step("Switch to Elemental Selenium page");
            Pages.switchToNewlyOpenTab();

        Step("Confirm Elemental Selenium tab is opened by finding Sauce labs link");
            Pages.ElementalSeleniumPage.SauceLabs().IsVisible(5);
            Info("Confirmed on Elemental Selenium page as Sauce Labs link is visible");
    }
}
