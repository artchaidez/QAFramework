package autoFramework;

import jdk.jfr.Timespan;
import org.testng.annotations.Test;

import java.net.http.HttpResponse;
import java.text.Annotation;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;

public class AutoLogger {

    private final TestContextLogger testContextLogger = new TestContextLogger();

    private int stepNumber = 1;

    private LocalDateTime testStartDate = LocalDateTime.now();
    private LocalDateTime testEndDate = LocalDateTime.now();
    private String suiteName;

    private String testName;

    private List<Timespan> timeTakenList = new ArrayList<>() {
    };

    public String GetCurrentTestName()
    {
        return testName;
    }

    public boolean IsCurrentTestPassed()
    {
        //ToDo Learn and create a testManager Class
        return false;
    }

    private void ResetTestStartTime()
    {
        testStartDate = LocalDateTime.now();
    }

    public void Debug(String message)
    {
        testContextLogger.Debug(message);
    }

    public void Info(String message)
    {
        testContextLogger.Info(message);
    }

    public void Error(String message)
    {
        //ToDo learn and create GetMessageWithTestName
        testContextLogger.Error(message);
    }

    public void Warning(String message)
    {
        //ToDo learn and create GetMessageWithTestName
        testContextLogger.Warning(message);
    }

    public void Pass(String message)
    {
        testContextLogger.Pass("   (PASS)   Got : " + message);
    }

    public void Pass(String message, String actualValue, Boolean includedGot)
    {
        if(includedGot == null)
            includedGot = true;

        String got = includedGot ? "Got: " : "";
        String defaultMessage = "   (PASS)  " + got + actualValue;

        testContextLogger.Pass(defaultMessage);
    }

    public void FailCompare(String message, Exception ex)
    {
        StringBuilder exceptionMessage = new StringBuilder(ex.getMessage());
        String splitOnTerm = "";
        String eMessage = "";

        int idx = exceptionMessage.lastIndexOf(("With configuration:\n - User declared types and members"));
        if (idx >= 0)
            eMessage = exceptionMessage.deleteCharAt(idx).toString();

        try
        {
            List<String> options = new ArrayList<>() {
            };
            options.add("actual");
            options.add("subject");
            options.add("Object");

            for (String option : options)
            {
                splitOnTerm = MessageFormat.format("Expected {0} to be ", option);

                if(eMessage.contains(splitOnTerm))
                {
                    break;
                }

                String exp = eMessage.split(splitOnTerm)[1];
                exp = exp.split(", but found")[0];

                String got = eMessage.split(splitOnTerm)[1];
                got = got.split(", but found")[1].trim();

                testContextLogger.Fail("   (FAIL) Got: " + got);
                testContextLogger.Info("          Exp: " + exp);

            }
        } catch(Exception e)
        {
            testContextLogger.Fail(eMessage);
        }
    }

    public void Step(String message)
    {
        testContextLogger.Step(message,stepNumber);

        stepNumber++;
    }

    // TODO: rework
    /** Used in Post to Log API info */
    public void apiLog(HttpResponse<String> response, String requestBody)
    {
        String message = response.request().method() + ": " + response.uri() + "\n" +
                "REQUEST BODY: " + requestBody + "\n" +
                "STATUS CODE: " + response.statusCode() + "\n" +
                "RESPONSE BODY: " + response.body();

        Info(message);
    }

    // TODO: needs to be reworked. Does not work in MyListener, MyInvokedMethodListener, or StartTest()
    public void ResetSteps()
    {
        stepNumber = 1;
    }

    public void IgnoreTest(String message)
    {
        Warning(message);
        //ToDo figure out how to ignore test
    }

    public void StartTest(String testName)
    {
        //ToDo Test execution context class, to get all test data
        /*Test wtf = Test.class.getAnnotation(Test.class);
        TestInfo context = TestInfo.class.getAnnotation(TestInfo.class);
        Annotation context2 = (Annotation) Test.class.getAnnotation(TestInfo.class);*/

        List<String> messages = new ArrayList<>();
        messages.add("");
        messages.add("==========================================================================");
        messages.add("     Starting test  :  " + testName);
        //messages.add("     Description    :  " + context.description());
        messages.add("==========================================================================");


        for(String message : messages)
        {
            Info(message);
        }


        /*
         * var description = something
         *
         * List<String> messages = new ArrayList<String>(){
         * "==========================================================================",
         * "     Starting test  :  " + context.CurrentTest.Name,
         * "     Description    :  " + description,
         * "=========================================================================="
         * }
         *
         *
         * for(String message : messages)
         * {
         * info(message);
         * }
         *
         * setTestStatusToPass()
         * stepNumber = 1;
         * this.testName = context.Current.MethodName;
         * */
    }

}
