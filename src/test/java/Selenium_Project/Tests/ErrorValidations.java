package Selenium_Project.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Selenium_Project.PageObjects.CartPage;
import Selenium_Project.PageObjects.CheckOutPage;
import Selenium_Project.PageObjects.ConfirmationPage;
import Selenium_Project.PageObjects.LoginPage;
import Selenium_Project.PageObjects.ProductCatalogue;
import Selenium_Project.TestComponents.BaseTest;
import Selenium_Project.TestComponents.Retry;

public class ErrorValidations extends BaseTest {

	@Test(groups = {"Error Handling"})
	public void submitOrder() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		String countryNeeded = "India";

		ProductCatalogue productCatalogue = loginPage.loginApplication("babuksvb95@gmail.com", "Babu@12345");
		String incorrectPassword = loginPage.getErrorMessage();
		System.out.println(incorrectPassword);
		Assert.assertEquals(incorrectPassword,"Incorrect email or password.");

	}


	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		String countryNeeded = "India";

		ProductCatalogue productCatalogue = loginPage.loginApplication("babumanikandan6228@gmail.com", "Babu@1234");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		CheckOutPage checkOutPage = cartPage.checkOutProduct(productName);
		checkOutPage.selectCountry(countryNeeded);
		ConfirmationPage confirmationPage = checkOutPage.placeOrder();
		String confirmationMessage = confirmationPage.confirmationMessageDisplay();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}


}
