package website.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import website.AbstractComponent.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement searchElement;

	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement countryElement;
	@FindBy(xpath = "//*[text()='Place Order ']")
	WebElement submitElement;

	public void selectCountry(String CountryName) {
		Actions a = new Actions(driver);
		a.sendKeys(searchElement, CountryName).build().perform();
		waitforElementToApper(By.xpath("//section[@class='ta-results list-group ng-star-inserted']"));
		countryElement.click();
	}

	public void submitOrder() {
		submitElement.click();

	}

}
