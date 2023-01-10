package listeners;

import autoFramework.AutoTestBase;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/** This one runs before and after EVERY method you have in the class (Before and After class, suite, test - everything)
 * even you see only two methods included in the listener itself:
 * Keep for now */


public class MyInvokedMethodListener extends AutoTestBase implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }

}
