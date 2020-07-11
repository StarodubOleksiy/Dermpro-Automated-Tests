package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class MyStoredPaymentAccounts extends PageObjectInitializer {

    @FindBy(xpath = "//select[@id='bluepay_payment_stored_acct']")
    private WebElement addNewStoredPaymentAccount;

    @FindBy(xpath = "//input[@id='CC_NUM']")
    private WebElement creditCardNumberInput;

    @FindBy(xpath = "//select[@id='CC_EXPIRES_MONTH']")
    private WebElement monthSelect;

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='telephone']")
    private WebElement telephoneInput;

    @FindBy(xpath = "//input[@id='street_1']")
    private WebElement street_1Input;

    @FindBy(xpath = "//input[@id='street_2']")
    private WebElement street_2Input;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityInput;

    @FindBy(xpath = "//select[@id='region_id']")
    private WebElement stateProvinceSelect;

    @FindBy(xpath = "//input[@id='zip']")
    private WebElement postalCodeInput;

    @FindBy(xpath = "//button[@id='submitBtn']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement storedPaymentAccountsMessage;

    @FindBy(xpath = "//div[@id='CC_NUM-error']")
    private WebElement creditCardNumberErrorMessage;

    @FindBy(xpath = "//div[@id='CC_EXPIRES_MONTH-error']")
    private WebElement monthErrorMessage;

    @FindBy(xpath = "//div[@id='CC_EXPIRES_YEAR-error']")
    private WebElement yearErrorMessage;

    @FindBy(xpath = "//div[@id='telephone-error']")
    private WebElement phoneNumberMessage;

    @FindBy(xpath = "//div[@id='street_1-error']")
    private WebElement addressErrorMessage;

    @FindBy(xpath = "//div[@id='city-error']")
    private WebElement cityErrorMessage;

    @FindBy(xpath = "//button[@id='deleteBtn']")
    private WebElement deleteButton;

    @FindBy(xpath = "//button[contains(@class,'action-accept')]")
    private WebElement okButton;

    @FindBy(xpath = "//div[@id='region_id-error']")
    private WebElement stateErrorMessage;

    @FindBy(xpath = "//div[@data-ui-id='result-message-success']")
    private WebElement paymentAccountDeletedSuccessfullyMessage;

    @FindBy(xpath = "//span[contains(text(),'Back')]")
    private WebElement backLink;

    public MyStoredPaymentAccounts(WebDriver driver) {
        super(driver);
    }

    @Step("Choose Stored Payment Method {fourLastDigits}")
    public MyStoredPaymentAccounts chooseStoredPaymentAccount(String fourLastDigits) {
        click(addNewStoredPaymentAccount);
        WebElement paymentMethodWebElement = driver.findElement(By.xpath(String.format("//option[contains(text(),'%s')]", fourLastDigits)));
        click(paymentMethodWebElement);
        return this;
    }

    @Step("Enter credit card number {number}")
    public MyStoredPaymentAccounts enterCreditCardNumberInput(String number) {
        sendText(creditCardNumberInput, number);
        return this;
    }

    @Step("Click on <<Delete>> button")
    public MyStoredPaymentAccounts clickDeleteButton() {
        click(deleteButton);
        return this;
    }

    @Step("Click on <<OK>> button")
    public MyStoredPaymentAccounts clickOkButton() {
        click(okButton);
        return this;
    }


    @Step("Click on <<Back>> link")
    public MyStoredPaymentAccounts clickBackLink() {
        click(backLink);
        return this;
    }


    @Step("Enter first name {name}")
    public MyStoredPaymentAccounts enterFirstName(String name) {
        sendText(firstNameInput, name);
        return this;
    }

    @Step("Enter last name {name}")
    public MyStoredPaymentAccounts enterLastName(String name) {
        sendText(lastNameInput, name);
        return this;
    }


    @Step("Enter phone number {telephone}")
    public MyStoredPaymentAccounts enterTelephone(String telephone) {
        sendText(telephoneInput, telephone);
        return this;
    }

    @Step("Enter street address {address}")
    public MyStoredPaymentAccounts enterStreetAddress1(String address) {
        sendText(street_1Input, address);
        return this;
    }

    @Step("Enter street address {address}")
    public MyStoredPaymentAccounts enterStreetAddress2(String address) {
        sendText(street_2Input, address);
        return this;
    }

     @Step("Enter city {city}")
    public MyStoredPaymentAccounts enterCity(String city) {
        sendText(cityInput, city);
        return this;
    }

    @Step("Enter Zip/Postal code {code}")
    public MyStoredPaymentAccounts enterZipPostalCode(String code) {
        sendText(postalCodeInput, code);
        return this;
    }


    @Step("Click on Submit button")
    public MyStoredPaymentAccounts clickSubmitButton() {
        click(submitButton);
        return this;
    }

    @Step("Set expiration date {month} {year}")
    public MyStoredPaymentAccounts setDate(String month, String year) {
        scrollIntoView(monthSelect, driver);
        String monthValidFormat = month.substring(0, 1).toUpperCase() + month.substring(1);
        monthSelect.click();
        WebElement monthWebElement = driver.findElement(By.xpath(String.format("//option[contains(text(),'%s')]", monthValidFormat)));
        monthWebElement.click();
        WebElement yearWebElement = driver.findElement(By.xpath(String.format("//option[contains(text(),'%s')]", year)));
        yearWebElement.click();
        return this;
    }

    @Step("Set State/Province {stateWebElement}")
    public MyStoredPaymentAccounts setStateProvince(String stateWebElement) {
        Select stateSelect = new Select(driver.findElement(By.xpath("//select[@id='region_id']")));
        stateSelect.selectByVisibleText(stateWebElement);
        return this;
    }

    @Step("Verify message on My Stored Payment Accounts {message}")
    public MyStoredPaymentAccounts verifyStoredPaymentAccountsMessage(String message) {
        wait.until(ExpectedConditions.visibilityOf(storedPaymentAccountsMessage));
        Assert.assertEquals(storedPaymentAccountsMessage.getText(), message);
        return this;
    }

    @Step("Verify Stored Payment Account deleted successfully")
    public MyStoredPaymentAccounts verifySuccessfullyDeletedPaymentAccount() {
        wait.until(ExpectedConditions.visibilityOf(paymentAccountDeletedSuccessfullyMessage));
        Assert.assertEquals(paymentAccountDeletedSuccessfullyMessage.getText(), "Payment account successfully deleted.");
        return this;
    }

    @Step("Verify empty fields")
    public MyStoredPaymentAccounts verifyEmptyFields() {
        wait.until(ExpectedConditions.visibilityOf(creditCardNumberErrorMessage));
        scrollIntoView(creditCardNumberErrorMessage, driver);
        Assert.assertEquals(creditCardNumberErrorMessage.getText(), "This is a required field.");
        scrollIntoView(monthErrorMessage, driver);
        Assert.assertEquals(monthErrorMessage.getText(), "This is a required field.");
        scrollIntoView(yearErrorMessage, driver);
        Assert.assertEquals(yearErrorMessage.getText(), "This is a required field.");
        scrollIntoView(addressErrorMessage, driver);
        Assert.assertEquals(addressErrorMessage.getText(), "This is a required field.");
        scrollIntoView(cityErrorMessage, driver);
        Assert.assertEquals(cityErrorMessage.getText(), "This is a required field.");
        scrollIntoView(stateErrorMessage, driver);
        Assert.assertEquals(stateErrorMessage.getText(), "Please select an option.");
        return this;
    }

}
