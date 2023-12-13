package Boohoo.BHM.AbstractComponents;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Boohoo.BHM.pageobjects.CartPage;
import Boohoo.BHM.pageobjects.LoginPage;
import Boohoo.BHM.pageobjects.MyAccountPage;
import Boohoo.BHM.pageobjects.ProductListingPage;

public class AbstractComponent {

	private WebDriver driver;
	private WebDriverWait wait;
	Actions act;
	Set<String> windowHandles;
	Iterator<String> it;
	String parrentWindow = "";
	String childWindow = "";

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
		windowHandles = driver.getWindowHandles();
		it = windowHandles.iterator();
	}

	@FindBy(id = "mini-cart")
	WebElement miniCartIcon;
	@FindBy(css = ".button.mini-cart-link-cart")
	WebElement openCartButton;


	@FindBy(css = "#navigation li[class*='has-submenu']:nth-child(1)")
	WebElement subMenuItems;
	@FindBy(css = "#navigation li[class*='has-submenu']:nth-child(1) div[class='level-2'] ul[class*='vertical']:nth-child(3) a")
	WebElement subCategories;
	@FindBy(css=".is-mobile.user-account")
	WebElement myAccountIcon;
	@FindBy(css=".user-link-item:first-of-type")
	WebElement logInButton;
	@FindBy(css=".user-link-item:last-of-type")
	WebElement registerButton;

	public void openChildWindow() {
	    parrentWindow = it.next();
		if(it.hasNext()) {
			childWindow = it.next();
		}
		driver.switchTo().window(childWindow);
	}
	public void openParrentWindow() {
		driver.switchTo().window(parrentWindow);
	}

	public void openSubMenu() throws InterruptedException {
		act.moveToElement(subMenuItems).click().build().perform();
		Thread.sleep(500);
	}

	public ProductListingPage openSubCategory() { 
		subCategories.click();
		return new ProductListingPage(driver);
	}

	public void openMiniCart() {
		act.moveToElement(miniCartIcon).build().perform();
		waitForElementtoAppear(openCartButton);
	}

	public CartPage openCart() {
		miniCartIcon.click();
		return new CartPage(driver);
	}

	public CartPage goToCartfromMinicart() {
		openCartButton.click();
		return new CartPage(driver);
	}
	
	public LoginPage goToLoginPage() {
		myAccountIcon.click();
		waitForElementtoAppear(logInButton);
		logInButton.click();
		return new LoginPage(driver);
	}

	public void waitForElementtoAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementtoAppear(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementtoDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForElementtoDisappear(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForURLAppear(String url) {
		wait.until(ExpectedConditions.urlContains(url));
	}


}
