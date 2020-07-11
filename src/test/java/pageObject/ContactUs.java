package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContactUs extends PageObjectInitializer {

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='telephone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//textarea[@id='comment']")
    private WebElement commentInput;

    @FindBy(xpath = "//button[@title='Submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@id='name-error']")
    private WebElement nameError;

    @FindBy(xpath = "//div[@id='email-error']")
    private WebElement emailError;

    @FindBy(xpath = "//div[@id='comment-error']")
    private WebElement commentError;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement pageMessage;

    public ContactUs(WebDriver driver) {
        super(driver);
    }

    @Step("Click on <<Submit>> button")
    public ContactUs clickSubmitButton() {
        scrollIntoView(submitButton, driver);
        submitButton.click();
        return this;
    }

    @Step("Enter Name {name}")
    public ContactUs enterName(String name) {
        scrollIntoView(nameInput, driver);
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Enter Email {email}")
    public ContactUs enterEmail(String email) {
        scrollIntoView(emailInput, driver);
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Enter Phone {phone}")
    public ContactUs enterPhone(String phone) {
        scrollIntoView(phoneInput, driver);
        phoneInput.sendKeys(phone);
        return this;
    }

    @Step("Enter Comment {comment}")
    public ContactUs enterComment(String comment) {
        scrollIntoView(commentInput, driver);
        commentInput.sendKeys(comment);
        return this;
    }

    @Step("Clear all fields")
    public ContactUs clearAllFields() {
        clear(nameInput);
        emailInput.clear();
        phoneInput.clear();
        commentInput.clear();
        return this;
    }

    @Step("Verify required fields")
    public ContactUs verifyRequiredFields() {
        Assert.assertEquals(nameError.getText(), "This is a required field.");
        Assert.assertEquals(emailError.getText(), "This is a required field.");
        Assert.assertEquals(commentError.getText(), "This is a required field.");
        return this;
    }

    @Step("Verify incorrect captcha message")
    public ContactUs verifyIncorrectCaptcha() {
        wait.until(ExpectedConditions.visibilityOf(pageMessage));
        Assert.assertEquals(pageMessage.getText(), "Incorrect CAPTCHA.");
        return this;
    }

    @Step("Verify contact us success message")
    public ContactUs verifyContactUsSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(pageMessage));
        Assert.assertEquals(pageMessage.getText(), "Thanks for contacting us with your comments and questions. We'll respond to you very soon.");
        return this;
    }

    @Step("Verify invalid email format")
    public ContactUs verifyInvalidEmail() {
        wait.until(ExpectedConditions.visibilityOf(emailError));
        Assert.assertEquals(emailError.getText(), "Please enter a valid email address (Ex: johndoe@domain.com).");
        return this;
    }

    @Step("Verify default {firstName} and {email} are displayed when open Contact Us page with logged user")
    public ContactUs verifyNameAndEmail(String firstName, String email) {
        Assert.assertTrue(nameInput.getAttribute("value").contains(firstName), String.format("Name %s should be present in name input",firstName));
        Assert.assertEquals(emailInput.getAttribute("value"), email);
        return this;
    }
}
