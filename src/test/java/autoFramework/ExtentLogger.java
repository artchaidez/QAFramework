package autoFramework;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;

public class ExtentLogger {

    public void LogStep(String message, int stepNumber)
    {
        Markup markUp = MarkupHelper.createLabel("Step " + stepNumber + " - " + message, ExtentColor.BLUE);
        ListenerBase.GetExtentTest().log(Status.INFO, markUp);
    }

    /** Used in Post() and Put() to log API info */
    public void ApiLog(Response response, String requestBody, String resource, String requestMethod)
    {
        String message = requestMethod + ": " + resource + "<br />";
        message = message + "REQUEST BODY: " +  requestBody + "<br />";
        message = message + "STATUS CODE: " + response.statusCode() + "<br />";
        message = message + "RESPONSE BODY: " + response.asString() + "<br />";
        ListenerBase.GetExtentTest().log(Status.INFO, message);
    }

    /** Used in Get() ands Delete() to log API info*/
    public void ApiLog(Response response, String resource, String requestMethod)
    {
        String message = requestMethod + ": " + resource + "<br />";
        message = message + "STATUS CODE: " + response.statusCode() + "<br />";
        message = message + "RESPONSE BODY: " + response.asString() + "<br />";
        ListenerBase.GetExtentTest().log(Status.INFO, message);
    }

    public void Info(String message){
        ListenerBase.GetExtentTest().log(Status.INFO, message);
    }

    public void PassStep(String message){
        Markup label = MarkupHelper.createLabel(message, ExtentColor.GREEN);
        ListenerBase.GetExtentTest().log(Status.PASS, label);
    }


    // TODO: Need to set up how to get level
    public void PassTest(String testName)
    {
        Markup message = MarkupHelper.createLabel(testName + " passed.", ExtentColor.GREEN);
        ListenerBase.GetExtentTest().log(Status.PASS, message)
                .assignCategory(testName);
                //.assignCategory(ExtentFactory.getInstance().GetLevel());
    }


    public void Fail(String actual, String expected) {
        String message = actual + "<br />" + expected;
        ListenerBase.GetExtentTest().log(Status.FAIL, message);
    }
}