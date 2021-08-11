package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	
	//Page Factory - OR
	@FindBy(xpath = "//input[@type= 'text']")
	WebElement username;
	
	@FindBy(xpath = "//input[@type= 'password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@type= 'submit']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//div[@class= 'navbar-header']//img")
	WebElement crmLogo;
	
	//Initializing the Page Objects
	public LoginPage(){ //how to intialize the webelements? using PageFactory.initElements(driver, this) this means its pointing to current class object.
		
		PageFactory.initElements(driver, this);
	}
	
	//Actions
    public String validateLoginPageTitle() {
    	return driver.getTitle();
    	
    }
    
    public boolean validateCRMImage() {
    	return crmLogo.isDisplayed();
    }
    
    public HomePage login(String un, String pwd) {
    	username.sendKeys(un);
    	password.sendKeys(pwd);
    	loginBtn.click();
    	return new HomePage(); //this method returns home page object, as u login it redirects to homepage
    	
    }
    
	
}
