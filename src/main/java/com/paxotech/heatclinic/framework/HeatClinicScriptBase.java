package com.paxotech.heatclinic.framework;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.paxotech.heatclinic.framework.controller.ApplicationController;

public class HeatClinicScriptBase extends ScriptBase {

	private ApplicationController heatClinic = null;

	@BeforeMethod(alwaysRun = true)
	@Override
	@Parameters({"browser"})
	public void beforeMethod(@Optional(value = "ch") String browser) {
		super.beforeMethod(browser);

		driver.manage().window().maximize();

		driver.navigate().to(AppConstant.App_URL);

	}
	

	public ApplicationController heatClinic() {
		if (heatClinic == null) {
			heatClinic = new ApplicationController(driver);

		}
		return heatClinic;
	}

	@AfterMethod(alwaysRun = true)
	@Override
	public void afterMethod() {
		super.afterMethod();
		heatClinic = null;
	}

}
