package com.paxotech.heatclinic.framework.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.paxotech.heatclinic.framework.pages.HomePage;
import com.paxotech.heatclinic.framework.pages.LoginPage;

public class ApplicationController {

	protected WebDriver driver = null;

	private HomePage homePage = null;
	private LoginPage loginPage = null;

	public ApplicationController(WebDriver driver) {
		this.driver = driver;

	}

	public HomePage homePage() {
		if (homePage == null) {
			homePage = new HomePage(driver);
			PageFactory.initElements(driver, homePage); 
		}
		return homePage;
	}

	public LoginPage loginPage() {
		if (loginPage == null) {
			loginPage = new LoginPage(driver);
			PageFactory.initElements(driver, loginPage); 
		}
		return loginPage;
	}
}
