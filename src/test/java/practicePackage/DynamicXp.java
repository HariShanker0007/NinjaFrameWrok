package practicePackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class DynamicXp {
public static void main(String[] args) throws Throwable {
	WebDriver driver = new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.get("https://www.saucedemo.com/");
	driver.findElement(By.id("user-name")).sendKeys("standard_user");
	driver.findElement(By.id("password")).sendKeys("secret_sauce");
	driver.findElement(By.id("login-button")).click();
	
	List<WebElement> name = driver.findElements(By.xpath("//div[@data-test='inventory-item-name']"));
	for (WebElement prodName : name) {
		String Val = prodName.getText();
		driver.findElement(By.xpath("//div[.='"+Val+"']")).click();
		driver.navigate().back();
		Thread.sleep(1000);
	}
}
}
