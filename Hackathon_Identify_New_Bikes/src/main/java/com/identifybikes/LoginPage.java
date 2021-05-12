package com.identifybikes;

import java.util.Set;

import org.openqa.selenium.By;
import com.aventstack.extentreports.Status;
import com.base.BaseUI;
import com.utils.ExcelData;

public class LoginPage extends BaseUI {

	// To click 'Login/SignUp' button on home page

	public void clickLogin() {
		logger.log(Status.INFO, "Mouse is moving towards the 'SignUp/Login' button and click it");
		driver.get(prop.getProperty("url"));
		driver.findElement(By.id(prop.getProperty("SignUp_Id"))).click();
		reportPass("'Signup/Login' button has been clicked");
		wait(5);

		String parent = driver.getWindowHandle();

		logger.log(Status.INFO, "Mouse is clicking on 'continue with Google'");

		driver.findElement(By.xpath(prop.getProperty("GoogleSignIn_Xpath"))).click();
		reportPass("'continue with Google' has been clicked");
		wait(8);

		Set<String> allWindows = driver.getWindowHandles();

		for (String child : allWindows) {

			if (!parent.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
			}
		}
	}

	// To enter invalid email and get error message

	public void enterLoginDetails() {

		logger.log(Status.INFO, "Invalid email is entered");
		String Data = null;

		try {
			Data = ExcelData
					.readExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelFiles\\Zigwheels.xlsx");

		} catch (Exception e) {
			e.getMessage();
		}
		driver.findElement(By.xpath(prop.getProperty("Email_Xpath"))).sendKeys(Data);
		wait(3);
		driver.findElement(By.xpath(prop.getProperty("Next_Xpath"))).click();
		reportPass("Screenshot' has taken");
		System.out.println("\nPart 3:");
		String ErrTxt = driver.findElement(By.xpath(prop.getProperty("ErrMsg_Xpath"))).getText();
		System.out.println("\n");
		System.out.println("The Error Message is : " + ErrTxt);
		takeScreenShots();
		wait(2);
	}

}
