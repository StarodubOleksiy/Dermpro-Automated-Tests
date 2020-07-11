package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class MenuPageWithProductsTest extends BaseTest {

    private String firstProductName;
    private String secondProductName;
    private String thirdProductName;
    private String privateLabel = "Private Label";

    @Override
    public void init() {
        firstProductName = testData.getFirstProductName();
        secondProductName = testData.getSecondProductName();
        thirdProductName = testData.getThirdProductName();
        UI.clearCart();
    }

    @Override
    public void initMethod() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
    }

    @TmsLink("TC_Menu_Page_With_Products_001")
    @Feature("Menu_Page_With_Products")
    @Test(description = "Add product to cart as guest")
    public void test01AddProductToCart() {
        UI.MenuPageWithProducts()
                .addToCartClick(firstProductName);
        UI.Cart()
                .verifyProductAddToCart(firstProductName)
                .clickRemoveItemButton();
    }

    @TmsLink("TC_Menu_Page_With_Products_002")
    @Feature("Menu_Page_With_Products")
    @Test(groups = {"SO-25"}, description = "Verify <<Add to WishList>> button is present as guest")
    public void test02VerifyAddToWishListButton() {
        UI.MenuPageWithProducts()
                .verifyAddToWishListButtonIsPresent();
    }

    @TmsLink("TC_Menu_Page_With_Products_003")
    @Feature("Menu_Page_With_Products")
    @Test(groups = {"SO-26"}, description = "Verify <<Add to Compare>> button is present as guest")
    public void test03VerifyAddToCompareButton() {
        UI.MenuPageWithProducts()
                .verifyAddToCompareButtonIsPresent();
    }

    @TmsLink("TC_Menu_Page_With_Products_004")
    @Feature("Menu_Page_With_Products")
    @Test(description = "Verify product name is present as guest")
    public void test04GuestVerifyProductName() {
        UI.MenuPageWithProducts()
                .verifyProductNamePresent(firstProductName);
    }

    @TmsLink("TC_Menu_Page_With_Products_005")
    @Feature("Menu_Page_With_Products")
    @Test(description = "Verify Grid and List views as guest")
    public void test05GridAndListView() {
        UI.MenuPageWithProducts()
                .clickModeListButton();
        UI.MenuPageWithProducts()
                .verifyListView()
                .clickGridButton();
        UI.MenuPageWithProducts()
                .verifyGridView();
    }

    @TmsLink("TC_Menu_Page_With_Products_006")
    @Feature("Menu_Page_With_Products")
    @Test(description = "Verify sorting on Grid View page as guest")
    public void test0SortingOnGridViews() {
        UI.MenuPageWithProducts()
                .chooseSorterOption(0)
                .waitUntilCheckoutLoaderDisappears();
        UI.MenuPageWithProducts()
                .chooseSorterOption(1)
                .waitUntilCheckoutLoaderDisappears();
        UI.MenuPageWithProducts()
                .verifyProductNamesSorting();
        UI.MenuPageWithProducts()
                .chooseSorterOption(2)
                .waitUntilCheckoutLoaderDisappears();
        UI.MenuPageWithProducts()
                .verifyProductPriceSorting();
    }

    @TmsLink("TC_Menu_Page_With_Products_007")
    @Feature("Menu_Page_With_Products")
    @Test(groups = {"SO-25"}, description = "Verify if Add to WishList is not available for unauthorized users as guest")
    public void test07GuestAddProductToWishList() {
        UI.MenuPageWithProducts()
                .clickAddToWishList(firstProductName);
        UI.SignIn()
                .verifySignInPage();
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
                .selectMenu(testData.getMenuVariable())
                .waitUntilProductsPageIsLoaded();
        UI.WishlistLeftSideBar()
                .verifyProductIsPresent(firstProductName)
                .clickRemoveItemFromWishlistLink();
    }

    @TmsLink("TC_Menu_Page_With_Products_008")
    @Feature("Menu_Page_With_Products")
    @Test(groups = {"SO-26"}, description = "Add product to compare as guest")
    public void test08GuestAddProductToCompare() {
        if (UI.CompareProducts().isElementsInComparePresent()) {
            UI.CompareProducts().deleteAllProductsFromCompareList();
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
        }
        UI.MenuPageWithProducts()
                .clickAddToCompare(firstProductName)
                .verifyProductAddToCompare(firstProductName);
        UI.MenuPageWithProducts()
                .clickAddToCompare(secondProductName);
        UI.MenuPageWithProducts()
                .verifyProductAddToCompare(secondProductName);
        UI.CompareLeftSideBar()
                .verifyItemsCounterIsEqualsOnCompareLeftSideBar("2 Items")
                .verifyProductsArePresentInCompareCorner(firstProductName, secondProductName)
                .clickCompareButton();
        UI.CompareProducts()
                .verifyIfYouOnCompareProductsPage();
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.CompareLeftSideBar()
                .clickRemoveItemFromCompareLeftSideBar(firstProductName)
                .clickOkButton();
        UI.CompareProducts().
                verifyDeleteProductFromCompare(firstProductName);
        UI.MenuPageWithProducts()
                .clickClearAllLink()
                .clickOkButton();
    }

    @TmsLink("TC_Menu_Page_With_Products_009")
    @Feature("Menu_Page_With_Products")
    @Test(groups = {"SO-28"}, description = "Verify <<Reviews>> link for list view as guest")
    public void test09VerifyReviewsLinkForListView() {
        if (UI.MenuPageWithProducts().isReviewSpanPresent(thirdProductName)) {
            UI.MenuPageWithProducts()
                    .clickModeListButton();
            UI.MenuPageWithProducts()
                    .verifyReviewSpanPresent();
        }
    }

    @TmsLink("TC_Menu_Page_With_Products_010")
    @Feature("Guest_Menu_Page_With_Products")
    @Test(groups = {"SO-28"}, description = "Verify <<Add Your Review>> link for list view as guest")
    public void test10VerifyAddYourReviewLinkForListView() {
        if (UI.MenuPageWithProducts().isReviewSpanPresent(thirdProductName)) {
            UI.MenuPageWithProducts()
                    .clickModeListButton();
            UI.MenuPageWithProducts()
                    .verifyAddYourReviewLinkOnListView(thirdProductName);
        }
    }

    @TmsLink("TC_Menu_Page_With_Products_011")
    @Feature("Menu_Page_With_Products")
    @Test(groups = {"SO-28"}, description = "Verify Reviews count as guest")
    public void test11VerifyReviewsCount() {
        if (UI.MenuPageWithProducts().isReviewSpanPresent(thirdProductName)) {
            UI.MenuPageWithProducts()
                    .clickModeListButton();
            UI.MenuPageWithProducts()
                    .verifyReviewsCount();
        }

    }

    @TmsLink("TC_Menu_Page_With_Products_012")
    @Feature("Menu_Page_With_Products")
    @Test(groups = {"SO-28"}, description = "Verify <<Learn more>> link for list view as guest")
    public void test12VerifyLearnMoreLinkForListView() {
        UI.MenuPageWithProducts()
                .clickModeListButton();
        UI.MenuPageWithProducts()
                .clickLearnMoreLink(firstProductName);
        UI.ProductDetails().verifyProductDetailsPage(firstProductName);
    }

    @TmsLink("TC_Menu_Page_With_Products_013")
    @Feature("Menu_Page_With_Products")
    @Test(description = "Verify Reviews count as guest")
    public void test13VerifyShowPerPageForBothGridAndListViews() {
        if (UI.MenuPageWithProducts().isPagePresent(2)) {
            UI.MenuPageWithProducts()
                    .clickOnPageWithIndex(2);
            UI.MenuPageWithProducts()
                    .verifyNextPageButtonIsPresent()
                    .verifyPreviousPageButtonIsPresent()
                    .clickModeListButton();
            UI.MenuPageWithProducts()
                    .clickOnPageWithIndex(2);
            UI.MenuPageWithProducts()
                    .verifyNextPageButtonIsPresent()
                    .verifyPreviousPageButtonIsPresent();
        }
    }

    @TmsLink("TC_Menu_Page_With_Products_014")
    @Feature("Menu_Page_With_Products")
    @Test(description = "Verify sorting by all types and for List views as guest")
    public void test14VerifySortingOnListView() {
        UI.MenuPageWithProducts()
                .clickModeListButton();
        UI.MenuPageWithProducts()
                .chooseSorterOption(0)
                .waitUntilCheckoutLoaderDisappears();
        UI.MenuPageWithProducts()
                .chooseSorterOption(1)
                .waitUntilCheckoutLoaderDisappears();
        UI.MenuPageWithProducts()
                .verifyProductNamesSorting();
        UI.MenuPageWithProducts()
                .chooseSorterOption(2)
                .waitUntilCheckoutLoaderDisappears();
        UI.MenuPageWithProducts()
                .verifyProductPriceSorting();
    }

    @TmsLink("TC_Menu_Page_With_Products_015")
    @Feature("Menu_Page_With_Products")
    @Test(groups = {"SO-32A"}, description = "Verify private labels page as guest")
    public void test15VerifyPrivateLabelPage() {
        UI.FooterBottom().siteMapClick();
        UI.MenuPageWithProducts()
                .clickCategory(privateLabel)
                .waitUntilProductsPageIsLoaded();
        int size = UI.MenuPageWithProducts().getShowPerPageOptionsSize();
        UI.MenuPageWithProducts()
                .chooseShowPerPageOption(size - 1)
                .waitUntilProductsPageIsLoaded();
        UI.MenuPageWithProducts()
                .verifyPrivateLabelProductsPage(testData.getStore().getName());
    }

}