package Boohoo.BHM.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Boohoo.BHM.AbstractComponents.AbstractComponent;

public class ShippingPage extends AbstractComponent {
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
	@FindBy(css = "button[class*='verification-address-button']")
	WebElement cofirmAdressButton;
	@FindBy(id = "address-autocomplete")
	WebElement addressSuggestionInput;
	@FindBy(css = "div[class*='addToAddressBook'] label")
	WebElement saveAddressCheckbox;  // not interactable
	By firstItem = By.cssSelector("div span[class='pcadescription']");
	By message = By.cssSelector("p[class='verification-address-description']");

	public void fillGuestUserData(String country) {
		firstNameInput.sendKeys("Yurii");
		lastNameInput.sendKeys("AutoTest");
		act.moveToElement(dayOfBithdropdown).click().keyDown(Keys.DOWN).keyDown(Keys.ENTER).build().perform();
		act.moveToElement(monthOfBithdropdown).click().keyDown(Keys.DOWN).keyDown(Keys.ENTER).build().perform();
		act.moveToElement(yearOfBithdropdown).click().keyDown(Keys.DOWN).keyDown(Keys.ENTER).build().perform();
		selector = new Select(countrySelector);
		selector.selectByVisibleText(country);
		phoneNumberInput.sendKeys("33333333");
	}
	public void fillRegiteredUserData(String country) {
		firstNameInput.sendKeys("Yurii");
		lastNameInput.sendKeys("AutoTest");
		saveAddressCheckbox.click();
		countrySelector.click();
		selector = new Select(countrySelector);
		selector.selectByVisibleText(country);
		phoneNumberInput.sendKeys("33333333");
	}

	public void fillAddress(String address1, String city, String postalCode) throws InterruptedException {
		address1Input.sendKeys(address1);
		cityInput.sendKeys(city);
		postalCodeInput.sendKeys(postalCode);
		Thread.sleep(3000);
	}

	public void addressAutocomplite(String saggestion) { // doesn't work
		addressSuggestionInput.sendKeys(saggestion);
		System.out.println(postalCodeInput.getText());
		while (postalCodeInput.getText().toCharArray().length == 0) {
			act.keyDown(Keys.ENTER).build().perform();
		}

	}

	public BillingPage goToBillingPage() throws InterruptedException {

		while (driver.getCurrentUrl().contains("shipping")) {
			System.out.println(driver.getCurrentUrl());
			act.moveToElement(goToBillingButton).click().build().perform();
			Thread.sleep(1000);
		}

		System.out.println(driver.getCurrentUrl());
		return new BillingPage(driver);
	}

}
