package autoFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.util.Objects;

public class WebDriverFactory extends AutoLogger{

    protected static ThreadLocal<WebDriver> _WebDriver = new ThreadLocal<>();
    protected static RemoteWebDriver rwd;
    public WebDriverFactory()
    {

    }

    public ThreadLocal<WebDriver> CreateSeleniumDriver()
    {
        CreateWebDriver();
        _WebDriver.set(ThreadGuard.protect(Objects.requireNonNull(rwd)));
        _WebDriver.get().manage().window().maximize();
        _WebDriver.get().manage().deleteAllCookies();

        Info("");
        Info("*****************************************************");
        Info("*** Created WebDriver ***");
        Info("*****************************************************");

        return _WebDriver;
    }

    public void CreateWebDriver()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");
        rwd = new ChromeDriver(options);
    }


}