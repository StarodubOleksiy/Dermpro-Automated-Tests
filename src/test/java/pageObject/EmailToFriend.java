package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class EmailToFriend extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//fieldset[@id='sender_options']")
    private WebElement senderBlock;

    @FindBy(xpath = "//fieldset[@class='fieldset recipients']")
    private WebElement inviteeBlock;

    @FindBy(xpath = "//input[@id='sender-name']")
    private WebElement senderNameInput;

    @FindBy(xpath = "//input[@id='sender-email']")
    private WebElement senderEmailInput;

    @FindBy(xpath = "//textarea[@id='sender-message']")
    private WebElement senderMessageInput;

    @FindBy(xpath = "//input[contains(@id,'recipients-name')]")
    private List<WebElement> inviteeNameInput;

    @FindBy(xpath = "//input[contains(@id,'recipients-email')]")
    private List<WebElement> inviteeEmailInput;

    @FindBy(xpath = "//button[@id='add-recipient-button']")
    private WebElement addInviteeButton;

    @FindBy(xpath = "//button[@class='action remove']")
    private List<WebElement> removeInviteeButton;

    @FindBy(xpath = "//button[@class='action submit primary']")
    private WebElement sendEmailButton;


    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement pageMessage;

    @FindBy(xpath = "//div[@id='sender-name-error']")
    private WebElement senderNameError;

    @FindBy(xpath = "//div[@id='sender-email-error']")
    private WebElement senderEmailError;

    @FindBy(xpath = "//div[@id='sender-message-error']")
    private WebElement senderMessageError;

    @FindBy(xpath = "//div[@id='recipients-name0-error']")
    private WebElement inviteeNameError;

    @FindBy(xpath = "//div[@id='recipients-email0-error']")
    private WebElement inviteeEmailError;

    public EmailToFriend(WebDriver driver) {
        super(driver);
    }

    @Step("Verify Email To Friend page")
    public EmailToFriend verifyEmailToFriendPage() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        verifyTitle(pageTitle, "Email to a Friend");
        Assert.assertTrue(isElementPresent("//fieldset[@id='sender_options']", driver));
        Assert.assertTrue(isElementPresent("//fieldset[@class='fieldset recipients']", driver));
        return this;
    }

    @Step("Enter sender Email {senderEmail}")
    public EmailToFriend enterSenderEmail(String senderEmail) {
        sendText(senderEmailInput, senderEmail);
        return this;
    }

    @Step("Clear sender Email")
    public EmailToFriend clearSenderEmail() {
        clear(senderEmailInput);
        return this;
    }

    @Step("Enter sender message {senderMessage}")
    public EmailToFriend enterSenderMessage(String senderMessage) {
        sendText(senderMessageInput, senderMessage);
        return this;
    }

    @Step("Enter invitee name {inviteeName} with index {index}")
    public EmailToFriend enterInviteeName(String inviteeName, int index) {
        sendText(inviteeNameInput.get(index), inviteeName);
        return this;
    }

    @Step("Enter invitee email {inviteeEmail} with index {index}")
    public EmailToFriend enterInviteeEmail(String inviteeEmail, int index) {
        sendText(inviteeEmailInput.get(index), inviteeEmail);
        return this;
    }


    @Step("Click on <<Add invitee>> button")
    public EmailToFriend clickAddInviteeButton() {
        click(addInviteeButton);
        return this;
    }

    @Step("Click on <<Send Email>> button")
    public EmailToFriend clickSendEmailButton() {
        click(sendEmailButton);
        return this;
    }

    @Step("Verify Send Invite Message")
    public EmailToFriend verifySendInviteMessage() {
        wait.until(ExpectedConditions.textToBePresentInElement(pageMessage, "The link to a friend was sent."));
        return this;
    }

    @Step("Clear all fields")
    public EmailToFriend clearAllFields() {
        clear(senderNameInput);
        senderEmailInput.clear();
        senderMessageInput.clear();
        inviteeNameInput.get(0).clear();
        inviteeEmailInput.get(0).clear();
        return this;
    }

    @Step("Verify required fields")
    public EmailToFriend verifyRequiredFields() {
        wait.until(ExpectedConditions.visibilityOf(senderEmailError));
        Assert.assertEquals(senderNameError.getText(), "This is a required field.");
        Assert.assertEquals(senderEmailError.getText(), "This is a required field.");
        Assert.assertEquals(senderMessageError.getText(), "This is a required field.");
        Assert.assertEquals(inviteeNameError.getText(), "This is a required field.");
        Assert.assertEquals(inviteeEmailError.getText(), "This is a required field.");
        return this;
    }

    @Step("Validate sender Email field")
    public EmailToFriend validateSenderEmail() {
        wait.until(ExpectedConditions.visibilityOf(senderEmailError));
        Assert.assertEquals(senderEmailError.getText(), "Please enter a valid email address (Ex: johndoe@domain.com).");
        return this;
    }

    @Step("Validate invitee Email field")
    public EmailToFriend validateInviteeEmail() {
        wait.until(ExpectedConditions.visibilityOf(inviteeEmailError));
        Assert.assertEquals(inviteeEmailError.getText(), "Please enter a valid email address (Ex: johndoe@domain.com).");
        return this;
    }
}
