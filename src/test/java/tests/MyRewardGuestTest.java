package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

public class MyRewardGuestTest extends BaseTest {

    private final String creditCardNumber = "4111111111111111";
    private final String expirationMonth = "05 - May";
    private final String expirationYear = "2028";
    private final String verificationNumber = "111";

    private String orderComment;
    private String provider;
    private boolean isSurveyQuestionPresent;

    @TmsLink("TC_My_Reward_Guest_001")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A"}, description = "Verify earn points after Sign Up")
    public void test01VerifyEarnPointsAfterSignUp() {
        String points = Integer.toString(testData.getStore().getRewards().getSignupRegisterOnStore());
        String password = "ABcde1234567890";
        String email = testData.getEmailAddress();
        String firstName = testData.getFirstName();
        String lastName = testData.getLastName();

        UI.Header().signUpClick();
        UI.SignUp()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmailAddress(email)
                .enterPassword(password)
                .enterPasswordConfirmation(password)
                .clickSignUpButton();
        UI.Header()
                .accountClick();
        UI.Account()
                .clickMyReward();
        UI.MyReward()
                .verifyEarnedPointsAfterSignUp(points);
        UI.Header().logOutClick();
    }

    @TmsLink("TC_My_Reward_Guest_002")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A"}, description = "Verify earn points for Newsletter subscription")
    public void test02VerifyEarnPointsForNewsletterSubscription() {
        String points = Integer.toString(testData.getStore().getRewards().getNewsletterSignup());
        String password = "ABcde1234567890";
        testData.setEmailAddress(new Date().getTime() + "@mailinator.com");
        String email = testData.getEmailAddress();
        String firstName = testData.getFirstName();
        String lastName = testData.getLastName();
        if (UI.SignIn().isLoggedIn()) {
            UI.Header().logOutClick();
        }
        UI.Header().signUpClick();
        UI.SignUp()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmailAddress(email)
                .enterPassword(password)
                .enterPasswordConfirmation(password)
                .clickSignUpButton();

        UI.Header().accountClick();
        UI.Account().clickNewsletterSubscriptions();
        UI.NewsletterSubscriptions()
                .checkGeneralSubscriptionCheckbox()
                .clickSaveButton();
        UI.Account()
                .clickMyReward();
        UI.MyReward()
                .verifyEarnedPointsForNewsletterSubscription(points);
        UI.Header().logOutClick();
    }

    @TmsLink("TC_My_Reward_Guest_003")
    @Feature("DermPRO My Reward page")
    @Test(groups = {"SO-53A", "SO-57A", "signedUser"}, description = "Verify Invitee list after Sign Up from referral url")
    public void test03VerifyInviteeListAfterSignUpFromReferralUrl() {
        String password = "ABcde1234567890";
        testData.setEmailAddress(new Date().getTime() + "@mailinator.com");
        String email = testData.getEmailAddress();
        String firstName = testData.getFirstName();
        String lastName = testData.getLastName();
        orderComment = testData.getStore().getCheckoutTermsAndConditions();

        List<String> questionOptions = testData.getStore().getCheckoutSurveyQuestionOptions();

        isSurveyQuestionPresent = questionOptions != null && !questionOptions.isEmpty();
        if (questionOptions != null && !questionOptions.isEmpty()) {
            provider = testData.getStore().getCheckoutSurveyQuestionOptions().get(0);
        }

        if (UI.SignIn().isLoggedIn()) {
            UI.Header().logOutClick();
        }
        UI.Header().logInClick();
        UI.SignIn().quickLogin(testData.getStore());

        UI.Header().accountClick();
        UI.Account().clickMyReward();
        UI.MyReward().clickMyReferralButton();

        String url = UI.MyReferral().getReferUrl();

        if (UI.SignIn().isLoggedIn()) {
            UI.Header().logOutClick();
        }
        driver.get(url);
        // Sign Up new user using referral Url
        UI.SignUp()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmailAddress(email)
                .enterPassword(password)
                .enterPasswordConfirmation(password)
                .clickSignUpButton()
                .verifySuccessfulRegistration(testData.getStore().getName());
        // Buy product by registered User
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .addToCartClick(testData.getFirstProductName());
        UI.Cart().waitUntilCartElementsLoaded();
        UI.Cart()
                .clickGoToCheckoutSpan();
        UI.Checkout()
                .waitUntilCheckoutLoaderDisappears();
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .enterCustomerFirstAddress(testData.getHomeAddress())
                .enterCustomerCity(testData.getCity())
                .selectCustomerCountry(testData.getCountry())
                .selectCustomerState(testData.getState())
                .enterCustomerZipCode(testData.getZipCode())
                .enterCustomerPhone(testData.getPhoneNumber())
                .clickBillingShippingTheSame()
                .enterCreditCardNumber(creditCardNumber)
                .selectExpirationMonth(expirationMonth)
                .selectExpirationYear(expirationYear)
                .enterCardVerificationNumber(verificationNumber)
                .clickBillingShippingTheSame();
        if (isSurveyQuestionPresent) {
            UI.Checkout().clickSurveyQuestion()
                    .selectSurveyProvider(provider);
        }
        UI.Checkout().clickTermsConditionsCheckbox()
                .clickPlaceOrder()
                .verifySuccessfulCheckout();
        // Check if the registered user appears in Invitee list
        UI.Header()
                .logOutClick()
                .logInClick();
        UI.SignIn().quickLogin(testData.getStore());
        UI.Header().accountClick();
        UI.Account().clickMyReward();
        UI.MyReward().clickMyReferralButton();
        UI.MyReferral().verifyInviteeList(email);
    }

}
