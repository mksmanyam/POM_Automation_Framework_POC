package testCases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.LoginPage;
import pageObjects.Order4506Page;
import pageObjects.SearchOrder;
import utils.ConfigUtil;

public class Order4506Tests extends BaseTest {

	@Test(priority = 1)
	public void validOrderCreation() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigUtil.getProperty("username"), ConfigUtil.getProperty("password"));
		driver.get(ConfigUtil.getProperty("orderurl"));
		Order4506Page orderPage = new Order4506Page(driver);
		orderPage.createOrder("7440408877", "John", "Doe", "01/01/1980", "123456712");
		Thread.sleep(4000);
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();// Switch to the alert pop-up
		System.out.println("This is the alertMessage:" + alertMessage);
		alert.dismiss();
		// Regular expression to match the order ID (assuming the order ID is a 6-digit
		// number)
		String regex = "\\d{8}"; // This matches a 6-digit number
		String orderId = null;
		// Compile the regular expression and find the match
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(alertMessage);
		if (matcher.find()) {
			orderId = matcher.group();
			System.out.println("Order ID: " + orderId); // Print the order ID
		} else {
			System.out.println("Order ID not found!");
		}
		driver.get(ConfigUtil.getProperty("searchOrderurl"));
		SearchOrder searchorder = new SearchOrder(driver);
		searchorder.enterOrderId(orderId);
		searchorder.clickSearchButton();
		searchorder.isOrderIdFound(orderId);
		searchorder.clickOnDetails();
		Thread.sleep(3000);
		String vendorOrderId = searchorder.getvendorOrderIDtext();
		Assert.assertEquals(vendorOrderId, orderId);
	}

	@Test(priority = 2, dataProvider = "invalidData")
	public void invalidDataValidation(String phone, String fname, String lname, String dob, String ssn,
			String validMsg) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(ConfigUtil.getProperty("username"), ConfigUtil.getProperty("password"));
		driver.get(ConfigUtil.getProperty("orderurl"));
		Order4506Page orderPage = new Order4506Page(driver);
		orderPage.createOrder(phone, fname, lname, dob, ssn);

		// Validate error message for invalid phone number
		String errorMessage = orderPage.validationTextforPhn();
		Assert.assertTrue(errorMessage.contains(validMsg));
	}


	@DataProvider(name = "invalidData")
	public Object[][] loginData() {
		return new Object[][] { 
				{ "123", "John", "Doe", "01/01/1980", "233232434", "Required field missing: Phone" },
				{ "1234567890", "John", "Doe", "01/01/1980", "", "Required field missing: Borrower 1 SSN" },
				{ "1234567890", "John", "Doe", "01/01/1980", "213242-22", "Invalid format: Borrower 1 SSN must contain numbers only." },
				{ "1234567890", "John", "Doe", "1980-01-01", "123456789", "Invalid date format: Borrower 1 DOB must be in format mm/dd/yyyy" }

		};
	}
}
