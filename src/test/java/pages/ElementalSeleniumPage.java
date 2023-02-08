package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

public class ElementalSeleniumPage extends UIBase {

    private final SeleniumControl sauceLabsLink = new SeleniumControl(By.xpath("//*[text()='Sauce Labs']"));

    public SeleniumControl SauceLabs()
    {
        return sauceLabsLink;
    }
}
