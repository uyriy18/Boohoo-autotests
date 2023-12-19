package Boohoo.BHM.resourses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	

	public static ExtentReports reportCreator() {
		String path = System.getProperty("user.dir") + "\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Boohoo Web Automation");
		reporter.config().setDocumentTitle("Boohoo Test Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA Engineer", "Bartash Yurii");
		extent.createTest(path);
		return extent;
	}
}
