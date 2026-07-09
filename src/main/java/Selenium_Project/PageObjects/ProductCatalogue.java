package Selenium_Project.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Selenium_Project.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	public WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By productsBy = By.cssSelector(".card-body");
	By addToCart = By.cssSelector(".fa-shopping-cart");
	By toastMessage = By.cssSelector("#toast-container");

	@FindBy(css = ".card-body")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement loader;

	public List<WebElement> getProductList() {

		waitForElementToBeAppear(productsBy);
		return products;
	}

	public void addProductToCart(String productName) {

		for (int i = 0; i < getProductList().size(); i++) {
			String product = getProductList().get(i).getText();

			if (product.contains(productName)) {
				getProductList().get(i).findElement(addToCart).click();
				break;
			}

		}
		waitForElementToBeAppear(toastMessage);
		
	}

}
