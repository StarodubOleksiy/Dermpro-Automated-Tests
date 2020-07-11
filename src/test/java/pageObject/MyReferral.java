package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class MyReferral extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']/span")
    private WebElement myReferralTitle;

    @FindBy(xpath = "//div[@class='block url_and_code']//strong")
    private WebElement referralUrlAndCodeBlock;

    @FindBy(xpath = "//div[@class='block refer-via-social']//strong")
    private WebElement referralViaSocialBlock;

    @FindBy(xpath = "//div[@class='block refer-via-social']//div[@class='box-content']")
    private WebElement referralViaSocialContent;

    @FindBy(xpath = "//div[@class='block invitee']//strong")
    private WebElement yourInviteeBlock;

    @FindBy(xpath = "//div[@class='block send-invitations']//strong")
    private WebElement sendInvitationsBlock;

    @FindBy(xpath = "//select[@id='send-by']")
    private WebElement sendFromSelect;

    @FindBy(xpath = "//textarea[@name='invitees']")
    private WebElement inviteEmailInput;

    @FindBy(xpath = "//textarea[@name='message']")
    private WebElement inviteMessageInput;

    @FindBy(xpath = "//button[@title='Send Now']")
    private WebElement sendNowButton;

    @FindBy(xpath = "//div[@class='message-notice notice message']")
    private WebElement referralValidationMessage;

    @FindBy(xpath = "//div[@class='message-notice notice message']")
    private WebElement noticeMessage;

    @FindBy(xpath = "//div[@class='message-success success message']")
    private WebElement successMessage;

    @FindBy(xpath = "//input[@name='refer_url']")
    private WebElement referUrlInput;

    @FindBy(xpath = "//input[@name='refer_code']")
    private WebElement referCodeInput;

    @FindBy(xpath = "//input[@name='refer_email']")
    private WebElement referEmailInput;

    @FindBy(xpath = "//div[input[@name='refer_url']]/div")
    private WebElement referUrlCopyButton;

    @FindBy(xpath = "//div[input[@name='refer_code']]/div")
    private WebElement referCodeCopyButton;

    @FindBy(xpath = "//div[input[@name='refer_email']]/div")
    private WebElement referEmailCopyButton;


    @FindBy(xpath = "//div[@class='block refer-via-social']//div/a/span/*")
    private List<WebElement> referralViaSocialElements;

    public MyReferral(WebDriver driver) {
        super(driver);
    }

    @Step("Verify My Referral page")
    public MyReferral verifyMyReferralPage() {
        wait.until(ExpectedConditions.visibilityOf(myReferralTitle));
        verifyTitle(myReferralTitle, "Referral Program");
        verifyTitle(referralUrlAndCodeBlock, "Referral URL And Code");
        verifyTitle(referralViaSocialBlock, "Referral Via Social");
        verifyTitle(yourInviteeBlock, "Your Invitee");
        verifyTitle(sendInvitationsBlock, "Send Invitations");
        return this;
    }

    @Step("Select Send From option {sendFrom}")
    public MyReferral selectSendFrom(String sendFrom) {
        Select option = new Select(driver.findElement(By.xpath("//select[@id='send-by']")));
        option.selectByVisibleText(sendFrom);
        return this;
    }

    @Step("Enter invite email address {inviteEmail}")
    public MyReferral enterInviteEmailAddress(String inviteEmail) {
        sendText(inviteEmailInput, inviteEmail);
        return this;
    }

    @Step("Enter invite message text {inviteMessage}")
    public MyReferral enterInviteMessage(String inviteMessage) {
        sendText(inviteMessageInput, inviteMessage);
        return this;
    }

    @Step("Click on <<Send Now>> button")
    public MyReferral clickSendNowButton() {
        click(sendNowButton);
        return this;
    }

    @Step("Verify notice message {noticeMsg}")
    public MyReferral verifyNoticeMessage(String noticeMsg) {
        wait.until(ExpectedConditions.visibilityOf(noticeMessage));
        Assert.assertEquals(noticeMessage.getText(), noticeMsg);
        return this;
    }

    @Step("Verify success message {successMsg}")
    public MyReferral verifySuccessMessage(String successMsg) {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        Assert.assertEquals(successMessage.getText(), successMsg);
        return this;
    }

    @Step("Get refer Url")
    public String getReferUrl() {
        return referUrlInput.getAttribute("value");
    }

    @Step("Verify Invitee list")
    public MyReferral verifyInviteeList(String email) {
        WebElement invitee = driver.findElement(By.xpath(String.format("//table[@id='transactions-history']//td[text()='%s']", email)));
        Assert.assertTrue(invitee.isDisplayed(), "Registered user should be displayed in the invitee list");
        return this;
    }

    @Step("Click on Refer Url Copy button")
    public MyReferral clickReferUrlCopyButton() {
        Actions action = new Actions(driver);
        action.moveToElement(referUrlCopyButton).perform();
        referUrlCopyButton.click();
        return this;
    }

    @Step("Verify if Refer Url is copied")
    public MyReferral verifyCopiedReferUrl() {
        Assert.assertEquals(referUrlInput.getAttribute("value"), paste());
        return this;
    }

    @Step("Click on Refer Code Copy button")
    public MyReferral clickReferCodeCopyButton() {
        Actions action = new Actions(driver);
        action.moveToElement(referCodeCopyButton).perform();
        referCodeCopyButton.click();
        return this;
    }

    @Step("Verify if Refer Code is copied")
    public MyReferral verifyCopiedReferCode() {
        Assert.assertEquals(referCodeInput.getAttribute("value"), paste());
        return this;
    }

    @Step("Click on Refer Email Copy button")
    public MyReferral clickReferEmailCopyButton() {
        Actions action = new Actions(driver);
        action.moveToElement(referEmailCopyButton).perform();
        referEmailCopyButton.click();
        return this;
    }

    @Step("Verify if Refer Email is copied")
    public MyReferral verifyCopiedReferEmail() {
        Assert.assertEquals(referEmailInput.getAttribute("value"), paste());
        return this;
    }

    @Step("Verify Referral Via Social icons")
    public MyReferral verifyReferralViaSocialIcons() {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='block refer-via-social']//div/a/span/*"), 6));
        Assert.assertEquals(referralViaSocialElements.get(5).getAttribute("title"), "More");
        return this;
    }


}
