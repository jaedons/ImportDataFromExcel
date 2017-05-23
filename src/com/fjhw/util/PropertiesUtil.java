package com.fjhw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static String getValue(String key){
		try {
			Properties prop =  new  Properties();    
			InputStream in = new FileInputStream(new File("oracle.properties"));    
			prop.load(in);
			return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}    
		return null;
		
	}
}
