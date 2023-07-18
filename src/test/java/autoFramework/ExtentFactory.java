package autoFramework;

import com.aventstack.extentreports.ExtentTest;

// FIXME: Does not run in parallel. However, parallel works in debugger
// IExecutionListener does not resolve issue
public class ExtentFactory
{
    ExtentFactory() {
    }

    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public ExtentTest GetExtentTest() { return extentTest.get(); }

    public void SetExtentTest(ExtentTest extentTestObject) { extentTest.set(extentTestObject); }

    public void RemoveExtentObject() { extentTest.remove();}

}
