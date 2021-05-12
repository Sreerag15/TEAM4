package com.identifybikes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.base.BaseUI;

public class LaunchPage extends BaseUI {

	// To click 'New Bikes' and 'Upcoming Bikes'

	public void clickUpcomingBikes() {
		try {
			Actions actions = new Actions(driver);
			logger.log(Status.INFO, "Mouse is moving towards the 'New Bikes'");
			WebElement nBikes = driver.findElement(By.xpath(prop.getProperty("NewBikes_Xpath")));
			actions.moveToElement(nBikes).perform();
			reportPass("Mouse moved to'New Bikes'");
			logger.log(Status.INFO, "'Upcoming Bikes' option is going to be clicked");
			WebElement uBikes = driver.findElement(By.xpath(prop.getProperty("UpcomingBikes_Xpath")));
			uBikes.click();
			reportPass("'Upcoming Bikes' has been clicked");
			wait(5);
		} catch (Exception e) {
			// TODO: handle exception
			reportFail(e.getMessage());
		}
	}

}
