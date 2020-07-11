package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class ReviewTest extends BaseTest {

    @TmsLink("TC_Guest_Product_Review_001")
    @Feature("Guest_Product_Review")
    @Test(groups = {"guest", "SO-28"}, description = "Verify Review message for not logged users")
    public void test01VerifyReviewGuestMessage() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts().clickProductByName(testData.getFirstProductName());
        UI.ProductDetails()
                .waitUntilCheckoutLoaderDisappears();
        UI.ProductDetails()
                .clickAddReview();
        UI.ProductDetailsReview()
                    .verifyReviewGuestMessage();
    }

}
