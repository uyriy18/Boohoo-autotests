package Boohoo.BHM;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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


		HomePage hPage = new HomePage(driver);
		hPage.acceptCookies();
		hPage.openSubMenu("CLOTHING");
		ProductListingPage plp = hPage.openSubCategory("T-Shirts");
		
		//PLP
		ProductDetailsPage pdp = plp.goToPDP();  // TO-DO : should be added verification if the product has available options
		//PDP
		
		pdp.selectProductSize();
		pdp.addToCart();
		pdp.openMiniCart();
		CartPage cart = pdp.goToCartfromMinicart();

		
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
