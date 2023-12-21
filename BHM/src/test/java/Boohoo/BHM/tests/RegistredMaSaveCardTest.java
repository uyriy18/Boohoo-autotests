package Boohoo.BHM.tests;
import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import Boohoo.BHM.TestComponents.BaseTest;
import Boohoo.BHM.pageobjects.BillingPage;
import Boohoo.BHM.pageobjects.CartPage;
import Boohoo.BHM.pageobjects.LoginPage;
import Boohoo.BHM.pageobjects.MA_Wallet;
import Boohoo.BHM.pageobjects.ConfirmationPage;
import Boohoo.BHM.pageobjects.MyAccountPage;
import Boohoo.BHM.pageobjects.ProductDetailsPage;
import Boohoo.BHM.pageobjects.ProductListingPage;
import Boohoo.BHM.pageobjects.ShippingPage;
public class RegistredMaSaveCardTest extends BaseTest {
	@Test(dataProvider = "getData", groups = { "GuestUser", "Smoke", "Regression" })
	public void placeOrderRegestredCard(HashMap<String, String> input) throws IOException, InterruptedException {

		// Home page
		homePage.acceptCookies();
		homePage.goToLocale(input.get("url"));
		LoginPage lp = homePage.goToLoginPage();
		MyAccountPage ma = lp.userLogin(input.get("userEmail1"), input.get("userPassword"));
		MA_Wallet wallet = ma.goToWalletPage();
		wallet.addNewCard(input.get("cartName"), input.get("cartNumber"), input.get("expDate"), input.get("cvc"));
		wallet.removeCard();
		Thread.sleep(5000);
	
	}




}
