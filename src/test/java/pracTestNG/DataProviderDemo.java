package pracTestNG;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderDemo {
	
	@Test(dataProvider = "loginDetails")
	public void login(String un, String pw) {
		Reporter.log(un+"===="+pw,true);
	}

	@DataProvider
	public Object[][] loginDetails() {
		Object[][] objArr= new Object[3][2];
		
		objArr[0][0]="anjali"; //1st un
		objArr[0][1]="a123";   //1st pw
		objArr[1][0]="drishya";//2st un
		objArr[1][1]="d123";   //2st pw
		objArr[2][0]="lavanya";//3st un
		objArr[2][1]="l123";   //3st pw
		
		return objArr;
	}
}	
