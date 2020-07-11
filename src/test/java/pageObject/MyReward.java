package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyReward extends PageObjectInitializer {

    @FindBy(xpath = "//div[@id='block-collapsible-nav']//strong[text()='Reward Dashboard']")
    private WebElement rewardDashboardButton;

    @FindBy(xpath = "//div[@id='block-collapsible-nav']//a[text()='Transactions']")
    private WebElement transactionsButton;

    @FindBy(xpath = "//div[@id='block-collapsible-nav']//a[text()='My Referral']")
    private WebElement myReferralButton;

    @FindBy(xpath = "//div[@id='block-collapsible-nav']//a[text()='Back']")
    private WebElement backButton;

    @FindBy(xpath = "//h1[@class='page-title']/span")
    private WebElement myRewardTitle;

    @FindBy(xpath = "//div[@class='box-balance available']")
    private WebElement availableBalance;

    @FindBy(xpath = "//div[@class='box-balance earned']")
    private WebElement totalEarned;

    @FindBy(xpath = "//div[@class='box-balance paid']")
    private WebElement totalSpent;

    @FindBy(xpath = "//div[@class='block-title']/strong[text()='Reward information']")
    private WebElement rewardInformationBlock;

    @FindBy(xpath = "//div[@class='block-title order']/strong[text()='Recent Transactions']")
    private WebElement recentTransactionsBlock;

    @FindBy(xpath = "//form[@id='form-validate']//span[text()='Email Notification']")
    private WebElement emailNotificationBlock;

    @FindBy(xpath = "//div[@class='block-title order']//span[text()='View All']")
    private WebElement viewAllButton;

    @FindBy(xpath = "//input[@id='notification-update']")
    private WebElement subscribeToBalanceUpdateCheckbox;

    @FindBy(xpath = "//input[@id='notification-expire']")
    private WebElement subscribeToPointsExpirationCheckbox;

    @FindBy(xpath = "//form[@id='form-validate']//span[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement myRewardPageMessage;

    @FindBy(xpath = "//span[@class='rewardpoints-title-content']/a[text()='Learn more']")
    private WebElement learnMoreLink;

    @FindBy(xpath = "//h1[@class='title_stripes']")
    private WebElement rewardProgramPageTitle;

    @FindBy(xpath = "//tr[td[contains(text(),'Earn point(s) for signing up')]]//td[@data-th='Amount']")
    private WebElement signUpRewardPoints;

    @FindBy(xpath = "//tr[td[contains(text(),'Receive point(s) for subscribing to newsletter')]]//td[@data-th='Amount']")
    private WebElement newsletterRewardPoints;

    @FindBy(xpath = "//li[@id='customer-toplink']")
    private WebElement totalRewardPoints;

    public MyReward(WebDriver driver) {
        super(driver);
    }

    @Step("Click on <<Transactions>> button")
    public MyReward clickTransactions() {
        click(transactionsButton);
        return this;
    }

    @Step("Click on <<My Referral>> button")
    public MyReward clickMyReferralButton() {
        click(myReferralButton);
        return this;
    }

    @Step("Click on <<Back>> button")
    public MyReward clickBackButton() {
        click(backButton);
        return this;
    }

    @Step("Click on <<View All>> button")
    public MyReward clickViewAllButton() {
        click(viewAllButton);
        return this;
    }

    @Step("Click on <<Subscribe to balance update>> checkbox")
    public MyReward clickSubscribeToBalanceUpdateCheckbox() {
        click(subscribeToBalanceUpdateCheckbox);
        return this;
    }

    @Step("Click on <<Subscribe to points expiration>> checkbox")
    public MyReward clickSubscribeToPointsExpirationCheckbox() {
        click(subscribeToPointsExpirationCheckbox);
        return this;
    }

    @Step("Click on <<Save>> button")
    public MyReward clickSaveButton() {
        click(saveButton);
        return this;
    }

    @Step("Verify My Reward page")
    public MyReward verifyMyRewardPage() {
        wait.until(ExpectedConditions.visibilityOf(myRewardTitle));
        verifyTitle(myRewardTitle, "My Reward");
        Assert.assertTrue(isElementPresent("//div[@class='box-balance available']", driver), "Available Balance statistic box should be displayed");
        Assert.assertTrue(isElementPresent("//div[@class='box-balance paid']", driver), "Total Earned statistic box should be displayed");
        Assert.assertTrue(isElementPresent("//div[@class='box-balance earned']", driver), "Total Spent statistic box should be displayed");
        Assert.assertEquals(rewardInformationBlock.getText(), "Reward Information");
        Assert.assertEquals(recentTransactionsBlock.getText(), "Recent Transactions");
        Assert.assertEquals(emailNotificationBlock.getText(), "Email Notification");
        return this;
    }

    @Step("Verify My Reward save message {message}")
    public MyReward verifySaveMessage(String message) {
        wait.until(ExpectedConditions.visibilityOf(myRewardPageMessage));
        Assert.assertEquals(myRewardPageMessage.getText(), message);
        return this;
    }

    @Step("Check Subscribe to balance update options")
    public MyReward checkSubscribeToBalanceUpdateCheckboxOptions() {
        if (subscribeToBalanceUpdateCheckbox.isSelected()) {
            return this;
        } else
            click(subscribeToBalanceUpdateCheckbox);
        return this;
    }

    @Step("Check Subscribe to point expiration options")
    public MyReward checkSubscribeToPointsExpirationOptions() {
        if (subscribeToPointsExpirationCheckbox.isSelected()) {
            return this;
        } else
            click(subscribeToPointsExpirationCheckbox);
        return this;
    }

    @Step("Click on <<Learn more>> link")
    public MyReward clickLearnMoreLink() {
        click(learnMoreLink);
        return this;
    }

    @Step("Verify that you are on the Reward Program page")
    public MyReward verifyRewardProgramPage() {
        wait.until(ExpectedConditions.visibilityOf(rewardProgramPageTitle));
        verifyTitle(rewardProgramPageTitle, "Rewards Program");
        return this;
    }

    @Step("Verify earned points {points} after Sign Up")
    public MyReward verifyEarnedPointsAfterSignUp(String points) {
        wait.until(ExpectedConditions.visibilityOf(signUpRewardPoints));
        Assert.assertEquals(signUpRewardPoints.getText(), points);
        return this;
    }

    @Step("Verify earned points {points} for Newsletter subscription")
    public MyReward verifyEarnedPointsForNewsletterSubscription(String points) {
        wait.until(ExpectedConditions.visibilityOf(newsletterRewardPoints));
        Assert.assertEquals(newsletterRewardPoints.getText(), points);
        return this;
    }

}
