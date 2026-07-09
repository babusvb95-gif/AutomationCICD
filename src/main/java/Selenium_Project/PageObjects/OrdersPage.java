package Selenium_Project.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium_Project.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	public WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> nameColumn;
	
	
	public String getOrderHistory(String productName) {
		for (WebElement productElement : nameColumn) {

            String product = productElement.getText();

            if (product.equalsIgnoreCase(productName)) {
                return product;
            }
        }

        return null;
		
	}

	
}
