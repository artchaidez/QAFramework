package autoFramework;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;

// FIXME: Does not run in parallel. However, parallel works in debugger
public class ExtentFactory {
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

    public ExtentTest getExtent() {
        return extent.get();
    }

    public void setExtent(ExtentTest extentTestObject) {
        extent.set(extentTestObject);
    }

    public void removeExtentObject() {
        extent.remove();
    }

    public void Log(String message, int stepNumber) {
        message = "Step " + stepNumber + " - " + message;
        ExtentFactory.getInstance().getExtent().log(Status.INFO, message);
    }

    /** Used in Post() and Put() to log API info */
    public void ApiLog(Response response, String requestBody, String resource, String requestMethod)
    {
        String message = requestMethod + ": " + resource + "<br />";
        message = message + "REQUEST BODY: " +  requestBody + "<br />";
        message = message + "STATUS CODE: " + response.statusCode() + "<br />";
        message = message + "RESPONSE BODY: " + response.asString() + "<br />";
        ExtentFactory.getInstance().getExtent().log(Status.INFO, message);
    }

    /** Used in Get() ands Delete() to log API info*/
    public void ApiLog(Response response, String resource, String requestMethod)
    {
        String message = requestMethod + ": " + resource + "<br />";
        message = message + "STATUS CODE: " + response.statusCode() + "<br />";
        message = message + "RESPONSE BODY: " + response.asString() + "<br />";
        ExtentFactory.getInstance().getExtent().log(Status.INFO, message);
    }

    // TODO: Figure out way SeleniumControl Info logging to be in one Status.Info log
    public void Info(String message){
        ExtentFactory.getInstance().getExtent().log(Status.INFO, message);
    }

    public void Pass(String message){
        ExtentFactory.getInstance().getExtent().log(Status.PASS, message);
    }

    public void Fail(String actual, String expected) {
        String message = actual + "<br />" + expected;
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, message);
    }
}
