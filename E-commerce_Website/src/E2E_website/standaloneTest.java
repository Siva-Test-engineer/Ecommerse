package E2E_website;

import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standaloneTest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("mathigk5@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Verizon@#$5");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		String product_name = "ZARA COAT 3";
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(product_name)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		List<WebElement> cart_products = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
		boolean match = cart_products.stream()
				.anyMatch(cart_product -> cart_product.getText().equalsIgnoreCase(product_name));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		Actions a = new Actions(driver);
		WebElement searchElement = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		a.sendKeys(searchElement, "india").build().perform();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
		driver.findElement(By.xpath("//*[text()='Place Order ']")).click();
		String Actualresult = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertTrue(Actualresult.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
	}
}