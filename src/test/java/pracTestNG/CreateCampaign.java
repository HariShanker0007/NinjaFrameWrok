package pracTestNG;

import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepoUtility.CreateCampaignPage;
import com.comcast.crm.ObjectRepoUtility.Homepage;
import com.comcast.crm.geniricUtility.BaseClass;

public class CreateCampaign extends BaseClass {

	@Test(priority = 0)
	public void createCampaignWithMandatoryDetails() throws Throwable {

		// Reading Data from Excel Sheet
		String campName = elib.toReadDataFromExcel("Campaign", 1, 0);
		String tarSize = elib.toReadDataFromExcel("Campaign", 1, 1);

		// Clicking on the create Campaign Button in the home page
		Homepage hp = new Homepage(driver);
		hp.getCreateCampaignBtn().click();

		// Creating the object of CreateCampaign page and Creating a campaign with
		// Mandatory Fields
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		cp.createCampaignWithMandatoryDetails(campName, tarSize);

		// Verify the toast msg
		hp.verifyMsg(driver, campName);

	}

	@Test(priority = 1)
	public void createCampaignWithExpectedCloseDate() throws Throwable {

		// Reading Data from Excel Sheet
		String campName = elib.toReadDataFromExcel("Campaign", 1, 0);
		String tarSize = elib.toReadDataFromExcel("Campaign", 1, 1);

		// Get the Date
		String reqDate = jlib.toGetRequiredDate(30);

		// Clicking on the create Campaign Button in the home page
		Homepage hp = new Homepage(driver);
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
			hp.getCreateCampaignBtn().click();

			// Creating the object of CreateCampaign page and Creating a campaign with
			// Mandatory Fields
				
			
			
	}
}
