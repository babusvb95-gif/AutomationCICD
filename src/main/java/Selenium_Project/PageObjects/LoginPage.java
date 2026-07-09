package Selenium_Project.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium_Project.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement passwrodEle;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css=".toast-message")
	WebElement errorMessage;

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCatalogue loginApplication(String email, String password) {
		userName.sendKeys(email);
		passwrodEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		
		String inccorctPassworderror =errorMessage.getText();
		return inccorctPassworderror;
	}
}
