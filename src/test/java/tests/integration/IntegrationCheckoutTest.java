package tests.integration;

import data.bean.AdminAccount;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import tests.admin.AdminBaseTest;

import java.util.List;

public class IntegrationCheckoutTest extends AdminBaseTest {

    private final String creditCardNumber = "4111111111111111";
    private final String expirationMonth = "05 - May";
    private final String expirationYear = "2028";
    private final String verificationNumber = "111";

    private String orderComment;
    private String provider;
    private boolean isSurveyQuestionPresent;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "admin")) {
            AdminAccount adminAccount = testData.getStore().getAdminAccount();

            UI.Header().logInClick();
            UI.SignIn()
                    .quickLogin(testData.getStore());

            UI_Admin.AdminSignIn()
                    .enterUserName(adminAccount.getUserName())
                    .enterPassword(adminAccount.getPassword())
                    .clickSignIn();
            UI_Admin.AdminDashboard()
                    .closePopupMessages();
        }
    }

    @TmsLink("TC_Integration_Checkout_001")
    @Feature("Integration checkout page")
    @Test(groups = {"admin"}, description = "Verify checkout order status after approve")
    public void test01VerifyOrderStatus() {
        orderComment = testData.getStore().getCheckoutTermsAndConditions();

        List<String> questionOptions = testData.getStore().getCheckoutSurveyQuestionOptions();

        isSurveyQuestionPresent = questionOptions != null && !questionOptions.isEmpty();
        if (questionOptions != null && !questionOptions.isEmpty()) {
            provider = testData.getStore().getCheckoutSurveyQuestionOptions().get(0);
        }
        String orderStatus = "Complete";
        // Create order
        UI.Menu().selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts().addToCartClick(testData.getSecondProductName());
        UI.Cart()
                .waitUntilCartElementsLoaded()
                .clickGoToCheckoutSpan();
        UI.Checkout().waitUntilCheckoutLoaderDisappears();
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
        String orderNumber = UI.Checkout().getOrderNumber();
        // Approve order from admin
        UI_Admin.AdminDashboard()
                .clickMenu("Sales")
                .clickSubMenu("Orders");
        UI_Admin.AdminOrders().clickOrderByNumber(orderNumber);
        UI_Admin.AdminOrderDetails()
                .clickShipButton()
                .clickSubmitShipmentButton()
                .verifySuccessShipmentMessage();
        // Verify order status after approve
        UI.Header().accountClick();
        UI.Account().clickMyOrders();
        UI.MyOrders().verifyOrderStatusById(orderNumber, orderStatus);
    }

}
