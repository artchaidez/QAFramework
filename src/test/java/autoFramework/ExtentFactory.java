package autoFramework;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;

// FIXME: Does not run in parallel. However, parallel works in debugger
// IExecutionListener does not resolve issue
public class ExtentFactory
{
    public ExtentFactory() {
    }

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal();

    public ExtentTest GetExtentTest() { return extentTest.get(); }

    public void SetExtentTest(ExtentTest extentTestObject) { extentTest.set(extentTestObject); }

    public void RemoveExtentObject() { extentTest.remove();}

}
