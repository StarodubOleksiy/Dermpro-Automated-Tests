package tests;

import creation.bean.AdditionalRequiredFields;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GuestCheckoutTest extends BaseTest {

    private final String creditCardNumber = "4111111111111111";
    private final String expirationMonth = "05 - May";
    private final String expirationYear = "2028";
    private final String verificationNumber = "111";

    private String termsAndConditions;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "guest")) {
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts()
                    .addToCartClick(testData.getSecondProductName());
            UI.Cart()
                    .clickGoToCheckoutSpan();
            termsAndConditions = testData.getStore().getCheckoutTermsAndConditions();
        }
    }

    @TmsLink("TC_Checkout_Guest_001")
    @Feature("DermPRO Guest checkout page")
    @Test(groups = {"SO-47", "guest"}, description = "Verify Additional required fields as guest")
    public void test01VerifyAdditionalRequiredFields() {
        List<AdditionalRequiredFields> data = testData.getStore().getAdditionalRequiredFields();
        UI.Checkout().verifyAdditionalRequiredFields(data);
    }


    @TmsLink("TC_Checkout_Guest_002")
    @Feature("DermPRO Guest checkout page")
    @Test(groups = {"SO-47", "guest"}, description = "Verify Terms and Conditions information as guest")
    public void test02VerifyTermsAndConditionsInformation() {
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .clickTermsConditionsLink()
                .verifyTermsConditionsInformation(termsAndConditions)
                .clickTermsConditionsCloseButton();
    }

    @TmsLink("TC_Checkout_Guest_003")
    @Feature("DermPRO Guest checkout page")
    @Test(groups = {"guest"}, description = "Verify guest checkout is allowed for Guests")
    public void test03VerifyGuestCheckoutIsAllowedForGuest() {
        if (!isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-47")) {
            UI.SignIn().verifyLoginPopup();
        } else {
            UI.Checkout().verifyCheckoutPage()
                    .verifyCreateAccountLater();
        }
    }

    @TmsLink("TC_Checkout_Guest_004")
    @Feature("DermPRO Guest checkout page")
    @Test(groups = {"SO-47", "guest"}, description = "Successful Checkout with Pickup from the store as guest")
    // some problem after first click on Place Order button, as temporary solution click Place Order twice
    public void test04SuccessfulCheckoutAsGuest() {
        LocalDate date = LocalDate.now();
        List<String> questionOptions = testData.getStore().getCheckoutSurveyQuestionOptions();
        String provider = null;
        boolean isSurveyQuestionPresent = questionOptions != null && !questionOptions.isEmpty();
        if (questionOptions != null && !questionOptions.isEmpty()) {
            provider = testData.getStore().getCheckoutSurveyQuestionOptions().get(0);
        }
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .enterCustomerEmail(testData.getEmailAddress())
                .enterCustomerFirstName(testData.getFirstName())
                .enterCustomerLastName(testData.getLastName())
                .enterCustomerFirstAddress(testData.getHomeAddress())
                .enterCustomerCity(testData.getCity())
                .selectCustomerCountry(testData.getCountry())
                .selectCustomerState(testData.getState())
                .enterCustomerZipCode(testData.getZipCode())
                .enterCustomerPhone(testData.getPhoneNumber());
        UI.Checkout()
                .clickBillingShippingTheSame()
                .enterCreditCardNumber(creditCardNumber)
                .selectExpirationMonth(expirationMonth)
                .selectExpirationYear(expirationYear)
                .enterCardVerificationNumber(verificationNumber);
        if (isSurveyQuestionPresent) {
            UI.Checkout().clickSurveyQuestion()
                    .selectSurveyProvider(provider);
        }
        UI.Checkout().clickTermsConditionsCheckbox()
                .enterOrdersComment(termsAndConditions)
                .clickSignUpNewsletterCheckbox()
                .clickPlaceOrder()
                .enterPaymentFirstName(testData.getFirstName())
                .enterPaymentLastName(testData.getLastName())
                .enterPaymentFirstAddress(testData.getHomeAddress())
                .enterPaymentCity(testData.getCity())
                .selectPaymentState(testData.getState())
                .enterPaymentZipCode(testData.getZipCode())
                .enterPaymentPhone(testData.getPhoneNumber())
                .clickOnPaymentUpdateButton()
                .clickOpenDatePicker()
                .enterDateOfBirth(String.valueOf(date.getDayOfMonth() - 1))
                .clickPlaceOrder()
                .verifySuccessfulCheckout()
                .clickContinueShoppingButton();
    }


}
