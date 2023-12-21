package Boohoo.BHM.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Boohoo.BHM.AbstractComponents.AbstractComponent;

public class MyAccountPage extends AbstractComponent{
	WebDriver driver;
	Actions act;
	@FindBy(css="div[class='account-nav-item']:first-of-type li:nth-child(3) a")
	WebElement Addresses;
	@FindBy(css="div[class='account-nav-item']:first-of-type li:nth-child(4) a")
	WebElement PaymentDetails;

	
	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}
	
	public MA_Wallet goToWalletPage() {
		PaymentDetails.click();
		return new MA_Wallet(driver);
		
	}

	
	
	



}
