package com.paxotech.heatclinic.framework.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;


public class PageBase {

	public static final int DEFAULT_WAIT_TIME = 30; // in seconds
	public static final int DEFAULT_POLLING_TIME = 100; // in milliseconds

	protected WebDriver driver = null;
	

	public PageBase(WebDriver driver) {
		this.driver = driver;
		

	} 
	
	public void delayFor(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ***"SPECIAL CODE"****

	public WebElement waitForElement(final By locator, int timeToWaitInSec) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_POLLING_TIME, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		Function<WebDriver, WebElement> f= new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);

			}
		};
		
		WebElement foo = wait.until(f);
		
		//^^this function is split up into 2 methods based on the lines below
		//This function waits until a specific method is called in the browser, 
			//then carries out the specific action requested after the element 
			//appears(next method, waitForElementDisplayed)

		/*WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);

			}
		});*/
		return foo;
	}

	public WebElement waitForElement(final By locator) {
		return waitForElement(locator, DEFAULT_WAIT_TIME);
		//BE SURE TO ADD THE SECOND INT, OR THE METHOD WILL CALL ITSELF INFINITELY
		
		//these are the same INT's declared from the top of the page
		//Another example of method overloading
		//Same method name, different parameters 

	}

	public WebElement waitForElementDisplayed(final By locator, int timeToWaitInSec) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeToWaitInSec, TimeUnit.SECONDS)
				.pollingEvery(DEFAULT_POLLING_TIME, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(locator);
				if (element != null && element.isDisplayed()) {	//Only returns element if its displayed
					return element;
				}
				return null;
			}
		});
		return foo;
	}

	public WebElement waitForElementDisplayed(final By locator) {
		return waitForElementDisplayed(locator, DEFAULT_WAIT_TIME);
	}
	
	
	public void jsClick(WebElement element){
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",element);
		//This will only work if regular .click() doesn't work
		//ONLY USE IF THERE IS NO OTHER OPTION(A user doesn't work like this...)
	}
	
	public void jsClick(By by){
		WebElement element = waitForElementDisplayed(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",element);
		//This is another way to do the jsClick function
	}
	
	
	public void hoverItem(WebElement element){
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform(); //fluent
		//actions.perform();
		//TO USE HOVER, WE NEED ACTION CLASS
		//ACTION ALLOWS YOU TO CHAIN MULTIPLE ACTIONS TOGETHER
	}
	
	public void hoverItem(By by){
		WebElement element = driver.findElement(by);
		hoverItem(element);
		
	}
	
	public void hoverItemEx(WebElement element){
		Locatable hoverItem = (Locatable) element;
		Mouse mouse = ((HasInputDevices) driver).getMouse();
		mouse.mouseMove(hoverItem.getCoordinates());
	}
	
	
	public void highlightElement(WebElement element){
		for(int i = 0; i < 2; i++){
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].setAttribute('style',arguments[1]);",
					element, "color: yellow; border: 2px solid yellow;");
			delayFor(200);
			js.executeScript(
					"arguments[0].setAttributes('style', arguements[1]);",
					element, "");
		}
	}

	public void highlightElement(By by){
		WebElement element = waitForElementDisplayed(by);
		highlightElement(element);
		
		/*for(int i = 0; i < 2; i++){
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
					"arguments[0].setAttribute('style',arguments[1]);",
					element, "color: yellow; border: 2px solid yellow;");
			delayFor(200);
			js.executeScript(
					"arguments[0].setAttributes('style', arguements[1]);",
					element, "");
			
		}*/
	}
	
}
