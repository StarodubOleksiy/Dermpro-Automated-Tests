package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class WishListTest extends BaseTest {


    @TmsLink("TC_Wish_List_Guest_001")
    @Feature("Wish_List_Guest")
    @Test(groups = {"guest"}, description = "Add product to wishlist if you are not signed in")
    public void test01VerifyAddProductToWishlistButtonOnMenuProductspage() {
        UI.MenuPageWithProducts()
                .clickAddToWishList(testData.getFirstProductName());
        UI.SignIn()
                .verifySignInPage();

    }

    @TmsLink("TC_Wish_List_Guest_002")
    @Feature("Wish_List_Guest")
    @Test(groups = {"guest"}, description = "Verify Wish List Link on header if you are not signed")
    public void test02VerifyWishListLinkOnAccount() {
        UI.Header()
                .wishListClick();
        UI.SignIn()
                .verifySignInPage();
    }



}
