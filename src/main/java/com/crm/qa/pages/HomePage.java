package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class HomePage extends TestBase{
	
	@FindBy(xpath="//td[contains(text(),'User: sirisha ak ')]")
	@CacheLookup// if we put @CacheLookup, instead of going to HTML DOM everytime it saves these element in cache memory 
	//and gets the element from cache memory everytime we use it.It speed up the performance of script.However we in backgroung pages gets refreshed this element gives stale element exception.
	WebElement userNameLabel;//when we try to fetch this element, it goes HTML dom and gets the element everytime we use it
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement createNewcontactLink;
	
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[@title ='New Deal']")
	WebElement createNewDealLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	
	//Initializing page objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	
	//Actions or methods
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyUserName() {
		return userNameLabel.isDisplayed();
	}
	
	public ContactPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactPage();
	}
	
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}
	
	
	
	public void NewContactLink() {
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		createNewcontactLink.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

		
	}
	
	public void newDealLink() {
		Actions action = new Actions(driver);
		action.moveToElement(dealsLink).build().perform();

//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(1000));
//		wait.until(ExpectedConditions.elementToBeClickable(createNewDealLink)).click();
		
		createNewDealLink.click();

		
	}
	
	
	
	
	
	
	
	
}
