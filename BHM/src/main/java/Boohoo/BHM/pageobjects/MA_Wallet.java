package Boohoo.BHM.pageobjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Boohoo.BHM.AbstractComponents.AbstractComponent;

public class MA_Wallet extends MyAccountPage{
	WebDriver driver;
	Actions act;
	private static int initialCardsNumber;

	
@FindBy (css=".add-card.button")
WebElement addCartBtn;
@FindBy (css="li[class='account-page-list-item payment-list-item']")
List<WebElement> cards;
@FindBy(css = "input[name='holderName']") 
WebElement cardNameInput;
@FindBy(css = "span[class*='card__cardNumber']")  
WebElement cardNumberInput;
@FindBy(css = "span[class*='card__exp-date']")  
WebElement cardExpDateInput;
@FindBy(css = "span[data-cse='encryptedSecurityCode']")  
WebElement cardCvcInput;
@FindBy (css="#add-card-submit")
WebElement submitBtn;

	
	public MA_Wallet(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
		initialCardsNumber = cards.size();
	}
	
	public void addNewCard(String cartName, String cardNumber, String cardExpDate, String cardCvc) {
		addCartBtn.click();
		waitForElementtoAppear(cardNameInput);
		act.moveToElement(cardNameInput).click().sendKeys(cartName).build().perform();
		act.moveToElement(cardNumberInput).click().sendKeys(cardNumber).build().perform();
		act.moveToElement(cardExpDateInput).click().sendKeys(cardExpDate).build().perform();
		act.moveToElement(cardCvcInput).click().sendKeys(cardCvc).build().perform();
		submitBtn.click();
		waitForElementtoAppear(cards.get(cards.size()-1));
	}
	public void removeCard () {
		cards.get(cards.size()-1).findElement(By.cssSelector("button[class*='button-delete']")).click();
		driver.switchTo().alert().accept();
		waitForElementtoDisappear(cards.get(cards.size()-1));
	}
	public boolean checkCardsNumber() {
		return initialCardsNumber == cards.size();
	}
	

	
	
	



}
