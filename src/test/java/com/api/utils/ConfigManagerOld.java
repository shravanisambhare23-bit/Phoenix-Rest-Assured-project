package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {
	private static Properties prop = new Properties();// 
	
	static {
		//operation of loading a property file in a memory once
		// static block will execute once During class loading time
		File configfile = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"Config"+File.separator+"config.properties");
		FileReader filereader = null;
		try {
			filereader = new FileReader(configfile);
			prop.load(filereader);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		 catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public static String getProperty(String key){
		
		//Properties prop = new Properties();// to make properties object once add line as line 10
	// to read the properties files from src/test/resources/config/config.properties
	
return prop.getProperty(key);
	
}	
}