package unitTests;

import autoFramework.AutoTestBase;
import autoFramework.TestInfo;
import listeners.BaseInvokedMethodListener;
import listeners.BaseTestListener;
import org.testng.annotations.*;

@Listeners({BaseTestListener.class, BaseInvokedMethodListener.class})
public class VerifyTestSuite extends AutoTestBase{

    @BeforeMethod
    public void TestSetUp()
    {
    }

    @AfterMethod
    public void TestTearDown()
    {
    }

    @Ignore("Test exists to show Verify works as intended.")
    @TestInfo(description = "Verify new asserts work and pass.")
    public void TestVerifyShouldPass() {

        boolean checkFalse = false;
        boolean checkTrue = true;
        String checkString = "Art";
        int checkInt = 5;

        Step("Verify.That(false).IsFalse() runs and passes");
            Verify.That(checkFalse).IsFalse();

        Step("Verify.That(true).IsTrue() runs and passes");
            Verify.That(checkTrue).IsTrue();

        Step("Verify.That(string).Equals(string) runs and passes");
            Verify.That(checkString).Equals("Art");

        Step("Verify.That(int).Equals(int) runs and passes");
            Verify.That(checkInt).Equals(5);

        Step("Verify.That(string).DoesNotEqual(string) runs and passes");
            Verify.That(checkString).DoesNotEqual("Arturo");

    }

    @Ignore("Test exists to show Verify works as intended.")
    @TestInfo(description = "Verify assert fails but test still completes.")
    public void TestFailsStillFinishes() {

        boolean checkFalse = false;
        String checkString = "Art";
        int checkInt = 5;

        Step("Verify.That(false).IsTrue() runs and fails");
            Verify.That(checkFalse).IsTrue();

        Step("Verify.That(string).Equals(string) runs and passes");
            Verify.That(checkString).Equals("Art");

        Step("Verify.That(int).Equals(int) runs and passes");
            Verify.That(checkInt).Equals(5);
    }

    @Ignore("Test exists to show Verify works as intended.")
    @TestInfo(description = "Verify assert shows two failures")
    public void TestTwoFailures() {

        boolean checkFalse = false;
        String checkString = "Art";
        int checkInt = 5;

        Step("Verify.That(false).IsTrue() runs and fails");
        Verify.That(checkFalse).IsTrue();

        Step("Verify.That(string).Equals(string) runs and passes");
        Verify.That(checkString).Equals("Art");

        Step("Verify.That(int).Equals(int) runs and fails");
        Verify.That(checkInt).Equals(6);
    }


}
