package autoFramework;

import jdk.jfr.Timespan;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AutoLogger {

    private final TestContextLogger testContextLogger = new TestContextLogger();
    private final TestExecutionContext testExecutionContext = new TestExecutionContext();

    private int stepNumber = 1;

    private LocalDateTime testStartDate = LocalDateTime.now();
    private LocalDateTime testEndDate = LocalDateTime.now();
    private String suiteName;

    private String testName;

    private List<Timespan> timeTakenList = new ArrayList<>() {
    };

    // TODO: Works for suites; not in parallel
    /*public AutoLogger()
    {
        TestContextLogger testContextLoggerObj = new TestContextLogger();
        testContextLogger.set(Objects.requireNonNull(testContextLoggerObj));

        TestExecutionContext testExecutionContextObj = new TestExecutionContext();
        testExecutionContext.set(Objects.requireNonNull(testExecutionContextObj));
    }*/

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

    /** Log what is currently being done by the code. Increments int stepNumber.*/
    public void Step(String message)
    {
        testContextLogger.Step(message,stepNumber);

        stepNumber++;
    }

    /** Used in Post to Log API info */
    public void apiLog(HttpResponse<String> response, String requestBody)
    {
        Info(response.request().method() + ": " + response.uri());
        Info("REQUEST BODY: " +  requestBody);
        Info("STATUS CODE: " + response.statusCode());
        Info("RESPONSE BODY: " + response.body());
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

    /** Logs testExecutionContext to provide information at the tart of the test. */
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
