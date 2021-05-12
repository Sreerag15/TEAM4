package com.identifybikes;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.aventstack.extentreports.Status;
import com.base.BaseUI;

public class SelectChennai extends BaseUI {

	// method to click on the option - 'Chennai'

	public void selectChennai() {
		try {
			logger.log(Status.INFO, "The Curser is going to hover over 'Used Cars' option");
			Actions actions = new Actions(driver);
			WebElement uCars = driver.findElement(By.xpath(prop.getProperty("Usedcar_Xpath")));
			actions.moveToElement(uCars).build().perform();
			reportPass("Curser is moved to 'User Cars' top menu");
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			WebElement ch = driver.findElement(By.xpath(prop.getProperty("Chennai_Xpath")));
			logger.log(Status.INFO, "'Chennai' option is going to be clicked");
			ch.click();
			reportPass("'Chennai' option is clicked");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

}
