package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.List;

public class SignedInCompareProductsTest extends BaseTest {

     private boolean wasProductsDeleted = false;
    private boolean isLogged = false;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-26") && isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(testData.getStore());
                isLogged = true;
            }
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            if (UI.CompareProducts().isElementsInComparePresent()) {
                UI.CompareProducts().deleteAllProductsFromCompareList();
            }

            UI.CompareProducts()
                    .clickCompareButton(testData.getFirstProductName())
                    .clickCompareButton(testData.getSecondProductName());
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-26")  && isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.CompareProducts()
                    .clickCompareProducts();
        }
    }



    @TmsLink("TC_signed_compare_products_001")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26", "signedUser"}, description = "Add product to Wish list from Compare products page")
    public void test01AddProductToWishListFromCompareProducts() {
        UI.CompareProducts()
                .clickAddToWishListFromCompare(testData.getFirstProductName());
        UI.Wishlist()
                .verifyProductWasAddedToWishlist(testData.getFirstProductName());
        UI.WishlistLeftSideBar()
                .clickRemoveItemFromWishlistLink();
    }

    @AfterClass
    public void after() {
        if (!wasProductsDeleted) {
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.CompareProducts()
                    .clickCompareProducts()
                    .deleteAllProductsFromCompareList();
        }
    }

}

