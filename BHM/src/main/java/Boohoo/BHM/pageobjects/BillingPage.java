package Boohoo.BHM.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Boohoo.BHM.AbstractComponents.AbstractComponent;

public class BillingPage extends AbstractComponent{
	WebDriver driver;
	Actions act;

	
	public BillingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}

	@FindBy(id = "dwfrm_billing_billingAddress_email_emailAddress")  
	WebElement emailInput;
	@FindBy(id = "dwfrm_billing_billingAddress_email_emailConfirm")  
	WebElement confirmEmailInput;
	@FindBy(css = "input[name='holderName']")  
	WebElement cardNameInput;
	@FindBy(css = "span[class*='card__cardNumber']")  
	WebElement cardNumberInput;
	@FindBy(css = "span[class*='card__exp-date']")  
	WebElement cardExpDateInput;
	@FindBy(css = "span[class*='card__cvc']")  
	WebElement cardCvcInput;
	@FindBy(id = "billingSubmitButton")  
	WebElement submitButton;
	
	public void fillUserEmail() {   // can be refactored
		emailInput.sendKeys("autotest@as.as");
		confirmEmailInput.sendKeys("autotest@as.as");
	}
	public void fillCardData(String cartName, String cartNumber, String cartExpDate, String cartCvc) {
		cardNameInput.sendKeys(cartName);
		act.moveToElement(cardNumberInput).click().sendKeys(cartNumber).build().perform();
		act.moveToElement(cardExpDateInput).click().sendKeys(cartExpDate).build().perform();
		act.moveToElement(cardCvcInput).click().sendKeys(cartCvc).build().perform();
	}
	
	public ConfirmationPage goConfirmationPage() {
		submitButton.click();
		return new ConfirmationPage(driver);		
	}

	

}
