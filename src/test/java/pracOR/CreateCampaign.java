package pracOR;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepoUtility.CreateCampaignPage;
import com.comcast.crm.ObjectRepoUtility.Homepage;
import com.comcast.crm.ObjectRepoUtility.LoginPage;
import com.comcast.crm.geniricUtility.ExcelUtility;
import com.comcast.crm.geniricUtility.FileUtility;
import com.comcast.crm.geniricUtility.JavaUtility;
import com.comcast.crm.geniricUtility.WebDriverUtility;

public class CreateCampaign {
	@Test
	public void CreateCampaign() throws Throwable {
		// Creating objects of Utilities
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		// Reading the data from properties Files
		String BROWSER = flib.toReadDatFromPropFiles("browser");
		String URL = flib.toReadDatFromPropFiles("url");
		String UN = flib.toReadDatFromPropFiles("un");
		String PW = flib.toReadDatFromPropFiles("pw");

		// Reading Data from Excel Sheet
		String campName = elib.toReadDataFromExcel("Campaign", 1, 0);
		String tarSize = elib.toReadDataFromExcel("Campaign", 1, 1);

		// Get the Date
		String reqDate = jlib.toGetRequiredDate(30);

		WebDriver driver = null;
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

		// Maximizing and entering the URL
		wlib.toMaximize(driver);
		wlib.waitForPageToLoad(driver);
		driver.get(URL);

		// Logging In using Propeties Files and POM
		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp(UN, PW);

		// Clicking on the create Campaign Button in the home page
		Homepage hp = new Homepage(driver);
		hp.getCreateCampaignBtn().click();

		// Creating the object of CreateCampaign page and Creating a campaign with
		// Mandatory Fields
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		cp.createCampaignWithMandatoryDetails(campName, tarSize);
		
		
		// Verify the toast msg
		hp.verifyMsg(driver, campName);

		// Logging out the closing the browser
		hp.logOut(driver);
	}
	
	}

