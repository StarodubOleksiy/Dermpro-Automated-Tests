package tests.signeduser;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import tests.BaseTest;

public class SignedInWishListTest extends BaseTest {

    private String firstName;
    private String secondName;

    private boolean isLogged = false;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-25") && isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            if (!isLogged) {
                UI.Header().logInClick();
                UI.SignIn()
                        .quickLogin(testData.getStore());
                isLogged = true;
            }

            UI.Header()
                    .accountClick();
            firstName = UI.AccountDashboard().getFirstName();
            secondName = UI.AccountDashboard().getLastName();
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts().clickAddToWishList(testData.getFirstProductName());
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts().clickAddToWishList(testData.getSecondProductName());
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts().clickAddToWishList(testData.getThirdProductName());
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-25") && isFeatureEnabled(testData.getStore().getFeatureStates(), "signedUser")) {
            UI.Header()
                    .accountClick();
            UI.Account()
                    .clickWishList();
        }
    }


    @TmsLink("TC_Wish_List_001")
    @Feature("Wish_List")
    @Test(groups = {"SO-25", "signedUser"}, description = "Amount of items are updated in header")
    public void test01AmountOfItemsAreUpdatedInHeader() {
        UI.WishlistLeftSideBar().verifyItemsCounter();
    }

    @TmsLink("TC_Wish_List_002")
    @Feature("Wish_List")
    @Test(groups = {"SO-25", "signedUser"}, description = "Verify <<Add to Cart>> button")
    public void test02VerifyAddToCartButton() {
        UI.Wishlist()
                .clickAddToCartButton(testData.getFirstProductName());
         UI.Cart().verifyProductWasAddedToCartFromWishlist(testData.getFirstProductName());
    }


    @TmsLink("TC_Wish_List_003")
    @Feature("Wish_List")
    @Test(groups = {"SO-25", "signedUser"}, description = "Verify <<Share Wish List>> button")
    public void test03VerifyShareWishListButton() {
        UI.Wishlist()
                .clickShareWishListButton();
        UI.WishListSharing()
                .verifyWishListSharingPage();
        UI.WishListSharing().enterEmailAddressesSharingInformationMessage(testData.getEmailAddress())
                .clickShareWishListButton();
        UI.Wishlist()
                .verifyWishListWasShared();
    }

    @TmsLink("TC_Wish_List_004")
    @Feature("Wish_List")
    @Test(groups = {"SO-25", "signedUser"}, description = "Verify <<Update Wish List>> button")
    public void test04VerifyUpdateWishListButton() {
        String comment = testData.getSentence();
        UI.Wishlist()
                .enterComment(comment, 0)
                .clickUpdateWishListButton()
                .verifyComment(comment, 0);
    }

    @TmsLink("TC_Wish_List_005")
    @Feature("Wish_List")
    @Test(groups = {"SO-25", "signedUser"}, description = "Verify Qty functionality (0, Empty)")
    public void test05VerifyQtyFunctionality() {
        final String quantity = "8";
        UI.Wishlist()
                .clearQty(0)
                .clickUpdateWishListButton()
                .verifyQtyInputError("Please enter a valid number in this field.")
                .enterQty(String.valueOf(0), 0)
                .clickUpdateWishListButton()
                .verifyQtyInputError("Please enter a number greater than 0 in this field.")
                .enterQty(quantity, 0)
                .clickUpdateWishListButton()
                .verifyQty(quantity, 0);
    }

    @TmsLink("TC_Wish_List_006")
    @Feature("Wish_List")
    @Test(groups = {"SO-25", "signedUser"}, description = "Verify <<Edit>> button")
    public void test06VerifyEditLink() {
        UI.Wishlist()
                .clickEditLink(testData.getSecondProductName());
        UI.ProductDetails()
                .verifyProductDetailsPage(testData.getSecondProductName());
    }

    @TmsLink("TC_Wish_List_007")
    @Feature("Wish_List")
    @Test(groups = {"SO-25", "signedUser"}, description = "Verify <<Back>> link")
    public void test07VerifyBackLink() {
        UI.Wishlist()
                .clickBackSpan();
        UI.Account()
                .verifyAccountDashboardInformation(firstName, secondName, testData.getStore().getAccount().getEmail());
    }

    @TmsLink("TC_Wish_List_008")
    @Feature("Wish_List")
    @Test(groups = {"SO-25", "signedUser"}, description = "Verify <<Remove item>>")
    public void test08VerifyRemoveItemLink() {
        UI.Wishlist()
                .clickRemoveItemLink(0);
    }

    @TmsLink("TC_Wish_List_009")
    @Feature("Wish_List")
    @Test(groups = {"SO-25", "signedUser"}, description = "Verify <<Add All to Cart>> button")
    public void test09VerifyAddAllToCartButton() {
        UI.Wishlist()
                .clickAddAllToCartButton();
        UI.Cart()
                .verifyFewProductsHaveBeenAddedToShoppingCart();
        UI.Header()
                .accountClick();
        UI.Account()
                .clickWishList();
        UI.Wishlist()
                .verifyWishListIsEmpty();
    }


    @AfterClass
    public void after() {
        if (UI.WishlistLeftSideBar().verifyWishListIsNotEmpty()) {
            UI.WishlistLeftSideBar()
                    .removeAllItems();
        }
    }
}
