package pracTestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class IC {
	@Test(invocationCount = 11,threadPoolSize = 5)
	public void icTry() throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.get("https://chat.qspiders.com/user-dashboard");
		Thread.sleep(1000);
		driver.quit();
	}
}
