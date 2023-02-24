package autoFramework;

import org.testng.Assert;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;
import org.testng.Reporter;
import java.util.List;
import java.util.ArrayList;

/** A fluent interface to improve readability and log asserts. */
public class Verify extends AutoLogger {

    private boolean thatBoolVar;
    private String thatStringVar;
    private int thatIntVar;
    // Needs to be static
    private static Map<ITestResult, List> verificationFailuresMap = new HashMap<>();

    public Verify That(boolean boolVar)
    {
        thatBoolVar = boolVar;
        return this;
    }

    public Verify That(String stringVar)
    {
        thatStringVar = stringVar;
        return this;
    }

    public Verify That(int intVar)
    {
        thatIntVar = intVar;
        return this;
    }

    public void IsTrue()
    {
        try {
            Assert.assertTrue(thatBoolVar);
            Info(String.format("Observed: %s, Expected: %s", thatBoolVar, true));
        } catch (Throwable e) {
            Error(String.format("Observed: %s, Expected: %s", thatBoolVar, true));
            addVerificationFailure(e);
        }
    }

    public void IsFalse()
    {
        try {
            Assert.assertFalse(thatBoolVar);
            Info(String.format("Observed: %s, Expected: %s", thatBoolVar, false));
        } catch (Throwable e) {
            Error(String.format("Observed: %s, Expected: %s", thatBoolVar, false));
            addVerificationFailure(e);
        }
    }

    public void Equals(String stringVar)
    {
        try {
            Assert.assertEquals(thatStringVar, stringVar);
            Info(String.format("Observed: %s, Expected: %s", thatStringVar, stringVar));

        } catch (Throwable e)
        {
            Error(String.format("Observed: %s, Expected: %s", thatStringVar, stringVar));
            addVerificationFailure(e);
        }
    }

    public void Equals(int intVar)
    {
        try {
            Assert.assertEquals(thatIntVar, intVar);
            Info(String.format("Observed: %s, Expected: %s", thatIntVar, intVar));
        } catch (Throwable e)
        {
            Error(String.format("Observed: %s, Expected: %s", thatIntVar, intVar));
            addVerificationFailure(e);
        }
    }

    public void DoesNotEqual(String stringVar)
    {
        try {
            Assert.assertNotEquals(thatStringVar, stringVar);
            Info(String.format("SHOULD NOT BE EQUAL: Observed: %s, Expected: %s", thatStringVar, stringVar));
        } catch (Throwable e)
        {
            addVerificationFailure(e);
            Error(String.format("SHOULD NOT BE EQUAL: Observed: %s, Expected: %s", thatStringVar, stringVar));
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
