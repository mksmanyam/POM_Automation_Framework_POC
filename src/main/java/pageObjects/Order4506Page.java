package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Order4506Page {
    private WebDriver driver;

    // Web elements
    private By phoneField = By.id("_ctl0_MPContent_ucOrder4506_txtPhone");
    private By w2Checkbox = By.id("_ctl0_MPContent_ucOrder4506_chkW2B1");
    private By yearDropdown = By.id("_ctl0_MPContent_ucOrder4506_cblYears_0");
    private By firstNameField = By.id("_ctl0_MPContent_ucOrder4506_txtFirstNameB1");
    private By lastNameField = By.id("_ctl0_MPContent_ucOrder4506_txtLastNameB1");
    private By dobField = By.id("_ctl0_MPContent_ucOrder4506_txtDOBB1");
    private By ssnField = By.id("_ctl0_MPContent_ucOrder4506_txtSSNB1");
    private By submitButton = By.id("_ctl0_MPContent_ucOrder4506_btnOrder");
    private By validationMessageForPhn = By.id("_ctl0_MPContent_ucOrder4506_ValidationSummary1");

    public Order4506Page(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterPhone(String phone) {
    	
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void selectW2Form() {
        driver.findElement(w2Checkbox).click();
    }

    public void selectYear() {
        driver.findElement(yearDropdown).click();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterDOB(String dob) {
        driver.findElement(dobField).sendKeys(dob);
    }

    public void enterSSN(String ssn) {
        driver.findElement(ssnField).sendKeys(ssn);
    }

    public void submitOrder() {
        driver.findElement(submitButton).click();
    }
    
    public String validationTextforPhn() {
       return driver.findElement(validationMessageForPhn).getText();
    }
    
    public void createOrder(String phone, String firstName, String lastName, String dob, String ssn) {
        enterPhone(phone);
        selectW2Form();
        selectYear();
        enterFirstName(firstName);
        enterLastName(lastName);
        enterDOB(dob);
        enterSSN(ssn);
        submitOrder();
    }
}
