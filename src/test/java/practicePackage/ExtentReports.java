package practicePackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReports {
	@Test
	public void demoReport() {

		// Spark report Config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// Add Env Info & Create Test
		com.aventstack.extentreports.ExtentReports report = new com.aventstack.extentreports.ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "WINDOWS-10");
		report.setSystemInfo("BROWSER", "CHROME-140");
		
		
		ExtentTest test = report.createTest("demoReportReport");

		test.log(Status.INFO, "Login to App");
		test.log(Status.INFO, "Navigate to Contact Page");
		test.log(Status.INFO, "Create Contact");

		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "Contact is Created");
		} else {
			test.log(Status.FAIL, "Contact is Not Created");
		}
		report.flush();
		System.out.println("Login into App");
	}
}
