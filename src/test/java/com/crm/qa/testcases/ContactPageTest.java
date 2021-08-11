package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactPageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testUtil;
	ContactPage contactpage;

	
	public ContactPageTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginpage = new LoginPage();
		contactpage = new ContactPage();
		testUtil = new TestUtil();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
	    contactpage = homepage.clickOnContactsLink();
	    Thread.sleep(2000);
		
	}
	
	@Test(priority =1, enabled = false)
	public void verifyContactsLabelTest() {
		Assert.assertTrue(contactpage.verifyContactsLabel());
		
	}
	
	@Test(priority =2, enabled = false)
	public void selectContactTest() {
		contactpage.selectContactsByName("arjun macha");
		
	}
	
	@Test(priority =3,enabled = false)
	public void selectMultipleContactTest() {
		contactpage.selectContactsByName("arjun macha");
		contactpage.selectContactsByName("kittu macha");
		
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object[][] data = testUtil.getTestData("contacts");
		return data;
	}
	
	@Test(priority =4, dataProvider = "getCRMTestData")
	public void validateCreateNewContact(String title, String firstname, String lastname,String company,String email) {
		homepage.NewContactLink();
		//contactpage.createNewConctact("Mr.", "Ram", "akula", "Rationshop", "ramakula@gmail.com"); hard code
		contactpage.createNewConctact(title, firstname, lastname, company, email); //reading from excel
		
	}
	@Test(priority =5, enabled = false)
	public void validateCreatedContactTest() {
		String text = contactpage.validateCreatedContact("parnitha akula");
		Assert.assertEquals(text, "parnitha akula");
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
