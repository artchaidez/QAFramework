package pages;

import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

public class PodiumTermsOfServicePage {

    private final SeleniumControl termsOfService = new SeleniumControl(By.xpath("//*[text()='Terms of Service']"));

    /** Returns 'Terms of Service' link*/
    public SeleniumControl TermsOfService() {
        return termsOfService;
    }
}
