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
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class BaseTestListener extends AutoTestBase implements ITestListener {

    String testName;
    String packageClassName;
    protected static ExtentReports report;
    protected static ExtentTest test;
    String screenshotsDir = "./failedTests/" + packageClassName + "/" + getTestEndDate() + testName + ".png";

    @Override
    public void onTestStart(ITestResult result)
    {
        // TODO: Extent report test names should be formatted "packageName.testName"
        testName = result.getMethod().getMethodName();
        packageClassName = result.getMethod().getRealClass().getCanonicalName();

        test = report.createTest(testName);
        ExtentFactory.getInstance().setExtent(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case: " + testName + " passed.");
        ExtentFactory.getInstance().removeExtentObject();
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        if (packageClassName.contains("webTestSuites")) {
            TakeScreenshot();
            ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: " + testName + " failed.")
                    .addScreenCaptureFromPath(screenshotsDir);
        }
        else {
            ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: " + testName + " failed.");
        }

        ExtentFactory.getInstance().removeExtentObject();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentFactory.getInstance().getExtent().log(Status.SKIP, "Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
        ExtentFactory.getInstance().removeExtentObject();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        // TODO: creating new Report for every suite. Should be done in ExecutionListener.onExecutionStart()
        try {
            report = ExtentReportManager.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
    }

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
