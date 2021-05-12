package com.identifybikes;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.Status;
import com.base.BaseUI;

public class SelectManufacturerPage extends BaseUI {

	// Method to select Manufacturer - 'Honda'

	public void selectHonda() {
		//wait(3);
		logger.log(Status.INFO, "This method is used to select Manufacturer 'Honda'");
		Select hBikes = new Select(driver.findElement(By.id(prop.getProperty("Manufacturer_Id"))));
		wait(3);
		hBikes.selectByVisibleText("Honda");
		reportPass("'Honda' manufacturer has been clicked");

	}

}
