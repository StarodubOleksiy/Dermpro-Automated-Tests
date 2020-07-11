package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

public class CompareProductsTest extends BaseTest {

    private String firstProductPrice;
    private String secondProductPrice;
    private boolean wasProductsDeleted = false;

    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-26") ) {
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            if (UI.CompareProducts().isElementsInComparePresent()) {
                UI.CompareProducts().deleteAllProductsFromCompareList();
            }
            UI.MenuPageWithProducts().verifyMenuPageIsLoaded();
            UI.CompareProducts()
                    .clickCompareButton(testData.getFirstProductName());
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.CompareProducts()
                    .clickCompareButton(testData.getSecondProductName());
            firstProductPrice = UI.MenuPageWithProducts().getPriceByProductName(testData.getFirstProductName());
            secondProductPrice = UI.MenuPageWithProducts().getPriceByProductName(testData.getSecondProductName());
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-26")) {
            UI.CompareProducts()
                    .clickCompareProducts();
        }
    }

    @TmsLink("TC_compare_products_001")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26"}, description = "Add product to compare list")
    public void test01GuestAddProductToCompareList() {
        UI.CompareProducts()
                .verifyProductPresence(testData.getFirstProductName(), firstProductPrice)
                .verifyProductPresence(testData.getSecondProductName(), secondProductPrice)
                .verifyCompareProductQty("2 items");
    }

    @TmsLink("TC_Guest_compare_products_002")
    @Feature("DermPRO Guest Compare products")
    @Test(groups = {"SO-26", "guest"}, description = "Add product to Wish list from Compare products page")
    public void test02GuestAddProductToWishListFromCompareProducts() {
        UI.CompareProducts()
                .clickAddToWishListFromCompare(testData.getFirstProductName());
        UI.SignIn()
                .verifySignInPage();
    }

    @TmsLink("TC_Guest_compare_products_003")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26"}, description = "Add product to Cart from Compare products page")
    public void test03AddProductToCartFromCompareProducts() {
        UI.CompareProducts()
                .clickAddToCartFromCompare(testData.getFirstProductName());
        UI.Cart()
                .waitUntilCartPageIsLoaded()
                .verifyProductWasAddedToCartFromWishlist(testData.getFirstProductName())
                .clickRemoveItemButton();
    }

    @TmsLink("TC_compare_products_004")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26"}, description = "Verify confirmation message that product added to the compare list")
    public void test04VerifyAddProductToCompareMessage() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts().verifyMenuPageIsLoaded();
        UI.CompareProducts()
                .clickCompareButton(testData.getFirstProductName())
                .verifyAddProductToCompareSuccessMessage(testData.getFirstProductName());
    }

    @TmsLink("TC_compare_products_005")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26"}, description = "Verify tooltips on the compare page")
    public void test05GuestVerifyTooltips() {
        UI.CompareProducts()
                .verifyPrintThisPageTooltip()
                .verifyRemoveButtonTooltip();
    }

    @TmsLink("TC_compare_products_006")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26"}, description = "Verify visibility of six or more products are in the compare list")
    public void test06VerifySixAndMoreProductInCompareList() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts().verifyMenuPageIsLoaded();
        List<String> productList = UI.CompareProducts().getProductNamesList();
        productList.forEach(p -> {
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts().verifyMenuPageIsLoaded();
            UI.CompareProducts()
                    .clickCompareButton(p);
        });
        UI.CompareProducts().clickCompareProducts()
                .verifySixAndMoreProductInCompareList();
    }

    @TmsLink("TC_Guest_compare_products_007")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26"}, description = "Verify that product added to compare list only once")
    public void test07VerifyProductAddedOnceToCompareList() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts().verifyMenuPageIsLoaded();
        UI.CompareProducts()
                .clickCompareButton(testData.getFirstProductName());
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts().verifyMenuPageIsLoaded();
        UI.CompareProducts()
                .clickCompareButton(testData.getSecondProductName())
                .clickCompareProducts()
                .verifyProductAddedOnceToCompareList(testData.getFirstProductName());
    }

    @TmsLink("TC_compare_products_008")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26"}, description = "Verify product attribute presence")
    public void test08GuestVerifyProductAttributePresence() {
        UI.CompareProducts()
                .verifyProductAttributePresentForGuest();
    }

    @TmsLink("TC_compare_products_009")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26"}, description = "Verify product review summery")
    public void test09GuestVerifyProductReviewSummary() {
        UI.CompareProducts()
                .verifyProductReviewSummary();
    }

    @TmsLink("TC_compare_products_010")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26"}, description = "Verify redirect to product from compare list")
    public void test10VerifyRedirectToProductFromCompareList() {
        UI.CompareProducts()
                .clickProductName(testData.getFirstProductName());
        UI.ProductDetails()
                .verifyItemName(testData.getFirstProductName());
    }

    @TmsLink("TC_compare_products_011")
    @Feature("DermPRO Compare products")
    @Test(groups = {"SO-26"}, description = "Delete product from compare products list on left side bar")
    public void test11GuestDeleteProductFromCompareLeftSideBar() {
        String productName = UI.CompareProducts().getFirstProductTitle();
        UI.CompareProducts()
                .deleteProductFromCompareList()
                .verifyDeleteProductFromCompare(productName);
    }

    @TmsLink("TC_compare_products_012")
    @Feature("DermPRO Guest Compare products")
    @Test(groups = {"SO-26"}, description = "Delete all products from compare products list")
    public void test12GuestDeleteAllProductFromCompare() {
        UI.CompareProducts()
                .waitUntilCompareProductsLoaded()
                .deleteAllProductsFromCompareList()
                .verifyEmptyCompareMessage();
        wasProductsDeleted = true;
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
