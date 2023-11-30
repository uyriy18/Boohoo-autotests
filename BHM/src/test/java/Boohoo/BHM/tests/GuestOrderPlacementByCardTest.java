package Boohoo.BHM.tests;
import java.io.IOException;
import org.testng.annotations.Test;
import Boohoo.BHM.TestComponents.BaseTest;
import Boohoo.BHM.pageobjects.BillingPage;
import Boohoo.BHM.pageobjects.CartPage;
import Boohoo.BHM.pageobjects.CheckoutLoginPage;
import Boohoo.BHM.pageobjects.ConfirmationPage;
import Boohoo.BHM.pageobjects.HomePage;
import Boohoo.BHM.pageobjects.ProductDetailsPage;
import Boohoo.BHM.pageobjects.ProductListingPage;
import Boohoo.BHM.pageobjects.ShippingPage;


public class GuestOrderPlacementByCardTest extends BaseTest {
	@Test
	public void placeOrderGuestCard() throws IOException, InterruptedException  {
		
		//Home page
		homePage.acceptCookies();
		homePage.openSubMenu("CLOTHING");
		ProductListingPage plp = homePage.openSubCategory("T-Shirts");		
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
		//Confirmation page
		ConfirmationPage cp = bp.goConfirmationPage();
		
	}
	

}
