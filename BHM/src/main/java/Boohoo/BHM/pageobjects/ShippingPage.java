package Boohoo.BHM.pageobjects;



import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Boohoo.BHM.AbstractComponents.AbstractComponent;

public class ShippingPage extends AbstractComponent{
	WebDriver driver;
	Actions act;
	Select selector;

	
	public ShippingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}

	@FindBy(id = "dwfrm_singleshipping_shippingAddress_addressFields_firstName")  
	WebElement firstNameInput;
	@FindBy(id = "dwfrm_singleshipping_shippingAddress_addressFields_lastName")  
	WebElement lastNameInput;
	@FindBy(id = "dwfrm_profile_customer_dayofbirth")  
	WebElement dayOfBithdropdown;
	@FindBy(id = "dwfrm_profile_customer_monthofbirth")  
	WebElement monthOfBithdropdown;
	@FindBy(id = "dwfrm_profile_customer_yearofbirth")  
	WebElement yearOfBithdropdown;
	@FindBy(id = "dwfrm_singleshipping_shippingAddress_addressFields_country")  
	WebElement countrySelector;
	@FindBy(id = "dwfrm_singleshipping_shippingAddress_addressFields_address1")  
	WebElement address1Input;
	@FindBy(id = "dwfrm_singleshipping_shippingAddress_addressFields_city")  
	WebElement cityInput;
	@FindBy(id = "dwfrm_singleshipping_shippingAddress_addressFields_postalcodes_postal")  
	WebElement postalCodeInput;
	@FindBy(id = "dwfrm_singleshipping_shippingAddress_addressFields_phone")  
	WebElement phoneNumberInput;
	@FindBy(css = "button[class='next-step-btn js-checkout-next-step-btn']")  
	WebElement goToBillingButton;
	@FindBy(css = "button[class='verification-address-button js-apply-address']")  
	WebElement cofirmAdressButton;
	
	public void fillUserData() {
		firstNameInput.sendKeys("Yurii");
		lastNameInput.sendKeys("AutoTest");
		act.moveToElement(dayOfBithdropdown).click().keyDown(Keys.DOWN).keyDown(Keys.ENTER).build().perform();
		act.moveToElement(monthOfBithdropdown).click().keyDown(Keys.DOWN).keyDown(Keys.ENTER).build().perform();
		act.moveToElement(yearOfBithdropdown).click().keyDown(Keys.DOWN).keyDown(Keys.ENTER).build().perform();
		countrySelector.click();
		selector = new Select(countrySelector);	
		selector.selectByVisibleText("France");
		address1Input.sendKeys("Paris");
		cityInput.sendKeys("Castelmayran");
		postalCodeInput.sendKeys("82210");
		phoneNumberInput.sendKeys("33333333");
	}
	public void goToSubmitAddress() {
		//waitForElementtoDisappear(null);
		goToBillingButton.click();
	}
	public BillingPage goToBillingPage() {
		waitForElementtoAppear(cofirmAdressButton);
		cofirmAdressButton.click();;
		return new BillingPage(driver);		
	}



}
