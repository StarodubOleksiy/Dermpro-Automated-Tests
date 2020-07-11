package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.*;
import tests.BaseTest;

import java.util.List;

public class SignedInShoppingCartTest extends BaseTest {

    private boolean cancelCoupon = false;
    private boolean deleteGiftOptions = false;

    private boolean isLogged = false;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(testData.getStore());
                isLogged = true;
            }
            UI.clearCart();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts()
                    .clickAddToCart(testData.getFirstProductName());
            UI.Cart().waitUntilCartPageIsLoaded();
        }
    }


    @TmsLink("TC_Shopping_Cart_001")
    @Feature("Shopping_Cart")
    @Test(groups = {"signedUser"}, description = "Shopping_Cart Verify cart summary")
    public void test01VerifyCartSummary() {
        UI.Cart()
                .verifyCartSummary();
    }

    @TmsLink("TC_Shopping_Cart_002")
    @Feature("Shopping_Cart")
    @Test(groups = {"signedUser"}, description = "Shopping_Cart Verify information about cart")
    public void test02CartInformation() {
        UI.Cart()
                .verifyCartPage(testData.getFirstProductName());
    }



    @TmsLink("TC_Shopping_Cart_003")
    @Feature("Shopping_Cart")
    @Test(groups = {"signedUser"}, description = "Verify <<Move to Wishlist>> button on shopping cart")
    public void test03VerifyMoveToWishlistButton() {
        UI.Cart().waitUntilCartPageIsLoaded()
                .clickMoveToWishListButton(0);
        UI.Wishlist()
                .verifyProductHasBeenMovedToWishlist(testData.getFirstProductName());
    }



    @AfterClass
    public void after() {
        if (UI.CartPopUp().isElementsInCartPresent()) {
            UI.CartPopUp().clickViewEditCart();
            UI.Cart().waitUntilCartPageIsLoaded();
            if (cancelCoupon) {
                UI.Cart()
                        .clickApplyDiscountCode()
                        .clickCancelCouponButton();
            }

            if (deleteGiftOptions) {
                UI.Cart()
                        .clickGiftOptionsDeleteButton();
            }
            UI.Cart().clickClearShoppingCartButton();
            UI.CartPopUp().verifyCartPopUpIsEmpty();
        }
    }

}
