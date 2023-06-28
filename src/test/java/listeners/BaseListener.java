package listeners;

import autoFramework.AutoTestBase;
import autoFramework.ExtentFactory;
import autoFramework.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.testng.internal.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

// TODO: Research if having all listeners in one class is good practice
// FIXME: Create new class with methods to be used here; that class should extend AutoTestBase
public class BaseListener extends AutoTestBase implements ITestListener, IInvokedMethodListener, IExecutionListener {
    String testName;
    String packageClassName;
    // TODO: Properly format when packageNames is used. This should be in new class with getters/ setters
    String[] packageNames;
    protected static ExtentReports report;
    protected static ExtentTest test;
    String screenshotsDir = "./failedTests/" + packageClassName + "/" + getTestEndDate() + testName + ".png";

    // IInvokedMethodListener methods
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

        testName = testResult.getMethod().getMethodName();
        packageClassName = testResult.getMethod().getRealClass().getCanonicalName();

        if (method.isTestMethod()) {
            try {
                StartTest(testName, packageClassName);
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

            // TODO: This should be its own method, then used from extended class
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

    // ITestListener methods
    @Override
    public void onTestStart(ITestResult result)
    {
        // TODO: Extent report test names should be formatted "packageName.testName"
        testName = result.getMethod().getMethodName();
        packageClassName = result.getMethod().getRealClass().getCanonicalName();

        packageNames = packageClassName.split("\\.");

        test = report.createTest(packageNames[1] + "." + testName);
        ExtentFactory.getInstance().setExtent(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case: " + testName + " passed.")
                .assignCategory(packageNames[1]);
        ExtentFactory.getInstance().removeExtentObject();
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        if (packageClassName.contains("webTestSuites")) {
            TakeScreenshot();
            ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable())
                    .addScreenCaptureFromPath(screenshotsDir)
                    .assignCategory(packageNames[1]);
        }
        else {
            ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable())
                    .assignCategory(packageNames[1]);
        }

        ExtentFactory.getInstance().removeExtentObject();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentFactory.getInstance().getExtent().log(Status.SKIP, "Test Case: "+result.getMethod().getMethodName()+ " skipped.")
                .assignCategory(packageNames[1]);
        ExtentFactory.getInstance().removeExtentObject();
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

    // IExecutionListener methods
    @Override
    public void onExecutionStart() {
        report = ExtentReportManager.SetUpExtentReporter();
    }

    @Override
    public void onExecutionFinish() {
        report.flush();
    }

    // TODO: This should be its own method, then used from extended class
    /** Called within TestFailure(). Will only be called for web tests, not API tests. */
    private void TakeScreenshot()
    {
        TakesScreenshot screenshot = (TakesScreenshot) Pages.getWebDriver();
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        File destFile = new File(screenshotsDir);

        try {
            FileUtils.copyFile(srcFile, destFile);
            Error(testName + " failed and a screenshot was taken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
