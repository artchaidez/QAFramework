package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import webTestFramework.SeleniumControl;

public class TrelloSignInPage extends UIBase {

    private final SeleniumControl logInMainPageBtn = new SeleniumControl(By.xpath("//*[text()='Log in']"));

    private final SeleniumControl emailText = new SeleniumControl(By.xpath("//*[@id='user']"));

    private final SeleniumControl continueBtn = new SeleniumControl(By.xpath("//*[@id='login']"));

    private final SeleniumControl passwordText = new SeleniumControl(By.xpath("//*[@id = 'password']"));

    private final SeleniumControl finalLogInBtn = new SeleniumControl(By.xpath("//*[@id = 'login-submit']"));


    public TrelloBoardPage trelloBoardPage;

    public TrelloSignInPage()
    {
        trelloBoardPage = new TrelloBoardPage();
    }

    public void ClickOnMainPageLogIn() throws Exception
    {
        for (int attempt = 0; attempt < 10; attempt++)
        {
            try {
                logInMainPageBtn.IsVisible(5);
                break;
            } catch (WebDriverException e)
            {

            }
        }
        logInMainPageBtn.Click(5);
        // for loop, look for element. If it is there, break and click on it
        // try catch is within loop
    }

    public void SetEmailText(String email) throws Exception
    {
        for (int attempt = 0; attempt < 10; attempt++)
        {
            try {
                emailText.IsVisible(5);
                break;
            } catch (WebDriverException e) {

            }
        }

        emailText.SetText(email, 5, false);
    }

    public void ClickOnMainPageContinue() throws Exception
    {
        for (int attempt = 0; attempt < 10; attempt++)
        {
            try {
                continueBtn.IsVisible(5);
                break;
            } catch (WebDriverException e) {

            }
        }

        continueBtn.Click(15);
    }

    public void SetPasswordText(String password) throws Exception
    {
        for (int attempt = 0; attempt < 10; attempt++)
        {
            try {
                passwordText.IsVisible(5);
                break;
            } catch (WebDriverException e) {

            }
        }

        passwordText.SetText(password, 15, false);
    }

    public void ClickOnLogInButton() throws Exception
    {
        for (int attempt = 0; attempt < 10; attempt++)
        {
            try {
                finalLogInBtn.IsVisible(5);
                break;
            } catch (WebDriverException e) {

            }
        }

        finalLogInBtn.Click(15);
    }

    public void LogIntoAccountOnMainPage(String email, String password) throws Exception
    {
        ClickOnMainPageLogIn();
        SetEmailText(email);
        ClickOnMainPageContinue();
        SetPasswordText(password);
        ClickOnLogInButton();
    }
}
