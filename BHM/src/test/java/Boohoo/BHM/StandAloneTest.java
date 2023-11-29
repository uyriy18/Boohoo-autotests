package Boohoo.BHM;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Boohoo.BHM.pageobjects.HomePage;
public class StandAloneTest {
	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("test-type");
        options.addArguments("disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Home page
		
		driver.get("https://storefront:Oreo2022@dwdev.boohooman.com/eu/");
		
		HomePage hPage = new HomePage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("onetrust-accept-btn-handler"))));
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.cssSelector("li[menu-id='mens']"))).build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("T-Shirts"))));
		driver.findElement(By.linkText("T-Shirts")).click();
		
		//PLP
		driver.findElement(By.cssSelector(".product-tile.js-product-tile")).click();
		
		//PDP
		driver.findElement(By.cssSelector("div[class*='size'] .variation-value.selectable:first-child")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[value='Add to Bag']"))));
		driver.findElement(By.cssSelector("#add-to-cart")).click();
		act.moveToElement(driver.findElement(By.id("mini-cart"))).build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".button.mini-cart-link-cart"))));
		driver.findElement(By.cssSelector(".button.mini-cart-link-cart")).click();
		
		//Cart
		driver.findElement(By.cssSelector("button[value='Checkout']")).click();
		
		//Checkout Login
		driver.findElement(By.cssSelector("button[value='Continue as a guest']")).click();
		
		//Shipping page
		driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_firstName")).sendKeys("Yurii");
		driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).sendKeys("Autotest");
		driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_lastName")).sendKeys("Autotest");
		act.moveToElement(driver.findElement(By.id("dwfrm_profile_customer_dayofbirth"))).
		click().keyDown(Keys.DOWN).keyDown(Keys.ENTER).build().perform();
		act.moveToElement(driver.findElement(By.id("dwfrm_profile_customer_monthofbirth"))).
		click().keyDown(Keys.DOWN).keyDown(Keys.ENTER).build().perform();
		act.moveToElement(driver.findElement(By.id("dwfrm_profile_customer_yearofbirth"))).
		click().keyDown(Keys.DOWN).keyDown(Keys.ENTER).build().perform();
		Select selector = new Select(driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_country")));
		driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_country")).click();
		selector.selectByVisibleText("France");
		driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_address1")).sendKeys("Paris");
		driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_city")).sendKeys("Castelmayran");
		driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_postalcodes_postal")).sendKeys("82210");
		driver.findElement(By.id("dwfrm_singleshipping_shippingAddress_addressFields_phone")).sendKeys("33333333");
		driver.findElement(By.cssSelector("button[class='next-step-btn js-checkout-next-step-btn']")).click();
		driver.findElement(By.cssSelector("button[class='verification-address-button js-apply-address']")).click();
		
		//Billing page
		driver.findElement(By.id("dwfrm_billing_billingAddress_email_emailAddress")).sendKeys("autotest@as.as");
		driver.findElement(By.id("dwfrm_billing_billingAddress_email_emailConfirm")).sendKeys("autotest@as.as");
        driver.findElement(By.cssSelector(("input[name='holderName']"))).sendKeys("Visa");
        act.moveToElement(driver.findElement(By.cssSelector("span[class*='card__cardNumber']"))).click().sendKeys("4111111111111111").build().perform();
        act.moveToElement(driver.findElement(By.cssSelector("span[class*='card__exp-date']"))).click().sendKeys("0330").build().perform();
        act.moveToElement(driver.findElement(By.cssSelector("span[class*='card__cvc']"))).click().sendKeys("737").build().perform();
        driver.findElement(By.id("billingSubmitButton")).click();
        
        driver.close();




		
	}
	

}
