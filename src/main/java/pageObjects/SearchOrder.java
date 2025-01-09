package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchOrder {

    WebDriver driver;

    // Locators
    By orderIdInputField = By.id("_ctl0_MPContent_txtTransactionID");  // Locator for the order ID input field
    By searchButton = By.id("_ctl0_MPContent_imgSearch");  // Locator for the search button
    By searchResultTable = By.xpath("_ctl0_MPContent_pnlResullts");  // Locator for the search result table
    By detailsLink = By.id("_ctl0_MPContent_dgResults__ctl3_lnkDetail");
    By vendorOrderIdtext = By.id("_ctl0_MPContent_lblOrderId");
    // Constructor
    public SearchOrder(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter the order ID
    public void enterOrderId(String orderId) {
        WebElement orderIdField = driver.findElement(orderIdInputField);
        orderIdField.sendKeys(orderId);
    }

    // Method to click the search button
    public void clickSearchButton() {
        WebElement searchBtn = driver.findElement(searchButton);
        searchBtn.click();
    }

    // Method to wait for the results table to be visible
    public void waitForResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultTable));
    }

    // Method to check if the order ID is found in the results
    public boolean isOrderIdFound(String orderId) {
        return driver.getPageSource().contains(orderId);
    }
    
    public void clickOnDetails() {
        driver.findElement(detailsLink).click();
    }
    
    public String getvendorOrderIDtext() {
        return driver.findElement(vendorOrderIdtext).getText();
    }

}
