package Boohoo.BHM.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.DOMException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import Boohoo.BHM.TestComponents.BaseTest;
import Boohoo.BHM.pageobjects.BillingPage;
import Boohoo.BHM.pageobjects.CartPage;
import Boohoo.BHM.pageobjects.LoginPage;
import Boohoo.BHM.pageobjects.ConfirmationPage;
import Boohoo.BHM.pageobjects.ProductDetailsPage;
import Boohoo.BHM.pageobjects.ProductListingPage;
import Boohoo.BHM.pageobjects.ShippingPage;

public class GuestOrderPlacementByCardTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "GuestUser", "Smoke", "Regression" })
	public void placeOrderGuestCard(HashMap<String, String> input) throws IOException, InterruptedException {

		// Home page
		homePage.acceptCookies();
		homePage.openSubMenu();
		ProductListingPage plp = homePage.openSubCategory();
		// PLP
		ProductDetailsPage pdp = plp.goToPDP(); // TO-DO : should be added verification if the product has available
												// options
		// PDP
		pdp.selectProductSize();
		pdp.addToCart();
		pdp.openMiniCart();
		CartPage cart = pdp.goToCartfromMinicart();
		// Cart
		LoginPage co = cart.goShippingPageAsGuest(); // Checkout Login
		ShippingPage sp = co.goShippingPageAsGuest();
		// Shipping page
		sp.fillGuestUserData(input.get("country"));
		sp.fillAddress(input.get("address1"), input.get("city"), input.get("postal"));
		BillingPage bp = sp.goToBillingPage();
		// Billing page
		bp.fillUserEmail();
		bp.fillCardData(input.get("cartName"), input.get("cartNumber"), input.get("expDate"), input.get("cvc"));
		// Confirmation page
		ConfirmationPage cp = bp.goConfirmationPage();
		Assert.assertTrue(cp.assertConfirmationMessage(input.get("confirmationMessage")));

	}


	
	

}
