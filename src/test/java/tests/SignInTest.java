package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {

    @TmsLink("TC_Sign_In_001")
    @Feature("DermPRO Sign In page")
    @Test(description = "Forgot password test")
    public void test01ForgotAndResetPassword() throws Exception {
        String email = testData.getEmailAddress();
        UI.Header().logInClick();
        UI.SignIn()
                .clickForgotYourPassword()
                .enterEmailForResetPassword(email)
                .clickResetMyPasswordButton()
                .verifyReceiveAnEmailWithALink(email);
    }

    @TmsLink("TC_Sign_In_002")
    @Feature("DermPRO Sign In page")
    @Test(description = "test empty fields")
    public void test02EmptyFields() throws Exception {
        UI.Header().logInClick();
        UI.SignIn()
                .clickSignInButton()
                .verifyEmptyFields();
    }

    @TmsLink("TC_Sign_In_003")
    @Feature("DermPRO Sign In page")
    @Test(description = "test invalid fields")
    public void test03InvalidFields() {
        UI.Header().logInClick();
        UI.SignIn()
                .enterEmail("1")
                .enterPassword("1")
                .clickSignInButton()
                .verifyInvalidEmail();
    }

    @TmsLink("TC_Sign_In_004")
    @Feature("DermPRO Sign In page")
    @Test(description = "Invalid email and password test")
    public void test04InvalidScenarios() {
        String email = "someemail@yahoo.com";
        String password = "12";
        UI.Header().logInClick();
        UI.SignIn()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignInButton()
                .verifyInvalidSignIn();
    }

    @TmsLink("TC_Sign_In_005")
    @Feature("DermPRO Sign In page")
    @Test(groups = "signedUser", description = "Sign In to store successful flow")
    public void test05SignInSuccessfulFlow() {
        UI.Header().logInClick();
        UI.SignIn()
                .quickLogin(testData.getStore());
        UI.Header()
                .verifyDashboardForSignedUser()
                .logOutClick();
    }

    @TmsLink("TC_Sign_In_006")
    @Feature("DermPRO Sign In page")
    @Test(groups = "signedUser", description = "Sign Out flow")
    public void test06SignOutFlow() {
        UI.Header().logInClick();
        UI.SignIn()
                .quickLogin(testData.getStore());
        UI.Header().logOutClick();
        UI.Header()
                .verifyDashboardForGuest();
    }

}
