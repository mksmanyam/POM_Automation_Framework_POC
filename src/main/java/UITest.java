
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigUtil;

public class UITest {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize WebDriver
        WebDriver driver;
        //
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Step 1: Open the URL
            driver.get("https://staging.dataverify.com/dvweb/user/login.aspx");

            // Step 2: Input the "username" field
            WebElement username = driver.findElement(By.xpath("//input[@id='_ctl0_MPContent_txtUsername']"));
            username.sendKeys(ConfigUtil.getProperty("username"));

            // Step 3: Input the "password" field
            WebElement password = driver.findElement(By.xpath("//input[@id='_ctl0_MPContent_txtPassword']"));
            password.sendKeys(ConfigUtil.getProperty("password"));

            // Step 4: Click the "login" button
            WebElement loginButton = driver.findElement(By.id("_ctl0_MPContent_btnLogin"));
            loginButton.click();

            // Step 5: Wait for the page to load (using implicit wait)
            driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);

            // Step 6: Extract the page title and print it to the console
            String title = driver.getTitle();
            System.out.println("Page Title: " + title);

        } finally {
            // Step 7: Close the browser
            driver.quit();
        }
    }
}