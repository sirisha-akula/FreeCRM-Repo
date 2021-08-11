package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class DealsPage extends TestBase{

	@FindBy(xpath ="//legend[contains(text(),'Deal')]")
	WebElement dealLabel;
	
	@FindBy(id ="title")
	WebElement title;
	
	@FindBy(name = "client_lookup")
	WebElement company;
	
	@FindBy(id ="amount")
	WebElement amount;
	
	@FindBy(xpath="//textarea[@id='description']")
	WebElement description;
	
	@FindBy(xpath="//form[@id='prospectForm']/table/tbody/tr/td/input[@value='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//input[@id='fieldId_close_date']")
	WebElement datePicker;
	
	@FindBy(id="f_trigger_c_close_date")
	WebElement cDate;
	
	//Initializing Page Objects
	public DealsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean dealLabel(){
		return dealLabel.isDisplayed();
	}
	
	
	public void createNewDeal(String titled, String comp, String amt, String type, String date, String descrptn) {
		
		title.sendKeys(titled);
		company.sendKeys(comp);
		amount.sendKeys(amt);
		
		Select select = new Select(driver.findElement(By.name("type")));
		select.selectByVisibleText(type);
		//datePicker.sendKeys(date);
		
		((JavascriptExecutor)driver).executeScript ("document.getElementById('f_trigger_c_close_date').removeAttribute('readonly',0);"); // Enables the from date box
		cDate.clear();
		cDate.sendKeys(date); //Enter date in required format
		  
		
		description.sendKeys(descrptn);
		
		saveBtn.click();
	}
	
	
}
