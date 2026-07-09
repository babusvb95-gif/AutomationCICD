package Selenium_Project.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Selenium_Project.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	public WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	By countryList = By.cssSelector(".list-group span");

	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;

	@FindBy(css = ".list-group span")
	List<WebElement> countries;

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement countryDropDown;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectIndia;
	
	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryNeeded) {
		Actions a = new Actions(driver);
		a.sendKeys(countryDropDown, countryNeeded).build().perform();
		waitForElementToBeAppear(results);
		selectIndia.click();

	}

	public ConfirmationPage placeOrder() throws InterruptedException {

		
		placeOrderButton.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}
