package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepoUtility.CreateCampaignPage;
import com.comcast.crm.ObjectRepoUtility.CreateProductPage;
import com.comcast.crm.ObjectRepoUtility.Homepage;
import com.comcast.crm.ObjectRepoUtility.ProductPage;
import com.comcast.crm.geniricUtility.BaseClass;

@Listeners(com.comcast.crm.listenersUtility.ListenerImplementation.class)

public class CreateProductTest extends BaseClass {
	
	/**
	 * Login to the Ninja CRM
	 * Creates a Product Using Mandatory Fields
	 * Logout
	 * 
	 * @param UserName
	 * @param Password
	 * 
	 * @author Hari
	 * 
	 *@return A Product With Mandatory Fields
	 */
	
	
	@Test(priority = 0,groups = "smoke")
	public void createProductWithMandatoryDetails() throws Throwable {

		int ranNum = jlib.getRandomNumber();
		// Reading Data from Excel Sheet
		String ProdName = elib.toReadDataFromExcel("Product", 1, 0) + ranNum;
		String Drop1 = elib.toReadDataFromExcel("Product", 1, 1);
		String Quan = elib.toReadDataFromExcel("Product", 1, 2);
		String Price = elib.toReadDataFromExcel("Product", 1, 3);
		String Drop2 = elib.toReadDataFromExcel("Product", 1, 4);

		// Clicking on the create Campaign Button in the home page
		Homepage hp = new Homepage(driver);
		hp.getProductsLink().click();
		
		//hi
		
		//hi
		
		ProductPage pp = new ProductPage(driver);
		pp.getAddProd().click();

		// Creating the object of CreateCampaign page and Creating a campaign with
		// Mandatory Fields
		CreateProductPage cpp = new CreateProductPage(driver);
		cpp.createProductWithMandatoryDetails(ProdName, Drop1, Quan, Price, Drop2);

		// Verify the toast msg
		//hp.verifyMsg(driver, ProdName);
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		wlib.waitForVisibilityOfElement(driver, toastMsg);
		String msg = toastMsg.getText();
		System.out.println(msg);
		Assert.assertEquals(msg,"Product "+ProdName+" Successfully Added");
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	}
}
