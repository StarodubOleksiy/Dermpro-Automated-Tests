package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.List;

public class SignedInCheckoutTest extends BaseTest {

    private final String creditCardNumber = "4111111111111111";
    private final String invalidCreditCardNumber = "4111";
    private final String invalidCreditCardMessage = "Invalid credit card number.";
    private final String expirationMonth = "05 - May";
    private final String invalidExpirationDateMessage = "Invalid card expiration date.";
    private final String expirationYear = "2028";
    private final String verificationNumber = "111";
    private final String invalidCvvMessage = "Invalid Card Verification Number.";
    private final String invalidDiscountCode = "qwer";
    private final String invalidGiftNumber = "12345";
    private final String giftMessageTo = "Bred";
    private final String giftMessageFrom = "Cory";
    private final String giftMessage = "Hello";

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
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts()
                    .addToCartClick(testData.getFirstProductName());
            UI.Cart()
                    .clickGoToCheckoutSpan();
            UI.Checkout().waitUntilCheckoutLoaderDisappears();

            orderComment = testData.getStore().getCheckoutTermsAndConditions();

            List<String> questionOptions = testData.getStore().getCheckoutSurveyQuestionOptions();

            isSurveyQuestionPresent = questionOptions != null && !questionOptions.isEmpty();
            if (questionOptions != null && !questionOptions.isEmpty()) {
                provider = testData.getStore().getCheckoutSurveyQuestionOptions().get(0);
            }
        }
    }

    @TmsLink("TC_Checkout_001")
    @Feature("DermPRO checkout page")
    @Test(groups = "signedUser", description = "Verify if you on checkout page")
    public void test01VerifyCheckoutPage() {
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .verifyCheckoutPage();
    }

    @TmsLink("TC_Checkout_002")
    @Feature("DermPRO checkout page")
    @Test(groups = "signedUser", description = "Verify invalid gift card message")
    public void test02VerifyInvalidGiftCardMessage() {
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .clickApplyGiftCard()
                .enterGiftCard(invalidGiftNumber)
                .clickApplyGiftCardButton()
                .verifyInvalidGiftCardMessage(invalidGiftNumber);
    }

    @TmsLink("TC_Checkout_003")
    @Feature("DermPRO checkout page")
    @Test(groups = "signedUser", description = "Verify Gift message")
    public void test03VerifyGiftMessage() {
        UI.Checkout()
                .waitUntilCheckoutPageLoads();
        if (UI.Checkout().isGiftCardCheckboxChecked()) {
            UI.Checkout().clickEditGiftMessageLink();
        } else {
            UI.Checkout().clickAddGiftMessageCheckbox();
        }
        UI.Checkout().enterGiftMessageTo(giftMessageTo)
                .enterGiftMessageFrom(giftMessageFrom)
                .enterGiftMessage(giftMessage)
                .clickUpdateGiftMessageButton()
                .verifyGiftMessageFilled();
    }

    @TmsLink("TC_Checkout_004")
    @Feature("DermPRO checkout page")
    @Test(groups = "signedUser", description = "Verify applying free shipping discount code and cancel")
    public void test04ApplyFreeShippingDiscountCodeAndCancel() {
        UI.Checkout()
                .waitUntilCheckoutLoaderDisappears();
        UI.Checkout()
                .waitUntilCheckoutPageLoads();
        if (UI.Checkout().isDiscountCodeFilled() && UI.Checkout().isCancelButtonVisible()) {
            UI.Checkout()
                    .clickCancelCouponButton()
                    .verifyCancelDiscountCodeMessage();
        } else if (UI.Checkout().isDiscountCodeFilled()) {
            UI.Checkout().clickApplyDiscountCode()
                    .clickCancelCouponButton()
                    .verifyCancelDiscountCodeMessage();
        } else {
            UI.Checkout().clickApplyDiscountCode();
        }
        UI.Checkout()
                .enterDiscountCode(testData.getStore().getPromoCode().getStandardCode())
                .clickApplyDiscountCodeButton()
                .waitUntilDiscountCodeMessageDisappears()
                .verifyFreeShippingDiscountCode()
                .waitUntilCheckoutLoaderDisappears();
        UI.Checkout()
                .clickCancelCouponButton()
                .verifyCancelDiscountCodeMessage()
                .waitUntilCheckoutLoaderDisappears();
        UI.Checkout()
                .clickApplyDiscountCode();
    }

    @TmsLink("TC_Checkout_005")
    @Feature("DermPRO checkout page")
    @Test(groups = "signedUser", description = "Verify invalid discount code message")
    public void test05VerifyInvalidDiscountCodeMessage() {
        UI.Checkout()
                .waitUntilCheckoutLoaderDisappears();
        UI.Checkout()
                .waitUntilCheckoutPageLoads();
        if (UI.Checkout().isDiscountCodeFilled() && UI.Checkout().isCancelButtonVisible()) {
            UI.Checkout()
                    .clickCancelCouponButton()
                    .verifyCancelDiscountCodeMessage();
        } else if (UI.Checkout().isDiscountCodeFilled()) {
            UI.Checkout().clickApplyDiscountCode()
                    .clickCancelCouponButton()
                    .verifyCancelDiscountCodeMessage();
        } else {
            UI.Checkout().clickApplyDiscountCode();
        }
        UI.Checkout()
                .enterDiscountCode(invalidDiscountCode)
                .clickApplyDiscountCodeButton()
                .verifyInvalidDiscountCodeMessage();
    }


    @TmsLink("TC_Checkout_006")
    @Feature("DermPRO checkout page")
    @Test(groups = {"SO-46A", "signedUser"}, description = "Verify checkout survey question options")
    public void test06VerifyCheckoutSurveyQuestionOptions() {
        String provider = testData.getStore().getCheckoutSurveyQuestionOptions().get(0);
        List<String> checkoutSurveyQuestionOptions = testData.getStore().getCheckoutSurveyQuestionOptions();
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .clickSurveyQuestion()
                .selectSurveyProvider(provider);

        checkoutSurveyQuestionOptions.forEach(
                option -> {
                    UI.Checkout().verifyCheckoutSurveyQuestionOptions(option);
                }
        );
    }

    @TmsLink("TC_Checkout_007")
    @Feature("DermPRO checkout page")
    @Test(groups = {"SO-46A", "signedUser"}, description = "Verify checkout survey question ")
    public void test07VerifyCheckoutSurveyQuestion() {
        String checkoutSurveyQuestion = testData.getStore().getCheckoutSurveyQuestion();
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .verifyCheckoutSurveyQuestion(checkoutSurveyQuestion);
    }

    @TmsLink("TC_Checkout_008")
    @Feature("DermPRO checkout page")
    @Test(groups = {"SO-46A", "signedUser"}, description = "Verify Survey question alert")
    public void test08VerifySurveyQuestionAlert() {
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .clickPlaceOrder()
                .verifySurveyQuestionAlert()
                .clickSurveyMessageOkButton();
    }

    @TmsLink("TC_Checkout_009")
    @Feature("DermPRO checkout page")
    @Test(groups = "signedUser", description = "Verify validation of error messages in the payment method")
    public void test09VerifyErrorMessagesInPaymentMethod() {
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .enterCreditCardNumber(invalidCreditCardNumber);
        if (isSurveyQuestionPresent) {
            UI.Checkout().clickSurveyQuestion()
                    .selectSurveyProvider(provider);
        }
        UI.Checkout().clickTermsConditionsCheckbox()
                .enterOrdersComment(orderComment)
                .clickPlaceOrder()
                .verifyValidationErrorsInPaymentMethod(invalidCreditCardMessage)
                .clearCreditCardNumber()
                .enterCreditCardNumber(creditCardNumber)
                .clickPlaceOrder()
                .verifyValidationErrorsInPaymentMethod(invalidExpirationDateMessage)
                .selectExpirationMonth(expirationMonth)
                .clickPlaceOrder()
                .verifyValidationErrorsInPaymentMethod(invalidExpirationDateMessage)
                .selectExpirationYear(expirationYear)
                .clickPlaceOrder()
                .verifyValidationErrorsInPaymentMethod(invalidCvvMessage)
                .clearCreditCardNumber();
    }

    @TmsLink("TC_Checkout_010")
    @Feature("DermPRO checkout page")
    @Test(groups = {"SO-44A", "signedUser"}, description = "Successful Checkout with Pickup from the store")
    public void test10PickupOrderInOffice() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .addToCartClick(testData.getThirdProductName());
        UI.Cart()
                .clickGoToCheckoutSpan();
        UI.Checkout()
                .waitUntilCheckoutLoaderDisappears();
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .clickOrderInOfficeYesButton()
                .selectStoreForPickup()
                .enterCreditCardNumber(creditCardNumber)
                .selectExpirationMonth(expirationMonth)
                .selectExpirationYear(expirationYear)
                .enterCardVerificationNumber(verificationNumber);
        if (isSurveyQuestionPresent) {
            UI.Checkout().clickSurveyQuestion()
                    .selectSurveyProvider(provider);
        }
        UI.Checkout().clickTermsConditionsCheckbox()
                .enterOrdersComment(orderComment)
                .clickPlaceOrder()
                .verifySuccessfulCheckout();
    }

    @TmsLink("TC_Checkout_011")
    @Feature("DermPRO checkout page")
    @Test(groups = {"signedUser"}, description = "Verify successful checkout")
    public void test11SuccessfulCheckout() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .addToCartClick(testData.getSecondProductName());
        UI.Cart()
                .clickGoToCheckoutSpan();
        UI.Checkout()
                .waitUntilCheckoutLoaderDisappears();
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
                .enterOrdersComment(orderComment)
                .clickPlaceOrder()
                .verifySuccessfulCheckout();
    }


    @TmsLink("TC_Checkout_012")
    @Feature("DermPRO checkout page")
    @Test(groups = {"signedUser"}, description = "Verify successful checkout with new billing address")
    public void test12SuccessfulCheckoutWithNewAddress() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .addToCartClick(testData.getThirdProductName());
        UI.Cart()
                .clickGoToCheckoutSpan();
        UI.Checkout()
                .waitUntilCheckoutLoaderDisappears();
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .clickNewAddressButton()
                .clearCustomerInformation()
                .enterCustomerFirstName(testData.getFirstName())
                .enterCustomerLastName(testData.getLastName())
                .enterCustomerFirstAddress(testData.getHomeAddress())
                .enterCustomerCity(testData.getCity())
                .selectCustomerCountry(testData.getCountry())
                .selectCustomerState(testData.getState())
                .enterCustomerZipCode(testData.getZipCode())
                .enterCustomerPhone(testData.getPhoneNumber())
                .clickSaveInAddressBookCheckbox()
                .clickSaveAddressButton()
                .enterCreditCardNumber(creditCardNumber)
                .selectExpirationMonth(expirationMonth)
                .selectExpirationYear(expirationYear)
                .enterCardVerificationNumber(verificationNumber);
        if (isSurveyQuestionPresent) {
            UI.Checkout().clickSurveyQuestion()
                    .selectSurveyProvider(provider);
        }
        UI.Checkout().clickTermsConditionsCheckbox()
                .enterOrdersComment(orderComment)
                .clickPlaceOrder()
                .verifySuccessfulCheckout();
    }


    @AfterClass
    public void after() {
        if (UI.CartPopUp().isElementsInCartPresent()) {
            UI.CartPopUp().clickViewEditCart();
            UI.Cart().clickClearShoppingCartButton();
        }
    }
}
