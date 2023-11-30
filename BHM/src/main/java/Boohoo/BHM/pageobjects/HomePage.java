package Boohoo.BHM.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Boohoo.BHM.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent{
	WebDriver driver;
	Actions act;
	WebDriverWait wait;

	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}

	@FindBy(id = "onetrust-accept-btn-handler")
	WebElement AcceptoneTrustButton;
	
	@FindBy(css = "li[menu-id='mens']")  // should be refactored by adding dynamic impl. like- find all WebElements put them into List and select one that contains sending Category name
	WebElement navigationMenuMens;
	
	@FindBy(linkText = "T-Shirts")   // should be refactored in the same way as navigationMenu
	WebElement tshirtsCategory;
	
	public void goToHomePage() {
		driver.get("https://storefront:Oreo2022@dwdev.boohooman.com/eu/");
	}
	
	public void acceptCookies() {
		waitForElementtoAppear(AcceptoneTrustButton);
		AcceptoneTrustButton.click();
	}
	
	public ProductListingPage goToPLP(String category, String subCategory) {  // should be refactored to open categories and subcategories which are provided as arguments (like WebElements.stream(.filter(x -> x.Contains(..))))
		act.moveToElement(navigationMenuMens).build().perform();
		waitForElementtoAppear(tshirtsCategory);
		tshirtsCategory.click();
		return new  ProductListingPage(driver);
	}

}
