package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepoUtility.CreateCampaignPage;
import com.comcast.crm.ObjectRepoUtility.Homepage;
import com.comcast.crm.geniricUtility.BaseClass;
import com.comcast.crm.geniricUtility.ExcelUtility;

import junit.framework.Assert;

@Listeners(com.comcast.crm.listenersUtility.ListenerImplementation.class)

public class CreateCampaignTest extends BaseClass {

	@Test(priority = 0, groups = "smoke")
	public void createCampaignWithMandatoryDetails() throws Throwable {

		// Reading Data from Excel Sheet
		String campName = elib.toReadDataFromExcel("Campaign", 1, 0);
		String tarSize = elib.toReadDataFromExcel("Campaign", 1, 2);
		
		//Hi And Hello

		// Clicking on the create Campaign Button in the home page
		Homepage hp = new Homepage(driver);
		Thread.sleep(3000);
		hp.getCreateCampaignBtn().click();


		// Creating the object of CreateCampaign page and Creating a campaign with
		// Mandatory Fields
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		cp.createCampaignWithMandatoryDetails(campName, tarSize);

		// Verify the toast msg
		hp.verifyMsg(driver, campName);

	}

	@Test(priority = 1, groups = "regression")
	public void createCampaignWithExpectedCloseDate() throws Throwable {

		// Reading Data from Excel Sheet
		String campName = elib.toReadDataFromExcel("Campaign", 1, 0);
		String tarSize = elib.toReadDataFromExcel("Campaign", 1, 2);

		// Get the Date
		String reqDate = jlib.toGetRequiredDate(30);

		// Clicking on the create Campaign Button in the home page
		Homepage hp = new Homepage(driver);
		Thread.sleep(3000);
		hp.getCreateCampaignBtn().click();

		// Creating the object of CreateCampaign page and Creating a campaign with
		// Mandatory Fields
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		cp.createCampaignWithCloseDate(campName, tarSize, reqDate);

		// Verify the toast msg
		hp.verifyMsg(driver, campName);
	}

	@Test
	public void createCampaignWithAllFields() throws Throwable {

		// Reading Data from Excel Sheet
		String campName = elib.toReadDataFromExcel("Campaign", 1, 0);
		String status = elib.toReadDataFromExcel("Campaign", 1, 1);
		String tarSize = elib.toReadDataFromExcel("Campaign", 1, 2);
		String tarAud = elib.toReadDataFromExcel("Campaign", 1, 3);
		String desc = elib.toReadDataFromExcel("Campaign", 1, 4);

		// Get the Date
		String reqDate = jlib.toGetRequiredDate(30);

		// Clicking on the create Campaign Button in the home page
		Homepage hp = new Homepage(driver);
		Thread.sleep(3000);
		hp.getCreateCampaignBtn().click();

		// Creating the object of CreateCampaign page and Creating a campaign with
		// Mandatory Fields
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		cp.createCampaignWithTarAud(campName, status, tarSize, reqDate, tarAud);

		// Verify the toast msg
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		wlib.waitForVisibilityOfElement(driver, toastMsg);
		String msg = toastMsg.getText();
		System.out.println(msg);
//			Assert.assertEquals(msg,"Campaign "+campName+" Successfully Added");
		Assert.assertTrue(msg.contains(campName));
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
//			hp.verifyMsg(driver, campName);
	}
}
