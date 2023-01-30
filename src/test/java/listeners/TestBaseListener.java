package listeners;

import autoFramework.AutoTestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestBaseListener extends AutoTestBase implements ITestListener {

    String testName;
    String packageClassName;

    @Override
    public void onTestStart(ITestResult result) {

        testName = result.getMethod().getMethodName();
        packageClassName = result.getMethod().getRealClass().getCanonicalName();

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
    public void onTestFailure(ITestResult result)
    {
        if (packageClassName.contains("webTestSuites")) { TakeScreenshot();}
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

    private void TakeScreenshot()
    {
        TakesScreenshot screenshot = (TakesScreenshot) pages.getWebDriver();
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        String destFileString = "./failedTests/" + packageClassName + "/" + getTestEndDate() + testName + ".png";
        File destFile = new File(destFileString);

        try {
            FileUtils.copyFile(srcFile, destFile);
            Error(testName + " failed and a screenshot was taken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
