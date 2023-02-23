package autoFramework;

import apis.Apis;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import pages.Pages;

public class AutoTestBase extends AutoLogger {

    protected Apis APIs;
    protected Pages Pages;
    protected Verify Verify;

    public AutoTestBase()
    {
        APIs = new Apis();
        Pages = new Pages();
        Verify = new Verify();
    }

    /* Will be deprecated soon; ResetSteps() working in Listener*/
    /** SHOULD ONLY BE CALLED WITHIN AUTOTESTBASE. DO NOT OVERWRITE.*/
    @AfterMethod
    protected final void FinalTestTearDown(ITestResult testResult)
    {

    }
}
