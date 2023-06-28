package listeners;

import autoFramework.AutoTestBase;
import autoFramework.ExtentFactory;
import autoFramework.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
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
    // TODO: Properly format when packageNames is used. This should be in new class with getters/ setters
    String[] packageNames;
    String screenshotsDir = "./failedTests/" + packageClassName + "/" + getTestEndDate() + testName + ".png";

    @Override
    public void onTestStart(ITestResult result)
    {
        testName = result.getMethod().getMethodName();
        packageClassName = result.getMethod().getRealClass().getCanonicalName();

        packageNames = packageClassName.split("\\.");

        test = report.createTest(packageNames[1] + "." + testName);
        ExtentFactory.getInstance().setExtent(test);
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        Markup message = MarkupHelper.createLabel(testName + " passed.", ExtentColor.GREEN);
        ExtentFactory.getInstance().getExtent().log(Status.PASS, message)
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
        ExtentFactory.getInstance().getExtent().log(Status.SKIP, result.getMethod().getMethodName()+ " skipped.")
                .assignCategory(packageNames[1]);
        ExtentFactory.getInstance().removeExtentObject();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        report = ExtentReportManager.SetUpExtentReporter();
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
