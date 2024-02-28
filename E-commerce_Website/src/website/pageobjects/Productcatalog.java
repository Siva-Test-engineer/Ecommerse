package website.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import website.AbstractComponent.AbstractComponents;

public class Productcatalog extends AbstractComponents {
	WebDriver driver;

	public Productcatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinnerElements;

	By productsBy = By.cssSelector(".mb-3");
	By addTocartBy = By.cssSelector(".card-body button:last-of-type");
	By toastMsgBy = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitforElementToApper(productsBy);
		return products;
	}

	public WebElement getProductName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addproductToCart(String productName) {
		WebElement prod = getProductName(productName);
		prod.findElement(addTocartBy).click();
		waitforElementToApper(toastMsgBy);
		waitForElementToDisapper(spinnerElements);
	}

}
