package listeners;

import autoFramework.AutoTestBase;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;


public class BaseInvokedMethodListener extends AutoTestBase implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        /*int errors = Verify.getErrors();
        int status = testResult.getStatus();

        // TODO: correctly changing test to fail; crashes
        if (method.isTestMethod()) {
            if (errors > 0) {
                testResult.setStatus(ITestResult.FAILURE);
                Verify.clearAsserts();
            }
        }
        status = testResult.getStatus();*/

        ResetSteps();

    }

}
