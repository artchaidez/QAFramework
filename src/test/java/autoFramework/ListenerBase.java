package autoFramework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ListenerBase extends AutoTestBase
{
    private static String testName;
    private static String packageName;
    private static String className;
    private static String screenshotDir;
    private final ExtentFactory extentFactory = new ExtentFactory();

    private final ExtentReportManager extentReportManager = new ExtentReportManager();

    public ExtentReports SetUpExtentReporter() {
        return extentReportManager.GetInstance();
    }

    // TODO: Keep, may not be needed
    public ExtentFactory GetExtentFactory() {
        return extentFactory;
    }

    public ExtentTest GetExtentTest() {
        return ThreadLocalExtentTest.GetTest();
    }


    // Not needed but keep just in case
    public void SetExtentTest(ExtentTest extentTestObject) {
        ThreadLocalExtentTest.SetTest(extentTestObject);
    }

    public void RemoveExtentTest() {
        ThreadLocalExtentTest.RemoveTest();
    }

    // Getter methods
    public String GetTestName() { return testName; }
    public String GetPackageName() { return packageName; }
    public String GetClassName() { return className; }
    /** Returns packageName.className */
    public String GetPackageClassName() { return packageName + "." + className; }
    /** Returns className.testName */
    public String GetClassTestName() { return className + "." + testName; }

    // Setter methods
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