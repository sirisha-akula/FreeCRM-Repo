package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class DealsPageTest extends TestBase {
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testUtil;
	DealsPage dealspage;
	
	public DealsPageTest() {
		super();
		
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginpage = new LoginPage();
		homepage = new HomePage();
		dealspage = new DealsPage();
		testUtil = new TestUtil();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		dealspage = homepage.clickOnDealsLink();
	    Thread.sleep(2000);
	}
	
	
	@Test(priority =1)
	public void validateDealLabelTest() throws InterruptedException{
		homepage.newDealLink();
		homepage.newDealLink();
		Assert.assertTrue(dealspage.dealLabel());

	}
	@DataProvider
	public Object[][] getCRMDealsTestData() {
		Object[][] data = testUtil.getTestData("deals");
		return data;
	}
	
	@Test(priority = 2 , dataProvider = "getCRMDealsTestData")
	public void verifyCreateNewDealTest(String title, String company, String amount, String type, String closeDate, String description) {
		homepage.newDealLink();
		dealspage.createNewDeal(title, company, amount, type, closeDate, description); //getting from excel
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
