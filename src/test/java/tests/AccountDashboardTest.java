package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class AccountDashboardTest extends BaseTest {

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Header()
                    .logInClick();
            UI.SignIn()
                    .quickLogin(testData.getStore());
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Header()
                    .accountClick();
        }
    }

    @TmsLink("TC_Account_Dashboard_001")
    @Feature("DermPRO Account Dashboard page")
    @Test(groups = "signedUser", description = "Verify Account Dashboard page")
    public void test01VerifyAccountDashboardPage() {
        UI.AccountDashboard()
                .verifyAccountDashboardPage();
    }

    @TmsLink("TC_Account_Dashboard_002")
    @Feature("DermPRO Account Dashboard page")
    @Test(groups = "signedUser", description = "Verify Contact information Edit ")
    public void test02VerifyContactInformationEdit() {
        UI.AccountDashboard()
                .clickContactEditButton();
        UI.AccountInformation()
                .verifyAccountInformationPage();
    }

    @TmsLink("TC_Account_Dashboard_003")
    @Feature("DermPRO Account Dashboard page")
    @Test(groups = "signedUser", description = "Verify Contact information Change password option")
    public void test03VerifyContactInformationChangePassword() {
        UI.AccountDashboard()
                .clickContactChangePasswordButton();
        UI.AccountInformation()
                .verifyAccountInformationPage()
                .verifyChangePasswordSection();
    }

    @TmsLink("TC_Account_Dashboard_004")
    @Feature("DermPRO Account Dashboard page")
    @Test(groups = "signedUser", description = "Verify Newsletter Edit option")
    public void test04VerifyNewsletterEdit() {
        UI.AccountDashboard()
                .clickNewsletterEditButton();
        UI.NewsletterSubscriptions()
                .verifyNewsletterSubscriptionsPage();
    }

    @TmsLink("TC_Account_Dashboard_005")
    @Feature("DermPRO Account Dashboard page")
    @Test(groups = "signedUser", description = "Verify Manage addresses")
    public void test05VerifyManageAddresses() {
        UI.AccountDashboard()
                .clickManageAddressesButton();
        UI.AddressBook()
                .verifyAddressBookPage();
    }

    @TmsLink("TC_Account_Dashboard_006")
    @Feature("DermPRO Account Dashboard page")
    @Test(groups = "signedUser", description = "Verify Edit Billing address")
    public void test06VerifyEditBillingAddress() {
        UI.AccountDashboard()
                .clickEditBillingAddressButton();
        UI.AddressBook()
                .verifyEditAddressPage();
    }

    @TmsLink("TC_Account_Dashboard_007")
    @Feature("DermPRO Account Dashboard page")
    @Test(groups = "signedUser", description = "Verify Edit Shipping address")
    public void test07VerifyEditShippingAddress() {
        UI.AccountDashboard()
                .clickEditShippingAddressButton();
        UI.AddressBook()
                .verifyEditAddressPage();
    }

    @TmsLink("TC_Account_Dashboard_008")
    @Feature("DermPRO Account Dashboard page")
    @Test(groups = "signedUser", description = "Verify View All Orders option")
    public void test08VerifyViewAllOrders() {
        if (UI.MyOrders().isOrdersPresent()) {
            UI.AccountDashboard()
                    .clickRecentOrdersViewAllButton();
            UI.MyOrders()
                    .verifyMyOrderPage();
        }
    }
}
