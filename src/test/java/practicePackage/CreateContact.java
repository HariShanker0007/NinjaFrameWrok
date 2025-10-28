package practicePackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.geniricUtility.ExcelUtility;
import com.comcast.crm.geniricUtility.FileUtility;
import com.comcast.crm.geniricUtility.JavaUtility;
import com.comcast.crm.geniricUtility.WebDriverUtility;

public class CreateContact {
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
		String orgName = elib.toReadDataFromExcel("Contact", 1, 0);
		String title = elib.toReadDataFromExcel("Contact", 1, 1);
		String dep = elib.toReadDataFromExcel("Contact", 1, 2);
		String ofcPhon = elib.toReadDataFromExcel("Contact", 1, 3);
		String contNam = elib.toReadDataFromExcel("Contact", 1, 4);
		String mobi = elib.toReadDataFromExcel("Contact", 1, 5);
		String email = elib.toReadDataFromExcel("Contact", 1, 6);

		// Random Number
		int ranNum = jlib.getRandomNumber();

		// 10 digit Num
		String tenRanNum = jlib.getTenDigitRandomNumber()+"";

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}

		wlib.toMaximize(driver);
		wlib.waitForPageToLoad(driver);
		driver.get(URL);

		// Logging In using Propeties Files
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PW);
		driver.findElement(By.xpath("//button[.='Sign In']")).click();
		Thread.sleep(2000);

		// Clicking on the Contact Link
		driver.findElement(By.partialLinkText("Contacts")).click();

		driver.findElement(By.xpath("//span[.='Create Contact']")).click();

		// Capturing pid
		String pid = driver.getWindowHandle();

		// Creating the contact
		driver.findElement(By.name("organizationName")).sendKeys(orgName + ranNum);
		driver.findElement(By.name("title")).sendKeys(title + ranNum);
		driver.findElement(By.name("department")).sendKeys(dep);
		driver.findElement(By.name("officePhone")).sendKeys(ofcPhon);
		driver.findElement(By.name("contactName")).sendKeys(contNam);
		driver.findElement(By.name("mobile")).sendKeys(tenRanNum);
		driver.findElement(By.name("email")).sendKeys(email+ranNum+"@gmail.com");
		driver.findElement(By.xpath("//*[name()='svg' and @data-icon='plus']")).click();

		// Switching the driver control to child window
		wlib.switchToChildWindow(driver);
		driver.findElement(By.xpath("//button[contains(@onclick,'CAM00001')]")).click();

		// switching back to parent window
		wlib.switchToParentWindow(driver, pid);

		driver.findElement(By.xpath("//button[.='Create Contact']")).click();

		// Capturing the toast msg
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));

		// Verifying the toast Msg
		String msg = toastmsg.getText();
		if (msg.contains(email)) {
			System.out.println(email + "===" + "Product Verified and created");
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
