package listeners;

import autoFramework.ListenerBase;
import autoFramework.Verify;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.Utils;

import java.util.List;

public class BaseInvokedMethodListener extends ListenerBase implements IInvokedMethodListener
{
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult)
    {
        if (method.isTestMethod()) {
            SetTestName(testResult.getMethod().getMethodName());
            SetPackageClassName(testResult.getMethod().getRealClass().getCanonicalName());
            try {
                LogStartTestInfo(GetTestName(), GetPackageClassName());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult)
    {
        ResetSteps();

        if (method.isTestMethod()) {

            List verificationFailures = Verify.getVerificationFailures();

            if (verificationFailures.size() > 0) {

                testResult.setStatus(ITestResult.FAILURE);

                //if there is an assertion failure add it to verificationFailures
                if (testResult.getThrowable() != null) {
                    verificationFailures.add(testResult.getThrowable());
                }

                int size = verificationFailures.size();

                if (size == 1) {
                    testResult.setThrowable((Throwable) verificationFailures.get(0));
                } else {
                    StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append(")\n");
                    for (int i = 0; i < size; i++) {
                        failureMessage.append("Failure ").append(i+1).append(" of ").append(size).append(": ");
                        Throwable t = (Throwable) verificationFailures.get(i);
                        String fullStackTrace = Utils.stackTrace(t, false)[1];
                        failureMessage.append(fullStackTrace);
                    }

                    Throwable merged = new Throwable(failureMessage.toString());
                    testResult.setThrowable(merged);
                }
            }
        }

    }

}
