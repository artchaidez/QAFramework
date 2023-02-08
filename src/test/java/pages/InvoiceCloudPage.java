package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

public class InvoiceCloudPage extends UIBase {

    private final SeleniumControl addElementBtn = new SeleniumControl(By.xpath("//button[text()='Add Element']"));

    private final SeleniumControl deleteElements = new SeleniumControl(By.id("Add Element"));

    private final SeleniumControl deleteBtn = new SeleniumControl(By.xpath("//*[@class='added-manually']"));

    private final SeleniumControl elementalSeleniumLink = new SeleniumControl(By.xpath("//*[text()='Elemental Selenium']"));

    public void AddElement() throws Exception
    {
        addElementBtn.Click(5);
    }

    public void DeleteButton() throws Exception
    {
        deleteBtn.Click(5);
    }

    public int TotalDeleteButtons() throws Exception
    {
        return deleteElements.getWebDriver().findElements(By.xpath("//*[@class='added-manually']")).size();
    }

    public void ClickOnElementalSelenium() throws Exception
    {
        elementalSeleniumLink.Click(5);
    }
}
