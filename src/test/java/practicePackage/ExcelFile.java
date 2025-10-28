package practicePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFile {
	public static void main(String[] args) throws Throwable {

		// step 1
		FileInputStream fis = new FileInputStream("./\\src\\test\\resources\\resources\\Campaign.xlsx");

		// step 2
		Workbook wb = WorkbookFactory.create(fis);

		// step 3
		Sheet sh = wb.getSheet("Campaign");

		// step 4
		Row rr = sh.getRow(0);

		// step 5
		Cell sell = rr.getCell(1);

		// step 6
		String val = sell.getStringCellValue();

		// step 7
		wb.close();

		System.out.println(val);

		String url = sh.getRow(0).getCell(1).getStringCellValue();
		String un = sh.getRow(0).getCell(1).getStringCellValue();
		String pw = sh.getRow(0).getCell(1).getStringCellValue();

		System.out.println(url);
		System.out.println(un);
		System.out.println(pw);
	}
}
