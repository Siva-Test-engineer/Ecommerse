package E2E_website;

import org.testng.Assert;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import website.pageobjects.Cartpage;
import website.pageobjects.CheckoutPage;
import website.pageobjects.ConfirmationPage;
import website.pageobjects.LandingPage;
import website.pageobjects.Productcatalog;

public class standaloneTest2 {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String product_name = "ZARA COAT 3";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		// logging in
		LandingPage Lp = new LandingPage(driver);
		Lp.loginApp("mathigk5@gmail.com", "Verizon@#$5");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// list the product
		Productcatalog productcatalog = new Productcatalog(driver);
		//List<WebElement> products = productcatalog.getProductList();
		productcatalog.addproductToCart(product_name);
		productcatalog.goTocartPage();
		// cart page product verification
		Cartpage cart = new Cartpage(driver);
		Assert.assertTrue(cart.verifyProductDisplay(product_name));
		cart.checkoutClick();
		CheckoutPage CP = new CheckoutPage(driver);
		CP.selectCountry("india");
		CP.submitOrder();
		ConfirmationPage confirmpage = new ConfirmationPage(driver);
		String Actualresult = confirmpage.verifyConfirmationMsg();
		Assert.assertTrue(Actualresult.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}
}