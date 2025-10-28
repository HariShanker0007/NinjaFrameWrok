package practicePackage;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.fasterxml.jackson.databind.cfg.ContextAttributes.Impl;

public class Demo {
public static void main(String[] args) {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	
	driver.get("https://demowebshop.tricentis.com/");
	driver.findElement(By.linkText("Facebook")).click();
	driver.findElement(By.linkText("Twitter")).click();
	driver.findElement(By.linkText("YouTube")).click();
	
	String pId = driver.getWindowHandle();
	Set<String> allId = driver.getWindowHandles();
	
	
	for (String string : allId) {
		driver.switchTo().window(string);
		if (driver.getCurrentUrl().contains("youtube.com/user/nopCommerce")) {
			System.out.println(driver.getTitle());
			break;
		}	
	}
	driver.switchTo().window(pId);
	System.out.println(driver.getTitle());
	driver.quit();
	
}
}
