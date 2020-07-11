package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class NewsletterSubscriptionsTest extends BaseTest {

    private final String updateSubscriptionMessage = "We have updated your subscription.";
    private final String removeSubscriptionMessage = "We have removed your newsletter subscription.";
    private final String saveSubscriptionMessage = "We have saved your subscription.";

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
            UI.Account()
                    .clickNewsletterSubscriptions();
        }
    }

    @TmsLink("TC_newsletter_subscriptions_001")
    @Feature("DermPRO Newsletter Subscriptions page")
    @Test(groups = "signedUser", description = "Verify Newsletter Subscriptions page")
    public void test01VerifyNewsletterSubscriptionsPage() {
        UI.NewsletterSubscriptions()
                .verifyNewsletterSubscriptionsPage();
    }

    @TmsLink("TC_newsletter_subscriptions_002")
    @Feature("DermPRO Newsletter Subscriptions page")
    @Test(groups = "signedUser", description = "Verify update Subscription message")
    public void test02VerifyUpdateSubscription() {
        UI.NewsletterSubscriptions()
                .clickSaveButton()
                .verifySubscriptionMessage(updateSubscriptionMessage);
    }

    @TmsLink("TC_newsletter_subscriptions_003")
    @Feature("DermPRO Newsletter Subscriptions page")
    @Test(groups = "signedUser", description = "Verify remove and save Subscription message")
    public void test03VerifyRemoveSaveSubscription() {
        UI.NewsletterSubscriptions()
                .clickGeneralSubscriptionCheckbox()
                .clickSaveButton()
                .verifySubscriptionMessage(removeSubscriptionMessage);
        UI.Account()
                .clickNewsletterSubscriptions();
        UI.NewsletterSubscriptions()
                .clickGeneralSubscriptionCheckbox()
                .clickSaveButton()
                .verifySubscriptionMessage(saveSubscriptionMessage);
    }

    @TmsLink("TC_newsletter_subscriptions_004")
    @Feature("DermPRO Newsletter Subscriptions page")
    @Test(groups = "signedUser", description = "Verify <<Back>> link")
    public void test04VerifyBackLink() {
        UI.NewsletterSubscriptions()
                .clickBackLink();
        UI.AccountDashboard().verifyAccountDashboardPage();
    }

    @AfterClass
    public void after() {
        UI.Header()
                .accountClick();
        UI.Account()
                .clickNewsletterSubscriptions();
        UI.NewsletterSubscriptions()
                .checkGeneralSubscriptionCheckbox()
                .clickSaveButton();
    }


}
