package tests.integration;

import data.bean.AdminAccount;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.admin.AdminBaseTest;

import java.time.LocalDateTime;

public class IntegrationReviewTest extends AdminBaseTest {

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-28") && isFeatureEnabled(testData.getStore().getFeatureStates(), "admin")) {
            AdminAccount adminAccount = testData.getStore().getAdminAccount();

            UI.Header().logInClick();
            UI.SignIn()
                    .quickLogin(testData.getStore());

            UI_Admin.AdminSignIn()
                    .enterUserName(adminAccount.getUserName())
                    .enterPassword(adminAccount.getPassword())
                    .clickSignIn();
            UI_Admin.AdminDashboard()
                    .closePopupMessages();
        }
    }

    @TmsLink("TC_Integration_Product_Review_001")
    @Feature("Integration_Product_Review")
    @Test(groups = {"SO-28", "admin"}, description = "Submit product review with admin approve successful flow")
    public void test01SubmitReviewWithApproveSuccessfulFlow() {
        String summary = testData.getFirstName() + LocalDateTime.now();
        String review = testData.getSecondaryAddress();
        String status = "Approved";
        UI.MenuPageWithProducts().clickProductByName(testData.getFirstProductName());
        UI.ProductDetails()
                .waitUntilCheckoutLoaderDisappears();
        int reviewCount = UI.ProductDetails().getReviewCount();
        int reviewTabCount = UI.ProductDetailsReview().getReviewTabCount();
        UI.ProductDetails()
                .clickAddReview();
        UI.ProductDetailsReview()
                .clickRating(5)
                .enterSummary(summary)
                .enterReview(review)
                .clickSubmitReviewButton()
                .verifyReviewSubmittedSuccessfully();

        UI_Admin.AdminDashboard()
                .clickMenu("Marketing")
                .clickSubMenu("Reviews");
        UI_Admin.AdminReview()
                .clickReviewByTitle(summary);
        UI_Admin.AdminReviewDetails()
                .selectReviewStatus(status)
                .clickSaveReview()
                .verifyReviewStatus(status, summary);

        driver.navigate().refresh();
        UI.ProductDetails()
                .verifyReviewCounter(reviewCount + 1);
        UI.ProductDetailsReview().verifyReviewTabCounter(reviewTabCount + 1);
    }

    @TmsLink("TC_Integration_Product_Review_002")
    @Feature("Integration_Product_Review")
    @Test(groups = {"SO-28", "admin"}, description = "Verify review from product list")
    public void test02VerifyReviewFromProductList() {
        String summary = testData.getFirstName() + LocalDateTime.now();
        String review = testData.getSecondaryAddress();
        String status = "Approved";
        UI.MenuPageWithProducts().clickProductByName(testData.getThirdProductName());
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

        UI_Admin.AdminDashboard()
                .clickMenu("Marketing")
                .clickSubMenu("Reviews");
        UI_Admin.AdminReview()
                .clickReviewByTitle(summary);
        UI_Admin.AdminReviewDetails()
                .selectReviewStatus(status)
                .clickSaveReview()
                .verifyReviewStatus(status, summary);

        UI.Menu().selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .clickReviewLink(testData.getThirdProductName());
        UI.ProductDetails()
                .verifyProductDetailsPage(testData.getThirdProductName());
    }

}
