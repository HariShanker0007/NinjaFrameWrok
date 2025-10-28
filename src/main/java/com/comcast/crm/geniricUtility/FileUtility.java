package com.comcast.crm.geniricUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
	public String toReadDatFromPropFiles(String key) throws Throwable {
		FileInputStream fis = new FileInputStream("./\\src\\test\\resources\\ConfigAppData\\comm.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}
}
