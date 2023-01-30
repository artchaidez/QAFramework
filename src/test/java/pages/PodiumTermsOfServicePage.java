package pages;

import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

public class PodiumTermsOfServicePage {

    private final SeleniumControl termsOfService = new SeleniumControl(By.xpath("//*[text()='Terms of Service']"));

    public SeleniumControl TermsOfService() {
        return termsOfService;
    }
}
