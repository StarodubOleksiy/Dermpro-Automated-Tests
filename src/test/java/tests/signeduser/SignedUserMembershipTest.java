package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.List;

public class SignedUserMembershipTest extends BaseTest {

    private String memberShipName;

    private final String creditCardNumber = "4111111111111111";
    private final String expirationMonth = "05 - May";
    private final String expirationYear = "2028";
    private final String verificationNumber = "111";

    private String provider;
    private boolean isSurveyQuestionPresent;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-37A")&& isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Header().logInClick();
            UI.SignIn()
                    .quickLogin(testData.getStore());
            memberShipName = testData.getStore().getMembership().getProductName();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-37A")&& isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Menu()
                    .selectMenu(testData.getStore().getMembership().getMenuTitle());
        }
    }

    @TmsLink("TC_Signed_User_Membership_001")
    @Feature("Signed_User_Membership")
    @Test(groups = {"SO-37A","signedUser"}, description = "Verify Membership order status")
    public void test01VerifyMembershipOrderStatus(){
        List<String> questionOptions = testData.getStore().getCheckoutSurveyQuestionOptions();
        isSurveyQuestionPresent = questionOptions != null && !questionOptions.isEmpty();
        if (questionOptions != null && !questionOptions.isEmpty()) {
            provider = testData.getStore().getCheckoutSurveyQuestionOptions().get(1);
        }

        if (UI.CartPopUp().isElementsInCartPresent()) {
            UI.clearCart();
            UI.Menu()
                    .selectMenu(testData.getStore().getMembership().getMenuTitle());
        }
        UI.MembershipList()
                .addToCartClick(memberShipName);
        UI.GiftCard()
                .clickAddToCart();
        UI.Cart()
                .waitUntilCartElementsLoaded()
                .clickGoToCheckout();
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .enterCreditCardNumber(creditCardNumber)
                .selectExpirationMonth(expirationMonth)
                .selectExpirationYear(expirationYear)
                .enterCardVerificationNumber(verificationNumber);
        if (isSurveyQuestionPresent) {
            UI.Checkout()
                    .clickSurveyQuestion()
                    .selectSurveyProvider(provider);
        }
        UI.Checkout()
                .clickTermsConditionsCheckbox();
        if (UI.Checkout().isSubscriptionTermsPresent()) {
            UI.Checkout().clickSubscriptionTermsCheckbox();
        }
        UI.Checkout()
                .clickPlaceOrder()
                .verifySuccessfulCheckout();
        String orderNumber = UI.Checkout().getOrderNumber();
        UI.Header().accountClick();
        UI.Account().clickMyOrders();
        UI.MyOrders().verifyOrderStatusById(orderNumber, "Complete");
    }

    @AfterClass
    public void after() {
        UI.clearCart();
    }

}
