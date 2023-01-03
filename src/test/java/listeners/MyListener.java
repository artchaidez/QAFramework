package listeners;

import autoFramework.AutoTestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
/** Using MyInvokedMethodListener for AutoLogger StartTest.
 * Keep this Listener for now */

public class MyListener extends AutoTestBase implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

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
