package practicePackage;

import com.comcast.crm.geniricUtility.ExcelUtility;
import com.comcast.crm.geniricUtility.FileUtility;

public class Exee {
	public static void main(String[] args) throws Throwable {

		FileUtility propp = new FileUtility();
		System.out.println(propp.toReadDatFromPropFiles("browser"));
		System.out.println(propp.toReadDatFromPropFiles("url"));
		System.out.println(propp.toReadDatFromPropFiles("un"));
		System.out.println(propp.toReadDatFromPropFiles("pw"));

		ExcelUtility elib = new ExcelUtility();
		System.out.println(elib.toReadDataFromExcel("Campaign", 1, 0));
		System.out.println(elib.toReadDataFromExcel("Campaign", 1, 1));
	}
}
