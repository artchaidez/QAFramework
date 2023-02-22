package autoFramework;

import apis.Apis;
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

    /* Listener does not reset steps and clear assertions; listener points to different memory space.
     * Public would allow FinalTestTearDown to be overridden; private does not work. Protected final
     * prevents the method from being overridden. */
    /** SHOULD ONLY BE CALLED WITHIN AUTOTESTBASE. DO NOT OVERWRITE.*/
    @AfterMethod
    protected final void FinalTestTearDown(ITestResult testResult)
    {
        Verify.assertAll();

        // TODO: Fix all this. Clearly log what tests and steps passed/ failed
        String testIsPass = "FAILED!!!";

        if (testResult.isSuccess())
        {
            testIsPass = "PASS!!!";
        }

        Step("Test Complete: "  + testResult.getMethod().getMethodName() + " Test Result " +
                testIsPass);

        Verify.clearAsserts();
        ResetSteps();
    }
}
