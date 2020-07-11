package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyGiftCards extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@id='giftcode']")
    private WebElement giftCodeInput;

    @FindBy(xpath = "//div[@id='giftcode-error']")
    private WebElement giftCodeErrorMessage;

    @FindBy(xpath = "//div[@class='messages giftcard-messages']")
    private WebElement giftCodeValidationMessage;

    @FindBy(xpath = "//button[@class='action action-check primary']")
    private WebElement checkButton;

    @FindBy(xpath = "//strong[@id='block-giftcard-list-heading']")
    private WebElement mySavedGiftCardBlock;

    @FindBy(xpath = "//strong[@id='block-giftcard-transaction-heading']")
    private WebElement transactionBlock;

    @FindBy(xpath = "//strong[@id='block-giftcard-setting-heading']")
    private WebElement notificationSettingsBlock;

    @FindBy(xpath = "//input[@id='giftcard-notification']")
    private WebElement giftCardUpdateNotificationCheckbox;

    @FindBy(xpath = "//input[@id='credit-notification']")
    private WebElement updateBalanceNotificationCheckbox;

    @FindBy(xpath = "//div[@id='giftcardContainer']//div[@data-ui-id='checkout-cart-validationmessages-message-success']")
    private WebElement notificationUpdateMessage;

    public MyGiftCards(WebDriver driver) {
        super(driver);
    }

    @Step("Verify My Gift Cards page")
    public MyGiftCards verifyMyGiftCardsPage() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        wait.until(ExpectedConditions.visibilityOf(giftCardUpdateNotificationCheckbox));
        wait.until(ExpectedConditions.visibilityOf(updateBalanceNotificationCheckbox));
        wait.until(ExpectedConditions.visibilityOf(checkButton));
        wait.until(ExpectedConditions.visibilityOf(giftCodeInput));
        verifyTitle(pageTitle, "My Gift Cards");
        Assert.assertTrue(isElementPresent("//strong[@id='block-giftcard-list-heading']", driver), "My Saved Gift Card section should be displayed");
        Assert.assertTrue(isElementPresent("//strong[@id='block-giftcard-transaction-heading']", driver), "Transactions section should be displayed");
        Assert.assertTrue(isElementPresent("//strong[@id='block-giftcard-setting-heading']", driver), "Notification Settings section should be displayed");
        return this;
    }

    @Step("Wait until My Gift Card page loaded")
    public MyGiftCards waitUntilMyGiftCardPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        wait.until(ExpectedConditions.visibilityOf(mySavedGiftCardBlock));
        wait.until(ExpectedConditions.visibilityOf(transactionBlock));
        wait.until(ExpectedConditions.visibilityOf(notificationSettingsBlock));
        return this;
    }


    @Step("Click on Check button")
    public MyGiftCards clickCheckButton() {
        click(checkButton);
        return this;
    }

    @Step("Enter Gift Code {giftCode}")
    public MyGiftCards enterGiftCode(String giftCode) {
        sendText(giftCodeInput, giftCode);
        return this;
    }

    @Step("Verify empty gift code message")
    public MyGiftCards verifyEmptyGiftCodeMessage() {
        wait.until(ExpectedConditions.visibilityOf(giftCodeErrorMessage));
        scrollIntoView(giftCodeErrorMessage, driver);
        Assert.assertEquals(giftCodeErrorMessage.getText(), "This is a required field.");
        return this;
    }

    @Step("Verify invalid gift code message")
    public MyGiftCards verifyInvalidGiftCodeMessage() {
        wait.until(ExpectedConditions.textToBePresentInElement(giftCodeValidationMessage, "Invalid gift card code."));
        return this;
    }

    @Step("Verify update notification settings")
    public MyGiftCards verifyUpdateNotificationSettings() {
        wait.until(ExpectedConditions.visibilityOf(notificationUpdateMessage));
        scrollIntoView(notificationUpdateMessage, driver);
        Assert.assertEquals(notificationUpdateMessage.getText(), "Notification Save Successfully.");
        return this;
    }

    @Step("Click on Gift Card Update Notification checkbox")
    public MyGiftCards clickGiftCardUpdateNotificationCheckbox() {
        click(giftCardUpdateNotificationCheckbox);
        return this;
    }

    @Step("Click on Update Balance Notification checkbox")
    public MyGiftCards clickUpdateBalanceNotificationCheckbox() {
        click(updateBalanceNotificationCheckbox);
        return this;
    }

    @Step("Check Gift Card Update Notification checkbox if unchecked")
    public MyGiftCards checkGiftCardUpdateNotificationCheckbox() {
        scrollIntoView(giftCardUpdateNotificationCheckbox, driver);
        if (giftCardUpdateNotificationCheckbox.isSelected()) {
            return this;
        } else
            click(giftCardUpdateNotificationCheckbox);
        return this;
    }

    @Step("Check Update Balance Notification checkbox if unchecked")
    public MyGiftCards checkUpdateBalanceNotificationCheckbox() {
        scrollIntoView(updateBalanceNotificationCheckbox, driver);
        if (updateBalanceNotificationCheckbox.isSelected()) {
            return this;
        } else
            click(updateBalanceNotificationCheckbox);
        return this;
    }


}
