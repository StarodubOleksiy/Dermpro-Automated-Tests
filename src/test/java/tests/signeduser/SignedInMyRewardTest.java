package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.List;

public class SignedInMyRewardTest extends BaseTest {

    private final String myRewardSuccessMessage = "Saved email settings successfully.";
    private final String referralNoticeMessage = "Please fill in the invitation field!";
    private final String referralSuccessMessage = "An invitation to your friends has been sent successfully!";

    private final String creditCardNumber = "4111111111111111";
    private final String expirationMonth = "05 - May";
    private final String expirationYear = "2028";
    private final String verificationNumber = "111";

    private String orderComment;
    private String provider;
    private boolean isSurveyQuestionPresent;

    private boolean isLogged = false;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(testData.getStore());
                isLogged = true;
            }

            orderComment = testData.getStore().getCheckoutTermsAndConditions();

            List<String> questionOptions = testData.getStore().getCheckoutSurveyQuestionOptions();

            isSurveyQuestionPresent = questionOptions != null && !questionOptions.isEmpty();
            if (questionOptions != null && !questionOptions.isEmpty()) {
                provider = testData.getStore().getCheckoutSurveyQuestionOptions().get(0);
            }
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Header()
                    .accountClick();
            UI.Account()
                    .clickMyReward();
        }
    }

    @TmsLink("TC_My_Reward_001")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "signedUser"}, description = "Verify My Reward page")
    public void test01VerifyMyRewardPage() {
        UI.MyReward()
                .verifyMyRewardPage();
    }

    @TmsLink("TC_My_Reward_002")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "signedUser"}, description = "Verify disable Email Notifications")
    public void test02VerifyDisableEmailNotifications() {
        UI.MyReward()
                .clickSubscribeToBalanceUpdateCheckbox()
                .clickSubscribeToPointsExpirationCheckbox()
                .clickSaveButton()
                .verifySaveMessage(myRewardSuccessMessage);
    }

    @TmsLink("TC_My_Reward_003")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "signedUser"}, description = "Verify navigation to Transactions from My Reward page")
    public void test03VerifyNavigationToTransactionsFromMyRewardPage() {
        UI.MyReward()
                .clickViewAllButton();
        UI.Transactions()
                .verifyTransactionsPage();
    }

    @TmsLink("TC_My_Reward_004")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "signedUser"}, description = "Verify Transactions page")
    public void test04VerifyTransactionsPage() {
        UI.MyReward()
                .clickTransactions();
        UI.Transactions()
                .verifyTransactionsPage();
    }

    @TmsLink("TC_My_Reward_005")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "SO-57A", "signedUser"}, description = "Verify My Referral page")
    public void test05VerifyMyReferralPage() {
        UI.MyReward()
                .clickMyReferralButton();
        UI.MyReferral()
                .verifyMyReferralPage();
    }

    @TmsLink("TC_My_Reward_006")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "SO-57A", "signedUser"}, description = "Verify fail of send invitation")
    public void test06VerifySendInvitationFail() {
        UI.MyReward()
                .clickMyReferralButton();
        UI.MyReferral()
                .clickSendNowButton()
                .verifyNoticeMessage(referralNoticeMessage);
    }

    @TmsLink("TC_My_Reward_007")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "SO-57A", "signedUser"}, description = "Verify success of send invitation")
    public void test07VerifySendInvitationSuccess() {
        UI.MyReward()
                .clickMyReferralButton();
        UI.MyReferral()
                .enterInviteEmailAddress(testData.getEmailAddress())
                .clickSendNowButton()
                .verifySuccessMessage(referralSuccessMessage);
    }

    @TmsLink("TC_My_Reward_008")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "SO-57A", "signedUser"}, description = "Verify success of send invitation with message")
    public void test08VerifySendInvitationWithMessageSuccess() {
        UI.MyReward()
                .clickMyReferralButton();
        UI.MyReferral()
                .selectSendFrom("Your email address")
                .enterInviteEmailAddress(testData.getEmailAddress())
                .enterInviteMessage(testData.getSentence())
                .clickSendNowButton()
                .verifySuccessMessage(referralSuccessMessage);
    }

    @TmsLink("TC_My_Reward_009")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "signedUser"}, description = "Verify back to Account dashboard option")
    public void test09VerifyBackToAccountOption() {
        UI.MyReward()
                .clickBackButton();
        UI.AccountDashboard()
                .verifyAccountDashboardPage();
    }

    @TmsLink("TC_My_Reward_010")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "signedUser"}, description = "Verify Learn more link redirection")
    public void test10VerifyLearnMoreRedirection() {
        UI.MyReward()
                .clickLearnMoreLink()
                .verifyRewardProgramPage();
    }


    @TmsLink("TC_My_Reward_011")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "SO-57A", "signedUser"}, description = "Verify copied refer Url, Code, Email")
    public void test11VerifyCopiedReferUrlCodeEmail() {
        UI.MyReward().clickMyReferralButton();
        UI.MyReferral()
                .clickReferUrlCopyButton()
                .verifyCopiedReferUrl()
                .clickReferCodeCopyButton()
                .verifyCopiedReferCode()
                .clickReferEmailCopyButton()
                .verifyCopiedReferEmail();
    }

    @TmsLink("TC_My_Reward_012")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "signedUser"}, description = "Verify earn points for purchase product")
    public void test12VerifyEarnPointsForPurchaseProduct() {
        int percent = testData.getStore().getRewards().getErningRateOnPurchase().getPersent();
        UI.Menu().selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts().addToCartClick(testData.getFirstProductName());
        String subTotal = UI.Cart().getSubTotal();
        int pointsRounded = UI.Cart().calculateEarnedPoints(percent, subTotal);
        UI.Cart()
                .verifyEarnedPointsForPurchaseProduct(pointsRounded);
        UI.clearCart();
    }

    @TmsLink("TC_My_Reward_013")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-28", "SO-53A", "signedUser"}, description = "Verify earn points for Review")
    public void test13VerifyEarnPointsForReview() {
        String points = Integer.toString(testData.getStore().getRewards().getSubmitProductReview());

        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts().clickProductByName(testData.getFirstProductName());
        UI.ProductDetails()
                .verifyEarnedPointsForReview(points);
    }

    @TmsLink("TC_My_Reward_014")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "SO-57A", "signedUser"}, description = "Verify Referral Via Social icons")
    public void test14VerifyReferralViaSocialIcons() {
        UI.MyReward().clickMyReferralButton();
        UI.MyReferral().verifyReferralViaSocialIcons();
    }

    @TmsLink("TC_My_Reward_015")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "signedUser"}, description = "Verify earned points after purchase product")
    public void test15VerifyEarnedPointsAfterPurchase() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .addToCartClick(testData.getFirstProductName());
        String earnedPoints = UI.Cart().getEarnedPoints();
        UI.Cart().clickGoToCheckoutSpan();
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .enterCreditCardNumber(creditCardNumber)
                .selectExpirationMonth(expirationMonth)
                .selectExpirationYear(expirationYear)
                .enterCardVerificationNumber(verificationNumber);
        if (isSurveyQuestionPresent) {
            UI.Checkout().clickSurveyQuestion()
                    .selectSurveyProvider(provider);
        }
        UI.Checkout().clickTermsConditionsCheckbox()
                .clickApplyDiscountCode()
                .enterDiscountCode(testData.getStore().getPromoCode().getStandardCode())
                .clickApplyDiscountCodeButton()
                .waitUntilDiscountCodeMessageDisappears()
                .clickPlaceOrder()
                .verifySuccessfulCheckout();
        UI.Header().accountClick();
        UI.AccountInformation().MyOrders();
        UI.MyOrders().clickFirstOrderViewButton();
        UI.ViewOrder().verifyEarnedPoints(earnedPoints);
    }


    @AfterClass
    public void after() {
        UI.Header()
                .accountClick();
        UI.Account()
                .clickMyReward();
        UI.MyReward()
                .checkSubscribeToBalanceUpdateCheckboxOptions()
                .checkSubscribeToPointsExpirationOptions()
                .clickSaveButton();
    }


}
