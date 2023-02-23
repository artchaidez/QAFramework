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
        Info(String.format("Observed: %s, Expected: %s", thatBoolVar, true));

        try {
            Assert.assertEquals(thatBoolVar, true);
        } catch (Throwable e) {
            addVerificationFailure(e);
        }
    }

    public void IsFalse()
    {
        Info(String.format("Observed: %s, Expected: %s", thatBoolVar, false));

        try {
            Assert.assertEquals(thatBoolVar, false);
        } catch (Throwable e) {
            addVerificationFailure(e);
        }
    }

    public void Equals(String stringVar)
    {
        Info(String.format("Observed: %s, Expected: %s", thatStringVar, stringVar));

        try {
            Assert.assertEquals(thatStringVar, stringVar);
        } catch (Throwable e)
        {
            addVerificationFailure(e);
        }
    }

    public void Equals(int intVar)
    {
        Info(String.format("Observed: %s, Expected: %s", thatIntVar, intVar));

        try {
            Assert.assertEquals(thatIntVar, intVar);
        } catch (Throwable e)
        {
            addVerificationFailure(e);
        }
    }

    public Verify DoesNotEqual(String stringVar)
    {
        Info(String.format("SHOULD NOT BE EQUAL: Observed: %s, Expected: %s", thatStringVar, stringVar));

        try {
            Assert.assertNotEquals(thatStringVar, stringVar);
        } catch (Throwable e)
        {
            addVerificationFailure(e);
        }
        return this;
    }

    /** Use method within a try catch. Will fail the test in BaseInvokedMethodListener */
    public void ThrowFailure()
    {
        try {
            Assert.assertTrue(false);
        } catch (Throwable e)
        {
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
