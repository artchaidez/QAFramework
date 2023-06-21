package autoFramework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReportManager {

    static ExtentReports extent;

    public static ExtentReports SetUpExtentReporter()
    {
        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "testReport.html");

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("Extent Report");
        reporter.config().setReportName("My tests");

        return extent;
    }

    public static ExtentReports getInstance() {
        if(extent == null) {
            SetUpExtentReporter();
        }
        return extent;
    }

}
