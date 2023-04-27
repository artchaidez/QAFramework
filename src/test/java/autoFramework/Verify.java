package autoFramework;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.testng.Assert;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;
import org.testng.Reporter;
import java.util.List;
import java.util.ArrayList;

/** A fluent interface to improve readability and log asserts. */
public class Verify extends AutoLogger {

    private boolean actualBoolVar;
    private String actualStringVar;
    private int actualIntVar;
    // Needs to be static
    private static Map<ITestResult, List> verificationFailuresMap = new HashMap<>();

    /** Argument should be actual bool variable that is being tested. */
    public Verify That(boolean boolVar)
    {
        actualBoolVar = boolVar;
        return this;
    }

    /** Argument should be actual string variable that is being tested. */
    public Verify That(String stringVar)
    {
        actualStringVar = stringVar;
        return this;
    }

    /** Argument should be actual int variable that is being tested. */
    public Verify That(int intVar)
    {
        actualIntVar = intVar;
        return this;
    }

    /** Verify actual bool variable being tested is True. */
    public void IsTrue()
    {
        try {
            Assert.assertTrue(actualBoolVar);
            Pass("True");
        } catch (Throwable e) {
            FailCompare(e);
            addVerificationFailure(e);
        }
    }

    /** Verify actual bool variable being tested is False. */
    public void IsFalse()
    {
        try {
            Assert.assertFalse(actualBoolVar);
            Pass("False");
        } catch (Throwable e) {
            FailCompare(e);
            addVerificationFailure(e);
        }
    }

    /** Argument should be expected string variable.*/
    public void Equals(String expectedStringVar)
    {
        try {
            Assert.assertEquals(actualStringVar, expectedStringVar);
            Pass(expectedStringVar);
        } catch (Throwable e)
        {
            FailCompare(e);
            addVerificationFailure(e);
        }
    }

    /** Argument should be expected int variable.*/
    public void Equals(int expectedIntVar)
    {
        try {
            Assert.assertEquals(actualIntVar, expectedIntVar);
            Pass(String.valueOf(expectedIntVar));
        } catch (Throwable e)
        {
            FailCompare(e);
            addVerificationFailure(e);
        }
    }

    /** Use method within a try catch. Will fail the test in BaseInvokedMethodListener */
    public void ThrowFailure()
    {
        try {
            Assert.fail();
        } catch (Throwable e)
        {
            Error("Thrown failure");
            addVerificationFailure(e);
        }
    }

    public static List getVerificationFailures() {
        List verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
        return verificationFailures == null ? new ArrayList() : verificationFailures;
    }

    private static void addVerificationFailure(Throwable e) {
        List verificationFailures = getVerificationFailures();
        verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
        verificationFailures.add(e);
    }


}
