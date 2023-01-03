package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

public class TrelloSignInPage extends UIBase {

    private final SeleniumControl logInMainPageBtn = new SeleniumControl(By.xpath("//*[@class='seg6b92uUYbLu1']"));

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
        logInMainPageBtn.Click(15);
    }

    public void SetEmailText(String email) throws Exception
    {
        emailText.SetText(email, 15, false);
    }

    public void ClickOnMainPageContinue() throws Exception
    {
        continueBtn.Click(15);
    }

    public void SetPasswordText(String password) throws Exception
    {
        passwordText.SetText(password, 15, false);
    }

    public void ClickOnLogInButton() throws Exception
    {
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
