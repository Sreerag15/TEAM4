package com.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseUI;
import com.identifybikes.HondaDetailsPages;
import com.identifybikes.LaunchPage;
import com.identifybikes.LoginPage;
import com.identifybikes.PopularModelsPage;
import com.identifybikes.SelectChennai;
import com.identifybikes.SelectManufacturerPage;

public class TestCases extends BaseUI {

	LaunchPage launchPage = new LaunchPage();
	SelectManufacturerPage selectManufacturer = new SelectManufacturerPage();
	HondaDetailsPages hondaDetails = new HondaDetailsPages();
	LoginPage login = new LoginPage();
	PopularModelsPage carModels = new PopularModelsPage();
	SelectChennai chennai = new SelectChennai();

	// method used for invoking driver

	@BeforeClass(groups = { "Smoke Test" })
	public void invokedriver() throws Exception {
		// logger = report.createTest("ZigWheels Upcoming Bikes in India - Extracting
		// Honda models");
		setupDriver();
		wait(3);
	}

	// Method to call 'clickupcomingBikes'

	@Test(groups = { "Smoke Test" })
	public void clickUpComingBikes() {

		logger = report.createTest("ZigWheels Upcoming Bikes in India - Extracting Honda models");
		launchPage.clickUpcomingBikes();
		wait(3);

	}

	// Method to call 'selectHonda'

	@Test(dependsOnMethods = "clickUpComingBikes", groups = { "Regression Test" })
	public void selectHondaBikes() {
		selectManufacturer.selectHonda();

	}

	// Method to call 'clickviewmore'

	@Test(dependsOnMethods = "selectHondaBikes", groups = { "Regression Test" })

	public void clickViewMore() {

		hondaDetails.clickViewMore(579, 1325);

	}

	// Method to call the 'displayUpcomingBikesInIndia'

	@Test(dependsOnMethods = "clickViewMore", groups = { "Regression Test" })
	public void displayUpcomingBikes() {

		hondaDetails.displayUpcomingBikesInIndia();
	}

	// Method to verifyHondaPage

	@Test(dependsOnMethods = "displayUpcomingBikes", groups = { "Smoke Test" })
	public void verifyHondaPage() {
		hondaDetails.verifyPage();

	}

	// Method to call 'selectChennai'

	@Test(dependsOnMethods = "verifyHondaPage", groups = { "Regression Test" })
	public void selectChennai() {
		logger = report.createTest("ZigWheels Used Cars in Chennai - Extracting popular models");
		// setupDriver();
		wait(5);
		chennai.selectChennai();
	}

	// Method to call the 'getPopularModels'

	@Test(dependsOnMethods = "selectChennai", groups = { "Regression Test" })
	public void getPopularModels() {
		wait(5);
		carModels.getPopularModels();
		wait(5);
	}

	// Method to call 'verifyplace'

	@Test(dependsOnMethods = "getPopularModels", groups = { "Regression Test" })
	public void verifyChennai() {
		carModels.verifyPlace();
	}

	// Method is used to call the 'clickLogin' and 'enterLoginDetails'

	@Test(dependsOnMethods = "verifyChennai", groups = { "Regression Test" })
	public void clickLogin() {
		logger = report.createTest("ZigWheels Login Test by providing invalid email");
		// setupDriver();

		login.clickLogin();

		login.enterLoginDetails();
		wait(2);
		report.flush();

	}

	// Method to close

	@AfterClass(groups = { "Smoke Test" })
	public void close() {

		driver.quit();
	}

}
