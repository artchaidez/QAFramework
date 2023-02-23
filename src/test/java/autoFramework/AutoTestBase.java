package autoFramework;

import apis.Apis;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import pages.Pages;

public class AutoTestBase extends AutoLogger {

    protected Apis apis;
    protected Pages pages;
    protected Verify Verify;

    public AutoTestBase()
    {
        apis = new Apis();
        pages = new Pages();
        Verify = new Verify();
    }

    /* Will be deprecated soon; ResetSteps() working in Listener*/
    /** SHOULD ONLY BE CALLED WITHIN AUTOTESTBASE. DO NOT OVERWRITE.*/
    @AfterMethod
    protected final void FinalTestTearDown(ITestResult testResult)
    {

    }
}
