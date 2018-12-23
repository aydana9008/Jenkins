package test.java.com.cbt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.java.com.cbt.utilities.Driver;

public class AllOrdersPage {
	public AllOrdersPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath = "//h1[.='Web Orders']")
	public WebElement webOrders;

	@FindBy(css = ".login_info")
	public WebElement welcomeMsg;

	@FindBy(xpath = "//h2[contains(.,'List of All Orders')]")
	public WebElement listOfAllOrders;

	@FindBy(linkText = "View all orders")
	public WebElement viewAllOrders;

	@FindBy(linkText = "View all products")
	public WebElement viewAllProducts;

	@FindBy(linkText = "Order")
	public WebElement orderTab;

	@FindBy(id = "ctl00_logout")
	public WebElement logoutLink;

	public void logout() {
		logoutLink.click();
	}

}