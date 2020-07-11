package tests.signeduser;

import creation.bean.SocialMediaInfo;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.List;

public class SignedInFooterTest extends BaseTest {

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Header().logInClick();
            UI.SignIn()
                    .quickLogin(testData.getStore());
        }
    }


    @TmsLink("TC_Signed_Footer_Test_001")
    @Feature("Footer Contact Us")
    @Test(groups = "signedUser", description = "Verify that each link from <<Support & Information>> section redirects to appropriate page")
    public void test01SignedVerifyEachLinkFromSupportInformationSection() {
        UI.FooterSecondary()
                .aboutTheRewardsProgramClick()
                .verifySupportInformationLinkRedirection("REWARDS PROGRAM")
                .fitzpatrickScaleClick()
                .verifySupportInformationLinkRedirection("Fitzpatrick Scale")
                .termsConditionsClick()
                .verifyWebsiteTermsConditions()
                .returnPolicyClick()
                .verifySupportInformationLinkRedirection("Returns and Exchanges")
                .contactUsClick()
                .verifySupportInformationLinkRedirection("Contact Us");
    }




}
