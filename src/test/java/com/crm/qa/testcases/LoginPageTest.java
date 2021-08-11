package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		//TestBase base = new TestBase();	
		initialization();
		loginpage = new LoginPage(); //to access all login page methods we create loginpage object
		
	}
	
	@Test(priority = 1, enabled = true)
	public void loginpageTitleTest() {
		log.info("*************Starting testcase************");
		log.info("**********login page title test**********");
		String title = loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
		log.info("*************ending testcase************");
	}
	
	@Test(priority = 2, enabled = true)
	public void crmLogoTest() {
		log.info("*************Starting testcase************");
		log.info("**********validateCRMImage**********");
		boolean b = loginpage.validateCRMImage();
		Assert.assertTrue(b);
		log.info("*************ending testcase************");

	}
	
	@Test(priority = 3)
	public void loginPageTest() {
		log.info("*************Starting testcase************");
		log.info("**********loginpageTest**********");
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		log.info("*************ending testcase************");
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	

}
