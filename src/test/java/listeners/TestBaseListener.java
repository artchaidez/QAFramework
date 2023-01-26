package listeners;

import autoFramework.AutoTestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestBaseListener extends AutoTestBase implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        String packageClassName = result.getMethod().getRealClass().getCanonicalName();

        try {
            StartTest(testName, packageClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
