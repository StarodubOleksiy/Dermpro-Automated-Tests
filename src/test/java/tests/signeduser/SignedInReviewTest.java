package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SignedInReviewTest extends BaseTest {

    private int numberOfReviews;
    private String nickName;

    private boolean isLogged = false;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-28") && isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(testData.getStore());
                isLogged = true;
            }

            UI.Header()
                    .accountClick();
            nickName = UI.AccountDashboard().getFirstName();

            String summary = testData.getSentence();
            String review = testData.getSecondaryAddress();
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts().clickProductByName(testData.getFirstProductName());
            UI.ProductDetails()
                    .waitUntilCheckoutLoaderDisappears();
            UI.ProductDetails()
                    .clickAddReview();
            UI.ProductDetailsReview()
                    .clickRating(5)
                    .enterSummary(summary)
                    .enterReview(review)
                    .clickSubmitReviewButton()
                    .verifyReviewSubmittedSuccessfully();
            UI.Header()
                    .accountClick();
            UI.Account()
                    .clickMyProductReviews();
            numberOfReviews = UI.MyProductReview().getNumbersOfReview();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-28") && isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
        }
    }

    @TmsLink("TC_Product_Review_001")
    @Feature("Product_Review")
    @Test(groups = {"SO-28", "signedUser"}, description = "Submit product review successful flow")
    public void test01SubmitReviewSuccessfulFlow() {
        String summary = testData.getSentence();
        String review = testData.getSecondaryAddress();
        UI.MenuPageWithProducts().clickProductByName(testData.getFirstProductName());
        UI.ProductDetails()
                .waitUntilCheckoutLoaderDisappears();
        UI.ProductDetails()
                .clickAddReview();
        UI.ProductDetailsReview()
                .clickRating(5)
                .enterSummary(summary)
                .enterReview(review)
                .clickSubmitReviewButton()
                .verifyReviewSubmittedSuccessfully();
        int finalNumbersOfReview = ++numberOfReviews;
        if (!UI.ProductDetailsReview().isReviewsAreNotPresent()) {
            UI.ProductDetails()
                    .clickOnReviewTab();
            UI.ProductDetailsReview()
                    .verifyReviews();
            UI.Header()
                    .accountClick();
            UI.Account()
                    .clickMyProductReviews();
            UI.MyProductReview()
                    .verifyProductReview(testData.getFirstProductName(), review)
                    .verifyNumbersOfProductReview(finalNumbersOfReview)
                    .clickSeeDetailsLink(0)
                    .verifyProductReviewDetails(testData.getFirstProductName(), review, summary);
        }
    }

    @TmsLink("TC_Product_Review_002")
    @Feature("Product_Review")
    @Test(groups = {"SO-28", "signedUser"}, description = "Verify product review is opened with specified Nickname")
    public void test02VerifyProductReviewIsOpenedWithSpecifiedNickName() {
        UI.MenuPageWithProducts().clickProductByName(testData.getFirstProductName());
        UI.ProductDetails()
                .waitUntilCheckoutLoaderDisappears();
        UI.ProductDetails()
                .clickAddReview();
        UI.ProductDetailsReview().
                verifyNickName(nickName);
    }

    @TmsLink("TC_Product_Review_003")
    @Feature("Product_Review")
    @Test(groups = {"SO-28", "signedUser"}, description = "Product review verify empty fields")
    public void test03VerifyEmptyFields() {
        UI.MenuPageWithProducts().clickProductByName(testData.getFirstProductName());
        UI.ProductDetails()
                .waitUntilCheckoutLoaderDisappears();
        UI.ProductDetails()
                .clickAddReview();
        UI.ProductDetailsReview()
                .clearNickName()
                .clickSubmitReviewButton()
                .verifyEmptyFields();
    }

    @TmsLink("TC_Product_Review_005")
    @Feature("Product_Review")
    @Test(groups = {"SO-28", "signedUser"}, description = "Verify <<Back>> link on My Product Reviews page")
    public void test05MyProductReviewBackLink() {
        UI.Header()
                .accountClick();
        UI.Account()
                .clickMyProductReviews();
        UI.MyProductReview()
                .clickBackLink();
        UI.AccountDashboard()
                .verifyAccountDashboardPage();
    }

    @TmsLink("TC_Product_Review_006")
    @Feature("Product_Review")
    @Test(groups = {"SO-28", "signedUser"}, description = "Verify pagination (total number of review) on My Product Reviews page")
    public void test06VerifyProductReviewPagination() {
        UI.Header()
                .accountClick();
        UI.Account()
                .clickMyProductReviews();
        UI.MyProductReview()
                .verifyPagination()
                .verifyNumbersOfProductReview(UI.MyProductReview().getNumbersOfReview());
    }


    @TmsLink("TC_Product_Review_007")
    @Feature("Product_Review")
    @Test(groups = {"SO-28", "signedUser"}, description = "Verify pagination on My Product Reviews page")
    public void test07VerifyAccountMyProductReviewsContent() {
        UI.Header()
                .accountClick();
        UI.Account()
                .clickMyProductReviews();
        UI.MyProductReview().verifyMyProductReviewContent();
        String productName = UI.MyProductReview().getProductName(0);
        UI.MyProductReview().clickSeeDetailsLink(0);
        UI.ProductDetailsReview()
                .verifyProductReviewName(productName);
    }

    @TmsLink("TC_Product_Review_008")
    @Feature("Product_Review")
    @Test(groups = {"SO-28", "signedUser"}, description = "Verify product review for not registered user")
    public void test08VerifyProductReviewForNotRegisteredUser() {
        UI.Header().logOutClick();
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts().clickProductByName(testData.getFirstProductName());
        UI.ProductDetails()
                .waitUntilCheckoutLoaderDisappears();
        UI.ProductDetails()
                .clickOnReviewTab();
        UI.ProductDetails().verifyOnlyRegisteredUsersCanWriteReviews();
    }


}
