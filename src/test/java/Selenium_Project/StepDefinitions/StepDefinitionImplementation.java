package Selenium_Project.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Selenium_Project.PageObjects.CartPage;
import Selenium_Project.PageObjects.CheckOutPage;
import Selenium_Project.PageObjects.ConfirmationPage;
import Selenium_Project.PageObjects.LoginPage;
import Selenium_Project.PageObjects.ProductCatalogue;
import Selenium_Project.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest {
	public LoginPage loginPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	public CheckOutPage checkOutPage;

	@Given("I landed on Ecommerce site")
	public void landed_Ecommerce_Site() throws IOException {

		loginPage = launchApplication();
	}

	@Given("^Logged in with username (.+) with passowrd (.+)$")
	public void login_Application(String username, String password) {
		productCatalogue = loginPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_added_Product(String productName) {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout the product (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException {
		cartPage = productCatalogue.goToCart();
		checkOutPage = cartPage.checkOutProduct(productName);
		checkOutPage.selectCountry("India");
		confirmationPage = checkOutPage.placeOrder();

	}

	@Then("{string} message is displayed on confirmation page")
	public void message_displayed_on_confirmationPage(String string) throws Exception {

		String confirmationMessage = confirmationPage.confirmationMessageDisplay();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();

	}
	
	@Then("{string} Error Message Should display")
	public void loginPageErrorDisplay(String string) {
		String incorrectPassword = loginPage.getErrorMessage();
		System.out.println(incorrectPassword);
	Assert.assertEquals(string,incorrectPassword );
	driver.close();
	}
}
