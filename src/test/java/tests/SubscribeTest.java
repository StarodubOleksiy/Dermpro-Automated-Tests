package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;


public class SubscribeTest extends BaseTest {

    @TmsLink("TC_Subscribe_Test_001")
    @Feature("DermPRO Subscribe")
    @Test(description = "Subscribe empty email")
    public void test01SubscribeEmptyEmail() {
        UI.FooterPrimary()
                .subscribeButtonClick();
        UI.Subscribe()
                .verifyEmptySubscribeAlert();
    }

    @TmsLink("TC_Subscribe_Test_002")
    @Feature("DermPRO Subscribe")
    @Test(description = "Subscribe invalid email")
    public void test02SubscribeInvalidEmail() {
        UI.FooterPrimary()
                .enterEmail("example")
                .subscribeButtonClick();
        UI.Subscribe()
                .verifyInvalidSubscription();
    }

    @TmsLink("TC_Subscribe_Test_003")
    @Feature("DermPRO Subscribe")
    @Test(description = "Subscribe already subscribed email")
    public void test03AlreadySubscribedEmail() {
        UI.FooterPrimary()
                .clearEmail()
                .enterEmail("example@mailforspam.com")
                .subscribeButtonClick();
        UI.Subscribe()
                .verifyAlreadySubscribedAlert();
    }

    @TmsLink("TC_Subscribe_Test_004")
    @Feature("DermPRO Subscribe")
    @Test(description = "Subscribe valid email")
    public void test04SubscribeValidEmail() {
        UI.FooterPrimary()
                .clearEmail()
                .enterEmail(testData.getEmailAddress())
                .subscribeButtonClick();
        UI.Subscribe()
                .verifySuccessfulSubscription();
    }

}
