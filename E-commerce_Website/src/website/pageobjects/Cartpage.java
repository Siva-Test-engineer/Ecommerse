package website.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import website.AbstractComponent.AbstractComponents;

public class Cartpage extends AbstractComponents {
	WebDriver driver;

	public Cartpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cartSection']//h3")
	List<WebElement> cart_products;
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutButton;

	public boolean verifyProductDisplay(String name) {
		return cart_products.stream().anyMatch(cart_product -> cart_product.getText().equalsIgnoreCase(name));
	}

	public void checkoutClick() {
		checkoutButton.click();
	}

}
