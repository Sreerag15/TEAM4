package com.identifybikes;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import com.base.BaseUI;
import com.utils.ExcelData;

public class HondaDetailsPages extends BaseUI {
	int count = 0;

	public String[] bNames = new String[30];
	public String[] bPrice = new String[30];
	public String[] bLaunch = new String[30];
	public String[] result = new String[30];
	int count1 = 0;

	// To click view more button

	public void clickViewMore(int x, int y) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(" + x + "," + y + ");");

		} catch (Exception e) {
			e.printStackTrace();
		}
		wait(5);
		driver.findElement(By.xpath(prop.getProperty("ViewMore_Xpath"))).click();
	}

	// To click on the 'Upcoming Bikes'

	public void displayUpcomingBikesInIndia() {
		List<WebElement> bikeNames = driver.findElements(By.xpath(prop.getProperty("BikeNames_Xpath")));
		List<WebElement> bikePrices = driver.findElements(By.xpath(prop.getProperty("BikePrices_Xpath")));
		List<WebElement> bikeLaunch = driver.findElements(By.xpath(prop.getProperty("BikeLaunch_Xpath")));
		System.out.println("\nPART 1:");
		System.out.println("\n");
		System.out.println("\nThe Upcoming Honda bikes details less than 4 lakhs are : \n");
		count = bikeNames.size();
		String priceTxt;

		try {
			for (int i = 0; i < count; i++) {
				priceTxt = bikePrices.get(i).getText();
				float price = Float.parseFloat(priceTxt.replaceAll("Rs. ", "").replaceAll(" Lakh", ""));
				if (price < 4) {
					System.out.println(bikeNames.get(i).getText() + "\t" + bikePrices.get(i).getText() + "\t"
							+ bikeLaunch.get(i).getText());

					/*********************************************************************************
					 * Below is code is for write bikes details in excel sheet
					 **********************************************************************************/

					bNames[count1] = bikeNames.get(i).getText();
					bPrice[count1] = bikePrices.get(i).getText();
					bLaunch[count1] = bikeLaunch.get(i).getText();
					result[count1] = "PASS";
					count1++;

				}

			}

			logger.log(Status.INFO, "Excel reports are going to be generated for Upcoming bikes in India");
			ExcelData.WriteExcelData(
					System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelFiles\\Zigwheels.xlsx", bNames,
					bPrice, bLaunch, result);
			reportPass("The Excel report is generated for the Upcoming bikes in India");

		} catch (Exception e) {
			e.getMessage();
			reportFail(e.getMessage());
		}

	}

	// Method use to verify the title og page

	public void verifyPage() {
		verifyPageTitle("title");
	}

}