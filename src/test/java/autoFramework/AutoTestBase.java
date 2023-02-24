package autoFramework;

import apis.Apis;
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
}
