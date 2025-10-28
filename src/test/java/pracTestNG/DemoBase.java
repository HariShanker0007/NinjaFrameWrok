package pracTestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoBase {
	
	WebDriver driver;

	@Test
	public void TC001() {
		Reporter.log("testCase done", true);
		
	}

	@BeforeClass
	public void beforeClass() {
		Reporter.log("launching Browser", true);
		driver = new ChromeDriver();
	}

	@AfterClass
	public void AfterClass() {
		Reporter.log("closing browser", true);
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {
		Reporter.log("Login", true);
		driver.get("http://49.249.28.218:8098/");
	}

	@AfterMethod
	public void afterMethod() {
		
		Reporter.log("Logout", true);
	}

	@BeforeTest
	public void beforeTest() {
		Reporter.log("Pre conditions", true);
	}

	@AfterTest
	public void afterTest() {
		Reporter.log("Post Conditions", true);
	}

	@BeforeSuite
	public void beforeSuite() {
		Reporter.log("Connect DB", true);
	}

	@AfterSuite
	public void afterSuite() {
		Reporter.log("Disconnect DB", true);
	}
}
