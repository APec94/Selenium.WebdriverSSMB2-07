package com.paxotech.heatclinic.framework.pages;

import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
//	***** THESE TWO LINES ^^^^ ARE NEEDED TO UTILIZE HAMCREST****
//			***THEY MUST BE IMPORTED AS STATIC ***

public class Page extends PageBase {

	public Page(WebDriver driver) {
		super(driver);

	}

	public void verifyTitle(String expected) {
		String actual = driver.getTitle();

		assertThat(actual, equalToIgnoringCase(expected));

	}

}
