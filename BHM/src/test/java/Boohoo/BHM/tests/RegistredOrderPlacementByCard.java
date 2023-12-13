package Boohoo.BHM.tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Boohoo.BHM.TestComponents.BaseTest;
import Boohoo.BHM.pageobjects.BillingPage;
import Boohoo.BHM.pageobjects.CartPage;
import Boohoo.BHM.pageobjects.LoginPage;
import Boohoo.BHM.pageobjects.ConfirmationPage;
import Boohoo.BHM.pageobjects.MyAccountPage;
import Boohoo.BHM.pageobjects.ProductDetailsPage;
import Boohoo.BHM.pageobjects.ProductListingPage;
import Boohoo.BHM.pageobjects.ShippingPage;
public class RegistredOrderPlacementByCard extends BaseTest {
	@Test(dataProvider = "getData", groups = { "GuestUser", "Smoke", "Regression" })
	public void placeOrderGuestCard(HashMap<String, String> input) throws IOException, InterruptedException {

		// Home page
		homePage.acceptCookies();
		homePage.openSubMenu();
		LoginPage lp = homePage.goToLoginPage();
		MyAccountPage ma = lp.userLogin("y.bartash+r73@astoundcommerce.com", "Test1234");
		ma.openSubMenu();
		ProductListingPage plp = ma.openSubCategory();
		// PLP
		ProductDetailsPage pdp = plp.goToPDP(); // TO-DO : should be added verification if the product has available
												// options
		// PDP
		pdp.selectProductSize();
		pdp.addToCart();
		pdp.openMiniCart();
		CartPage cart = pdp.goToCartfromMinicart();
		// Cart
		ShippingPage sp = cart.goShippingPageAsRegistered();
		// Shipping page
		sp.fillRegiteredUserData(input.get("country"));
		sp.fillAddress(input.get("address1"), input.get("city"), input.get("postal"));
		BillingPage bp = sp.goToBillingPage();
		// Billing page
		bp.fillCardData(input.get("cartName"), input.get("cartNumber"), input.get("expDate"), input.get("cvc"));
		// Confirmation page
		ConfirmationPage cp = bp.goConfirmationPage();
		Assert.assertTrue(cp.assertConfirmationMessage(input.get("confirmationMessage")));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//Boohoo//BHM//data//DE-OrderPlacement.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}


}
