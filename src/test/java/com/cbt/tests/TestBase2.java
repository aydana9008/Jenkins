package test.java.com.cbt.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import test.java.com.cbt.utilities.BrowserUtils;
import test.java.com.cbt.utilities.ConfigurationReader;
import test.java.com.cbt.utilities.Driver;

public class TestBase2 {

	protected WebDriver driver;
	protected Actions actions;
	protected Select select;
	protected ExtentReports report;
	protected ExtentHtmlReporter htmlReporter;
	protected ExtentTest extentLogger;
	
	@BeforeTest
	public void setUpTest() {
		report = new ExtentReports();
		String filePath = System.getProperty("user.dir")+"/test-output/report.html";
		htmlReporter=new ExtentHtmlReporter(filePath);
		
		report.attachReporter(htmlReporter);
		report.setSystemInfo("Env", "staging");
		report.setSystemInfo("browser", ConfigurationReader.getProperty("browser"));
		report.setSystemInfo("OS", System.getProperty("os.name"));
		
		htmlReporter.config().setReportName("Automating Northwestern Bank");
	}
	
	@BeforeMethod(alwaysRun=true)
	public void invokeBrowser() {
		driver = Driver.getDriver();
		actions = new Actions(driver);
		//select = new Select("");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get(ConfigurationReader.getProperty("url"));
	}
	
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
			extentLogger.fail(result.getName());
			extentLogger.addScreenCaptureFromPath(screenshotLocation);
			extentLogger.fail(result.getThrowable());
		}else if(result.getStatus()==ITestResult.SKIP) {
			extentLogger.skip("Test case is skipped "+result.getName());
			
		}
		Driver.closeDriver();
	}
	
	@AfterTest
	public void tearDownTest() {
		report.flush();
		
	}
}
