package autoFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.net.URL;
import java.util.Objects;

public class WebDriverFactory extends AutoLogger{

    protected static ThreadLocal<WebDriver> _WebDriver = new ThreadLocal<>();
    protected static RemoteWebDriver rwd;
    WebDriverFactory()
    {

    }

    public ThreadLocal<WebDriver> CreateSeleniumDriver() {
        CreateWebDriver();
        _WebDriver.set(ThreadGuard.protect(Objects.requireNonNull(rwd)));
        _WebDriver.get().manage().window().maximize();
        _WebDriver.get().manage().deleteAllCookies();

        SetUpInfo("");
        SetUpInfo("*****************************************************");
        SetUpInfo("*** Created WebDriver ***");
        SetUpInfo("*****************************************************");

        return _WebDriver;
    }

    public void CreateWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");
        // TODO: Looking into a better way to handle running locally or on grid
        try {
            rwd = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"), options);
        } catch (Throwable e) {
            rwd = new ChromeDriver(options);
        }
    }


}