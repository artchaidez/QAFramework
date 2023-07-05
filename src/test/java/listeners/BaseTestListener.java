package listeners;

import autoFramework.ExtentFactory;
import autoFramework.ExtentReportManager;
import autoFramework.ListenerBase;
import autoFramework.ThreadLocalExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class BaseTestListener extends ListenerBase implements ITestListener
{
    static ExtentReports report;

    @Override
    public void onTestStart(ITestResult result)
    {
        SetTestName(result.getMethod().getMethodName());
        SetPackageClassName(result.getMethod().getRealClass().getCanonicalName());
        SetScreenShotDir();

        ThreadLocalExtentTest.SetTest(report.createTest(GetClassTestName()));
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        Markup message = MarkupHelper.createLabel(GetTestName() + " passed.", ExtentColor.GREEN);
        ExtentFactory.getInstance().GetExtent().log(Status.PASS, message)
                .assignCategory(GetClassName())
                .assignCategory(ExtentFactory.getInstance().GetLevel());
        //ExtentFactory.getInstance().RemoveExtentObject();
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        if (GetPackageName().contains("webTestSuites")) {
            TakeScreenshot();
            ExtentFactory.getInstance().GetExtent().log(Status.FAIL, result.getThrowable())
                    .addScreenCaptureFromPath(GetScreenShotDir())
                    .assignCategory(GetClassName())
                .assignCategory(ExtentFactory.getInstance().GetLevel());
        }
        else {
            ExtentFactory.getInstance().GetExtent().log(Status.FAIL, result.getThrowable())
                    .assignCategory(GetClassName())
                    .assignCategory(ExtentFactory.getInstance().GetLevel());
        }

        //ExtentFactory.getInstance().RemoveExtentObject();
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        ExtentFactory.getInstance().GetExtent().log(Status.SKIP, result.getMethod().getMethodName()+ " skipped.")
                .assignCategory(GetClassName())
                .assignCategory(ExtentFactory.getInstance().GetLevel());;
        //ExtentFactory.getInstance().RemoveExtentObject();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        report = ExtentReportManager.GetInstance();
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
    }

}
