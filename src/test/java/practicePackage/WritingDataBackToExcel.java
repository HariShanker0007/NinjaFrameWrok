package practicePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WritingDataBackToExcel {
public static void main(String[] args) throws Throwable {
	
	
	FileInputStream fis = new FileInputStream("./\\src\\test\\resources\\resources\\Campaign.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh = wb.createSheet("Sheet2");
	Row rr = sh.createRow(2);
	
	//Step 6
	Cell sell = rr.createCell(2);
	
	//step 7
	sell.setCellValue("Entering");
	
	FileOutputStream fos = new FileOutputStream("./\\src\\test\\resources\\resources\\Campaign.xlsx");	
	wb.write(fos);
	wb.close();
	System.out.println("Successful");
}
}
