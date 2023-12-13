package Boohoo.BHM.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Boohoo.BHM.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;
	Actions act;

	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}

	@FindBy(css = ".confirmation-message-title")  
	WebElement actualConfirmMessage;
	
	public boolean assertConfirmationMessage(String expMessage) {
		waitForElementtoAppear(actualConfirmMessage);
		return expMessage.contains(expMessage)? true : false;
	}
	
	



}
