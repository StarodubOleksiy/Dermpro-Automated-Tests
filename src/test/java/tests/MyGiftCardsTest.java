package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class MyGiftCardsTest extends BaseTest {

    private final String giftCode = "invalid";

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Header().logInClick();
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
                    .clickMyGiftCards();
            UI.MyGiftCards()
                    .waitUntilMyGiftCardPageLoaded();
        }
    }

    @TmsLink("TC_My_Gift_Cards_001")
    @Feature("My Gift Cards")
    @Test(groups = "signedUser", description = "Verify My Gift Cards page")
    public void test01VerifyShopByBrandCategory() {
        UI.MyGiftCards()
                .verifyMyGiftCardsPage();
    }

    @TmsLink("TC_My_Gift_Cards_002")
    @Feature("My Gift Cards")
    @Test(groups = "signedUser", description = "Verify apply empty gift code")
    public void test02VerifyApplyEmptyGiftCode() {
        UI.MyGiftCards()
                .waitUntilMyGiftCardPageLoaded()
                .clickCheckButton()
                .verifyEmptyGiftCodeMessage();
    }

    @TmsLink("TC_My_Gift_Cards_003")
    @Feature("My Gift Cards")
    @Test(groups = "signedUser", description = "Verify invalid gift code")
    public void test03VerifyInvalidGiftCode() {
        UI.MyGiftCards()
                .waitUntilMyGiftCardPageLoaded()
                .enterGiftCode(giftCode)
                .clickCheckButton()
                .verifyInvalidGiftCodeMessage();
    }

    @TmsLink("TC_My_Gift_Cards_004")
    @Feature("My Gift Cards")
    @Test(groups = "signedUser", description = "Verify update notification settings")
    public void test04VerifyUpdateNotificationSettings() {
        UI.MyGiftCards()
                .waitUntilMyGiftCardPageLoaded()
                .clickGiftCardUpdateNotificationCheckbox()
                .verifyUpdateNotificationSettings()
                .clickUpdateBalanceNotificationCheckbox()
                .verifyUpdateNotificationSettings();
    }

    @AfterClass
    public void after() {
        UI.Header()
                .accountClick();
        UI.Account()
                .clickMyGiftCards();
        UI.MyGiftCards()
                .waitUntilMyGiftCardPageLoaded();
        UI.MyGiftCards()
                .checkGiftCardUpdateNotificationCheckbox()
                .checkUpdateBalanceNotificationCheckbox();
    }
}
