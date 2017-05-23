package com.fjhw.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;

public class TestLog {
	@Test
	public void testLogError(){
		try {
			Properties prop =  new  Properties();    
			InputStream in = new FileInputStream(new File("log4j.properties"));    
			prop.load(in);
			System.setProperties(prop);
			Log log = LogFactory.getLog(this.getClass());
			log.debug("debug");
			log.error("error");
			log.info("info");
			log.fatal("fatal");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (LogConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
