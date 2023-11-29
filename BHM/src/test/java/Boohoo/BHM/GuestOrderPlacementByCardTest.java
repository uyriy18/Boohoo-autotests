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

import Boohoo.BHM.pageobjects.BillingPage;
import Boohoo.BHM.pageobjects.CartPage;
import Boohoo.BHM.pageobjects.CheckoutLoginPage;
import Boohoo.BHM.pageobjects.ConfirmationPage;
import Boohoo.BHM.pageobjects.HomePage;
import Boohoo.BHM.pageobjects.ProductDetailsPage;
import Boohoo.BHM.pageobjects.ProductListingPage;
import Boohoo.BHM.pageobjects.ShippingPage;
public class GuestOrderPlacementByCardTest {
	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("test-type");
        options.addArguments("disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Home page
		
		driver.get("https://storefront:Oreo2022@dwdev.boohooman.com/eu/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Actions act = new Actions(driver);

		HomePage hPage = new HomePage(driver);
		hPage.acceptCookies();
		ProductListingPage plp = hPage.goToPLP(null, null);
		
		//PLP
		ProductDetailsPage pdp = plp.goToPDP();  // TO-DO : should be added verification if the product has available options
		//PDP
		
		pdp.selectProductSize();
		pdp.addToCart();
		CartPage cart = pdp.goToCart();

		
		//Cart
		CheckoutLoginPage co = cart.goShippingPageAsGuest();
		
		//Checkout Login
		ShippingPage sp = co.goShippingPageAsGuest();
		
		//Shipping page
		sp.fillUserData();
		sp.goToSubmitAddress();
		BillingPage bp = sp.goToBillingPage();

		
		//Billing page
		bp.fillUserEmail();
		bp.fillCardData("Visa", "4111111111111111", "0330", "737");
		ConfirmationPage cp = bp.goConfirmationPage();

        
        driver.close();




		
	}
	

}
