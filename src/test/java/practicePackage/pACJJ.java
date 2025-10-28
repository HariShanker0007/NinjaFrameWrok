package practicePackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class pACJJ {
public static void main(String[] args) {
	//Get the Date
	Date date= new Date();
	SimpleDateFormat sif= new SimpleDateFormat("dd-MM-yyyy");
//	sif.format(date);
	System.out.println(date);
	Calendar cal = sif.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH,30);
	String dateReq = sif.format(cal.getTime());
	System.out.println(dateReq);
}
}
