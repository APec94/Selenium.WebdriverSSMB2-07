package com.paxotech.heatclinic.framework.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends Page{

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	public RegistrationPage then() {
		return this;
		
	}

	public RegistrationPage enterEmailAddress(String email) {	
		driver.findElement(By.id("customer.emailAddress")).sendKeys(email);
		return this;
	}

	public RegistrationPage and() {
		return this;
	}
	
	public RegistrationPage enterFirstName(String fName){
		driver.findElement(By.id("customer.firstName")).sendKeys(fName);
		return this;
	}

	public RegistrationPage enterLastName(String lName) {
		driver.findElement(By.id("customer.lastName")).sendKeys(lName);
		return this;
	}

	public RegistrationPage enterPassword(String password) {
		driver.findElement(By.id("password")).sendKeys(password);
		return this;
	}

	public RegistrationPage enterConfirmPassword(String password) {	
		driver.findElement(By.id("passwordConfirm")).sendKeys(password);
		return this;
	}

	public void clickRegisterButton() {
		driver.findElement(By.cssSelector(".register_button.big.red")).click();
		
	}

	public RegistrationPage enterDOB(String string) {
		
		return this;
	}

	public RegistrationPage withRegistrationPage() {
		// TODO Auto-generated method stub
		return this;
	}

	public void registerUser() {
		
		String email = "Email_" + RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
		String fName = "FName_" + RandomStringUtils.randomAlphanumeric(10);
		String lName = "LName_" + RandomStringUtils.randomAlphanumeric(10);
		String password = RandomStringUtils.randomAlphanumeric(6);
		
		System.out.println("Email: " + email);
		System.out.println("FName: " + fName);
		System.out.println("LName: " + lName);
		System.out.println("Password: " + password);
		
		enterEmailAddress(email);
		enterFirstName(fName);
		enterLastName(lName);
		enterPassword(password);
		enterConfirmPassword(password);
		
		delayFor(5000);
		clickRegisterButton();
		delayFor(5000);
		
		HomePage homePage = new HomePage(driver);
		homePage.navigateToHomePage()
		.openLoginPage()
		.login(email, password);
		
		homePage.verifyUserName(fName);
		
		//verifyUserCreated();
		
	}
	
	

}
