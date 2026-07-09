package Selenium_Project.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Selenium_Project.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ZARA COAT 3";
		String countryNeeded = "India";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		LoginPage loginPage = new LoginPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("babumanikandan6228@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Babu@1234");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card-body")));
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));

		for (int i = 0; i < products.size(); i++) {
			String product = products.get(i).getText();

			if (product.contains(productName)) {
			
				products.get(i).findElement(By.cssSelector(".fa-shopping-cart")).click();
				break;
			}
			
		}
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts =driver.findElements(By.cssSelector(".cartSection h3"));
		
		for(int j=0;j<cartProducts.size();j++) {
			
			String cartProduct = cartProducts.get(j).getText();
			boolean match =cartProduct.equalsIgnoreCase(productName); 
			Assert.assertTrue(match);		
		}
		driver.findElement(By.cssSelector(".subtotal .btn-primary")).click();
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".list-group span")));
		List<WebElement> countries =driver.findElements(By.cssSelector(".list-group span"));
		for(int k=0;k<countries.size();k++) {
			String country = countries.get(k).getText();
			System.out.println(country);
			if (country.equalsIgnoreCase(countryNeeded)) {
				
				countries.get(k).click();
			}
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();

	}

}
