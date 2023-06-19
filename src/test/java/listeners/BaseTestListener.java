package listeners;

import autoFramework.AutoTestBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
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
    private static ExtentReports extent = new ExtentReports();
    private ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "testReport.html");
    String screenshotsDir = "./failedTests/" + packageClassName + "/" + getTestEndDate() + testName + ".png";

    @Override
    public void onTestStart(ITestResult result)
    {
        // TODO: Extent report test names should be formatted "packageName.testName"
        testName = result.getMethod().getMethodName();
        packageClassName = result.getMethod().getRealClass().getCanonicalName();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extent.attachReporter(reporter);
        extent.createTest(testName)
                .log(Status.PASS, "Test passed");
        extent.flush();
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        if (packageClassName.contains("webTestSuites")) { TakeScreenshot();}

        // TODO: Log step number
        // Attach reporter to Extent Report
        extent.attachReporter(reporter);
        // Added failed test info
        extent.createTest(testName)
                .log(Status.FAIL, result.getThrowable())
                .addScreenCaptureFromPath(screenshotsDir);
                //.fail(MediaEntityBuilder.createScreenCaptureFromPath(screenshotsDir).build()); // attaches screenshot to failed step

        extent.flush(); // creates report
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("Extent Report");
        reporter.config().setReportName("My tests");
    }

    @Override
    public void onFinish(ITestContext context) {
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
