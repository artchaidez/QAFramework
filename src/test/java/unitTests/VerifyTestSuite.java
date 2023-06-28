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
public class VerifyTestSuite extends AutoTestBase{

    @BeforeMethod
    public void TestSetUp()
    {
    }

    @AfterMethod
    public void TestTearDown()
    {
    }

    @Test(description = "Test exists to show Verify works as intended." )
    @TestInfo(description = "Verify new asserts work and pass.")
    public void TestVerifyShouldPass() {

        boolean actualFalse = false;
        boolean actualTrue = true;
        String actualString = "Art";
        String expectedString = "Art";
        int actualInt = 5;
        int expectedInt = 5;

        Step("Verify.That(false).IsFalse() runs and passes");
            Verify.That(actualFalse).IsFalse();

        Step("Verify.That(true).IsTrue() runs and passes");
            Verify.That(actualTrue).IsTrue();

        Step(String.format("Verify.That(%s).Equals(%s) runs and passes", actualString, expectedString));
            Verify.That(actualString).Equals(expectedString);

        Step(String.format("Verify.That(%s).Equals(%s) runs and passes", actualInt, expectedInt));
            Verify.That(actualInt).Equals(expectedInt);

    }

    @Test(enabled = false, description = "Test exists to show Verify works as intended.")
    @TestInfo(description = "Verify assert fails but test still completes.")
    public void TestFailsStillFinishes() {

        boolean actualFalse = false;
        String actualString = "Art";
        String expectedString = "Art";
        int actualInt = 5;
        int expectedInt = 5;

        Step("Verify.That(false).IsTrue() runs and fails");
            Verify.That(actualFalse).IsTrue();

        Step(String.format("Verify.That(%s).Equals(%s) runs and passes", actualString, expectedString));
            Verify.That(actualString).Equals("Art");

        Step(String.format("Verify.That(int).Equals(int) runs and passes", actualInt, expectedInt));
            Verify.That(actualInt).Equals(expectedInt);
    }

    @Test(enabled = false, description = "Test exists to show Verify works as intended.")
    @TestInfo(description = "Verify assert shows two failures")
    public void TestTwoFailures() {

        boolean actualFalse = false;
        String actualString = "Art";
        String expectedString = "Art";
        int actualInt = 5;
        int expectedInt = 6;

        Step("Verify.That(false).IsTrue() runs and fails");
            Verify.That(actualFalse).IsTrue();

        Step(String.format("Verify.That(%s).Equals(%s) runs and passes", actualString, expectedString));
            Verify.That(actualString).Equals(expectedString);

        Step(String.format("Verify.That(%s).Equals(%s) runs and fails", actualInt, expectedInt));
            Verify.That(actualInt).Equals(expectedInt);
    }

    @Test(enabled = false, description = "Test exists to show Verify works as intended.")
    @TestInfo(description = "Verify all asserts fail correctly with correct logging info")
    public void TestAllAssertsFail()
    {
        int actualInt = 5;
        int expectedInt = 6;
        String actualString = "Art";
        String expectedString = "Arturo";

        Step("Verify.That(false).IsTrue() fails and logs correctly");
            Verify.That(false).IsTrue();

        Step("Verify.That(True).IsFalse() fails and logs correctly");
            Verify.That(true).IsFalse();

        Step(String.format("Verify.That(%s).Equals(%s) string assert fails and logs correctly", actualString, expectedString));
            Verify.That(actualString).Equals(expectedString);

        Step(String.format("Verify.That(%s).Equals(%s) int assert fails and logs correctly", actualInt, expectedInt));
            Verify.That(actualInt).Equals(expectedInt);

    }



}
