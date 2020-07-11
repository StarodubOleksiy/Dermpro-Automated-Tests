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

public class SignUp extends PageObjectInitializer {

    @FindBy(xpath = "//option[contains(text(),'Male')]")
    private WebElement maleOption;

    @FindBy(xpath = "//select[@id='gender']")
    private WebElement genderSelect;

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='email_address']")
    private WebElement emailAddressInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='captcha_user_create']")
    private WebElement captchaUserCreateInput;

    @FindBy(xpath = "//input[@id='password-confirmation']")
    private WebElement passwordConfirmationInput;

    @FindBy(xpath = "//button[@type='submit']//span[contains(text(),'Sign Up')]")
    private WebElement signUpButton;

    @FindBy(xpath = "//button[@type='button']//span[contains(text(),'Reload captcha')]")
    private WebElement reloadCaptchaButton;

    @FindBy(xpath = "//div[@id='firstname-error']")
    private WebElement firstNameErrorMessage;

    @FindBy(xpath = "//div[@id='lastname-error']")
    private WebElement lastNameErrorErrorMessage;

    @FindBy(xpath = "//div[@id='email_address-error']")
    private WebElement emailAddressErrorMessage;

    @FindBy(xpath = "//div[@id='password-error']")
    private WebElement passwordErrorMessage;

    @FindBy(xpath = "//div[@id='dob-error']")
    private WebElement dateOfBirthErrorMessage;

    @FindBy(xpath = "//div[@id='password-confirmation-error']")
    private WebElement passwordConfirmationErrorMessage;

    @FindBy(xpath = "//div[@id='captcha_user_create-error']")
    private WebElement captchaUserCreateErrorMessage;

    @FindBy(xpath = "//span[@id='password-strength-meter-label']")
    private WebElement passwordStrengthMeterLabel;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement actionMessage;

    @FindBy(xpath = "//input[@id='dob']")
    private WebElement datePickerInput;

    @FindBy(xpath = "//button[contains(@class,'ui-datepicker-trigger')]")
    private WebElement datepickerOpenButton;


    @FindBy(xpath = "//button[contains(@class,'ui-datepicker-close')]")
    private WebElement datepickerCloseButton;

    @FindBy(xpath = "//span[@class='hello']/strong")
    private WebElement helloMessage;

    public SignUp(WebDriver driver) {
        super(driver);
    }

    @Step("Enter first name {name}")
    public SignUp enterFirstName(String name) {
        sendText(firstNameInput, name);
        return this;
    }

    @Step("Enter last name {name}")
    public SignUp enterLastName(String name) {
        sendText(lastNameInput, name);
        return this;
    }

    @Step("Enter captcha {captcha}")
    public SignUp enterUserCaptcha(String captcha) {
        sendText(captchaUserCreateInput, captcha);
        return this;
    }

    @Step("Enter email address {email}")
    public SignUp enterEmailAddress(String email) {
        sendText(emailAddressInput, email);
        return this;
    }

    @Step("Enter password {password}")
    public SignUp enterPassword(String password) {
        sendText(passwordInput, password);
        return this;
    }

    @Step("Enter password confirmation {password}")
    public SignUp enterPasswordConfirmation(String password) {
        sendText(passwordConfirmationInput, password);
        return this;
    }

    @Step("Click on <<Sign Up>> button")
    public SignUp clickSignUpButton() {
        click(signUpButton);
        return this;
    }

    @Step("Click on <<Select Date>> button")
    public SignUp clickDatePickerOpenButton() {
        click(datepickerOpenButton);
        return this;
    }

    @Step("Click on date picker <<Close>> button")
    public SignUp clickDatePickerCloseButton() {
        click(datepickerCloseButton);
        return this;
    }


    @Step("Clear date")
    public SignUp clearDate() {
        clear(datePickerInput);
        return this;
    }

    @Step("Enter date {date}")
    public SignUp enterDate(String date) {
        sendText(datePickerInput, date);
        return this;
    }

    @Step("Click on <<Reload captcha>> button")
    public SignUp clickReloadCaptchaButton() {
        click(reloadCaptchaButton);
        return this;
    }

    @Step("Click on gender select")
    public SignUp clickGenderSelect() {
        click(genderSelect);
        return this;
    }

    @Step("Select gender {gender}")
    public SignUp selectGender(String gender) {
        scrollIntoView(genderSelect, driver);
        Select genderSelect = new Select(driver.findElement(By.xpath("//select[@id='gender']")));
        genderSelect.selectByVisibleText(gender);
        return this;
    }

    @Step("Click on <<Male>> option")
    public SignUp clickMaleOption() {
        click(maleOption);
        return this;
    }

    @Step("Verify empty fields")
    public SignUp verifyEmptyFields() {
        wait.until(ExpectedConditions.visibilityOf(firstNameErrorMessage));
        Assert.assertEquals(firstNameErrorMessage.getText(), "This is a required field.");
        Assert.assertEquals(lastNameErrorErrorMessage.getText(), "This is a required field.");
        Assert.assertEquals(dateOfBirthErrorMessage.getText(), "This is a required field.");
        Assert.assertEquals(emailAddressErrorMessage.getText(), "This is a required field.");
        Assert.assertEquals(passwordErrorMessage.getText(), "This is a required field.");
        Assert.assertEquals(passwordConfirmationErrorMessage.getText(), "This is a required field.");
        //Assert.assertEquals(captchaUserCreateErrorMessage.getText(), "This is a required field.");
        return this;
    }

    @Step("Verify invalid fields")
    public SignUp verifyInvalidFields() {
        wait.until(ExpectedConditions.visibilityOf(emailAddressErrorMessage));
        Assert.assertEquals(emailAddressErrorMessage.getText(), "Please enter a valid email address (Ex: johndoe@domain.com).");
        Assert.assertEquals(passwordErrorMessage.getText(), "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.");
        Assert.assertEquals(passwordStrengthMeterLabel.getText(), "Weak");
        Assert.assertEquals(passwordConfirmationErrorMessage.getText(), "Please enter the same value again.");
        return this;
    }

    @Step("Verify invalid CAPTCHA")
    public SignUp verifyInvalidCAPTCHA() {
        wait.until(ExpectedConditions.visibilityOf(actionMessage));
        Assert.assertEquals(actionMessage.getText(), "Incorrect CAPTCHA");
        return this;
    }

    @Step("Verify invalid Date")
    public SignUp verifyInvalidDate() {
        wait.until(ExpectedConditions.visibilityOf(actionMessage));
        Assert.assertEquals(actionMessage.getText(), "Invalid date");
        return this;
    }

    @Step("Verify if password you are entered is invalid")
    public SignUp verifyInvalidPassword() {
        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
        Assert.assertEquals(passwordErrorMessage.getText(), "Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters.");
        return this;
    }

     @Step("Verify if you registered successfully {firstName} {lastName}")
    public SignUp verifySuccessfulRegistration(String firstName, String lastName) {
        wait.until(ExpectedConditions.visibilityOf(helloMessage));
        Assert.assertTrue(helloMessage.getText().contains(firstName + " " + lastName), "Hello message should contain First and Last names");
        return this;
    }

    @Step("Verify if you registered successfully {name}")
    public SignUp verifySuccessfulRegistration(String name) {
        wait.until(ExpectedConditions.visibilityOf(actionMessage));
        Assert.assertEquals(actionMessage.getText(), "Thank you for registering with " + name + ".");
        return this;
    }

}
