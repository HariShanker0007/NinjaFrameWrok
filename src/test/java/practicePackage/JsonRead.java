package practicePackage;


import java.io.FileReader;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class JsonRead {
	public static void main(String[] args) throws Throwable {
		
		//step 1 Reading the file String
		FileReader fir = new FileReader("C:\\Users\\Lenovo-QSP\\OneDrive\\Desktop\\JsonCommData.json");
		
		//step 2 Converting String into JSON object 
		JSONParser parser= new JSONParser();
		Object obj = parser.parse(fir);
		
		//DownCasting to JSON object
		JSONObject jobj = (JSONObject)obj;
		
		//Step 4 Read the Data from Json
		Object BROWSER = jobj.get("browser").toString();
		Object URL = jobj.get("url");
		Object UN = jobj.get("un");
		Object PW = jobj.get("pw");
		Object NUM = jobj.get("num");
		
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PW);
		System.out.println(NUM);		
		
	}
}
