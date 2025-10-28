package practicePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesFiles {
public static void main(String[] args) throws Throwable {
	
	//step 1
	FileInputStream fis = new FileInputStream("./\\src\\test\\resources\\ConfigAppData\\comm.properties");
	
	//step 2
	Properties prop = new Properties();
	
	//step 3
	prop.load(fis);
	
	//step 4 
	String BROWSER = prop.getProperty("browser");
	String URL = prop.getProperty("url");
	String UN = prop.getProperty("un");
	String PW = prop.getProperty("pw");
	
	//Printingg to check the Data Readability
	System.out.println(BROWSER);
	System.out.println(URL);
	System.out.println(UN);
	System.out.println(PW);
	
//	//Write Data back to Properties
//	prop.setProperty("hari","shankerrr");
//	prop.setProperty("Advance","Selenium");
//	prop.setProperty("KEYSS", "VALUESS");
	
//	//Creating File of OutPutStream
//	FileOutputStream fos = new FileOutputStream("C:\\Users\\Lenovo-QSP\\OneDrive\\Desktop\\ninjacommData.properties");
//	prop.store(fos, "Upadted Values");
//	fos.close();
//	System.out.println("Details Entered Succesfully");
}
}
