package Selenium_Project.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Selenium_Project.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	public WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".subtotal .btn-primary")
	WebElement checkOutButton;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

public CheckOutPage checkOutProduct(String productName) {
	for(int j=0;j<cartProducts.size();j++) {
		
		String cartProduct = cartProducts.get(j).getText();
		if (cartProduct.equalsIgnoreCase(productName))
		{System.out.println(cartProduct);
		checkOutButton.click();
			break;
		}	
	}
	CheckOutPage checkOutPage = new CheckOutPage(driver);
	return checkOutPage;
}
}
