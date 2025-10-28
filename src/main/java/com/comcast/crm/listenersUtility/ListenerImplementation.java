package com.comcast.crm.listenersUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.geniricUtility.BaseClass;

public class ListenerImplementation implements ITestListener, ISuiteListener {

	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Report Configuration", true);
		// Creating object of Date
		Date dd = new Date();
		String newDate = dd.toString().replace(" ", "_").replace(":", "_");

		// Spark report Config
		spark = new ExtentSparkReporter("./AdvanceReport/report_" + newDate + ".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "WINDOWS-10");
		report.setSystemInfo("BROWSER", "Edge");

	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		Reporter.log("Report Backup", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());

		test.log(Status.INFO, "=====" + result.getMethod().getMethodName() + "Execution STARTED");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());

		test.log(Status.PASS, "=====" + result.getMethod().getMethodName() + "Execution SUCCESS");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());

		test.log(Status.SKIP, "=====" + result.getMethod().getMethodName() + "Execution SKIPPED");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();
		// Creating object of Date
		Date dd = new Date();
		String newDate = dd.toString().replace(" ", "_").replace(":", "_");

		// Taking ss with timeStamp
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String temp = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(temp, testName + newDate);
		test.log(Status.FAIL, "====" + testName + "FAILURE=====");
	}
}
