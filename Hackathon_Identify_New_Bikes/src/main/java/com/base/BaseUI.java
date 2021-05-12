package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utils.DateUtils;
import com.utils.ExtentReportManager;
import org.openqa.selenium.remote.*;
public class BaseUI {

	public static WebDriver driver;
	public static Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger;

	SoftAssert softAssert = new SoftAssert();

	public BaseUI() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\ObjectRepository\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	public static WebDriver setupDriver() throws Exception {
		String browser = prop.getProperty("Browser");
		// To launch in Chrome browser
		if (browser.equalsIgnoreCase("chrome")) {
			//To launch chrome using chrome browser
			DesiredCapabilities capabilities=null;
			capabilities=DesiredCapabilities.chrome();
			driver=new RemoteWebDriver(new URL(" http://192.168.1.100:4444/wd/hub"), capabilities); 
			
		/*	// To launch in Chrome browser normally
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);// options  */
		}

		// To launch in Edge browser
		else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();
			// options.addArguments("--disable-notifications");
			driver = new EdgeDriver(options);
			
		}
		
		// To launch in Firefox browser
		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
			
			
		}
		
		else if (browser.equalsIgnoreCase("exit")) {
			System.out.println("\nExecution terminated\n");
			System.exit(0);
		} else {
			System.out.println("\nInvalid browser\n");
			System.exit(0);
		}

		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		closeAd(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
		return driver;
	}

	// To verify page title
	public void verifyPageTitle(String pageTitle) {
		try {
			String actualTitle = driver.getTitle();
			logger.log(Status.INFO, "Actual Title is : " + actualTitle);
			logger.log(Status.INFO, "Expected tile is : " + prop.getProperty(pageTitle));
			Assert.assertEquals(actualTitle, prop.getProperty(pageTitle));
			System.out.println("\n");
			System.out.println("The Title of the page is : " + actualTitle);
			System.out.println("\n");
			System.out.println(
					"\n-------------------------------------------------------------------------------------------------------------------------------------");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// to close popup
	public static void closeAd(WebDriver driver) {
		boolean eleSelected = existsElement("//*[@id='alternate-login-close']");
		if (eleSelected == true)
			driver.findElement(By.xpath("//*[@id='alternate-login-close']")).click();
	}

	public static boolean existsElement(String xpath) {

		try {
			driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// Assertion Functions

	public void assertTrue(boolean flag) {
		softAssert.assertTrue(flag);
	}

	public void assertFalse(boolean flag) {
		softAssert.assertFalse(flag);
	}

	public void assertequals(String actual, String expected) {
		softAssert.assertEquals(actual, expected);
	}

	public void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Reporting Functions

	public static void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShots();
		Assert.fail();
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	// To capture ScreenShot
	public static void takeScreenShots() {
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
		driver.manage().timeouts().implicitlyWait(50000, TimeUnit.SECONDS);
		File dest = new File(
				System.getProperty("user.dir") + "\\ScreenShot\\ScreenShots" + DateUtils.getTimeStamp() + ".png");

		try {
			FileUtils.copyFile(source, dest);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\ScreenShot\\ScreenShots" + DateUtils.getTimeStamp() + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
