package autoFramework;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;

// FIXME: Does not run in parallel. However, parallel works in debugger
// IExecutionListener does not resolve issue
// TODO: Create instance as another class (ExtentReportManager?, or as Logger?), move methods with it.
public class ExtentFactory
{
    private String testLevel;
    public ExtentFactory() {

    }

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal();

    public ExtentTest GetExtentTest()
    {
        return extentTest.get();
    }

    public void SetExtentTest(ExtentTest extentTestObject)
    {
        extentTest.set(extentTestObject);
    }

    public void RemoveExtentObject()
    {
        extentTest.remove();
    }


    // TODO: this should be done elsewhere
    public void SetLevel(String level){ testLevel = level;}

    // TODO: this should be done elsewhere
    public String GetLevel() {return testLevel;}

}
