package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SignedInMenuPageWithProductsTest extends BaseTest {

    private String firstProductName;

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

            firstProductName = testData.getFirstProductName();
            UI.clearCart();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
        }
    }






    @TmsLink("TC_SignedMenu_Page_With_Products_001")
    @Feature("Menu_Signed_Page_With_Products")
    @Test(groups = {"SO-25", "signedUser"}, description = "Add product to wishlist sucessfully")
    public void test01SignedAddProductToWishlist() {
        UI.MenuPageWithProducts()
                .clickAddToWishList(firstProductName);
        UI.Wishlist()
                .verifyProductWasAddedToWishlist(firstProductName);
        UI.WishlistLeftSideBar()
                .verifyProductIsPresent(firstProductName)
                .verifyItemsCounter();
        UI.Wishlist()
                .clickAddToCartButton(firstProductName);
        UI.Cart()
                .verifyProductWasAddedToCartFromWishlist(firstProductName)
                .clickMoveToWishListButton(0);
        UI.Wishlist()
                .verifyProductHasBeenMovedToWishlist(firstProductName);
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.WishlistLeftSideBar()
                .verifyProductIsPresent(firstProductName)
                .clickRemoveItemFromWishlistLink();
    }


}