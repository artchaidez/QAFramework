package autoFramework;

import apis.Apis;
import org.testng.annotations.AfterMethod;
import pages.Pages;

public class AutoTestBase extends AutoLogger {

    protected Apis apis;
    protected Pages pages;

    public AutoTestBase()
    {
        apis = new Apis();
        pages = new Pages();

    }

    /* Using a listener does not reset steps; listener points to different memory space.
     * Public would allow FinalTestTearDown to be overridden; private does not work. Protected final
     * prevents the method from being overridden. */
    /** SHOULD ONLY BE CALLED WITHIN AUTOTESTBASE. DO NOT OVERWRITE.*/
    @AfterMethod
    protected final void FinalTestTearDown()
    {
        ResetSteps();
    }
}
