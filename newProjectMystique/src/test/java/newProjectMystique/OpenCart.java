package newProjectMystique;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenCart {

	WebDriver driver;

	@Test(priority = 1)
	public void verifyLaunchingUrlSuccessfully() {

		String expected = "https://www.amazon.in/";
		String actual = driver.getCurrentUrl();
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 2)
	public void verifySearchFunctionalityForProduct() {

		WebElement searchButton = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchButton.sendKeys("bags");
		WebElement searchArrow = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		searchArrow.click();

		WebElement bagFromSearch = driver.findElement(By.xpath(
				"//div[@class='a-section aok-relative s-image-fixed-height']//img[@class='s-image' and@data-image-index='4']"));
		Boolean actual = bagFromSearch.isDisplayed();
		Assert.assertTrue(actual);

	}

	@Test(priority = 3)
	public void verifyNavigateToProductDisplayPage() {
		WebElement searchButton = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchButton.sendKeys("bags");
		WebElement searchArrow = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		searchArrow.click();
		WebElement bagFromSearch = driver.findElement(By.xpath(
				"//div[@class='a-section aok-relative s-image-fixed-height']//img[@class='s-image' and@data-image-index='4']"));
		bagFromSearch.click();

		String expected = "https://www.amazon.in/s?k=bags&ref=nb_sb_noss";
		String actual = driver.getCurrentUrl();

		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 4)
	public void verifyAddProductToCart() {
		WebElement searchButton = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchButton.sendKeys("bags");
		WebElement searchArrow = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		searchArrow.click();

		WebElement bagFromSearch = driver.findElement(By.xpath(
				"//div[@class='a-section aok-relative s-image-fixed-height']//img[@class='s-image' and@data-image-index='4']"));
		bagFromSearch.click();

		WebElement addToCart = driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]"));
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-to-cart-button\"]"))); // explicit
		//js.executeScript("arguments[0].click;", addToCart);
		// addToCart.click();
		
		WebElement cartImageClick = driver.findElement(By.xpath("//span[@class='nav-cart-icon nav-sprite']"));
		cartImageClick.click();

		WebElement shoppingCart = driver
				.findElement(By.xpath("//div[@class='a-row sc-cart-header sc-compact-bottom']"));
		Boolean actualResult = shoppingCart.isDisplayed();
		Assert.assertTrue(actualResult);
	}

	@BeforeMethod
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(ops);
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
