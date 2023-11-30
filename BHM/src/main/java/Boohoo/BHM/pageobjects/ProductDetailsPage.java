package Boohoo.BHM.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Boohoo.BHM.AbstractComponents.AbstractComponent;

public class ProductDetailsPage extends AbstractComponent {
	WebDriver driver;
	Actions act;

	
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}

	@FindBy(css = "div[class*='size'] .variation-value.selectable:first-child")  // should be refactored by adding dynamic impl. like- find all WebElements put them into List and select one that contains sending Category name
	WebElement sizeOption;

	
	By addToCartButton = By.cssSelector("button span[class='add-to-cart-text']");
	
	public void selectProductSize() {
		sizeOption.click();
	}
	
	public void addToCart() {
		waitForElementtoAppear(addToCartButton);
		driver.findElement(addToCartButton).click();
	}
	
	
			
	 



}
