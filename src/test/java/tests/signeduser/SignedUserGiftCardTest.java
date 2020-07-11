package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.time.LocalDate;
import java.util.List;

public class SignedUserGiftCardTest extends BaseTest {

    private final String giftCardName = "E-Gift Card";
    private final String amount = "55";
    private String userName;

    private final String creditCardNumber = "4111111111111111";
    private final String expirationMonth = "05 - May";
    private final String expirationYear = "2028";
    private final String verificationNumber = "111";

    private String provider;
    private boolean isSurveyQuestionPresent;

    private boolean isLogged = false;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-34A") &&
                isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(testData.getStore());
                isLogged = true;
            }
            UI.Header()
                    .accountClick();
            userName = UI.AccountDashboard().getFirstName();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-34A") &&
                isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Menu()
                    .selectMenu(testData.getStore().getGiftCard().getMenuTitle());
            testData.getStore().getGiftCard().getMenuTitle();// testData.Store з mainMenuCategories
            testData.getStore().getMainMenuCategories();
            UI.GiftCardList()
                    .waitUntilGiftCardListPageIsLoaded();
        }
    }

    @TmsLink("TC_Signed_User_Gift_Card_001")
    @Feature("SignedUser_Gift_Card")
    @Test(groups = {"SO-34A", "signedUser"}, description = "Verify that Logged in user name is displayed in From field on preview gift card image.")
    public void test01VerifyUserNameIsDisplayedInFromField() {
        UI.GiftCardList()
                .addToCartClick(giftCardName);
        UI.GiftCard()
                .verifyUserName(userName);
    }

    @TmsLink("TC_Signed_User_Gift_Card_002")
    @Feature("SignedUser_Gift_Card_Gift_Card")
    @Test(groups = {"SO-34A", "signedUser"}, description = "Verify Gift Card order status")
    public void test02VerifyGiftCardOrderStatus() {
        List<String> questionOptions = testData.getStore().getCheckoutSurveyQuestionOptions();
        isSurveyQuestionPresent = questionOptions != null && !questionOptions.isEmpty();
        if (questionOptions != null && !questionOptions.isEmpty()) {
            provider = testData.getStore().getCheckoutSurveyQuestionOptions().get(0);
        }

        if (UI.CartPopUp().isElementsInCartPresent()) {
            UI.clearCart();
            UI.Menu()
                    .selectMenu(testData.getStore().getGiftCard().getMenuTitle());
            UI.GiftCardList()
                    .waitUntilGiftCardListPageIsLoaded();
        }
        UI.GiftCardList()
                .addToCartClick(giftCardName);
        waitUntilProductsPageIsLoaded();
        UI.GiftCard()
                .verifySelectedGiftCardPage(giftCardName)
                .enterAmount(amount)
                .enterRecipientEmail(testData.getEmailAddress())
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
                .clickTermsConditionsCheckbox()
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
