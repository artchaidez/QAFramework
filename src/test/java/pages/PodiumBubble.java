package pages;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import webTestFramework.SeleniumControl;

public class PodiumBubble extends UIBase {

    // Objects are private final
    private final SeleniumControl podiumBubble = new SeleniumControl(By.id("podium-bubble"));

    private final SeleniumControl podiumBtn = new SeleniumControl(By.className("ContactBubble__Bubble"));


    // Methods are public
    /** Switch to podium-bubble iframe.*/
    public void GoToPodiumBubbleFrame()
    {
        switchToMainFrame();
        switchToiFrame("podium-bubble");
        Info("Currently within Podium bubble");
    }

    /** Returns podium-bubble, if it exists. */
    public SeleniumControl PodiumBubble()
    {
        return podiumBubble;
    }

    /** Must be within podium-bubble*/
    public void ClickOnPodiumButton() throws Exception
    {
        podiumBtn.Click(5);
        Info("Clicked on Podium button");
    }

    /** Switch to podium-modal iframe.*/
    public void GoToPodiumModalFrame()
    {
        switchToMainFrame();
        switchToiFrame("podium-modal");
        Info("Currently within Podium modal");
    }

    /** Jumps to podium-modal iframe regardless of where currently at.*/
    public void JumpToPodiumModal() throws Exception
    {
        GoToPodiumBubbleFrame();
        podiumBtn.Click(15);
        GoToPodiumModalFrame();
    }
}
