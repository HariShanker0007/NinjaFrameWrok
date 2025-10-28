package practicePackage;

import java.io.File;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class SsWithTimeStamp {

	@Test
	public void takeSS() throws Throwable {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.instagram.com/");
		
		//Creating object of Date
		Date dd= new Date();
		String newDate = dd.toString().replace(" ","_").replace(":","_");
		
		//Taking ss with timeStamp
		TakesScreenshot ts = (TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm =new File("./ScreenShot/takeSS_"+newDate+".png");
		FileHandler.copy(temp, perm);	
	}
}
