package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ProductDetailsTest extends BaseTest {

    @Override
    public void initMethod() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .clickProductByName(testData.getFirstProductName());
    }

    @TmsLink("TC_Product_Details_001")
    @Feature("Product Details")
    @Test(description = "Verify product description")
    public void test01VerifyItemDescription() {
        UI.ProductDetails()
                .clickDescriptionTabIfNotActive()
                .verifyDescription();
    }

    @TmsLink("TC_Product_Details_002")
    @Feature("Product Details")
    @Test(description = "Verify Add To Cart redirection")
    public void test02VerifyAddToCartRedirection() {
        UI.ProductDetails()
                .clickAddToCartButton();
        UI.Cart()
                .waitUntilCartElementsLoaded()
                .verifyCartPage(testData.getFirstProductName());
    }

    @TmsLink("TC_Product_Details_003")
    @Feature("Product Details")
    @Test(description = "Verify Add To Compare success message")
    public void test03VerifyAddToCompareSuccess() {
        UI.ProductDetails()
                .clickAddToCompareButton()
                .verifyAddToCompareMessage(testData.getFirstProductName());
    }

    @TmsLink("TC_Product_Details_004")
    @Feature("Product Details")
    @Test(description = "Verify Add To Wish List redirection")
    public void test04VerifyAddToWishListRedirection() {
        UI.ProductDetails()
                .clickAddToWishListButton();
        UI.Wishlist()
                .verifyProductWasAddedToWishlist(testData.getFirstProductName())
                .verifyWishListPage();
    }

    @TmsLink("TC_Product_Details_005")
    @Feature("Product Details")
    @Test(description = "Verify Next/Previous products switching")
    public void test05VerifyNextPreviousProductsSwitching() {
        UI.ProductDetails()
                .clickNextButton()
                .verifyNextPreviousProductPage(testData.getFirstProductName())
                .clickPreviousButton()
                .verifyItemName(testData.getFirstProductName());
    }

    @TmsLink("TC_Product_Details_006")
    @Feature("Product Details")
    @Test(description = "Verify recommended product name")
    public void test06VerifyRecommendedSection() {
        UI.ProductDetails()
                .verifyRecommendedProduct();
    }

    @TmsLink("TC_Product_Details_007")
    @Feature("Product Details")
    @Test(description = "Verify Email to friend validation")
    public void test07VerifyEmailToFriendValidation() {
        UI.ProductDetails()
                .clickEmailToFriendLink();
        UI.EmailToFriend()
                .verifyEmailToFriendPage()
                .clearAllFields()
                .clickSendEmailButton()
                .verifyRequiredFields();
    }

    @TmsLink("TC_Guest_Product_Details_008")
    @Feature("Guest Product Details")
    @Test(groups = "guest", description = "Verify send Email to friend successful")
    public void test08GuestVerifySendEmailToFriendGuest() {
        UI.ProductDetails()
                .clickEmailToFriendLink();
        UI.SignIn()
                .verifySignInPage();
    }

    @TmsLink("TC_Guest_Product_Details_009")
    @Feature("Guest Product Details")
    @Test(groups = {"SO-28", "guest"}, description = "Verify  Review link is displayed under the product name for guest")
    public void test09VerifyGuestReviewLinks() {
        UI.ProductDetails()
                .clickAddReview()
                .verifyReviewGuestTab();
    }

    @TmsLink("TC_Product_Details_010")
    @Feature("Product Details")
    @Test(description = "Add different qty of products to the Cart")
    public void test10AddDifferentProductsQtyToCart() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .clickProductByName(testData.getThirdProductName());
        String qty = "3";
        UI.ProductDetails()
                .clearQty()
                .enterQty(qty)
                .clickAddToCartButton();
        UI.Cart().verifyProductQty(qty, testData.getThirdProductName());
    }

    @TmsLink("TC_Product_Details_011")
    @Feature("Product Details")
    @Test(description = "Add product with invalid qty to Cart")
    public void test11AddProductWithInvalidQtyToCart() {
        String zeroMessage = "Please enter a quantity greater than 0.";
        String invalidQtyMessage = "Please enter a valid number in this field.";
        UI.ProductDetails()
                .clearQty()
                .clickAddToCartButton()
                .verifyQtyErrorMessage(invalidQtyMessage)
                .enterQty("0")
                .clickAddToCartButton()
                .verifyQtyErrorMessage(zeroMessage);
    }

    @TmsLink("TC_Product_Details_011")
    @Feature("Product Details")
    @Test(description = "Verify Add To Compare option when it Enabled/Disabled in config")
    public void test11VerifyEnabledDisabledAddToCompareOption() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-26")) {
            UI.ProductDetails().verifyEnabledAddToCompareOption();
        } else {
            UI.ProductDetails().verifyDisabledAddToCompareOption();
        }
    }

    @TmsLink("TC_Product_Details_012")
    @Feature("Product Details")
    @Test(description = "Verify Add To WishList option when it Enabled/Disabled in config")
    public void test12VerifyEnabledDisabledAddToWishListOption() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-25")) {
            UI.ProductDetails().verifyEnabledAddToWishListOption();
        } else {
            UI.ProductDetails().verifyDisabledAddToWishListOption();
        }
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