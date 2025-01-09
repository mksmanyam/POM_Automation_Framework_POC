package testCases;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.LoginPage;
import utils.ConfigUtil;

public class LoginTest extends BaseTest {


   
    // Test method to perform login with valid credentials
    @Test(dataProvider = "loginData", invocationCount = 1)
    public void testLoginWithValidCredentials(String username, String password, String ExpectedUrl) throws InterruptedException {
        // Perform login using valid credentials
    	 LoginPage loginPage = new LoginPage(driver); 
        loginPage.login(username, password);

        // Verify that the user is logged in and lands on the dashboard
        String expectedUrl = ExpectedUrl;
        String actualUrl = driver.getCurrentUrl();
        boolean bool = expectedUrl.equals(actualUrl);
        Assert.assertTrue(bool);
    }
    
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {ConfigUtil.getProperty("username"), ConfigUtil.getProperty("password"), ConfigUtil.getProperty("afterLoginUrl")}
            //{ConfigUtil.getProperty("username"), "", ""}
           
        };
    }


}