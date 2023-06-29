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
    private String testLevel;
    //Singleton design Pattern
    //private constructor so that no one else can create object of this class
    private ExtentFactory() {

    }

    private static ExtentFactory instance  = new ExtentFactory();

    public static ExtentFactory getInstance() {
        return instance;
    }

    //factory design pattern --> define separate factory methods for creating objects and create objects by calling that methods
    ThreadLocal<ExtentTest> extent = new ThreadLocal<>();

    public ExtentTest GetExtent() {
        return extent.get();
    }

    public void SetExtent(ExtentTest extentTestObject) {
        extent.set(extentTestObject);
    }

    public void RemoveExtentObject() {
        extent.remove();
    }

    public void LogStep(String message, int stepNumber)
    {
        Markup markUp = MarkupHelper.createLabel("Step " + stepNumber + " - " + message, ExtentColor.BLUE);
        ExtentFactory.getInstance().GetExtent().log(Status.INFO, markUp);
    }

    /** Used in Post() and Put() to log API info */
    public void ApiLog(Response response, String requestBody, String resource, String requestMethod)
    {
        String message = requestMethod + ": " + resource + "<br />";
        message = message + "REQUEST BODY: " +  requestBody + "<br />";
        message = message + "STATUS CODE: " + response.statusCode() + "<br />";
        message = message + "RESPONSE BODY: " + response.asString() + "<br />";
        ExtentFactory.getInstance().GetExtent().log(Status.INFO, message);
    }

    /** Used in Get() ands Delete() to log API info*/
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
    }

    public void SetLevel(String level){ testLevel = level;}

    public String GetLevel() {return testLevel;}

}
