package Boohoo.BHM.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Boohoo.BHM.pageobjects.CartPage;
import Boohoo.BHM.pageobjects.ProductListingPage;

public class AbstractComponent {

	private WebDriver driver;
	private WebDriverWait wait;
	Actions act;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		act = new Actions(driver);
	}

	@FindBy(id = "mini-cart")
	WebElement miniCartIcon;
	@FindBy(css = ".button.mini-cart-link-cart")
	WebElement openCartButton;


	@FindBy(css = "#navigation li[class*='has-submenu']")
	List<WebElement> subMenuItems;
	@FindBy(css = "#navigation li[class*='has-submenu'] li a")
	List<WebElement> subCategories;

	public void openSubMenu(String categoryName) throws InterruptedException {

		WebElement submenu = subMenuItems.stream()
				.filter(x -> x.findElement(By.tagName("a")).getText().equalsIgnoreCase(categoryName)).findFirst()
				.orElse(null);
		act.moveToElement(submenu).click().build().perform();
		Thread.sleep(500);
	}

	public ProductListingPage openSubCategory(String subCategoryName) { 
		WebElement subCategory = subCategories.stream()
				.filter(x -> x.getText().equalsIgnoreCase(subCategoryName)).findFirst()
				.orElse(null);
		subCategory.click();
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

}
