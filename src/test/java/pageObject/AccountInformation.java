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

public class AccountInformation extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']/span")
    private WebElement accountInformationTitle;

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//select[@id='gender']")
    private WebElement genderList;

    @FindBy(xpath = "//select[@id='gender']/option[@selected='selected']")
    private WebElement selectedGender;

    @FindBy(xpath = "//input[@id='dob']")
    private WebElement dateOfBirth;

    @FindBy(xpath = "//button[contains(@class,'ui-datepicker-close')]")
    private WebElement datepickerCloseButton;

    @FindBy(xpath = "//input[@id='change-email']")
    private WebElement changeEmailCheckBox;

    @FindBy(xpath = "//input[@id='change-password']")
    private WebElement changePasswordCheckBox;

    @FindBy(xpath = "//fieldset[@class='fieldset password']")
    private WebElement changePasswordSection;

    @FindBy(xpath = "//button[@title='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='current-password']")
    private WebElement currentPasswordInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement newPasswordInput;

    @FindBy(xpath = "//input[@id='password-confirmation']")
    private WebElement confirmationPasswordInput;

    @FindBy(xpath = "//div[@id='firstname-error']")
    private WebElement firstnameErrorMessage;

    @FindBy(xpath = "//div[@id='lastname-error']")
    private WebElement lastnameErrorMessage;

    @FindBy(xpath = "//div[@id='dob-error']")
    private WebElement dateOfBirthErrorMessage;

    @FindBy(xpath = "//div[@id='email-error']")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//div[@id='current-password-error']")
    private WebElement currentPasswordErrorMessage;

    @FindBy(xpath = "//div[@id='password-error']")
    private WebElement newPasswordErrorMessage;

    @FindBy(xpath = "//div[@id='password-confirmation-error']")
    private WebElement passwordConfirmationErrorMessage;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement accountInformationMessage;

    @FindBy(xpath = "//a[@class='action back']")
    private WebElement goBackButton;

    public AccountInformation(WebDriver driver) {
        super(driver);
    }

    @Step("Enter first name {name}")
    public AccountInformation enterFirstName(String name) {
        sendText(firstNameInput, name);
        return this;
    }

    @Step("Enter last name {name}")
    public AccountInformation enterLastName(String name) {
        sendText(lastNameInput, name);
        return this;
    }

    @Step("Get current gender value")
    public String getCurrentGenderValue() {
        if (isElementPresent("//select[@id='gender']/option[@selected='selected']", driver)) {
            return selectedGender.getText();
        } else return "";
    }

    @Step("Select Gender {gender}")
    public AccountInformation selectGender(String gender) {
        scrollIntoView(genderList, driver);
        Select genderSelect = new Select(driver.findElement(By.xpath("//select[@id='gender']")));
        genderSelect.selectByVisibleText(gender);
        return this;
    }

    @Step("Verify gender value {gender}")
    public AccountInformation verifyGenderValue(String gender) {
        wait.until(ExpectedConditions.visibilityOf(selectedGender));
        Assert.assertEquals(selectedGender.getText(), gender);
        return this;
    }

    @Step("Check Change Email checkbox")
    public AccountInformation checkChangeEmailCheckbox() {
        if (!changeEmailCheckBox.isSelected()) {
            click(changeEmailCheckBox);
        }
        return this;
    }

    @Step("Uncheck Change Email checkbox")
    public AccountInformation uncheckChangeEmailCheckbox() {
        if (changeEmailCheckBox.isSelected()) {
            click(changeEmailCheckBox);
        }
        return this;
    }

    @Step("Verify Change Email section is not displayed when <<Change Email>> checkbox is disabled")
    public AccountInformation verifyChangeEmailSectionNotVisibleIfCheckboxDisabled() {
        uncheckChangePasswordCheckbox();
        uncheckChangeEmailCheckbox();
        Assert.assertFalse(currentPasswordInput.isDisplayed());
        Assert.assertFalse(emailInput.isDisplayed());
        return this;
    }

    @Step("Check Change Password checkbox")
    public AccountInformation checkChangePasswordCheckbox() {
        if (!changePasswordCheckBox.isSelected()) {
            click(changePasswordCheckBox);
        }
        return this;
    }

    @Step("Uncheck Change Password checkbox")
    public AccountInformation uncheckChangePasswordCheckbox() {
        if (changePasswordCheckBox.isSelected()) {
            click(changePasswordCheckBox);
        }
        return this;
    }

    @Step("Verify Change Password section is not displayed when <<Change Password>> checkbox is disabled")
    public AccountInformation verifyChangePasswordSectionNotVisibleIfCheckboxDisabled() {
        uncheckChangePasswordCheckbox();
        uncheckChangeEmailCheckbox();
        Assert.assertFalse(currentPasswordInput.isDisplayed());
        Assert.assertFalse(newPasswordInput.isDisplayed());
        Assert.assertFalse(confirmationPasswordInput.isDisplayed());
        return this;
    }


    @Step("Enter date of birth {dob}")
    public AccountInformation enterDateOfBirth(String dob) {
        sendText(dateOfBirth, dob);
        return this;
    }

    @Step("Get current Date Of Birth")
    public String getCurrentDateOfBirth() {
        wait.until(ExpectedConditions.visibilityOf(dateOfBirth));
        return dateOfBirth.getAttribute("value");
    }

    @Step("Verify date of birth {dob} value")
    public AccountInformation verifyDateOfBirth(String dob) {
        wait.until(ExpectedConditions.visibilityOf(dateOfBirth));
        Assert.assertEquals(dateOfBirth.getAttribute("value"), dob);
        return this;
    }

    @Step("Click on save button")
    public AccountInformation clickSaveButton() {
        click(saveButton);
        return this;
    }

    @Step("Clear first name")
    public AccountInformation clearFirstName() {
        clear(firstNameInput);
        return this;
    }

    @Step("Clear last name")
    public AccountInformation clearLastName() {
        clear(lastNameInput);
        return this;
    }

    @Step("Enter email {email}")
    public AccountInformation enterEmail(String email) {
        sendText(emailInput, email);
        return this;
    }

    @Step("Clear email")
    public AccountInformation clearEmail() {
        clear(emailInput);
        return this;
    }

    @Step("Clear Date of Birth")
    public AccountInformation clearDateOfBirth() {
        clear(dateOfBirth);
        return this;
    }

    @Step("Enter current password {password}")
    public AccountInformation enterCurrentPassword(String password) {
        sendText(currentPasswordInput, password);
        return this;
    }

    @Step("Enter new password {password}")
    public AccountInformation enterNewPassword(String password) {
        sendText(newPasswordInput, password);
        return this;
    }

    @Step("Enter new password confirmation {password}")
    public AccountInformation enterConfirmationPasswordInput(String password) {
        sendText(confirmationPasswordInput, password);
        return this;
    }

    @Step("Verify empty fields")
    public AccountInformation verifyEmptyFields() {
        scrollIntoView(firstnameErrorMessage, driver);
        Assert.assertEquals(firstnameErrorMessage.getText(), "This is a required field.");
        scrollIntoView(lastnameErrorMessage, driver);
        Assert.assertEquals(lastnameErrorMessage.getText(), "This is a required field.");
        scrollIntoView(dateOfBirthErrorMessage, driver);
        Assert.assertEquals(dateOfBirthErrorMessage.getText(), "This is a required field.");
        scrollIntoView(emailErrorMessage, driver);
        Assert.assertEquals(emailErrorMessage.getText(), "This is a required field.");
        scrollIntoView(currentPasswordErrorMessage, driver);
        Assert.assertEquals(currentPasswordErrorMessage.getText(), "This is a required field.");
        scrollIntoView(newPasswordErrorMessage, driver);
        Assert.assertEquals(newPasswordErrorMessage.getText(), "This is a required field.");
        scrollIntoView(passwordConfirmationErrorMessage, driver);
        Assert.assertEquals(passwordConfirmationErrorMessage.getText(), "This is a required field.");
        return this;
    }

    @Step("Verify new password field {message}")
    public AccountInformation verifyNewPassword(String message) {
        scrollIntoView(newPasswordErrorMessage, driver);
        Assert.assertEquals(newPasswordErrorMessage.getText(), message);
        return this;
    }

    @Step("Verify password confirmation field {message}")
    public AccountInformation verifyPasswordConfirmation(String message) {
        scrollIntoView(passwordConfirmationErrorMessage, driver);
        Assert.assertEquals(passwordConfirmationErrorMessage.getText(), message);
        return this;
    }

    @Step("Verify current password field {message}")
    public AccountInformation verifyCurrentPassword(String message) {
        wait.until(ExpectedConditions.visibilityOf(accountInformationMessage));
        scrollIntoView(accountInformationMessage, driver);
        Assert.assertEquals(accountInformationMessage.getText(), message);
        return this;
    }

    @Step("Verify email field {message}")
    public AccountInformation verifyEmail(String message) {
        scrollIntoView(emailErrorMessage, driver);
        Assert.assertEquals(emailErrorMessage.getText(), message);
        return this;
    }

    @Step("Verify Account information page")
    public AccountInformation verifyAccountInformationPage() {
        scrollIntoView(accountInformationTitle, driver);
        verifyTitle(accountInformationTitle, "Edit Account Information");
        Assert.assertTrue(isElementPresent("//input[@id='firstname']", driver), "First name field should be displayed");
        Assert.assertTrue(isElementPresent("//input[@id='lastname']", driver), "Last name field should be displayed");
        Assert.assertTrue(isElementPresent("//select[@id='gender']", driver), "Gender field should be displayed");
        Assert.assertTrue(isElementPresent("//input[@id='dob']", driver), "Date of Birth field should be displayed");
        return this;
    }

    @Step("Verify Change password section")
    public AccountInformation verifyChangePasswordSection() {
        wait.until(ExpectedConditions.visibilityOf(currentPasswordInput));
        wait.until(ExpectedConditions.visibilityOf(newPasswordInput));
        wait.until(ExpectedConditions.visibilityOf(confirmationPasswordInput));
        Assert.assertTrue(changePasswordCheckBox.isSelected(), "Password checkbox should be selected");
        return this;
    }

    @Step("Is on Account Information Page")
    public boolean isOnEditAccountInformation() {
        return isElementPresent("//span[contains(text(),'Edit Account Information')]", driver);
    }

    @Step("Click on date picker <<Close>> button")
    public AccountInformation clickDatePickerCloseButton() {
        if (isElementPresent("//button[contains(@class,'ui-datepicker-close')]", driver)) {
            click(datepickerCloseButton);
        }
        return this;
    }

    @Step("Click on <<Go back>> button")
    public AccountInformation clickGoBackButton() {
        click(goBackButton);
        return this;
    }

}
