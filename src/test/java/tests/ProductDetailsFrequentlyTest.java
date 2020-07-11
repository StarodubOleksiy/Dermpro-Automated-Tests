package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ProductDetailsFrequentlyTest extends BaseTest {

    private String firstProductPrice;

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
            firstProductPrice = UI.MenuPageWithProducts().getPriceByProductName(testData.getFirstProductName());
            UI.MenuPageWithProducts()
                    .clickProductByName(testData.getFirstProductName());
        }
    }

    @TmsLink("TC_Product_Details_FBT_001")
    @Feature("Product Details Frequently BT")
    @Test(groups = "signedUser", description = "Verify Frequently bought together product")
    public void test01VerifyFrequentlyBoughtTogetherProduct() {
        if (UI.ProductDetails().isFrequentlyBoughtPresent()) {
            UI.ProductDetails()
                    .verifyFrequentlyBoughtProduct(testData.getFirstProductName());
        } else {
            System.out.println("There is no frequently bought together proposition for current product");
        }
    }

    @TmsLink("TC_Product_Details_FBT_002")
    @Feature("Product Details Frequently BT")
    @Test(groups = "signedUser", description = "Verify Add To Cart redirection from frequently bought together block")
    public void test02VerifyFrequentlyAddToCartRedirection() {
        if (UI.ProductDetails().isFrequentlyBoughtPresent()) {
            UI.ProductDetails()
                    .clickFrequentlyAddToCartButton();
            UI.Cart()
                    .verifyCartPage(testData.getFirstProductName());
        } else {
            System.out.println("There is no frequently bought together proposition for current product");
        }
    }

    @TmsLink("TC_Product_Details_FBT_003")
    @Feature("Product Details Frequently BT")
    @Test(groups = {"SO-25", "signedUser"}, description = "Verify Add To Wish list redirection from frequently bought together block")
    public void test03VerifyFrequentlyAddToWishListRedirection() {
        if (UI.ProductDetails().isFrequentlyBoughtPresent()) {
            UI.ProductDetails()
                    .clickFrequentlyAddToWishListButton();
            UI.Wishlist()
                    .verifyWishListPage();
        } else {
            System.out.println("There is no frequently bought together proposition for current product");
        }
    }

    @TmsLink("TC_Product_Details_FBT_004")
    @Feature("Product Details Frequently BT")
    @Test(groups = "signedUser", description = "Verify frequently bought calculation")
    public void test04VerifyFrequentlyBoughtCalculation() {
        if (UI.ProductDetails().isFrequentlyBoughtPresent()) {
            int totalPrice = UI.ProductDetails().getFrequentlyTotalPrice();
            int currentProductPrice = Integer.parseInt(firstProductPrice.substring(1, firstProductPrice.indexOf(".")));
            System.out.println(totalPrice);
            System.out.println(currentProductPrice);
            UI.ProductDetails()
                    .clickThisItemCheckbox()
                    .verifyTotalPriceExcludingCurrentProduct(totalPrice, currentProductPrice);
        } else {
            System.out.println("There is no frequently bought together proposition for current product");
        }
    }

    @TmsLink("TC_Product_Details_FBT_005")
    @Feature("Product Details Frequently BT")
    @Test(groups = "signedUser", description = "Verify Frequently bought block with unchecked products")
    public void test05VerifyFrequentlyBoughtBlockWithUncheckedProducts() {
        if (UI.ProductDetails().isFrequentlyBoughtPresent()) {
            UI.ProductDetails()
                    .uncheckAllFrequentlyBoughtCheckboxes()
                    .verifyIfFrequentlyProductsIsNotVisible();
        } else {
            System.out.println("There is no frequently bought together proposition for current product");
        }
    }

    @AfterClass
    public void after() {
        UI.Account()
                .clickWishList();
        if (UI.WishlistLeftSideBar().verifyWishListIsNotEmpty()) {
            UI.WishlistLeftSideBar()
                    .removeAllItems();
        }

        UI.clearCart();
    }

}
