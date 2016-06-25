package com.paxotech.heatclinic.scripts;

import org.testng.annotations.Test;

import com.paxotech.heatclinic.framework.AppConstant;
import com.paxotech.heatclinic.framework.HeatClinicScriptBase;

public class LoginFunctionality extends HeatClinicScriptBase {

	@Test//(groups={"smoke","functional"}, description="Successful valid username/password test", dependsOnMethods="createNewUser")	
	public void validLogin() {
		// heatClinic().homePage().verifyTitle("Broadleaf Demo - Heat Clinic");
		
		heatClinic().homePage().and().verifyTitle()
								.then().openLoginPage()
								.and().verifyTitle()
								.and().validLogin()
								.then().verifyUserName(AppConstant.DEFAULT_USER_NAME);	

	}

	@Test(enabled=true)
	public void invalidLogin() {
		heatClinic().homePage().verifyTitle();
		heatClinic().homePage().openLoginPage();
		heatClinic().loginPage().verifyTitle();
		heatClinic().loginPage().invalidLogin();
		heatClinic().loginPage().verifyLoginErrorMessage();
	}

	@Test(enabled=false)
	public void createNewUser() {
		heatClinic().homePage().verifyTitle()
								.and().goToRegistrationPage()
								.then().enterEmailAddress("paxoqa1@gmail.com")
								.and().enterFirstName("Alex")
								.and().enterLastName("Pecora")
								.and().enterPassword("password123")
								.and().enterConfirmPassword("password123")
								.and().clickRegisterButton();
		
	}
	
	@Test
	public void createNewUser2() {
		heatClinic().homePage().verifyTitle()
								.and().goToRegistrationPage()
								.and().withRegistrationPage()
								.registerUser();
		
		/*
								.then().enterEmailAddress("paxoqa1@gmail.com")
								.and().enterFirstName("Alex")
								.and().enterLastName("Pecora")
								.and().enterPassword("password123")
								.and().enterConfirmPassword("password123")
								.and().enterDOB("01/09/1994")
								.and().clickRegisterButton();
		*/
								
		//This is considered an architectural script view
	}
	
}
