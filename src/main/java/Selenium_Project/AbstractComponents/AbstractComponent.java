package Selenium_Project.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium_Project.PageObjects.CartPage;
import Selenium_Project.PageObjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[routerlink='/dashboard/cart']")
	WebElement cartIcon;
	
	@FindBy(css=".ng-animating")
	WebElement loader;

	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement ordersIcon;
	
	public CartPage goToCart() throws InterruptedException {
		Thread.sleep(3000);
		waitForElementToBeClikable(cartIcon);
		cartIcon.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrdersPage() {
		ordersIcon.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
		
		
	}
	
	

	public void waitForElementToBeAppear(By FindBy) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FindBy));
	}

	public void waitForElementToBeDisappear(WebElement ele) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void waitForElementToBeAppear(WebElement ele) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForElementToBeClikable(WebElement ele) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

}
