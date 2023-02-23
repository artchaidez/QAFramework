package listeners;

import autoFramework.AutoTestBase;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.Utils;
import java.util.List;

public class BaseInvokedMethodListener extends AutoTestBase implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult)
    {
        ResetSteps();

        // TODO: Refactor into Verify test methods; may need to stay if methods have to be public
        if (method.isTestMethod()) {

            List verificationFailures = Verify.getVerificationFailures();

            //if there are verification failures...
            if (verificationFailures.size() > 0) {

                //set the test to failed
                testResult.setStatus(ITestResult.FAILURE);

                //if there is an assertion failure add it to verificationFailures
                if (testResult.getThrowable() != null) {
                    verificationFailures.add(testResult.getThrowable());
                }

                int size = verificationFailures.size();
                //if there's only one failure just set that
                if (size == 1) {
                    testResult.setThrowable((Throwable) verificationFailures.get(0));
                } else {
                    //create a failure message with all failures and stack traces (except last failure)
                    StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):nn");
                    for (int i = 0; i < size-1; i++) {
                        failureMessage.append("Failure ").append(i+1).append(" of ").append(size).append(":n");
                        Throwable t = (Throwable) verificationFailures.get(i);
                        String fullStackTrace = Utils.stackTrace(t, false)[1];
                        failureMessage.append(fullStackTrace).append("nn");
                    }

                    //final failure
                    Throwable last = (Throwable) verificationFailures.get(size-1);
                    failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":n");
                    failureMessage.append(last.toString());

                    //set merged throwable
                    Throwable merged = new Throwable(failureMessage.toString());
                    merged.setStackTrace(last.getStackTrace());

                    testResult.setThrowable(merged);
                }
            }
        }

    }

}
