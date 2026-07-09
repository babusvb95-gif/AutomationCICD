package Selenium_Project.Tests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium_Project.PageObjects.CartPage;
import Selenium_Project.PageObjects.CheckOutPage;
import Selenium_Project.PageObjects.ConfirmationPage;
import Selenium_Project.PageObjects.LoginPage;
import Selenium_Project.PageObjects.OrdersPage;
import Selenium_Project.PageObjects.ProductCatalogue;
import Selenium_Project.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	String countryNeeded = "India";

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCart();
		CheckOutPage checkOutPage = cartPage.checkOutProduct(input.get("productName"));
		checkOutPage.selectCountry(countryNeeded);
		ConfirmationPage confirmationPage = checkOutPage.placeOrder();
		String confirmationMessage = confirmationPage.confirmationMessageDisplay();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void getProductDisplay() throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = loginPage.loginApplication("babusvb95@gmail.com", "Babu@1234");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertEquals(productName, ordersPage.getOrderHistory(productName));
	}

//	@DataProvider
//	public Object[][] getData() {
//
//		Object[][] a = new Object[][] { { "babusvb95@gmail.com", "Babu@1234", "ZARA COAT 3" },
//				{ "babumanikandan6228@gmail.com", "Babu@1234", "ADIDAS ORIGINAL" } };
//		return a;
//	
	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "babusvb95@gmail.com");
//	map.put("password", "Babu@1234");
//	map.put("productName", "ZARA COAT 3");
//
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "babumanikandan6228@gmail.com");
//	map1.put("password", "Babu@1234");
//	map1.put("productName", "ADIDAS ORIGINAL");
//}
	

	@DataProvider
	public Object[][] getData() throws IOException {

	    List<HashMap<String, String>> data = getJsonDataToMap(
	            System.getProperty("user.dir")
	            + "/src/test/java/Selenium_Project/data/purchaseOrder.json");

	    return new Object[][] {
	            { data.get(0) },
	            { data.get(1) }
	    };
	}
}
