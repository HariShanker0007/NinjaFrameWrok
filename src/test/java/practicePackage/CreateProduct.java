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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProduct {
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
		String ProdName = elib.toReadDataFromExcel("Product", 1, 0);
		String Drop1 = elib.toReadDataFromExcel("Product", 1, 1);
		String Quan = elib.toReadDataFromExcel("Product", 1, 2);
		String Price = elib.toReadDataFromExcel("Product", 1, 3);
		String Drop2 = elib.toReadDataFromExcel("Product", 1, 4);

		// Random Number
		int ranNum = jlib.getRandomNumber();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}

		wlib.toMaximize(driver);
		wlib.waitForPageToLoad(driver);
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PW);
		driver.findElement(By.xpath("//button[.='Sign In']")).click();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//span[.='Add Product']")).click();

		driver.findElement(By.name("productName")).sendKeys(ProdName + ranNum);
		driver.findElement(By.name("productCategory")).sendKeys(Drop1);
		driver.findElement(By.name("quantity")).sendKeys(Quan);
		WebElement price = driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys(Price);
		driver.findElement(By.name("vendorId")).sendKeys(Drop2);

		driver.findElement(By.xpath("//button[.='Add']")).click();

		// Capturing the toast msg
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));

		// Verifying the toast Msg
		String msg = toastmsg.getText();
		if (msg.contains(ProdName)) {
			System.out.println(ProdName + "===" + "Product Verified and created");
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
