package Boohoo.BHM.tests;
import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
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
public class RegistredBySavedCCSavedAddress extends BaseTest {
	@Test(dataProvider = "getData", groups = { "GuestUser", "Smoke", "Regression" })
	public void placeOrderRegestredCard(HashMap<String, String> input) throws IOException, InterruptedException {

		// Home page
		homePage.acceptCookies();
		homePage.goToLocale(input.get("url"));
		LoginPage lp = homePage.goToLoginPage();
		MyAccountPage ma = lp.userLogin(input.get("userEmail1"), input.get("userPassword"));
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
		BillingPage bp = sp.goToBillingPage();
		// Billing page
		bp.selectSavedCard("Visa",  input.get("cvc"));
		// Confirmation page
		ConfirmationPage cp = bp.goConfirmationPage();
		Assert.assertTrue(cp.assertConfirmationMessage(input.get("confirmationMessage")));

	}




}
