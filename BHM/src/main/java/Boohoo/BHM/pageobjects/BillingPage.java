package Boohoo.BHM.pageobjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
	@FindBy(css="label[class*='paypal']")
	WebElement PayPalLabel;
	@FindBy(css="div[class*='paypal-button']")
	WebElement PayPalButton;
	@FindBy(css="input[id='email']")
	WebElement PayPalEmailInput;
	@FindBy(css="input[id='btnNext']")
	WebElement PayPalNextButton;
	@FindBy(css="input[id='password']")
	WebElement PayPalPasswordInput;
	@FindBy(css="button[id='acceptAllButton']")
	WebElement PayPalAcceptButton;
	@FindBy(css="button[id='btnLogin']")
	WebElement PayPalLoginButton;
	@FindBy(css="button[id='acceptAllButton']")
	WebElement PayPalAcceptCookiesButton;
	@FindBy(css="button[id='payment-submit-btn']")
	WebElement PayPalCompletePurchaseButton;
	@FindBy(css = "input[name='holderName']") 
	WebElement cardNameInput;
	@FindBy(css = "span[class*='card__cardNumber']")  
	WebElement cardNumberInput;
	@FindBy(css = "span[class*='card__exp-date']")  
	WebElement cardExpDateInput;
	@FindBy(css = "span[data-cse='encryptedSecurityCode']")  
	List<WebElement> cardCvcInput;
	@FindBy(id = "adyenCreditCardList")
	WebElement cardDropdown;
	@FindBy(css="#adyenCreditCardList option")
	List<WebElement> availableCards;
	@FindBy(id = "billingSubmitButton")  
	WebElement submitButton;
	
	public void fillUserEmail() {   // can be refactored
		emailInput.sendKeys("autotest@as.as");
		confirmEmailInput.sendKeys("autotest@as.as");
	}
	public void fillCardData(String cartName, String cardNumber, String cardExpDate, String cardCvc) {
		cardNameInput.sendKeys(cartName);
		act.moveToElement(cardNumberInput).click().sendKeys(cardNumber).build().perform();
		act.moveToElement(cardExpDateInput).click().sendKeys(cardExpDate).build().perform();
		act.moveToElement(cardCvcInput.get(0)).click().sendKeys(cardCvc).build().perform();
	}
	
	public ConfirmationPage goConfirmationPage() {
		submitButton.click();
		return new ConfirmationPage(driver);		
	}
	public void openBillingWindow() {
		PayPalLabel.click();
		PayPalButton.click();
	}
	
	public void selectSavedCard(String cardName, String cardCvc) throws InterruptedException {
		Select cartSelector = new Select(cardDropdown);
		String card = availableCards.stream().filter(x -> x.getText().contains(cardName)).findFirst().orElse(null).getText();		
		cartSelector.selectByVisibleText(card);
		Thread.sleep(500);	
		act.moveToElement(cardCvcInput.get(1)).click().sendKeys(cardCvc).build().perform();
	}
	public ConfirmationPage placeOrderByPayPal(){		
		PayPalEmailInput.sendKeys("y.bartash+man@astoundcommerce.com");
		PayPalNextButton.click();
		waitForElementtoAppear(PayPalAcceptButton);
		PayPalAcceptButton.click();
		PayPalCompletePurchaseButton.click();
		return new ConfirmationPage(driver);	
	}

	

}
