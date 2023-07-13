package listeners;

import autoFramework.ListenerBase;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class BaseTestListener extends ListenerBase implements ITestListener
{

    @Override
    public void onTestStart(ITestResult result)
    {
        SetTestName(result.getMethod().getMethodName());
        SetPackageClassName(result.getMethod().getRealClass().getCanonicalName());
        SetScreenShotDir();

        SetExtentTest(GetExtentReport().createTest(GetClassTestName()));
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        Markup message = MarkupHelper.createLabel(GetTestName() + " passed.", ExtentColor.GREEN);
        GetExtentTest().log(Status.PASS, message)
                .assignCategory(GetClassName())
                .assignCategory(GetTestLevel());
        RemoveExtentTest();
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        if (GetPackageName().contains("webTestSuites")) {
            TakeScreenshot();
            GetExtentTest().log(Status.FAIL, result.getThrowable())
                    .addScreenCaptureFromPath(GetScreenShotDir())
                    .assignCategory(GetClassName())
                    .assignCategory(GetTestLevel());
        }
        else {
            GetExtentTest().log(Status.FAIL, result.getThrowable())
                    .assignCategory(GetClassName())
                    .assignCategory(GetTestLevel());
        }
        RemoveExtentTest();
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        GetExtentTest().log(Status.SKIP, GetTestName() + " skipped")
                .assignCategory(GetClassName())
                .assignCategory(GetTestLevel());
        RemoveExtentTest();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) { SetUpExtentReporter(); }

    @Override
    public void onFinish(ITestContext context) { FlushExtentReport(); }

}
