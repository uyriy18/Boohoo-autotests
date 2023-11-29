package Boohoo.BHM.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Boohoo.BHM.AbstractComponents.AbstractComponent;

public class ProductListingPage extends AbstractComponent{
	WebDriver driver;
	Actions act;

	
	public ProductListingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}

	@FindBy(css = ".product-tile.js-product-tile")  // should be refactored by adding dynamic impl. like- find all WebElements put them into List and select one that contains sending Category name
	WebElement plpTile;
	
	public ProductDetailsPage goToPDP() {
		plpTile.click();
		return new ProductDetailsPage(driver);		
	}



}
