package autoFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webTestFramework.SeleniumControl;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;

// TODO: rename this class
public class UIBase extends AutoLogger {

    // WebDriver must be protected static
    protected static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    public SeleniumControl seleniumControl;

    private final WebDriverFactory webDriverFactory = new WebDriverFactory();

    /** Sleep webDriver for a specified amount of seconds. */
    public void Sleep(int seconds) throws InterruptedException{
        if(seconds > 5)
            Info(MessageFormat.format("  Sleeping for {0} seconds....", seconds));

        Thread.sleep(seconds * 1000L);
    }

    /** Initiate selenium webDriver. */
    public void InitWebDriver() {
        webDriver = webDriverFactory.CreateSeleniumDriver();
        setWebDriver(webDriver);
    }

    /** Go to specified URL. */
    public void GoToURL(String url)
    {
        getWebDriver().navigate().to(url);
        Info("Navigating to " + url);
    }

    /** Ends selenium webDriver by calling quit().*/
    public void Quit()
    {
        getWebDriver().quit();
        Info("");
        Info("*****************************************************");
        Info("*** Quit WebDriver ***");
        Info("*****************************************************");
        Info("");
    }

    public void setWebDriver(ThreadLocal<WebDriver> webDriver)
    {
        UIBase.webDriver = webDriver;
    }

    public WebDriver getWebDriver()
    {
        return Objects.requireNonNull(webDriver.get());
    }

    public boolean webDriverExists()
    {
        return !getWebDriver().toString().contains("(null)");
    }

    /** Switches to specified iFrameID, if available. */
    public void switchToiFrame(String iFrameID)
    {
        Duration duration = Duration.ofSeconds(5);
        new WebDriverWait(getWebDriver(), duration).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(iFrameID)));
        Info(String.format("Switched to iFrame: %s", iFrameID));
    }

    /** Switches to either the first frame on the page, or the main document when a page contains iframes. */
    public void switchToMainFrame()
    {
        webDriver.set(getWebDriver().switchTo().defaultContent());
        Info("Switched to main iFrame");
    }

    /** Switches to newly open tab, which will be the last tab in browser window. */
    public void switchToNewlyOpenTab()
    {
        ArrayList<String> allTabs = new ArrayList<>(getWebDriver().getWindowHandles());
        int lastTabIndex = allTabs.size() - 1;
        webDriver.set(getWebDriver().switchTo().window(allTabs.get(lastTabIndex)));
        Info(String.format("Switched to newest tab: %s", getWebDriver().getTitle()));
    }
}
