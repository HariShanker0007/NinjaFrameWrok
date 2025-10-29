package com.comcast.crm.geniricUtility;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.comcast.crm.ObjectRepoUtility.Homepage;
import com.comcast.crm.ObjectRepoUtility.LoginPage;

public class BaseClass {

	public WebDriver driver = null;
	public static WebDriver sdriver = null;//Listeners Purpose
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();

	@BeforeSuite(groups = {"smoke","regression"})
	public void beforeSuite() {
		Reporter.log("Establisihing DataBase connection", true);
	}

	@BeforeTest(groups = {"smoke","regression"})
	public void beforeTest() {
		Reporter.log("Pre-Conditions", true);
	}

	//@Parameters("browser")
	@BeforeClass(groups = {"smoke","regression"})
	public void beforeClass() throws Throwable {
		String BROWSER = flib.toReadDatFromPropFiles("browser");
		//String BROWSER = System.getProperty("browser");
		if (BROWSER.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
		} else if (BROWSER.equals("edge")) {
			// WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}
		sdriver=driver;
		Reporter.log("Launched Browser", true);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@BeforeMethod(groups = {"smoke","regression"})
	public void beforeMethod() throws Throwable {
		String URL = flib.toReadDatFromPropFiles("url");
		String UN = flib.toReadDatFromPropFiles("un");
		String PW = flib.toReadDatFromPropFiles("pw");
		
//		String URL =System.getProperty("url");
//		String UN =System.getProperty("un");
//		String PW =System.getProperty("pw");

		driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp(UN, PW);
		Reporter.log("Login Done", true);
	}

	// ----> @Test Will be executed Here

	@AfterMethod(groups = {"smoke","regression"})
	public void afterMethod() throws Throwable {
		Homepage hp = new Homepage(driver);
		hp.logOut(driver);
		Reporter.log("Logout done", true);
	}

	@AfterClass(groups = {"smoke","regression"})
	public void AfterClass() {
		Reporter.log("closing browser", true);
		driver.quit();
	}

	@AfterTest(groups = {"smoke","regression"})
	public void afterTest() {
		Reporter.log("Post Conditions", true);
	}

	@AfterSuite(groups = {"smoke","regression"})
	public void afterSuite() {
		Reporter.log("Disconnect DB", true);
	}
}
