package autoFramework;

import org.openqa.selenium.WebDriver;
import webTestFramework.SeleniumControl;

import java.text.MessageFormat;
import java.util.ArrayList;

public class UIBase extends AutoLogger {

    protected static WebDriver webDriver;
    public SeleniumControl seleniumControl;

    private final WebDriverFactory webDriverFactory = new WebDriverFactory();

    public void Sleep(int seconds) throws InterruptedException{
        if(seconds > 5)
            Info(MessageFormat.format("  Sleeping for {0} seconds....", seconds));

        Thread.sleep(seconds * 1000L);
    }

    public void InitWebDriver()
    {
        webDriver = webDriverFactory.CreateSeleniumDriver();
        setWebDriver(webDriver);
    }

    public void GoToURL(String url)
    {
        webDriver.navigate().to(url);
        Info("Navigating to " + url);
    }

    public void Quit()
    {
        webDriver.quit();
        Info("*** Quit WebDriver ***");
    }

    public void setWebDriver(WebDriver webDriver)
    {
        UIBase.webDriver = webDriver;
    }

    public WebDriver getWebDriver()
    {
        return webDriver;
    }

    public void switchToiFrame(String iFrameID)
    {
        webDriver = webDriver.switchTo().frame(iFrameID);
    }

    public void switchToMainFrame()
    {
        webDriver = webDriver.switchTo().defaultContent();
    }

    public void switchToNewlyOpenTab()
    {
        ArrayList<String> allTabs = new ArrayList<>(webDriver.getWindowHandles());
        int lastTabIndex = allTabs.size() - 1;
        webDriver = webDriver.switchTo().window(allTabs.get(lastTabIndex));
        Info(String.format("Switched to newest tab: %s", this.webDriver.getTitle()));
    }
}
