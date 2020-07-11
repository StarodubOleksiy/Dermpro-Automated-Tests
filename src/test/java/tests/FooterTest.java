package tests;

import creation.bean.SocialMediaInfo;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.List;

public class FooterTest extends BaseTest {

    @TmsLink("TC_Guest_Footer_Test_001")
    @Feature("Guest_Footer Contact Us")
    @Test(groups = {"guest"}, description = "Verify that each link from <<Support & Information>> section redirects to appropriate page as guest")
    public void test01GuestVerifyEachLinkFromSupportInformationSection() {
        UI.FooterSecondary()
                .aboutTheRewardsProgramClick()
                .verifySupportInformationLinkRedirection("Rewards Program")
                .fitzpatrickScaleClick()
                .verifySupportInformationLinkRedirection("Fitzpatrick Scale")
                .termsConditionsClick()
                .verifyWebsiteTermsConditions()
                .returnPolicyClick()
                .verifySupportInformationLinkRedirection("Returns and Exchanges")
                .contactUsClick()
                .verifySupportInformationLinkRedirectionContactUs();
    }

    @TmsLink("TC_Footer_Test_002")
    @Feature("Footer Contact Us")
    @Test(description = "Verify Contact Us information as guest")
    public void test02VerifyContactUsInformation() {
        String text = testData.getStore().getContactInformation();
        UI.FooterPrimary()
                .verifyContactUsInformation(text);
    }

    @TmsLink("TC_Footer_Test_003")
    @Feature("Footer Social Info")
    @Test(groups = {"SO-13A"}, description = "Verify Footer Social Information as guest")
    public void test03GuestVerifyFooterSocialInfo() {
        List<SocialMediaInfo> name = testData.getStore().getFooterIconsInfo();
        UI.FooterPrimary()
                .verifySocialsBlock(name);
    }

    @TmsLink("TC_Footer_Test_004")
    @Feature("Footer Contact Us")
    @Test(groups = {"SO-13A"}, description = "Verify that each link from <<MY ACCOUNT>> section redirects to appropriate page as Guest")
    public void test04VerifyEachLinkFromMyAccountSection() {
        UI.FooterSecondary().accountClick();
        UI.SignIn().verifySignInPage();
        UI.FooterSecondary().myAddressesClick();
        UI.SignIn().verifySignInPage();
        UI.FooterSecondary().myOrderHistoryClick();
        UI.SignIn().verifySignInPage();
        UI.FooterSecondary().myRewardsClick();
        UI.SignIn().verifySignInPage();
    }

}
