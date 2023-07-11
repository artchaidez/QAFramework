package autoFramework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Objects;

public class ExtentReportManager {

    ExtentReportManager() {}
    static ExtentReports extent;

    /** Set up ExtentReport if it is null, or return existing extent. */
    public static ExtentReports SetUpExtentReporter()
    {
        if (Objects.isNull(extent)) {
            ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "testReport.html");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            reporter.config().setTheme(Theme.DARK);
            reporter.config().setDocumentTitle("Extent Report");
            reporter.config().setReportName("QA Framework");
        }
        return extent;
    }

}
