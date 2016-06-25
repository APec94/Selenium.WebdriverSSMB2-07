package com.paxotech.heatclinic.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.paxotech.heatclinic.framework.AppConstant;



public class LoginPage extends Page {

	protected static final String LOGIN_PAGE_TITLE = "Login - Broadleaf Demo - Heat Clinic";
	
	@FindBy(how = How.NAME, using = "j_username")
	private WebElement emailTextbox;
	
	@FindBy(how = How.NAME, using = "j_password")
	private WebElement passwordTextbox;

	private WebElement loginButton = null;
	private WebElement errorMessageLabel = null;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	//*******MODEL METHODS*********
	protected WebElement getEmailTextbox() {
		return emailTextbox;
	}

	protected WebElement getPasswordTextbox() {
		return passwordTextbox;
	}

	protected WebElement getLoginButton() {
		loginButton = waitForElement(By.cssSelector(".login_button.big.red"), 20); // identifies login button// button
		highlightElement(loginButton);
		return loginButton;
	}

	protected WebElement getErrorMessageLabel() {
		highlightElement(errorMessageLabel);
		errorMessageLabel = waitForElementDisplayed(By.cssSelector(".error>p>span")); // finds
																						// error
																						// message
																						// by
																						// itself

		return errorMessageLabel;
	}

	// **** CONTROLLER METHODS****

	public LoginPage verifyTitle() {
		super.verifyTitle(LOGIN_PAGE_TITLE);
		return this;
	}

	public void login(String user, String password) {
		getEmailTextbox().sendKeys(user);
		getPasswordTextbox().sendKeys(password);
		hoverItemEx(getLoginButton());
		jsClick(getLoginButton());
		//getLoginButton().click();		
	}

	/*protected void login() {
		login(AppConstant.DEFAULT_USER_EMAIL, AppConstant.DEFAULT_PASSWORD);				
	}
	*/
	
	public HomePage validLogin(){
		login(AppConstant.DEFAULT_USER_EMAIL,AppConstant.DEFAULT_PASSWORD);
		return new HomePage(driver);
	}
	
	public LoginPage invalidLogin(){
		login(AppConstant.DEFAULT_USER_EMAIL,"invalid");
		return this;
	}

	public void verifyLoginErrorMessage() {
		String actual = getErrorMessageLabel().getText();
		assertThat(actual, equalToIgnoringCase(AppConstant.DEFAULT_LOGIN_ERROR_MESSAGE));

	}

	public LoginPage and() {
		return this;
	}

}
