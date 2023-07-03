package autoFramework;

import com.aventstack.extentreports.ExtentTest;

public class ThreadLocalExtentTest {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal();

    public static ExtentTest GetTest() {
        return extentTest.get();
    }

    public static void SetTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static void RemoveTest()
    {
        extentTest.remove();
    }
}
