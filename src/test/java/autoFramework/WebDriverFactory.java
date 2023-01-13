package autoFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory extends AutoLogger{

    // TODO: private or protected? Static?
    protected static WebDriver _WebDriver;
    public WebDriverFactory()
    {

    }

    public WebDriver CreateSeleniumDriver()
    {
        Info("Creating WebDriver.....");

        _WebDriver = CreateWebDriver();
        _WebDriver.manage().window().maximize();
        _WebDriver.manage().deleteAllCookies();

        return _WebDriver;
    }

    public WebDriver CreateWebDriver()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        return new ChromeDriver(options);
    }


}