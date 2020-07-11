package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class CartPopUpTest extends BaseTest {

    private String firstProductPrice;
    private String secondProductPrice;

    @Override
    public void init() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        firstProductPrice = UI.MenuPageWithProducts().getPriceByProductName(testData.getFirstProductName());
        secondProductPrice = UI.MenuPageWithProducts().getPriceByProductName(testData.getSecondProductName());
    }

    @Override
    public void initMethod() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .clickAddToCart(testData.getFirstProductName());
        UI.Cart().waitUntilCartPageIsLoaded();
    }

    @TmsLink("TC_Guest_Card_Pop_Up_001")
    @Feature("Guest_Card_Pop_Up")
    @Test(groups = {"guest"}, description = "Verify Cart Pop Up as guest")
    public void test01VerifyCartPopUpAsGuest() {
        UI.CartPopUp()
                .waitUntilCounterWillBeVisible()
                .verifyCartPopUpWithDataAsGuest();
    }

    @TmsLink("TC_Card_Pop_Up_002")
    @Feature("Card_Pop_Up")
    @Test(description = "Verify <<View and Edit Cart>> link")
    public void test02VerifyViewAndEditLink() {
        UI.Cart()
                .waitUntilCartElementsLoaded();
        UI.CartPopUp()
                .clickViewEditCart();
        UI.Cart()
                .verifyCartPageTitle();
    }

    @TmsLink("TC_Card_Pop_Up_003")
    @Feature("Card_Pop_Up")
    @Test(description = "Verify Cart name, price, item and subtotal")
    public void test03VerifyProductNamePriceItemAndSubtotal() {
        UI.CartPopUp()
                .waitUntilCounterWillBeVisible()
                .verifyProductIsPresent(testData.getFirstProductName())
                .verifyProductPriceIsPresent(firstProductPrice)
                .verifyNumberOfItem()
                .verifyCartSubtotal();
    }

    @TmsLink("TC_Card_Pop_Up_004")
    @Feature("Card_Pop_Up")
    @Test(description = "Verify <<Edit>> button")
    public void test04VerifyEditButton() {
        UI.CartPopUp()
                .waitUntilCounterWillBeVisible()
                .clickEditItemLink(0);
        UI.EditCurrentProduct()
                .waitUntilEditCurrentProductPageIsLoaded()
                .clickUpdateCartButton();
        UI.Cart()
                .verifyProductWasUpdatedOnCart(testData.getFirstProductName());
    }

    @TmsLink("TC_Card_Pop_Up_005")
    @Feature("Card_Pop_Up")
    @Test(description = "Verify Adding multiple items to Cart")
    public void test05VerifyAddingMultipleItemsToCart() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .clickAddToCart(testData.getSecondProductName());
        UI.Cart().waitUntilCartPageIsLoaded()
                .verifyProductAddToCartV2(testData.getSecondProductName());
        UI.CartPopUp()
                .waitUntilCounterWillBeVisible()
                .verifyProductIsPresent(testData.getSecondProductName())
                .verifyProductPriceIsPresent(secondProductPrice)
                .verifyNumberOfItem()
                .verifyCartSubtotal()
                .verifyCounterNumber(UI.CartPopUp().getQtyOfProductsInCart());
    }

    @TmsLink("TC_Card_Pop_Up_006")
    @Feature("Card_Pop_Up")
    @Test(description = "Select product by clicking on it's title")
    public void test06SelectProductByTitle() {
        UI.CartPopUp()
                .clickOnCurrentProductInCart(testData.getFirstProductName());
        UI.ProductDetails()
                .waitUntilProductDetailsLoaded()
                .verifyProductDetailsPage(testData.getFirstProductName());
    }

    @TmsLink("TC_Card_Pop_Up_007")
    @Feature("Card_Pop_Up")
    @Test(description = "Verify Qty item input")
    public void test07VerifyQtyItemInputAsGuest() {
        final String quantity = "8";
        UI.CartPopUp()
                .waitUntilCounterWillBeVisible()
                .enterQtyItem(quantity)
                .clickUpdateButton()
                .verifyQtyItemUpdated(quantity);
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .clickAddToCart(testData.getFirstProductName());
        UI.CartPopUp()
                .verifyCounterNumber(UI.CartPopUp().getQtyOfProductsInCart());
    }


    @TmsLink("TC_Card_Pop_Up_008")
    @Feature("Card_Pop_Up")
    @Test(description = "Verify <<Go to Checkout>> button")
    public void test08VerifyGoToCheckoutDetailedButton() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.MenuPageWithProducts()
                .clickAddToCart(testData.getFirstProductName());
        UI.Cart().waitUntilCartPageIsLoaded();
        UI.CartPopUp()
                .clickOnCurrentProductInCart(testData.getFirstProductName());
        UI.CartPopUp()
                .waitUntilCheckoutLoaderDisappears();
        UI.CartPopUp()
                .waitUntilCounterWillBeVisible()
                .clickGoToCheckoutButton();
        UI.Checkout()
                .waitUntilCheckoutPageLoads()
                .verifyCheckoutPage();
    }

    @TmsLink("TC_Card_Pop_Up_009")
    @Feature("Card_Pop_Up")
    @Test(description = "Verify <<Cancel>> button of Pop Up Window")
    public void test09VerifyRemoveButton() {
        if (!UI.CartPopUp().isElementsInCartPresent()) {
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts()
                    .clickAddToCart(testData.getFirstProductName());
            UI.Cart().waitUntilCartPageIsLoaded();
        }
        UI.CartPopUp()
                .waitUntilCheckoutLoaderDisappears();
        UI.CartPopUp()
                .waitUntilCounterWillBeVisible()
                .clickRemoveItemLink(0)
                .clickCancelButton()
                .waitUntilCheckoutLoaderDisappears();
        UI.CartPopUp()
                .waitUntilCounterWillBeVisible()
                .clickRemoveItemLink(0)
                .clickOkButton();
    }

    @AfterClass
    public void after() {
        UI.clearCart();
    }

}
