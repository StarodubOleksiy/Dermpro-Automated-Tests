package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SignedInContactUsTest extends BaseTest {
//pre requirements: captcha should be disabled

    private boolean isLogged = false;

    private String firstName;

    @Override
    public void init() {
        if(isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(testData.getStore());
                isLogged = true;
            }
            UI.Header()
                    .accountClick();
            firstName = UI.AccountDashboard().getFirstName();
        }
    }

    @Override
    public void initMethod() {
        if(isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.FooterBottom()
                    .contactUsClick();
            UI.ContactUs()
                    .clearAllFields();
        }
    }



    @TmsLink("TC_Contact_Us_Test_001")
    @Feature("DermPRO Contact Us page")
    @Test(groups = "signedUser", description = "Verify default name and email are displayed when open Contact Us page with logged user")
    public void test01VerifyNameAndEmailAreDisplayed() {
        UI.FooterBottom()
                .contactUsClick();
        UI.ContactUs()
                .verifyNameAndEmail(firstName, testData.getStore().getAccount().getEmail());
    }


}
