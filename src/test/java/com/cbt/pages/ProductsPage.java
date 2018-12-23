package test.java.com.cbt.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.java.com.cbt.utilities.Driver;

public class ProductsPage {
	public ProductsPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//table[@class='ProductsTable']/tbody/tr/td[1]")
	public List<WebElement> productNames;
	
	@FindBy(xpath="//table[@class='ProductsTable']/tbody/tr")
	public List<WebElement> productsRows;
	
}