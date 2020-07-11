package pageObject;

import data.bean.Store;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignIn extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='pass']")
    private WebElement passwordInput;

    @FindBy(xpath = "//div[@id='email-error']")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//div[@id='pass-error']")
    private WebElement passwordErrorMessage;

    @FindBy(xpath = "//a[@title='Forgot Your Password?']")
    private WebElement forgotYourPasswordLink;

    @FindBy(xpath = "//button[@id='send2']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@id='email_address']")
    private WebElement emailForResetPasswordInput;

    @FindBy(xpath = "//button[@type='submit']//span[contains(text(),'Reset My Password')]")
    private WebElement resetMyPasswordButton;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement signMessage;

    public SignIn(WebDriver driver) {
        super(driver);
    }

    @Step("Verify if you on SignIn page")
    public SignIn verifySignInPage() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        verifyTitle(pageTitle, "Customer Login");
        return this;
    }

    public SignIn quickLogin(String email, String password) {
        this.enterEmail(email)
                .enterPassword(password)
                .clickSignInButton();
        return this;
    }

    public SignIn quickLogin(Store store) {
        quickLogin(store.getAccount().getEmail(), store.getAccount().getPassword());
        return this;
    }

    @Step("Enter email {email}")
    public SignIn enterEmail(String email) {
        sendText(emailInput, email);
        return this;
    }

    @Step("Enter email for reset password {password}")
    public SignIn enterEmailForResetPassword(String password) {
        sendText(emailForResetPasswordInput, password);
        return this;
    }

    @Step("Enter password {password}")
    public SignIn enterPassword(String password) {
        sendText(passwordInput, password);
        return this;
    }

    @Step("Click on <<Sign In>> button")
    public SignIn clickSignInButton() {
        click(signInButton);
        return this;
    }

    @Step("Click on <<Forgot Your Password?>> link")
    public SignIn clickForgotYourPassword() {
        click(forgotYourPasswordLink);
        return this;
    }

    @Step("Click on <<Reset My Password>> button")
    public SignIn clickResetMyPasswordButton() {
        click(resetMyPasswordButton);
        return this;
    }

    @Step("Verify empty fields")
    public SignIn verifyEmptyFields() {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage));
        Assert.assertEquals(emailErrorMessage.getText(), "This is a required field.");
        Assert.assertEquals(passwordErrorMessage.getText(), "This is a required field.");
        return this;
    }

    @Step("Verify invalid email")
    public SignIn verifyInvalidEmail() {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage));
        Assert.assertEquals(emailErrorMessage.getText(), "Please enter a valid email address (Ex: johndoe@domain.com).");
        return this;
    }

    @Step("Verify invalid Sign In")
    public SignIn verifyInvalidSignIn() {
        wait.until(ExpectedConditions.visibilityOf(signMessage));
        Assert.assertEquals(signMessage.getText(), "You did not sign in correctly or your account is temporarily disabled.");
        return this;
    }

    @Step("Verify receive an email with a link {email}")
    public SignIn verifyReceiveAnEmailWithALink(String email) {
        wait.until(ExpectedConditions.visibilityOf(signMessage));
        Assert.assertEquals(signMessage.getText(), "If there is an account associated with " + email + " you will receive an email with a link to reset your password.");
        return this;
    }


    @Step("Verify is on Login Page")
    public boolean isOnLoginPage() {
        return isElementPresent("//span[contains(text(),'Customer Login')]", driver);
    }

    @Step("Check if Logged In")
    public boolean isLoggedIn() {
        return isElementPresent("//a[@title='Sign Out']", driver);
    }

    @Step("Verify Login popup")
    public SignIn verifyLoginPopup() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='block-authentication']")));
        Assert.assertTrue(isElementPresent("//input[@id='customer-email']", driver));
        Assert.assertTrue(isElementPresent("//input[@id='pass']", driver));
        return this;
    }

}
