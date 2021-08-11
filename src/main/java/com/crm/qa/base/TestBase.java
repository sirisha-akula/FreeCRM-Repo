package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.crm.qa.util.TestUtil;
import com.crm.qa.util.EventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

public static WebDriver driver;
public static Properties prop;
public static Logger log=Logger.getLogger(TestBase.class);
	
public static EventFiringWebDriver e_driver;
public static EventListener eventListener;
	//	
	public TestBase() {
		
		prop = new Properties();
		try {
			FileInputStream fip = new FileInputStream("/Users/sirishaakula/eclipse-workspace/FreeCRMTest/src/main/java/"
					+ "com/crm/qa/config/config.properties");
			prop.load(fip);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		    log.info("launching chrome browser");

		}
		
		else if(browserName.equals("FF")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		   log.info("launching FF browser");

			}
		
		else {
			 driver = new SafariDriver();	
		}
	
	    e_driver = new EventFiringWebDriver(driver);
		eventListener = new EventListener();
		//e_driver.register(eventListener);
		driver = e_driver;
		
		
        System.out.println("driver: " +driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		
		driver.get(prop.getProperty("url"));
		log.info("launching the freecrm website");
		log.warn("hey this is warning");
		log.debug("hey this is debug message");
		log.fatal("hey this is fatal message");
		log.error("hey this is error");
	}
	
	
	
	
	
	
}
