package autoFramework;

import io.restassured.response.Response;
import jdk.jfr.Timespan;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AutoLogger {

    private final TestContextLogger testContextLogger = new TestContextLogger();
    private final TestExecutionContext testExecutionContext = new TestExecutionContext();

    // Needs to be static for Listeners
    private static int stepNumber = 1;

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

    /** Log info */
    public void Info(String message)
    {
        testContextLogger.Info(message);
    }

    /** Log error */
    public void Error(String message)
    {
        //ToDo learn and create GetMessageWithTestName
        testContextLogger.Error(message);
    }

    /** Log warning */
    public void Warning(String message)
    {
        //ToDo learn and create GetMessageWithTestName
        testContextLogger.Warning(message);
    }

    public void Pass(String message)
    {
        testContextLogger.Pass("   (PASS)   Got : " + message);
    }

    public void FailCompare(Throwable ex)
    {
        // eMessage will always be "expected [exp] but found [got]"
        String eMessage = ex.getMessage();

        String[] splitMessage = eMessage.split("\\[");
        // expected answer in "expected [exp] but found [got]"
        String expected = splitMessage[1].split("\\]")[0];

        // actual answer in "expected [exp] but found [got]"
        String actual = splitMessage[2].split("\\]")[0];

        testContextLogger.Fail("   (FAIL) Actual: " + actual);
        testContextLogger.Fail("          Expected: " + expected);
    }

    /** Log what is currently being done by the code. Increments int stepNumber.*/
    public void Step(String message)
    {
        testContextLogger.Step(message,stepNumber);

        stepNumber++;
    }

    /** Log what the actual value should be. Increments int stepNumber.
     * @param message String accepts argument position {0} using Message.format()
     * @param actual String of actual value */
    public void Step(String message, String actual)
    {
        message = MessageFormat.format(message, actual);
        testContextLogger.Step(message,stepNumber);

        stepNumber++;
    }

    /** Used in Post() and Put() to log API info */
    public void apiLog(Response response, String requestBody, String resource, String requestMethod)
    {
        // Response does not contain request method or uri; passed in as args
        Info(requestMethod + ": " + resource);
        Info("REQUEST BODY: " +  requestBody);
        Info("STATUS CODE: " + response.statusCode());
        Info("RESPONSE BODY: " + response.asString());
    }

    /** Used in Get() ands Delete() to log API info*/
    public void apiLog(Response response, String resource, String requestMethod)
    {
        // Response does not contain request method or uri; passed in as args
        Info(requestMethod + ": " + resource);
        Info("STATUS CODE: " + response.statusCode());
        Info("RESPONSE BODY: " + response.asString());
    }

    /** Resets int stepNumber after test method finishes. */
    public void ResetSteps()
    {
        stepNumber = 1;
    }

    public void IgnoreTest(String message)
    {
        Warning(message);
        //ToDo figure out how to ignore test
    }

    /** Logs testExecutionContext to provide information at the start of the test. */
    public void StartTest(String testName, String packageClassName) throws ClassNotFoundException {

        testExecutionContext.getTestInfoContext(testName, packageClassName);

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


        for(String message : messages)
        {
            Info(message);
        }
    }

    // TODO: figure out way to get level and description to log on extent report
    public String GetTestLevel(){
        return testExecutionContext.getLevel();
    }

    /** Returns date format of: yyyy-mm-dd_hh-mm. Hours (hh) will be in 24-hour format. */
    public String getTestEndDate()
    {
        // toLocaleDate.toString() --> yyyy-mm-dd
        String formattedDate = testEndDate.toLocalDate().toString();
        // toLocaleTime.toString() --> hh:mm:ss.ms
        // split(":", 3) converts LocaleTime into [hh, mm, ss.ms]
        String[] formattedTime = testEndDate.toLocalTime().toString().split(":", 3);
        formattedDate = formattedDate.concat("_" + formattedTime[0] + "-" + formattedTime[1]);

        return formattedDate;
    }

}
