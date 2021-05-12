package com.identifybikes;

import java.io.IOException;
import java.util.Iterator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.base.BaseUI;
import com.utils.ExcelData;

public class PopularModelsPage extends BaseUI {

	public String[] chennaiModels = new String[30];
	public String[] result = new String[30];
	public int count = 0;

	// Method to extract all the 'Popular Car Models' in the Chennai

	public void getPopularModels() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");

		List<WebElement> listOfModels = driver.findElements(By.xpath(prop.getProperty("PopularModels_Xpath")));
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		System.out.println("\nPart 2:");
		System.out.println("\n");
		System.out.println("Number of popular models displayed are: " + listOfModels.size());
		System.out.println("\n");
		Iterator<WebElement> i = listOfModels.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			System.out.println(row.getText());
			chennaiModels[count] = row.getText();
			result[count] = "PASS";
			count++;
		}

		try {
			logger.log(Status.INFO, "Excel reports are going to be generated for Userd Cars in Chennai");
			ExcelData.WriteExcelData(
					System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelFiles\\Zigwheels.xlsx",
					chennaiModels, result);
			reportPass("The Excel report is generated for the Used Cars in Chennai");
		} catch (IOException e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	// Method to verify place

	public void verifyPlace() {
		String validLoc;
		String validLocResult;
		String UsedCarsPlace = driver.findElement(By.id("usedcarttlID")).getText();
		if (UsedCarsPlace.contains("Chennai")) {
			System.out.println("\n");
			validLoc = "The used car details are retreived for Chennai";

			validLocResult = "PASS";
			System.out.println(validLoc + "\t" + validLocResult);
			System.out.println("\n");
			System.out.println(
					"\n-----------------------------------------------------------------------------------------------------------------------------------");

		} else {
			System.out.println("\n");
			validLoc = "The Used cars details are not retreived for Chennai";
			validLocResult = "FAIL";
			System.out.println(validLoc + "\t" + validLocResult);
			System.out.println("\n");
			System.out.println(
					"\n-----------------------------------------------------------------------------------------------------------------------------------");
			assertFalse(true);
		}
	}

}
