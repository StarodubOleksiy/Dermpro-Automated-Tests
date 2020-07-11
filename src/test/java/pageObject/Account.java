package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Account extends PageObjectInitializer {

    @FindBy(xpath = "//main[@id='maincontent']//span[contains(text(),'Account Dashboard')]")
    private WebElement accountDashboardHeader;

    @FindBy(xpath = "//main[@id='maincontent']//li[@class='nav item']//a[contains(text(),'My Orders')]")
    private WebElement myOrders;

    @FindBy(xpath = "//div[@class='item']//a[contains(text(),'Wish List')]")
    private WebElement wishList;

    @FindBy(xpath = "//main[@id='maincontent']//li[@class='nav item']//a[contains(text(),'Address Book')]")
    private WebElement addressBook;

    @FindBy(xpath = "//main[@id='maincontent']//li[@class='nav item']//a[contains(text(),'Account Information')]")
    private WebElement accountInformation;

    @FindBy(xpath = "//main[@id='maincontent']//li[@class='nav item']//a[contains(text(),'My Product Reviews')]")
    private WebElement myProductReviews;

    @FindBy(xpath = "//main[@id='maincontent']//li[@class='nav item']//a[contains(text(),'Newsletter Subscriptions')]")
    private WebElement newsletterSubscriptions;

    @FindBy(xpath = "//main[@id='maincontent']//li[@class='nav item']//a[contains(text(),'My Stored Payment Accounts')]")
    private WebElement myStoredPaymentAccounts;

    @FindBy(xpath = "//main[@id='maincontent']//li[@class='nav item']//a[contains(text(),'My Gift Cards')]")
    private WebElement myGiftCards;

    @FindBy(xpath = "//main[@id='maincontent']//li[@class='nav item']//a[contains(text(),'My Reward')]")
    private WebElement myReward;

    @FindBy(xpath = "//div[contains(@class,'block-dashboard-info')]//p")
    private WebElement accountDashboardInformation;

    public Account(WebDriver driver) {
        super(driver);
    }


    @Step("Click on My Orders")
    public Account clickMyOrders() {
        click(myOrders);
        return this;
    }

    @Step("Click on Wish List")
    public Account clickWishList() {
        click(wishList);
        return this;
    }

    @Step("Click on Address Book")
    public Account clickAddressBook() {
        click(addressBook);
        return this;
    }

    @Step("Click on Account Information")
    public Account clickAccountInformation() {
        click(accountInformation);
        return this;
    }

    @Step("Click on My Product Reviews")
    public Account clickMyProductReviews() {
        click(myProductReviews);
        return this;
    }

    @Step("Click on Newsletter Subscriptions")
    public Account clickNewsletterSubscriptions() {
        click(newsletterSubscriptions);
        return this;
    }

    @Step("Click on My Stored Payment Accounts")
    public Account clickMyStoredPaymentAccounts() {
        click(myStoredPaymentAccounts);
        return this;
    }

    @Step("Click on My Gift Cards")
    public Account clickMyGiftCards() {
        click(myGiftCards);
        return this;
    }

    @Step("Click on My Reward")
    public Account clickMyReward() {
        click(myReward);
        return this;
    }


    @Step("Verify account dashboard information{0} {1} {2}")
    public Account verifyAccountDashboardInformation(String firstName, String lastName, String email) {
        scrollIntoView(accountDashboardInformation, driver);
        verifyTitle(accountDashboardHeader, "Account Dashboard");
        Assert.assertTrue(accountDashboardInformation.getText().contains(firstName), firstName + "should be displayed");
        Assert.assertTrue(accountDashboardInformation.getText().contains(lastName), lastName + "should be displayed");
        Assert.assertTrue(accountDashboardInformation.getText().contains(email), email + "should be displayed");
        return this;
    }

    @Step("Verify account dashboard email {email}")
    public Account verifyEmail(String email) {
        scrollIntoView(accountDashboardInformation, driver);
        verifyTitle(accountDashboardHeader, "Account Dashboard");
        Assert.assertTrue(accountDashboardInformation.getText().contains(email), email + "should be displayed");
        return this;
    }
}
