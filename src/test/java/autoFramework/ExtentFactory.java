package autoFramework;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;

// FIXME: Does not run in parallel. However, parallel works in debugger
// IExecutionListener, ISuiteListener methods do not resolve issue
// TODO: Create instance as another class (ExtentReportManager?, or as Logger?), move methods with it.
public class ExtentFactory
{
    private String testLevel;
    public ExtentFactory() {

    }


    /*public static ExtentFactory getInstance() {
        return instance;
    }*/

    public ExtentTest GetExtent() {
        return ThreadLocalExtentTest.GetTest();
    }

    // Not needed but keep just in case
    public void SetExtent(ExtentTest extentTestObject) {
        ThreadLocalExtentTest.SetTest(extentTestObject);
    }

    // Not needed but keep just in case. Does not seem to effect thread tests
    public void RemoveExtentObject() {
        ThreadLocalExtentTest.RemoveTest();
    }
/*
    public void LogStep(String message, int stepNumber)
    {
        Markup markUp = MarkupHelper.createLabel("Step " + stepNumber + " - " + message, ExtentColor.BLUE);
        ExtentFactory.getInstance().GetExtent().log(Status.INFO, markUp);
    }

    *//** Used in Post() and Put() to log API info *//*
    public void ApiLog(Response response, String requestBody, String resource, String requestMethod)
    {
        String message = requestMethod + ": " + resource + "<br />";
        message = message + "REQUEST BODY: " +  requestBody + "<br />";
        message = message + "STATUS CODE: " + response.statusCode() + "<br />";
        message = message + "RESPONSE BODY: " + response.asString() + "<br />";
        ExtentFactory.getInstance().GetExtent().log(Status.INFO, message);
    }

    *//** Used in Get() ands Delete() to log API info*//*
    public void ApiLog(Response response, String resource, String requestMethod)
    {
        String message = requestMethod + ": " + resource + "<br />";
        message = message + "STATUS CODE: " + response.statusCode() + "<br />";
        message = message + "RESPONSE BODY: " + response.asString() + "<br />";
        ExtentFactory.getInstance().GetExtent().log(Status.INFO, message);
    }

    public void Info(String message){
        ExtentFactory.getInstance().GetExtent().log(Status.INFO, message);
    }

    public void Pass(String message){
        ExtentFactory.getInstance().GetExtent().log(Status.PASS, message);
    }

    public void Fail(String actual, String expected) {
        String message = actual + "<br />" + expected;
        ExtentFactory.getInstance().GetExtent().log(Status.FAIL, message);
    }*/

    // TODO: this should be done elsewhere
    public void SetLevel(String level){ testLevel = level;}

    // TODO: this should be done elsewhere
    public String GetLevel() {return testLevel;}

}
