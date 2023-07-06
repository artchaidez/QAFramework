package autoFramework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.Pages;

import java.io.File;
import java.io.IOException;

/** Extended by listener classes. */
public class ListenerBase extends AutoLogger
{
    // TODO: should move these Strings and getter/ setters to AutoLogger
    private static String testName;
    private static String packageName;
    private static String className;
    private static String screenshotDir;
    private static final ExtentFactory extentFactory = new ExtentFactory();
    private final ExtentReportManager extentReportManager = new ExtentReportManager();

    // Getter methods for Strings
    public String GetTestName() { return testName; }

    public String GetPackageName() { return packageName; }

    public String GetClassName() { return className; }

    /** Returns packageName.className */
    public String GetPackageClassName() { return packageName + "." + className; }

    /** Returns className.testName */
    public String GetClassTestName() { return className + "." + testName; }

    // Setter methods for Strings
    public void SetTestName(String name) { testName = name; }

    /** Splits PackageName.ClassName and sets them.
     * @param name PackageName.ClassName*/
    public void SetPackageClassName(String name)
    {
        SplitPackageClassName(name);
    }

    private void SplitPackageClassName(String packageClassName)
    {
        String[] split = packageClassName.split("\\.");

        packageName = split[0];
        className = split[1];
    }

    // Methods for Extent Report
    /** Set up ExtentReport and ExtentSparkReporter. */
    public ExtentReports SetUpExtentReporter() {
        return extentReportManager.GetInstance();
    }

    // TODO: Keep, may not be needed
    public ExtentFactory GetExtentFactory() {
        return extentFactory;
    }

    public static ExtentTest GetExtentTest() {
        return extentFactory.GetExtentTest();
    }

    /** Set up ExtentTest at the start of test*/
    public void SetExtentTest(ExtentTest extentTestObject) {
        extentFactory.SetExtentTest(extentTestObject);
    }

    /** Removes ExtentTest from thread. */
    public void RemoveExtentTest() {
        extentFactory.RemoveExtentObject();
    }

    // Screenshot methods
    // TODO: Screenshot first failure, last failure, all failures?
    /** Should only be called for web tests, not API tests. */
    protected void TakeScreenshot()
    {
        TakesScreenshot screenshot = (TakesScreenshot) Pages.getWebDriver();
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        File destFile = new File(screenshotDir);

        try {
            FileUtils.copyFile(srcFile, destFile);
            Error(GetTestName() + " failed and a screenshot was taken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void SetScreenShotDir()
    {
        screenshotDir = "./failedTests/" + GetPackageClassName() + "/" + GetTestEndDate() + GetTestName() + ".png";
    }

    public String GetScreenShotDir()
    {
        return screenshotDir;
    }
}