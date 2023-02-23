package unitTests;

import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import listeners.BaseInvokedMethodListener;
import listeners.BaseTestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({BaseTestListener.class, BaseInvokedMethodListener.class})
public class VerifyAsserts extends AutoTestBase{

    @BeforeMethod
    public void TestSetUp()
    {
    }

    @AfterMethod
    public void TestTearDown()
    {
    }

    @Test
    @TestInfo(description = "Verify new asserts work and pass.")
    public void TestVerifyShouldPass() {

        boolean checkFalse = false;
        boolean checkTrue = true;
        String checkString = "Art";
        int checkInt = 5;

        Step("Verify.That(false).Equals(false) runs and passes");
            Verify.That(checkFalse).IsFalse();

        Step("Verify.That(true).Equals(true) runs and passes");
            Verify.That(checkTrue).IsTrue();

        Step("Verify.That(string).Equals(string) runs and passes");
            Verify.That(checkString).Equals("Art");

        Step("Verify.That(int).Equals(int) runs and passes");
            Verify.That(checkInt).Equals(5);

        Step("Verify.That(string).NotEquals(string) runs and passes");
            Verify.That(checkString).DoesNotEqual("Arturo");

    }

    @Test
    @TestInfo(description = "Verify assert fails but test still completes.")
    public void TestFailsStillFinishes() {

        boolean checkFalse = false;
        String checkString = "Art";
        int checkInt = 5;

        Step("Verify.That(false).Equals(true) runs and fails");
            Verify.That(checkFalse).IsFalse();

        Step("Verify.That(string).Equals(string) runs and passes");
            Verify.That(checkString).Equals("Art");

        Step("Verify.That(int).Equals(int) runs and passes");
            Verify.That(checkInt).Equals(5);
    }


}
