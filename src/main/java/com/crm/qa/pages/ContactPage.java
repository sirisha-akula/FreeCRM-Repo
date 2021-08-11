package com.crm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import com.crm.qa.base.TestBase;

public class ContactPage extends TestBase {

	@FindBy(xpath ="//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(id = "first_name")
	WebElement firstName;
	
	@FindBy(id = "surname")
	WebElement lastName;
	
	@FindBy(name = "client_lookup")
	WebElement company;
	
	@FindBy(id = "email")
	WebElement Email;
	
	@FindBy(xpath="//input[@value='Load From Company']//following-sibling::input[@value='Save']")
	WebElement saveBtn;
	
	////a[contains(text(),'arjun macha')]//parent::td//preceding-sibling::td/input[@type='checkbox']
	
	//Initializing the Page Objects
		public ContactPage(){ //how to intialize the webelements? using PageFactory.initElements(driver, this) this means its pointing to current class object.
			
			PageFactory.initElements(driver, this);
		}
		
		public boolean verifyContactsLabel() {
			return contactsLabel.isDisplayed();
		}
		
		public void selectContactsByName(String name) {
			driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]//parent::td//preceding-sibling::td/input[@type='checkbox']")).click();
		}
		
		public void createNewConctact(String title, String ftName, String ltName,String comp,String email) {
			Select select = new Select(driver.findElement(By.name("title")));
			select.selectByVisibleText(title);
			
			firstName.sendKeys(ftName);
			lastName.sendKeys(ltName);
			company.sendKeys(comp);
			Email.sendKeys(email);
			
			saveBtn.click();
			
		}
		
		public String validateCreatedContact(String expectedText) {
			List<WebElement> row =	driver.findElements(By.xpath("//form[@id='vContactsForm']/table/tbody/tr"));

			List<WebElement> col =	row.get(1).findElements(By.xpath("//form[@id='vContactsForm']/table/tbody/tr[3]/td"));
			
//			System.out.println("rows are: "+ row.size());
//			System.out.println("cols are: "+ col.size());
			
			String before_xpath ="//form[@id='vContactsForm']/table/tbody/tr[";
			String after_xpath ="]/td[2]";
			String text = "";
			String actualText="";
			for(int i= 4; i<row.size(); i++) {
				text = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
				actualText = text.trim();
				if(actualText.contains(expectedText)) {
                  System.out.println("contact created successfully");				
                  break;
				}
				

			}
			return actualText;
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
}
