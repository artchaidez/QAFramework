package autoFramework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.Pages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Extended by listener classes. */
public class ListenerBase extends AutoLogger
{
    private static String testName;
    private static String packageName;
    private static String className;
    private static String screenshotDir;
    private static String testLevel;
    private static final ExtentFactory extentFactory = new ExtentFactory();
    private static final ExtentReportManager extentReportManager = new ExtentReportManager();
    private final TestExecutionContext testExecutionContext = new TestExecutionContext();

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
    public void SetPackageClassName(String name) { SplitPackageClassName(name); }

    private void SplitPackageClassName(String packageClassName)
    {
        String[] split = packageClassName.split("\\.");

        packageName = split[0];
        className = split[1];
    }

    // Methods for Extent Report
    /** Set up ExtentReport and ExtentSparkReporter. */
    public static ExtentReports SetUpExtentReporter() { return extentReportManager.SetUpExtentReporter(); }

    // TODO: Keep, may not be needed
    public ExtentFactory GetExtentFactory() { return extentFactory; }

    public static ExtentReports GetExtentReport() {
        return SetUpExtentReporter();
    }

    public static void FlushExtentReport() { extentReportManager.Flush(); }

    public static ExtentTest GetExtentTest() { return extentFactory.GetExtentTest(); }

    /** Set up ExtentTest at the start of test*/
    public void SetExtentTest(ExtentTest extentTestObject) { extentFactory.SetExtentTest(extentTestObject); }

    /** Removes ExtentTest from thread. */
    public void RemoveExtentTest() { extentFactory.RemoveExtentObject(); }

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

    public String GetScreenShotDir() { return screenshotDir; }

    /** Logs testExecutionContext to provide information at the start of the test. */
    public void LogStartTestInfo(String testName, String packageClassName) throws ClassNotFoundException {

        testExecutionContext.GetTestInfoContext(testName, packageClassName);

        List<String> messages = new ArrayList<>();
        messages.add("");
        messages.add("==========================================================================");
        messages.add("     Starting test  : " + testName);
        messages.add("     Test package   : " + testExecutionContext.getPackageName());
        messages.add("     Test class     : " + testExecutionContext.getClassName());
        messages.add("     Description    : " + testExecutionContext.getDescription());
        messages.add("     Test level     : " + testExecutionContext.getLevel());
        //messages.add("     Test categories:  " + testExecutionContext.getCategories());
        messages.add("==========================================================================");

        // set test level to be categorized by Extent Report
        // need to save level in static var to be used in other methods
        testLevel = testExecutionContext.getLevel();

        // Log test info
        for(String message : messages)
        {
            SetUpInfo(message);
        }
    }

    public static String GetTestLevel() { return testLevel; }
}