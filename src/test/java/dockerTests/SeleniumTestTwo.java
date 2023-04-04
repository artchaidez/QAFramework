package dockerTests;

import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.net.URL;

@Ignore("Only exists to test Selenium Grid")
public class SeleniumTestTwo extends AutoTestBase {

    @Test()
    @TestInfo(description = "Recreated InvoiceCloudPage test to see if Selenium works. " +
            "Thread.sleep() added to see execution.")
    public void TestAddElements() throws Exception
    {
        int addFiveElements = 5;

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");

        Step("Go to Heroku URL");
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        Thread.sleep(5000);

        Step(String.format("Click on 'Add Element' %d times", addFiveElements));
        for (int steps = 0; steps < addFiveElements; steps++) {
            driver.findElement(By.xpath("//button[text()='Add Element']")).click();
            Thread.sleep(1000);
        }

        Step(String.format("Confirm total delete buttons on page are %d", addFiveElements));
        int totalDeleteBtns = driver.findElements(By.xpath("//*[@class='added-manually']")).size();
        Verify.That(addFiveElements).Equals(totalDeleteBtns);
        Thread.sleep(5000);
        Info(String.format("Confirmed total delete buttons are %d", addFiveElements));

        driver.quit();
    }
}
