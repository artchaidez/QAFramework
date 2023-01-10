package webTestFramework;

import autoFramework.UIBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SeleniumControl extends UIBase {

    protected By chromeLocator;

    protected WebElement cacheElement;

    protected int implicitWaitTimeout = 10;

    public String controlName = null;

    public String getTagName() { return TagName; }

    public String TagName;

    public By Locator;

    public String LocatorValue;

    public WebElement WebElement;

    public String getText()
    {
        return getWebElement().getText();
    }

    public String getAttribute(String name)
    {
        return getWebElement().getCssValue(name);
    }

    public SeleniumControl(By chromeLocator)
    {
        this.chromeLocator = chromeLocator;
        setLocator(chromeLocator);
    }

    public SeleniumControl(WebElement element, String controlName)
    {
        this.controlName = controlName;
        this.cacheElement = element;
    }

    public void SetControlName(String name) { this.controlName = name; }

    public void setLocator(By locator) { Locator = this.chromeLocator; }

    public By getLocator() { return this.chromeLocator; }

    public String getLocatorValue() {
        String template = Locator.toString();
        int idx = template.indexOf(":");
        String byType = template.substring(0, idx);
        return byType.toLowerCase().replaceAll("by.", "");
    }

    public org.openqa.selenium.WebElement getWebElement()
    {
        return FindElement(implicitWaitTimeout);
    }

    public void setWebElement(org.openqa.selenium.WebElement webElement)
    {
        WebElement = webElement;
    }

    public WebElement FindElement(int timeOut)
    {
        WebElement element = null;
        if(timeOut == 0)
            timeOut = 10;

        Duration duration = Duration.ofSeconds(timeOut);

        if (cacheElement != null)
            element = cacheElement;
        else
        {
            try
            {
                WebDriverWait wait = new WebDriverWait(getWebDriver(), duration);
                element = wait.until(ExpectedConditions.elementToBeClickable(this.Locator));
                setWebElement(element);

                if(!IsVisible(element))
                    element = null;
            } catch (SecurityException e)
            {
                Error(e.toString());
            }
        }

        return element;
    }

    public String SetText(String text, int Max_Retries, Boolean escape) throws Exception {
        if (Max_Retries == 0)
            Max_Retries = 5;
        if (escape == null)
            escape = false;

        boolean textEntered = false;
        Exception innerException = null;

        while(Max_Retries > 0)
        {
            WebElement element = FindElement(5);

            try
            {
                if(element == null)
                    throw new NoSuchElementException(MessageFormat.format("Could not find element at '{0}'.", this.Locator));

                String elemType = this.GetAttribute("type");

                if(elemType == null || !elemType.equals("password"))
                    Info("[SetText] " + this.FormatControlName() + " ---> '" + text.replace("\n", "<Enter>") + "'");
                else
                    Info("[SetText] " + this.FormatControlName() + " ---> '*****'");

                try
                {
                    element.sendKeys(Keys.CONTROL + "a");
                } catch (SecurityException e)
                {
                    element.clear();
                }

                element.sendKeys(text != "" ? text : " " + Keys.BACK_SPACE);

                if(escape)
                {
                    Sleep(1);
                    element.sendKeys(Keys.ESCAPE);
                }

                textEntered = true;
                break;

            } catch (InterruptedException ex)
            {
                innerException = ex;
            }

            Thread.sleep(1000L);
            Max_Retries--;
        }

        if (!textEntered)
            throw innerException;

        return text;
    }

    public void SendKeys(String keysToSend)
    {
        Info(MessageFormat.format("[SendKeys] {0} ---> {1}", this.FormatControlName(), keysToSend.toString()));

        this.WebElement.sendKeys(keysToSend);
    }

    public void Click(int Max_Retries) throws Exception
    {
        if (Max_Retries == 0)
            Max_Retries = 5;

        boolean clickable = false;
        Exception innerException = null;

        while (Max_Retries > 0)
        {
            Max_Retries--;

            Info("[Click] " + this.FormatControlName());

            WebElement element = FindElement(5);

            try
            {
                if(element == null)
                    throw new NoSuchElementException(MessageFormat.format("Could not find element using locator '{0}'.", this.Locator));
                else if (!element.isEnabled())
                    throw new Exception(MessageFormat.format("Element at '{0}'", this.Locator));

                element.click();
                clickable = true;
                break;
            } catch (NoSuchElementException e)
            {
                innerException = FormatException(e);
                Info("Element not found, Retrying...");
            } catch (Exception e)
            {
                Info(e.getMessage());

                innerException = FormatException(e);
            }
        }

        if (!clickable)
            throw innerException;
    }

    public void Clear()
    {
        Info("[Clear] " + this.FormatControlName());
    }

    public void Submit()
    {
        Info("[Submit] " + this.FormatControlName());
        this.WebElement.submit();
    }

    public boolean IsVisible(int timeOut)
    {
        return IsVisible(FindElement(timeOut));
    }

    public boolean IsNotVisible(int timeOut)
    {
        return !IsVisible(FindElement(timeOut));
    }

    protected boolean IsVisible(WebElement element)
    {
        try
        {
            if(element == null)
                return false;
            else if (!element.isDisplayed())
            {
                return false;
            } else return element.getAttribute("class") == null || !element.getAttribute("class").contains("ng-hide");
        } catch (SecurityException e)
        {
            Error(e.toString());
        }
        return false;
    }

    public String GetAttribute(String attribute) throws InterruptedException
    {
        int Max_Retry = 5;

        while (Max_Retry > 0)
        {
            Max_Retry--;

            try
            {
                return this.WebElement.getAttribute(attribute);
            } catch (SecurityException e)
            {
                Error(e.toString());
            }

            Thread.sleep(500);
        }

        return null;
    }

    public String FormatControlName()
    {
        if(this.controlName == null)
        {
            if(Locator != null)
            {
                String byType = Locator.toString().split(":")[0];
                String byValue = (Locator.toString().split(":")[0]).trim();

                if((byType.toLowerCase().contains("xpath") || byType.toLowerCase().contains("css")) && (byValue.contains("'")))
                {
                    String[] split = byValue.split("'");
                    return split[split.length - 2];
                }
                else
                {
                    return byValue;
                }
            }
            else
            {
                return TagName;
            }
        }
        else
        {
            return this.controlName;
        }
    }

    protected Exception FormatException(Exception ex)
    {
        String message = ex.getMessage().replace("<", "&lt;").replace(">", "&gt");
        return new Exception(message, ex);
    }

    public List<SeleniumControl> FindAll(int Max_Retries) throws InterruptedException {
        if(Max_Retries == 0)
            Max_Retries = 10;

        List<WebElement> elements = null;
        List<SeleniumControl> controls = new ArrayList<>();

        while(Max_Retries > 0) {
            Max_Retries--;
            Thread.sleep(1000);

            try
            {
                elements = webDriver.findElements(this.Locator);

                if (elements == null || elements.size() == 0)
                {
                    elements = null;
                } else
                    break;
            } catch (Exception e) {
                elements = null;
            }
        }
        if (elements == null)
            return null;

        for (WebElement element: elements)
            controls.add(new SeleniumControl(element, null));

        return controls;
    }

    public List<WebElement> FindAllVisible(int Max_Retries) throws InterruptedException
    {
        if (Max_Retries == 0)
            Max_Retries = 10;

        List<WebElement> visibleElements = new ArrayList<>();
        List<SeleniumControl> elements = FindAll(Max_Retries);

        for (SeleniumControl element : elements)
        {
            if (IsVisible(element.WebElement))
                visibleElements.add(element.WebElement);
        }
        return visibleElements;
    }

    public void ClearCache()
    {
        this.cacheElement = null;
    }

    public void SetLocatorParms(By LocatorTemplate, String[] args)
    {
        String template = LocatorTemplate.toString();
        int idx = template.indexOf(":");
        String byType = template.substring(0, idx);
        String byValue = template.substring(idx + 1).trim();
        By locator = null;

        switch (byType.toLowerCase().replace("by", ""))
        {
            case "xpath":
                locator = By.xpath(MessageFormat.format(byValue, (Object) args));
                break;
            case "id":
                locator = By.id(MessageFormat.format(byValue, (Object) args));
                break;
            case "cssselector":
                locator = By.cssSelector(MessageFormat.format(byValue, (Object) args));
                break;
        }

        this.chromeLocator = locator;
        ClearCache();
    }
}
