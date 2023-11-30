package Boohoo.BHM.tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Boohoo.BHM.TestComponents.BaseTest;
import Boohoo.BHM.pageobjects.BillingPage;
import Boohoo.BHM.pageobjects.CartPage;
import Boohoo.BHM.pageobjects.CheckoutLoginPage;
import Boohoo.BHM.pageobjects.ConfirmationPage;
import Boohoo.BHM.pageobjects.ProductDetailsPage;
import Boohoo.BHM.pageobjects.ProductListingPage;
import Boohoo.BHM.pageobjects.ShippingPage;


public class GuestOrderPlacementByCardTest extends BaseTest {
	@Test(dataProvider = "getData", groups = {"GuestUser","Smoke","Regression"})
	public void placeOrderGuestCard(HashMap<String,String> input) throws IOException, InterruptedException  {
		
		//Home page
		homePage.acceptCookies();
		homePage.openSubMenu(input.get("categoryName"));
		ProductListingPage plp = homePage.openSubCategory(input.get("subCategoryName"));		
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
		bp.fillCardData(input.get("cartName"), input.get("cartNumber"), input.get("expDate"), input.get("cvc"));
		//Confirmation page
		ConfirmationPage cp = bp.goConfirmationPage();		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Boohoo//BHM//data//OrderPlacement.json");			
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
	

}
