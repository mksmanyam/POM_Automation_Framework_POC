package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    private WebDriver driver;
   
    
    // Web elements
    private By usernameField = By.name("_ctl0:MPContent:txtUsername");
    private By passwordField = By.id("_ctl0_MPContent_txtPassword");
    private By loginButton = By.id("_ctl0_MPContent_btnLogin");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username) throws InterruptedException {
    	Thread.sleep(2000);
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) throws InterruptedException {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
