package practicePackage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.geniricUtility.ExcelUtility;
import com.comcast.crm.geniricUtility.FileUtility;
import com.comcast.crm.geniricUtility.JavaUtility;
import com.comcast.crm.geniricUtility.WebDriverUtility;

public class CreateCampaignWithExpectedCloseDate {
	public static void main(String[] args) throws Throwable {

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

		// Random Number
		int ranNum = jlib.getRandomNumber();

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
			//WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Logging In using Propeties Files
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PW);
		driver.findElement(By.xpath("//button[.='Sign In']")).click();
		driver.findElement(By.xpath("//span[.='Create Campaign']")).click();

		// Sending the data from Excel Sheet
		driver.findElement(By.name("campaignName")).sendKeys(campName);
		driver.findElement(By.name("targetSize")).sendKeys(tarSize);
		driver.findElement(By.name("expectedCloseDate")).sendKeys(reqDate);

		driver.findElement(By.xpath("//button[.='Create Campaign']")).click();

		// Capturing the toast msg
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));

		// Verifying the toast Msg
		String msg = toastmsg.getText();
		if (msg.contains(campName)) {
			System.out.println(campName + "===" + "Product Verified and created");
		} else {
			System.out.println("Product not verified");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

		// Logging Out of the Application
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		wlib.mouseHoverOnWebelemment(driver, icon);
		WebElement logout = driver.findElement(By.xpath("//div[contains(text(),'Logout')]"));
		logout.click();
		driver.quit();
	}
}
