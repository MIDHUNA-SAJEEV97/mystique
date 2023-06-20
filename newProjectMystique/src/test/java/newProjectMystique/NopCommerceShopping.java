package newProjectMystique;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NopCommerceShopping {

	WebDriver driver;

	@Test(priority = 1)
	public void verifyLaunchingTheUrlSuccessfully_TC001() {

		String expected = "https://demo.nopcommerce.com/";
		String actual = driver.getCurrentUrl();
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 2)
	public void verifySearchFunctionalityForProduct_TC002() {

		WebElement searchStore = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		searchStore.sendKeys("phone");
		WebElement searchButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		searchButton.click();
		
		WebElement HTCPhoneAddToCartButton = driver.findElement(By.xpath(
				"(//button[@type='button' and @class='button-2 product-box-add-to-cart-button'])[1]"));
		Boolean actual = HTCPhoneAddToCartButton.isDisplayed();
		Assert.assertTrue(actual);

	}

	@Test(priority = 3)
	public void verifyNavigateToProductDisplayPage_TC003() {
		
		WebElement searchStore = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		searchStore.sendKeys("phone");
		WebElement searchButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		searchButton.click();
		
		String expected = "https://demo.nopcommerce.com/search?q=phone";
		String actual = driver.getCurrentUrl();
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 4)
	public void verifyAddProductToCart_TC004()  {
		
		WebElement searchStore = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		searchStore.sendKeys("phone");
		WebElement searchButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
		searchButton.click();
		WebElement HTCPhoneAddToCartButton = driver.findElement(By.xpath(
				"(//button[@type='button' and @class='button-2 product-box-add-to-cart-button'])[1]"));
		HTCPhoneAddToCartButton.click();
		WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
		shoppingCart.click();

		WebElement addedProduct = driver
				.findElement(By.xpath("//table[@class='cart']"));
		Boolean actualResult = addedProduct.isDisplayed();
		Assert.assertTrue(actualResult);
	}

	@BeforeMethod
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(ops);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
