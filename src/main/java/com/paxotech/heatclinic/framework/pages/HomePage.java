package com.paxotech.heatclinic.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.paxotech.heatclinic.framework.AppConstant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HomePage extends Page {

	public static final String HOME_PAGE_TITLE = "Broadleaf Demo - Heat Clinic";
	// ****MODEL VARIABLE****
	@FindBy(linkText = "Login")
	private WebElement loginLink;
	@FindBy(css=".my-account")
	private WebElement loggedInUserLink;

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	// ****MODEL METHODS****
	protected WebElement getLoginLink() {
		return loginLink;
	}
	
	protected WebElement getLoggedInUserLink() {
		return loggedInUserLink;
	}

	// **** CONTROLLER METHODS****

	public HomePage navigateToHomePage(){
		driver.navigate().to(AppConstant.App_URL);
		return this;
	}

	public HomePage verifyTitle() { /// This is where you set the home page URL
								/// *OVERLOAD*
		super.verifyTitle(HOME_PAGE_TITLE); /// This is the <title>
		return this;
	}
	
	public void verifyUserName(String expected){
		String actual = getLoggedInUserLink().getText();
		
		assertThat(actual, equalToIgnoringCase(expected));
		
		
	}

	public LoginPage openLoginPage() {
		getLoginLink().click();
		return new LoginPage(driver);	//returns instance of LoginPage from HomePage
	}

	public HomePage and() {
		return this;
	}

	public HomePage then() {
		return this;
	}

	public RegistrationPage goToRegistrationPage() {
		driver.findElement(By.linkText("Register")).click();
		return new RegistrationPage(driver);
	}

}
