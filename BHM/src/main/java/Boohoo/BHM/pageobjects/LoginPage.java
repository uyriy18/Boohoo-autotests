package Boohoo.BHM.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Boohoo.BHM.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	WebDriver driver;
	Actions act;

	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}

	@FindBy(css = "div[class*='col-2'] button[class*='login-page-button']")  
	WebElement goToShippingAsGuestButton;
	@FindBy(css="input[class*='input-text username']")
	WebElement userEmail;
	@FindBy(css="input[class*='input-text password']")
	WebElement userPassword;
	@FindBy(css = "button[class='login-page-button js-login-page-button']")  
	WebElement LogInButton;
	
	
	public ShippingPage goShippingPageAsGuest() {
		goToShippingAsGuestButton.click();
		return new ShippingPage(driver);		
	}
	
	public MyAccountPage userLogin(String email, String password) {
		waitForElementtoAppear(userEmail);
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		LogInButton.click();
		//waitForURLAppear("myaccount");
		return new MyAccountPage(driver);
		
	}



}
