package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static Properties prop = new Properties();// 
	private static String path ="Config/config.qa.properties";
	private static String env;
	private ConfigManager() { // default constructor 
		
	}
	
	static {
		
		 env = System.getProperty("env");
		 
		 switch (env) {
		 case "dev" :{
			 path = "Config/config.dev.properties";
			 break;
		 }
		 case "qa" :{
			 path = "Config/config.qa.properties";
			 break;
		 }
		 case "uat": {
			 path = "Config/config.uat.properties";
			 break;
		 }
		 default : path = "Config/config.qa.properties";
			 
		 }
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if(input == null) {
			throw new RuntimeException("Cannot find the File at the path" + path);
		}
		
		try {
			
			prop.load(input); 
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