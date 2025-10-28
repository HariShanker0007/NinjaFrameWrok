package com.comcast.crm.geniricUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	//To get Random Number
	public int getRandomNumber() {
		Random ran = new Random();
		int ranInt = ran.nextInt(1000);
		return ranInt;
	}
	
	//To get current Date
	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sif = new SimpleDateFormat("dd-MM-yyyy");
		String currDate = sif.format(date);
		return currDate;
	}
	
	// To get Required date
	public String toGetRequiredDate(int days) {
		Date date = new Date();
		SimpleDateFormat sif = new SimpleDateFormat("dd-MM-yyyy");
		String currDate = sif.format(date);
		Calendar cal = sif.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String dateRequired = sif.format(cal.getTime());
		return dateRequired;
	}

	// To generate 10 digi Num
	public long getTenDigitRandomNumber() {
		Random ran = new Random();
		long number = 1000000000L + (long) (ran.nextDouble() * 9000000000L);
		return number;
	}

}
