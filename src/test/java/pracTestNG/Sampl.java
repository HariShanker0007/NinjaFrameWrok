package pracTestNG;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Sampl {

	@Test(invocationCount = 5, threadPoolSize = 2)
	public void createAcc() {
		Reporter.log("Created Acc", true);
	}

	@Test(invocationCount = 10, threadPoolSize = 5)
	public void editAcc() {
		Reporter.log("Edited Acc", true);
	}

	@Test(invocationCount = 4, threadPoolSize = 3)
	public void deleteAcc() {
		Reporter.log("Deleted Acc", true);
	}
	
}
