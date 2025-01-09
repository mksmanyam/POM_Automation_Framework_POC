package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    int i=1;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        // Set up ChromeDriver
    	Thread.sleep(2000);
    	System.out.println("driver Started"+i);
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staging.dataverify.com/dvweb/user/login.aspx");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
    	
        if (driver != null) {
            driver.quit();
            System.out.println("driver session closed"+i++);
            Thread.sleep(2000);        }
    }
    
 // Explicit Wait method that can be used in any PageObject class
    protected WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected boolean isElementPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
