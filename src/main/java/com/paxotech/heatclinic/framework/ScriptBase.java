package com.paxotech.heatclinic.framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

//import com.paxovision.execution.reporter.listener.ReporterTestListener;
//import com.paxovision.execution.reporter.service.ReporterService;

//paxotech imports and @BeforeClass do not work...


//@Listeners({ReporterTestListener.class})
public class ScriptBase {

	protected WebDriver driver = null;
	//protected ReporterService reporter = ReporterService.reporter();
		
	/*@BeforeClass
	public void beforeClass(){
		System.out.println("Starting thread " + Thread.currentThread().getId());
		reporter.setReportPath(System.getProperty("user.dir") + "/test-output/htmlReport/");
		reporter.setReportName("HeatClinic");
		reporter.setReportTitle("HeatClinic Demo");
		reporter.serCreateUniqueFileName(false);
		
		
	}*/
	

	@BeforeMethod(alwaysRun = true)
				//This sets an optional parameter stating that if another browser is applied, then to use it. Otherwise, default to Firefox (ff)
	public void beforeMethod(String browser) {
		
		if(browser.contentEquals("HtmlUnit")){
			driver = new HtmlUnitDriver();
		}
		/*else if (browser.contentEquals("phantomjs")){
			System.setProperty("phantomjs.binary.path", System.getProperty("user.dir") + "/src/main/resources/driver/32/phantomjs.exe" );
			driver = new PhantomJSDriver();	
		}*/
		else if(browser.contentEquals("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/main/resources/driver/32/IEDriverServer.exe" );
			driver = new InternetExplorerDriver();
		}
		else if(browser.contentEquals("ff")){
			driver = new FirefoxDriver();
		}
		else if(browser.contentEquals("ch")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/driver/32/chromedriver.exe" );
			driver = new ChromeDriver();
			
		}
		else{
			driver = new FirefoxDriver();
		}
		
		
		//driver = new HtmlUnitDriver();	//Completes script without calling browser on screen
										//this is called "Headless browser"
		
		
		//driver = new PhantomJSDriver();
		//System.setProperty("phantomjs.binary.path", System.getProperty("user.dir") + "/src/main/resources/driver/32/phantomjs.exe" );
		 
		
		
		//System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/main/resources/driver/32/IEDriverServer.exe" );
		//driver = new InternetExplorerDriver();	//This runs tests in internet explorer
		
				
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/driver/32/chromedriver.exe" );										
		//driver = new ChromeDriver();				//these two lines enable the Chrome driver, so you can 
													//run tests in Chrome
		
		
		driver = new FirefoxDriver();			//Run tests in Firefox
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {

		driver.close();
		driver.quit();
		driver = null;

	}
}
