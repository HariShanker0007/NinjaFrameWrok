package practicePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssert {
	@Test
	public void assertDemo() {
		String exptitle = "Instagrams";

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.instagram.com/");

		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, exptitle);
		System.out.println("Step1");
	}
}
