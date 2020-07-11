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

public class AddressBook extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement addressBookTitle;

    @FindBy(xpath = "//div[@class='block block-addresses-default']")
    private WebElement defaultAddressesBlock;

    @FindBy(xpath = "//button[@title='Add New Address']")
    private WebElement addNewAddressButton;

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

    @FindBy(xpath = "//button[@title='Save Address']")
    private WebElement saveAddressButton;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement addressBookMessage;

    @FindBy(xpath = "//div[@id='telephone-error']")
    private WebElement phoneNumberMessage;

    @FindBy(xpath = "//div[@id='street_1-error']")
    private WebElement addressErrorMessage;

    @FindBy(xpath = "//div[@id='city-error']")
    private WebElement cityErrorMessage;

    @FindBy(xpath = "//button[contains(@class,'action-accept')]")
    private WebElement okButton;

    @FindBy(xpath = "//div[@id='region_id-error']")
    private WebElement stateErrorMessage;

    @FindBy(xpath = "//div[@id='zip-error']")
    private WebElement zipErrorMessage;

    @FindBy(xpath = "//div[@data-ui-id='result-message-success']")
    private WebElement paymentAccountDeletedSuccessfullyMessage;

    @FindBy(xpath = "//input[@id='primary_billing']")
    private WebElement useAsMyDefaultBillingAddressCheckBox;

    @FindBy(xpath = "//input[@id='primary_shipping']")
    private WebElement useAsMyDefaultShippingAddressCheckBox;

    @FindBy(xpath = "//span[contains(text(),'Delete Address')]")
    private List<WebElement> deleteAddressList;

    @FindBy(xpath = "//span[contains(text(),'Edit Address')]")
    private List<WebElement> editAddressList;

    @FindBy(xpath = "//strong[contains(text(),'Additional Address Entries')]")
    private WebElement additionalAddressEntriesHeader;

    @FindBy(xpath = "//ol[@class='items addresses']//address")
    private WebElement addressContact;

    @FindBy(xpath = "//div[contains(@class,'box-address-billing')]//address")
    private WebElement billingAddressContact;

    @FindBy(xpath = "//div[contains(@class,'box-address-shipping')]//address")
    private WebElement shippingAddressContact;

    @FindBy(xpath = "//span[contains(text(),'Change Billing Address')]")
    private WebElement changeBillingAddressSpan;

    @FindBy(xpath = "//span[contains(text(),'Change Shipping Address')]")
    private WebElement changeShippingAddressSpan;

    public AddressBook(WebDriver driver) {
        super(driver);
    }

    @Step("Click on <<Add New Address>> button")
    public AddressBook clickAddNewAddressButton() {
        if (isElementPresent("//button[@title='Add New Address']", driver)) { //if no stored addresses app opens Add New Address page
            click(addNewAddressButton);
        }
        return this;
    }


     @Step("Click on <<Delete Address>> span")
    public AddressBook clickDeleteAddress() {
        if (isAddressToDeletePresent()) {
            click(deleteAddressList.get(0));
        }
        return this;
    }

    @Step("Check address to delet is present")
    public boolean isAddressToDeletePresent() {
        return isElementPresent("//span[contains(text(),'Delete Address')]", driver);
    }

    @Step("Delete all addresses")
    public AddressBook deleteAllAddresses() {
        while (isAddressToDeletePresent()) {
            List<WebElement> deleteAddressList = driver.findElements(By.xpath("//span[contains(text(),'Delete Address')]"));
            click(deleteAddressList.get(0));
            clickOkButton();
        }
        return this;
    }

    @Step("Click on <<Change Billing Address>> span")
    public AddressBook clickChangeBillingAddress() {
        click(changeBillingAddressSpan);
        return this;
    }

    @Step("Click on <<Change Shipping Address>> span")
    public AddressBook clickChangeShippingAddress() {
        click(changeShippingAddressSpan);
        return this;
    }

    @Step("Click on <<Edit Address>> span")
    public AddressBook clickEditAddress() {
        click(editAddressList.get(0));
        return this;
    }

    @Step("Click on <<OK>> button")
    public AddressBook clickOkButton() {
        click(okButton);
        return this;
    }

    @Step("Enter first name {name}")
    public AddressBook enterFirstName(String name) {
        sendText(firstNameInput, name);
        return this;
    }

    @Step("Clear first name")
    public AddressBook clearFirstName() {
        clear(firstNameInput);
        return this;
    }

    @Step("Enter last name {name}")
    public AddressBook enterLastName(String name) {
        sendText(lastNameInput, name);
        return this;
    }

    @Step("Clear last name")
    public AddressBook clearLastName() {
        clear(lastNameInput);
        return this;
    }

    @Step("Enter phone number {telephone}")
    public AddressBook enterTelephone(String telephone) {
        sendText(telephoneInput, telephone);
        return this;
    }

    @Step("Clear phone number")
    public AddressBook clearTelephone() {
        clear(telephoneInput);
        return this;
    }

    @Step("Enter street address{address}")
    public AddressBook enterStreetAddress1(String address) {
        sendText(street_1Input, address);
        return this;
    }

    @Step("Clear street address")
    public AddressBook clearStreetAddress1() {
        clear(street_1Input);
        return this;
    }

    @Step("Enter street address {address}")
    public AddressBook enterStreetAddress2(String address) {
        sendText(street_2Input, address);
        return this;
    }

   @Step("Enter city {city}")
    public AddressBook enterCity(String city) {
        sendText(cityInput, city);
        return this;
    }

    @Step("Enter city")
    public AddressBook clearCity() {
        clear(cityInput);
        return this;
    }

    @Step("Enter Zip/Postal code {code}")
    public AddressBook enterZipPostalCode(String code) {
        sendText(postalCodeInput, code);
        return this;
    }

    @Step("Click on <<Save Address>> button")
    public AddressBook clickSaveAddressButton() {
        click(saveAddressButton);
        return this;
    }

    @Step("Click on <<Use as my default billing address>> checkbox")
    public AddressBook clickUseAsMyDefaultBillingAddressCheckbox() {
        click(useAsMyDefaultBillingAddressCheckBox);
        return this;
    }

    @Step("Click on <<Use as my default shipping address>> checkbox")
    public AddressBook clickUseAsMyDefaultShippingAddressCheckbox() {
        click(useAsMyDefaultShippingAddressCheckBox);
        return this;
    }

    @Step("Set State/Province {stateWebElement}")
    public AddressBook setStateProvince(String stateWebElement) {
        wait.until(ExpectedConditions.elementToBeClickable(stateProvinceSelect));
        scrollIntoView(stateProvinceSelect, driver);
        Select stateSelect = new Select(driver.findElement(By.xpath("//select[@id='region_id']")));
        stateSelect.selectByVisibleText(stateWebElement);
        return this;
    }

    @Step("Verify message on My Stored Payment Accounts {message}")
    public AddressBook verifyAddressBookMessage(String message) {
        wait.until(ExpectedConditions.visibilityOf(addressBookMessage));
        Assert.assertEquals(addressBookMessage.getText(), message);
        return this;
    }

    @Step("Verify empty fields")
    public AddressBook verifyEmptyFields() {
        wait.until(ExpectedConditions.visibilityOf(addressErrorMessage));
        scrollIntoView(addressErrorMessage, driver);
        Assert.assertEquals(addressErrorMessage.getText(), "This is a required field.");
        scrollIntoView(cityErrorMessage, driver);
        Assert.assertEquals(cityErrorMessage.getText(), "This is a required field.");
        scrollIntoView(stateErrorMessage, driver);
        Assert.assertEquals(stateErrorMessage.getText(), "Please select an option.");
        scrollIntoView(zipErrorMessage, driver);
        Assert.assertEquals(zipErrorMessage.getText(), "This is a required field.");
        return this;
    }

    @Step("Verify if fields are changed {firstName} {lastName} {address} {city} {telephone}")
    public AddressBook verifyChangedFields(String firstName, String lastName, String address, String city, String telephone) {
        wait.until(ExpectedConditions.visibilityOf(addressContact));
        scrollIntoView(addressContact, driver);
        Assert.assertTrue(addressContact.getText().contains(firstName), "Contact address should contain " + firstName);
        Assert.assertTrue(addressContact.getText().contains(lastName), "Contact address should contain " + lastName);
        Assert.assertTrue(addressContact.getText().contains(city), "Contact address should contain " + city);
        Assert.assertTrue(addressContact.getText().contains(address), "Contact address should contain " + address);
        Assert.assertTrue(addressContact.getText().contains(telephone), "Contact address should contain " + telephone);
        return this;
    }

    @Step("Verify saved billing address contact {firstName} {lastName} {address} {city} {telephone}")
    public AddressBook verifySavedBillingAddressContact(String firstName, String lastName, String address, String city, String telephone) {
        wait.until(ExpectedConditions.visibilityOf(billingAddressContact));
        scrollIntoView(billingAddressContact, driver);
        Assert.assertTrue(billingAddressContact.getText().contains(firstName), "Billing address should contain " + firstName);
        Assert.assertTrue(billingAddressContact.getText().contains(lastName), "Billing address should contain " + lastName);
        Assert.assertTrue(billingAddressContact.getText().contains(city), "Billing address should contain " + city);
        Assert.assertTrue(billingAddressContact.getText().contains(address), "Billing address should contain " + address);
        Assert.assertTrue(billingAddressContact.getText().contains(telephone), "Billing address should contain " + telephone);
        return this;
    }

    @Step("Verify saved shipping address contact {firstName} {lastName} {address} {city} {telephone}")
    public AddressBook verifySavedShippingAddressContact(String firstName, String lastName, String address, String city, String telephone) {
        wait.until(ExpectedConditions.visibilityOf(shippingAddressContact));
        scrollIntoView(shippingAddressContact, driver);
        Assert.assertTrue(shippingAddressContact.getText().contains(firstName), "Shipping address should contain " + firstName);
        Assert.assertTrue(shippingAddressContact.getText().contains(lastName), "Shipping address should contain " + lastName);
        Assert.assertTrue(shippingAddressContact.getText().contains(city), "Shipping address should contain " + city);
        Assert.assertTrue(shippingAddressContact.getText().contains(address), "Shipping address should contain " + address);
        Assert.assertTrue(shippingAddressContact.getText().contains(telephone), "Shipping address should contain " + telephone);
        return this;
    }

    @Step("Verify Address Book page")
    public AddressBook verifyAddressBookPage() {
        if(!addressBookTitle.getText().equalsIgnoreCase("Add new address")) {
            wait.until(ExpectedConditions.visibilityOf(addressBookTitle));
            verifyTitle(addressBookTitle, "Address Book");
            Assert.assertTrue(isElementPresent("//div[@class='block block-addresses-default']", driver), "Default addresses block should be displayed");
            Assert.assertTrue(isElementPresent("//strong[contains(text(),'Additional Address Entries')]", driver), "Additional Address Entries block should be displayed");
        }
        return this;
    }

    @Step("Verify Edit Address page")
    public AddressBook verifyEditAddressPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@class='fieldset']//span[text()='Contact Information']")));
        Assert.assertTrue(isElementPresent("//fieldset[@class='fieldset']//span[text()='Address']", driver), "Address section should be displayed");
        return this;
    }

    @Step("Check if address book is empty")
    public boolean isAddressBookEmpty() {
        wait.until(ExpectedConditions.visibilityOf(addressBookTitle));
        String titleText = addressBookTitle.getText().toLowerCase();
        return (titleText.equals("add new address"));
    }
}
