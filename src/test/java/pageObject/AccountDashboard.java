package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class AccountDashboard extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']//span")
    private WebElement dashboardTitle;

    // Address Information elements

    @FindBy(xpath = "//div[@class='block-title']/strong[text()='Account Information']")
    private WebElement accountInformationBlock;

    @FindBy(xpath = "//div[@class='box box-information']")
    private WebElement contactInformation;

    @FindBy(xpath = "//div[@class='box box-information']//span[text()='Edit']")
    private WebElement contactEditButton;

    @FindBy(xpath = "//div[@class='box box-information']//a[contains(text(),'Change Password')]")
    private WebElement contactChangePasswordButton;

    @FindBy(xpath = "//div[@class='box box-newsletter']")
    private WebElement newsletterInformation;

    @FindBy(xpath = "//div[@class='box box-newsletter']//span[text()='Edit']")
    private WebElement newsletterEditButton;

    // Address Book elements

    @FindBy(xpath = "//div[@class='block-title']/strong[text()='Address Book']")
    private WebElement addressBookBlock;

    @FindBy(xpath = "//div[@class='block block-dashboard-addresses']//span[text()='Manage Addresses']")
    private WebElement manageAddressesButton;

    @FindBy(xpath = "//div[@class='box box-billing-address']")
    private WebElement billingAddressInformation;

    @FindBy(xpath = "//div[@class='box box-billing-address']//span[text()='Edit Address']")
    private WebElement editBillingAddressButton;

    @FindBy(xpath = "//div[@class='box box-shipping-address']")
    private WebElement shippingAddressInformation;

    @FindBy(xpath = "//div[@class='box box-shipping-address']//span[text()='Edit Address']")
    private WebElement editShippingAddressButton;

    // My Recent Reviews elements

    @FindBy(xpath = "//div[@class='block block-reviews-dashboard']")
    private WebElement myRecentReviewsBlock;

    // Recent Orders elements

    @FindBy(xpath = "//div[@class='block block-dashboard-orders']")
    private WebElement recentOrdersBlock;

    @FindBy(xpath = "//div[@class='block block-dashboard-orders']//span[text()='View All']")
    private WebElement recentOrdersViewAllButton;

    @FindBy(xpath = "//td[@class='col id']")
    private List<WebElement> orderId;

    @FindBy(xpath = "//td[@class='col date']")
    private List<WebElement> orderDate;

    @FindBy(xpath = "//td[@class='col status']")
    private List<WebElement> orderStatus;

    @FindBy(xpath = "//a[@class='action view']/span[text()='View Order']")
    private List<WebElement> viewOrderButton;

    @FindBy(xpath = "//a[@class='action order']/span[text()='Reorder']")
    private List<WebElement> reorderButton;

    @FindBy(xpath = "//div[contains(@class,'block-dashboard-info')]//div[@class='box-content']//p")
    private WebElement firstAndLastNames;

    public AccountDashboard(WebDriver driver) {
        super(driver);
    }

    @Step("Verify if you on Account Dashboard page")
    public AccountDashboard verifyAccountDashboardPage() {
        scrollIntoView(dashboardTitle, driver);
        verifyTitle(dashboardTitle, "Account Dashboard");
        Assert.assertTrue(isElementPresent("//div[@class='block-title']/strong[text()='Account Information']", driver));
        Assert.assertTrue(isElementPresent("//div[@class='block-title']/strong[text()='Address Book']", driver));
        return this;
    }

    @Step("Click on Contact information <<Edit>> button")
    public AccountDashboard clickContactEditButton() {
        click(contactEditButton);
        return this;
    }

    @Step("Click on Contact information <<Change password>> button")
    public AccountDashboard clickContactChangePasswordButton() {
        click(contactChangePasswordButton);
        return this;
    }

    @Step("Click on Newsletters <<Edit>> button")
    public AccountDashboard clickNewsletterEditButton() {
        click(newsletterEditButton);
        return this;
    }

    @Step("Click on <<Manage Addresses>> button")
    public AccountDashboard clickManageAddressesButton() {
        click(manageAddressesButton);
        return this;
    }

    @Step("Click on <<Edit Billing Address>> button")
    public AccountDashboard clickEditBillingAddressButton() {
        click(editBillingAddressButton);
        return this;
    }

    @Step("Click on <<Edit Shipping Address>> button")
    public AccountDashboard clickEditShippingAddressButton() {
        click(editShippingAddressButton);
        return this;
    }

    @Step("Click on Recent Orders <<View All>> button")
    public AccountDashboard clickRecentOrdersViewAllButton() {
        click(recentOrdersViewAllButton);
        return this;
    }


    @Step("Check is on Account Dashboard page")
    public boolean isOnAccountDashboard() {
        return isElementPresent("//span[contains(text(),'Account Dashboard')]", driver) &&
                isElementPresent("//div[@class='block-title']/strong[text()='Account Information']", driver) &&
                isElementPresent("//div[@class='block-title']/strong[text()='Address Book']", driver);
    }

    private List<String> getFirstAndLastNames() {
        return Arrays.asList(firstAndLastNames.getText().split(" "));
    }

    public String getFirstName() {
        return this.getFirstAndLastNames().get(0);
    }

    public String getLastName() {
        return this.getFirstAndLastNames().get(1);
    }

}
