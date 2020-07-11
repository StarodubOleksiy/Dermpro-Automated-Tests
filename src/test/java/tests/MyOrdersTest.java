package tests;

import data.bean.OrderData;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.text.ParseException;

public class MyOrdersTest extends BaseTest {

    private final String creditCardNumber = "4111111111111111";
    private final String expirationMonth = "05 - May";
    private final String expirationYear = "2028";
    private final String verificationNumber = "111";

    private boolean isLogged = false;

    @Override
    public void init() { //TODO need finish with successful Checkout
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(testData.getStore());
                isLogged = true;
            }

            String orderComment = testData.getStore().getCheckoutTermsAndConditions();

            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts()
                    .addToCartClick(testData.getFirstProductName());
            UI.Cart()
                    .clickGoToCheckoutSpan();
            UI.Checkout()
                    .waitUntilCheckoutLoaderDisappears();


            //TODO uncomment once checkout will be working
//            String provider = testData.getStore().getCheckoutSurveyQuestionOptions().get(0);
//            String code = testData.getStore().getPromoCode().getStandardCode();
//        UI.Checkout()
//                .waitUntilCheckoutPageLoads()
//                .enterCreditCardNumber(creditCardNumber)
//                .selectExpirationMonth(expirationMonth)
//                .selectExpirationYear(expirationYear)
//                .enterCardVerificationNumber(verificationNumber)
//                .clickSurveyQuestion()
//                .selectSurveyProvider(provider)
//                .clickTermsConditionsCheckbox()
//                .enterOrdersComment(orderComment)
//                .clickPlaceOrder()
//                .verifySuccessfulCheckout();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Header()
                    .accountClick();
            UI.Account()
                    .clickMyOrders();
        }
    }

    @TmsLink("TC_my_orders_001")
    @Feature("DermPRO My Orders page")
    @Test(groups = {"signedUser"}, description = "Verify My Orders page")
    public void test01VerifyMyOrdersPage() {
        UI.MyOrders()
                .verifyMyOrderPage();
    }

    @TmsLink("TC_my_orders_002")
    @Feature("DermPRO My Orders page")
    @Test(groups = {"signedUser"}, description = "Verify View order option")
    public void test02VerifyViewOrder() {
        UI.MyOrders()
                .clickFirstOrderViewButton();
        UI.ViewOrder()
                .verifyViewOrderPage()
                .clickBackToMyOrdersButton();
        UI.MyOrders()
                .verifyMyOrderPage();
    }

    @TmsLink("TC_my_orders_003")
    @Feature("DermPRO My Orders page")
    @Test(groups = {"signedUser"}, description = "Verify Reorder product option")
    public void test03VerifyReorderProduct() {
        UI.MyOrders()
                .clickFirstReorderButton();
        UI.Cart()
                .verifyCartPage(testData.getFirstProductName());
    }

    @TmsLink("TC_view_order_004")
    @Feature("DermPRO View Order page")
    @Test(groups = {"signedUser"}, description = "Verify Reorder product option from current order")
    public void test04VerifyReorderProductFromOrder() {
        UI.MyOrders()
                .clickFirstOrderViewButton();
        UI.ViewOrder()
                .clickReorderButton();
        UI.Cart()
                .verifyCartPage(testData.getFirstProductName());
    }

    @TmsLink("TC_view_order_005")
    @Feature("DermPRO View Order page")
    @Test(groups = {"signedUser"}, description = "Verify information of Ordered Item")
    public void test05VerifyViewOrderInformation() throws ParseException {
        OrderData data = UI.MyOrders().getFirstOrderData();
        UI.MyOrders()
                .clickFirstOrderViewButton();
        UI.ViewOrder()
                .verifyOrderedItemInformation(data.getOrderId(), data.getDate(), data.getStatus(), data.getGrandTotal())
                .verifyOrderInformation()
                .clickInvoicesTab()
                .verifyInvoiceInformation(data.getGrandTotal());
    }

    @TmsLink("TC_view_order_006")
    @Feature("DermPRO View Order page")
    @Test(groups = "signedUser", description = "Verify <<Back>> link")
    public void test06VerifyBackLink() {
        UI.MyOrders()
                .clickBackLink();
        UI.AccountDashboard().verifyAccountDashboardPage();
    }
}
