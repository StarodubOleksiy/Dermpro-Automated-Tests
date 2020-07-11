package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import tests.BaseTest;


public class SignedUserServicesTest extends BaseTest {

    private final String creditCardNumber = "4111111111111111";
    private final String expirationMonth = "05 - May";
    private final String expirationYear = "2028";
    private final String verificationNumber = "111";
    private String orderComment;

    private boolean isLogged = false;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-35") && isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(testData.getStore());
                isLogged = true;
            }
            UI.clearCart();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-35") && isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Menu()
                    .selectMenu(testData.getStore().getServices().getMenuTitle());
        }
    }

    @TmsLink("TC_Signed_User_Services_001")
    @Feature("Signed_User_Services")
    @Test(groups = {"signedUser"}, description = "Create checkout and verify order to current services product")
    public void test01TreatmentGoToCheckoutVerifyMyOrder() throws Exception {
        orderComment = testData.getStore().getCheckoutTermsAndConditions();
        String productName = UI.ServicesList().getProductName(0);
        UI.ServicesList()
                .addToCartClick(productName);
        UI.Cart()
                .waitUntilCartElementsLoaded()
                .clickGoToCheckoutSpan();
        UI.Checkout().waitUntilCheckoutLoaderDisappears();
        UI.Checkout().verifyCheckoutPage();
        if (UI.Checkout().ifPaymentInputFieldsArePresent())
            UI.Checkout()
                    .enterCustomerCity(testData.getCity())
                    .selectCustomerCountry(testData.getCountry())
                    .selectCustomerState(testData.getState())
                    .enterCustomerZipCode(testData.getZipCode())
                    .enterCustomerPhone(testData.getPhoneNumber())
                    .clickOnPaymentUpdateButton();
        UI.Checkout()
                .enterCreditCardNumber(creditCardNumber)
                .selectExpirationMonth(expirationMonth)
                .selectExpirationYear(expirationYear)
                .enterCardVerificationNumber(verificationNumber)
                .clickTermsConditionsCheckbox()
                .enterOrdersComment(orderComment)
                .clickPlaceOrder()
                .verifySuccessfulCheckout();
    }

    @AfterClass
    public void after() {
        UI.clearCart();
    }

}
