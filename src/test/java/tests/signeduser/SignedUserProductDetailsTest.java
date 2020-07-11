package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SignedUserProductDetailsTest extends BaseTest {

    private final String invalidEmail = "invalid";

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
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts()
                    .clickProductByName(testData.getFirstProductName());
        }
    }


    @TmsLink("TC_Signed_User_Product_Details_001")
    @Feature("Signed User Product Details")
    @Test(groups = "signedUser", description = "Verify send Email to friend successful")
    public void test01VerifySendEmailToFriend() {
        UI.ProductDetails()
                .clickEmailToFriendLink();
        UI.EmailToFriend()
                .verifyEmailToFriendPage()
                .enterSenderMessage(testData.getSentence())
                .enterInviteeName(testData.getFirstName(), 0)
                .enterInviteeEmail(testData.getEmailAddress(), 0)
                .clickSendEmailButton()
                .verifySendInviteMessage();
    }

    @TmsLink("TC_Signed_User_Product_Details_002")
    @Feature("Signed User Product Details")
    @Test(groups = "signedUser", description = "Validate sender/invitee Email with invalid data")
    public void test02ValidateSenderInviteeEmailFields() {
        UI.ProductDetails()
                .clickEmailToFriendLink();
        UI.EmailToFriend()
                .verifyEmailToFriendPage()
                .clearSenderEmail()
                .enterSenderEmail(invalidEmail)
                .enterInviteeEmail(invalidEmail, 0)
                .clickSendEmailButton()
                .validateSenderEmail()
                .validateInviteeEmail();
    }

    @TmsLink("TC_Signed_User_Product_Details_003")
    @Feature("Signed User Product Details")
    @Test(groups = "signedUser", description = "Verify send Email to friend with two invitee")
    public void test03VerifySendEmailToFriendWithTwoInvitee() {
        UI.ProductDetails()
                .clickEmailToFriendLink();
        UI.EmailToFriend()
                .verifyEmailToFriendPage()
                .enterSenderMessage(testData.getSentence())
                .enterInviteeName(testData.getFirstName(), 0)
                .enterInviteeEmail(testData.getEmailAddress(), 0)
                .clickAddInviteeButton()
                .enterInviteeName(testData.getFirstName(), 1)
                .enterInviteeEmail(testData.getEmailAddress(), 1)
                .clickSendEmailButton();
        UI.EmailToFriend()
                .verifySendInviteMessage();
    }

    @TmsLink("TC_Signed_User_Product_Details_004")
    @Feature("Signed User Product Details")
    @Test(groups = "signedUser", description = "Verify which of the Review link is displayed under the product name")
    public void test04VerifyReviewLinks() {
        if (UI.ProductDetails().isBeTheFirstPresent()) {
            UI.ProductDetails()
                    .clickBeTheFirstLink()
                    .verifyReviewTab();
        } else {
            UI.ProductDetails().verifyAddYouReview();
        }
    }

    @TmsLink("TC_Signed_User_Product_Details_005")
    @Feature("Signed User Product Details")
    @Test(groups = {"SO-28", "signedUser"}, description = "Verify required marks")
    public void test05VerifyRequiredMarks() {
        UI.ProductDetails()
                .clickOnReviewTab()
                .verifyRequiredFields();
    }

    @TmsLink("TC_Signed_User_Product_Details_006")
    @Feature("Signed User Product Details")
    @Test(groups = "signedUser", description = "Verify if Review options when it Enabled/Disabled in config")
    public void test06VerifyEnabledDisabledReviewOption() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-28")) {
            UI.ProductDetails().verifyEnabledReviewOptions();
        } else {
            UI.ProductDetails().verifyDisabledReviewOptions();
        }
    }

    @TmsLink("TC_Signed_User_Product_Details_007")
    @Feature("Signed User Product Details")
    @Test(groups = {"SO-36A", "signedUser"}, description = "Verify if Patient Certification is required field and redirection to cart")
    public void test07VerifyPatientCertification() {
        if (UI.ProductDetails().isPatientCertificationPresent()) {
            UI.ProductDetails()
                    .clickAddToCartButton()
                    .patientCertificationButtonValidation()
                    .clickPatientCertificationButton()
                    .clickAddToCartButton();
            UI.Cart()
                    .verifyProductAddToCart(testData.getFirstProductName())
                    .verifyCartPageTitle();
        } else {
            System.out.println("For current shop this feature is not available");
        }
    }

    @TmsLink("TC_Signed_User_Product_Details_008")
    @Feature("Signed User Product Details")
    @Test(groups = {"SO-36A", "signedUser"}, description = "Verify Product Scent Options")
    public void test08VerifyProductSelectOptions() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        if (UI.MenuPageWithProducts().isProductsStartingAtSpanPresent()) {
            UI.MenuPageWithProducts()
                    .clickTreatmentWithStartingAtSpan();
            if (!UI.ProductDetails().isRadioButtonsOptionsPresent()) {
                UI.ProductDetails()
                        .verifyBoxOptions();
            } else {
                UI.ProductDetails()
                        .verifyRadioButtonOptions();
            }
        }
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
    }

    @AfterClass
    public void after() {   //TODO need change methods for deleting data (if compare list empty skip next steps related to this. The same for Wish List & Cart)
        UI.CompareProducts()
                .deleteItemsFromCompare();
        UI.Account()
                .clickWishList();
        if (UI.WishlistLeftSideBar().verifyWishListIsNotEmpty()) {
            UI.WishlistLeftSideBar()
                    .removeAllItems();
        }
        UI.CartPopUp()
                .clickViewEditCart();
        UI.Cart()
                .clickClearShoppingCartButton();
    }

}