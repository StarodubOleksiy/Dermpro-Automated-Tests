package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class ContactUsTest extends BaseTest {
//pre requirements: captcha should be disabled

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "guest")) {
            UI.FooterBottom().contactUsClick();
        }
    }

    @TmsLink("TC_Guest_Contact_Us_Test_001")
    @Feature("DermPRO Guest Contact Us page")
    @Test(groups = {"guest"}, description = "Guest - successfully send Contact Us request")
    public void test01GuestSuccessContactUsRequest() {
        UI.ContactUs()
                .enterName(testData.getFirstName())
                .enterEmail(testData.getEmailAddress())
                .enterPhone(testData.getPhoneNumber())
                .enterComment(testData.getLastName())
                .clickSubmitButton()
                .verifyContactUsSuccessMessage();
    }

    @TmsLink("TC_Contact_Us_Test_002")
    @Feature("DermPRO Contact Us page")
    @Test(groups = {"guest"}, description = "Guest - Verify required fields")
    public void test02GuestVerifyRequiredFields() {
        UI.ContactUs()
                .clickSubmitButton()
                .verifyRequiredFields();
    }

    @TmsLink("TC_Guest_Contact_Us_Test_003")
    @Feature("DermPRO Guest Contact Us page")
    @Test(groups = {"guest"}, description = "Guest - check if email format is valid")
    public void test03GuestInvalidEmailFormat() {
        UI.ContactUs()
                .enterEmail(testData.getLastName())
                .clickSubmitButton()
                .verifyInvalidEmail();
    }

}
