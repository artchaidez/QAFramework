package dockerTests;

import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumTestOne extends AutoTestBase {

    @Test
    @TestInfo(description = "Test google.com opens and types into searchbar. " +
            "Thread.sleep() added to see execution.")
    public void TestGoogle() throws MalformedURLException, InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

        driver.get("http://www.google.com");
        Thread.sleep(5000);

        driver.findElement(By.name("q")).sendKeys("Zalenium");

        // see execution
        Thread.sleep(5000);
        driver.quit();
    }


}
