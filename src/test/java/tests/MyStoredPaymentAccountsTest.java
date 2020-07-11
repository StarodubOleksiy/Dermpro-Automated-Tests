package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class MyStoredPaymentAccountsTest extends BaseTest {

    private boolean paymentCardCreated = false;


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
                    .clickMyStoredPaymentAccounts();
        }
    }

    @TmsLink("TC_My_Stored_Payment_Accounts_Test_001")
    @Feature("DermPRO My Stored Payment Accounts page")
    @Test(groups = "signedUser", description = "My Stored Payment Accounts Success Flow")
    public void test01SuccessFlow() {
        UI.MyStoredPaymentAccounts()
                .enterCreditCardNumberInput(testData.getMasterCardCreditCardNumber())
                .enterFirstName(testData.getFirstName())
                .enterLastName(testData.getLastName())
                .setDate(testData.getMonth(), testData.getYear())
                .enterTelephone(testData.getPhoneNumber())
                .enterStreetAddress1(testData.getHomeAddress())
                .enterStreetAddress2(testData.getSecondaryAddress())
                .enterCity(testData.getCity())
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickSubmitButton()
                .verifyStoredPaymentAccountsMessage("Card Added Sucessfully.");
        paymentCardCreated = true;
    }

    @TmsLink("TC_My_Stored_Payment_Accounts_Test_002")
    @Feature("DermPRO My Stored Payment Accounts page")
    @Test(groups = "signedUser", description = "My Stored Payment Accounts invalid credit card")
    public void test02InvalidCreditCard() {
        UI.MyStoredPaymentAccounts()
                .enterCreditCardNumberInput("123")
                .enterFirstName(testData.getFirstName())
                .enterLastName(testData.getLastName())
                .setDate(testData.getMonth(), testData.getYear())
                //.enterCompany(testData.getCompany())
                .enterTelephone(testData.getPhoneNumber())
                .enterStreetAddress1(testData.getHomeAddress())
                .enterStreetAddress2(testData.getSecondaryAddress())
                .enterCity(testData.getCity())
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickSubmitButton()
                .verifyStoredPaymentAccountsMessage("There was an unspecified error");
    }

    @TmsLink("TC_My_Stored_Payment_Accounts_Test_003")
    @Feature("DermPRO My Stored Payment Accounts page")
    @Test(groups = "signedUser", description = "My Stored Payment Accounts test empty fields")
    public void test03EmptyFields() {
        UI.MyStoredPaymentAccounts()
                .clickSubmitButton()
                .verifyEmptyFields();
    }

    @TmsLink("TC_My_Stored_Payment_Accounts_Test_004")
    @Feature("DermPRO My Stored Payment Accounts page")
    @Test(groups = "signedUser", description = "My Stored Payment Accounts delete Stored Payment Account")
    public void test04DeleteStoredPaymentAccount() {
        UI.MyStoredPaymentAccounts()
                .enterCreditCardNumberInput(testData.getVisaCreditCardNumber())
                .enterFirstName(testData.getFirstName())
                .enterLastName(testData.getLastName())
                .setDate(testData.getMonth(), testData.getYear())
                .enterTelephone(testData.getPhoneNumber())
                .enterStreetAddress1(testData.getHomeAddress())
                .enterStreetAddress2(testData.getSecondaryAddress())
                .enterCity(testData.getCity())
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickSubmitButton();
        String number = testData.getVisaCreditCardNumber();
        String fourLastDigitsOfNumber = String.valueOf(number.toCharArray(), number.length() - 4, 4);
        UI.MyStoredPaymentAccounts()
                .chooseStoredPaymentAccount(fourLastDigitsOfNumber)
                .clickDeleteButton()
                .clickOkButton()
                .verifySuccessfullyDeletedPaymentAccount();
    }

    @TmsLink("TC_My_Stored_Payment_Accounts_Test_005")
    @Feature("DermPRO My Stored Payment Accounts page")
    @Test(groups = "signedUser", description = "My Stored Payment Accounts Card Account Not valid")
    public void test05CardAccountNotValid() {
        UI.MyStoredPaymentAccounts()
                .enterCreditCardNumberInput("1234567890987654")
                .enterFirstName(testData.getFirstName())
                .enterLastName(testData.getLastName())
                .setDate(testData.getMonth(), testData.getYear())
                //.enterCompany(testData.getCompany())
                .enterTelephone(testData.getPhoneNumber())
                .enterStreetAddress1(testData.getHomeAddress())
                .enterStreetAddress2(testData.getSecondaryAddress())
                .enterCity(testData.getCity())
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickSubmitButton()
                .verifyStoredPaymentAccountsMessage("CARD ACCOUNT NOT VALID");
    }


    @TmsLink("TC_My_Stored_Payment_Accounts_Test_006")
    @Feature("DermPRO My Stored Payment Accounts page")
    @Test(groups = "signedUser", description = "My Stored Payment Accounts number too long")
    public void test06CardNumberTooLong() {
        UI.MyStoredPaymentAccounts()
                .enterCreditCardNumberInput("1234567890101112131415161718192021222324252627282930")
                .enterFirstName(testData.getFirstName())
                .enterLastName(testData.getLastName())
                .setDate(testData.getMonth(), testData.getYear())
                //.enterCompany(testData.getCompany())
                .enterTelephone(testData.getPhoneNumber())
                .enterStreetAddress1(testData.getHomeAddress())
                .enterStreetAddress2(testData.getSecondaryAddress())
                .enterCity(testData.getCity())
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickSubmitButton()
                .verifyStoredPaymentAccountsMessage("CC_NUM too long");
    }

    @TmsLink("TC_My_Stored_Payment_Accounts_Test_007")
    @Feature("DermPRO My Stored Payment Accounts page")
    @Test(groups = "signedUser", description = "My Stored Payment Accounts Verify <<Back>> link")
    public void test07VerifyBackLink() {
        UI.MyStoredPaymentAccounts()
                .clickBackLink();
        UI.AccountDashboard().verifyAccountDashboardPage();
    }

    @TmsLink("TC_My_Stored_Payment_Accounts_Test_008")
    @Feature("DermPRO My Stored Payment Accounts page")
    @Test(groups = "signedUser", description = "My Stored Payment Accounts invalid expiration date")
    public void test08InvalidExpirationDate() {
        LocalDate date = LocalDate.now().minusMonths(1);
        UI.MyStoredPaymentAccounts()
                .enterCreditCardNumberInput(testData.getMasterCardCreditCardNumber())
                .enterFirstName(testData.getFirstName())
                .enterLastName(testData.getLastName())
                .setDate(date.getMonth().toString().toLowerCase(), testData.getYear())
                .enterTelephone(testData.getPhoneNumber())
                .enterStreetAddress1(testData.getHomeAddress())
                .enterStreetAddress2(testData.getSecondaryAddress())
                .enterCity(testData.getCity())
                .setStateProvince(testData.getState())
                .enterZipPostalCode(testData.getZipCode())
                .clickSubmitButton()
                .verifyStoredPaymentAccountsMessage("Card Expired");
    }


    @AfterClass
    public void after() {
        if (paymentCardCreated) {
            String number = testData.getMasterCardCreditCardNumber();
            String fourLastDigitsOfNumber = String.valueOf(number.toCharArray(), number.length() - 4, 4);
            UI.MyStoredPaymentAccounts()
                    .chooseStoredPaymentAccount(fourLastDigitsOfNumber)
                    .clickDeleteButton()
                    .clickOkButton();
            paymentCardCreated = false;
        }
    }

}

