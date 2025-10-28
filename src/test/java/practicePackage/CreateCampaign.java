package practicePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaign {
	public static void main(String[] args) throws Throwable {

		// Reading the data from properties Files
		FileInputStream fis = new FileInputStream("./\\src\\test\\resources\\ConfigAppData\\comm.properties");
		Properties prop = new Properties();
		prop.load(fis);

		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String UN = prop.getProperty("un");
		String PW = prop.getProperty("pw");

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//Logging In using Propeties Files
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PW);
		driver.findElement(By.xpath("//button[.='Sign In']")).click();
		driver.findElement(By.xpath("//span[.='Create Campaign']")).click();
		
		//Reading Data from Excel Sheet		
		FileInputStream fis1 = new FileInputStream("C:\\Users\\Lenovo-QSP\\OneDrive\\Desktop\\NinjaExcelData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		String CampName = sh.getRow(1).getCell(0).getStringCellValue();
		String TarSize = sh.getRow(1).getCell(1).getStringCellValue();
		
		driver.findElement(By.name("campaignName")).sendKeys(CampName);
		driver.findElement(By.name("targetSize")).sendKeys(TarSize);
		driver.findElement(By.xpath("//button[.='Create Campaign']")).click();	
		
		//Capturing the toast msg
		 WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']")); 
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		  wait.until(ExpectedConditions.visibilityOf(toastmsg)); 
		  
		  //Verifying the toast Msg
		  String msg = toastmsg.getText(); 
		  if (msg.contains(CampName)) { 
		  System.out.println(CampName+"==="+"Product Verified and created"); 
		  } else { 
		  System.out.println("Product not verified"); 
		  } 
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		  
		  //Logging Out of the Application 
		  WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']")); 
		  Actions act = new Actions(driver); 
		  act.moveToElement(icon).perform(); 
		  Thread.sleep(1000);
		  WebElement logout = driver.findElement(By.xpath("//div[contains(text(),'Logout')]")); 
		  logout.click(); 			
		  driver.quit();
	}
}
