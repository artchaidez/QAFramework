package listeners;

import autoFramework.AutoTestBase;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestClass;
import org.testng.ITestResult;

/** This one runs before and after EVERY method you have in the class (Before and After class, suite, test - everything)
 * even you see only two methods included in the listener itself:*/


public class MyInvokedMethodListener extends AutoTestBase implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String testName = method.getTestMethod().getMethodName();
            String moduleName = method.getTestMethod().getRealClass().getCanonicalName();

            try {
                StartTest(testName, moduleName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    }

}
