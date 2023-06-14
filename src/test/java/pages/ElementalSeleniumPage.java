package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

public class ElementalSeleniumPage extends UIBase {

    private final SeleniumControl ElementalSeleniumLink = new SeleniumControl(By.xpath("//*[@alt='Elemental Selenium Logo']"));

    /** Returns element for Elemental Selenium link. */
    public SeleniumControl ElementalSelenium()
    {
        return ElementalSeleniumLink;
    }
}
