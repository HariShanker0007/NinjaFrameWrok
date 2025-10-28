package practicePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {
	@Test
	public void assertDemo() {
		String exptitle = "Instagrams";

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.instagram.com/");
		System.out.println("Step1");
		String actTitle = driver.getTitle();
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actTitle, exptitle);
		System.out.println("Step2");
		soft.assertAll();	
	}
}
