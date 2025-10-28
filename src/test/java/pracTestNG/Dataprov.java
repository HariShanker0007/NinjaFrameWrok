package pracTestNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprov {

	@Test(dataProvider = "loginDetails")
	public void login(String un, String pw) throws Throwable {

		WebDriver driver = new EdgeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys(un);
		driver.findElement(By.id("inputPassword")).sendKeys(pw);
		driver.findElement(By.xpath("//button[.='Sign In']")).click();
		Thread.sleep(2000);
		driver.quit();
	}

	@DataProvider
	public Object[][] loginDetails() throws Throwable {

		FileInputStream fis = new FileInputStream("./src\\test\\resources\\resources\\dataProvider.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		int lastRowNum = sh.getLastRowNum();
		System.out.println(lastRowNum);

		Object[][] objArr = new Object[lastRowNum][2];
		for (int i = 0; i < lastRowNum; i++) {
			objArr[i][0] = sh.getRow(i+1).getCell(0).getStringCellValue();

			objArr[i][1] = sh.getRow(i+1).getCell(1).getStringCellValue();
		}
		return objArr;
	}
}
