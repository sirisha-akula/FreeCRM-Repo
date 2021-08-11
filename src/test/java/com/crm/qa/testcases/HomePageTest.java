package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testUtil;
	ContactPage contactpage;
	DealsPage dealspage;
	
	public HomePageTest() {
		super();
	}
	
	//test cases should be separated -- independent of each other
	//before each test case launch the browser and login
	//@test--execute test case
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginpage = new LoginPage();
		contactpage = new ContactPage();
		testUtil = new TestUtil();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority =1)
	public void verifyHomePageTitleTest() {
		String homePageTitle = homepage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO" ,"homepage title is not matched");
	}
	
	@Test(priority =2)
	public void verifyCorrectUserName() {
		testUtil.switchToFrame();
		Assert.assertTrue(homepage.verifyUserName());
		
	}
	
	@Test(priority =3)
	public void verifyContactsLinkTest() {
		testUtil.switchToFrame();
	     contactpage = homepage.clickOnContactsLink();
	}
	
	@Test(priority =4)
	public void verifyNewContactsLinkTest() {
		testUtil.switchToFrame();
	     homepage.NewContactLink();
	}
	
	@Test(priority =5)
	public void verifyDealsLinkTest() {
		testUtil.switchToFrame();
	     dealspage = homepage.clickOnDealsLink();
	}
	
	@Test(priority = 6)
	public void verifyNewDealLinkTest() {
		testUtil.switchToFrame();
		homepage.newDealLink();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
