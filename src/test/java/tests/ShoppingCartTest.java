package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

public class ShoppingCartTest extends BaseTest {

    private boolean cancelCoupon = false;
    private boolean deleteGiftOptions = false;

    @Override
    public void init() {
        UI.clearCart();
    }

    @Override
    public void initMethod() {
            UI.Menu()
                    .selectMenu(testData.getMenuVariable());
            UI.MenuPageWithProducts()
                    .clickAddToCart(testData.getFirstProductName());
            UI.Cart().waitUntilCartPageIsLoaded();
    }

    @TmsLink("TC_Guest_Shopping_Cart_001")
    @Feature("Guest_Shopping_Cart")
    @Test(groups = {"guest"}, description = "Verify cart summary as guest")
    public void test01GuestVerifyCartSummary() {
        UI.Cart()
                .verifyCartSummaryGuest();
    }

    @TmsLink("TC_Guest_Shopping_Cart_002")
    @Feature("Guest_Shopping_Cart")
    @Test(groups = {"guest"}, description = "Verify cart information as guest")
    public void test02GuestCartInformation() {
        UI.Cart()
                .verifyCartPageGuest(testData.getFirstProductName());
    }

    @TmsLink("TC_Shopping_Cart_003")
    @Feature("Shopping_Cart")
    @Test(description = "Verify Gift options, <<Edit>> and <<Delete>> buttons as guest")
    public void test03CartGiftOptionsVerifyEditAndDeleteButtons() {
        String message = testData.getSentence();
        UI.Cart().waitUntilCartPageIsLoaded();
        UI.Cart().waitUntilGiftOptionsBlockIsLoaded();

        UI.Cart().clickGiftOptionsSpan();
        if (UI.Cart().isDeleteButtonPresent()) {
            UI.Cart().clickGiftOptionsDeleteButton()
                    .waitUntilCartPageIsLoaded()
                    .waitUntilGiftOptionsBlockIsLoaded();
        }

        UI.Cart()
                .clickGiftOptionsSpan()
                .enterToInput(testData.getSentence())
                .enterFromInput(testData.getSentence())
                .enterGiftOptionsMessage(message)
                .clickUpdateButton()
                .waitUntilCartPageIsLoaded()
                .verifyGiftOptionsMessage("Message: " + message);
        String secondMessage = testData.getSecondSentence();
        UI.Cart()
                .clickGiftOptionsEditButton()
                .clearGiftOptionsMessage()
                .enterGiftOptionsMessage(secondMessage);
        UI.Cart()
                .clickUpdateButton();
        UI.Cart().waitUntilCartPageIsLoaded();
        UI.Cart()
                .verifyGiftOptionsMessage("Message: " + secondMessage)
                .clickGiftOptionsDeleteButton();
        UI.Cart().waitUntilCartPageIsLoaded();
        UI.Cart()
                .verifyGiftOptionsMessageIsEmpty();
    }

    @TmsLink("TC_Shopping_Cart_004")
    @Feature("Shopping_Cart")
    @Test(description = "Verify <<Edit>> button on shopping cart as guest")
    public void test04VerifyEditProductButton() {
        UI.Cart()
                .clickEditButton();
        UI.EditCurrentProduct()
                .waitUntilEditCurrentProductPageIsLoaded()
                .clickUpdateCartButton();
        UI.Cart()
                .verifyProductWasUpdatedOnCart(testData.getFirstProductName());
    }

    @TmsLink("TC_Shopping_Cart_005")
    @Feature("Shopping_Cart")
    @Test(description = "Verify <<Continue Shopping>> button on shopping cart as guest")
    public void test05VerifyContinueShoppingButton() {
        UI.Cart().clickContinueShoppingButton();
        UI.Cart().waitUntilProductsPageIsLoaded();
        UI.Cart().verifyIsNotOnCartPage();
    }

    @TmsLink("TC_Shopping_Cart_006")
    @Feature("Shopping_Cart")
    @Test(description = "Invalid Zip/Postal Code as guest")
    public void test07ZipPostalCodeIsInvalid() {
        UI.Cart().clickEstimateShippingAndTaxSpan()
                .waitUntilZipPostalCodeInputIsLoaded()
                .enterZipPostalCodeInput("qew123")
                .verifyInvalidZipPostalCode();
    }

    @TmsLink("TC_Shopping_Cart_007")
    @Feature("Shopping_Cart")
    @Test(description = "Verify invalid discounts code as guest")
    public void test08InvalidDiscountCode() {
        String invalidCode = "123qwe";
        UI.Cart().clickApplyDiscountCode()
                .waitUntilDiscountCodeInputIsLoaded()
                .enterDiscountCode(invalidCode)
                .clickApplyDiscountCodeButton()
                .waitUntilCartPageIsLoaded()
                .verifyInvalidDiscountCode(invalidCode);
    }

    @TmsLink("TC_Shopping_Cart_008")
    @Feature("Shopping_Cart")
    @Test(description = "Verify invalid gift card code as guest")
    public void test09InvalidGiftCardCode() {
        String invalidCode = "934pin";
        UI.Cart().clickApplyGiftCards()
                .waitUntilGiftCardCodeInputIsLoaded()
                .enterGiftCardCode(invalidCode)
                .clickApplyGiftCardsCodeButton()
                .verifyInvalidGiftCardCode(invalidCode);
    }

    @TmsLink("TC_Shopping_Cart_009")
    @Feature("Shopping_Cart")
    @Test( description = "Verify Estimate Shipping and Tax radio buttons as guest")
    public void test10VerifyEstimateShippingAndTaxRadioButtons() {
        final String flatRateVerifyMessage = testData.getStore().getShippingFees();
        if (!UI.Cart().isCityInputVisible()) {
            UI.Cart().clickEstimateShippingAndTaxSpan();
        }
        UI.Cart().clickOnFlatRateRadioButton()
                .verifyPerOrderRadioButtonVerifyMessage(flatRateVerifyMessage);
        List<String> locations = testData.getStore().getLocations();
        if (locations != null && !locations.isEmpty()) {
            for (int i = 0; i < locations.size(); i++) {
                String[] locationNameAndAddress = locations.get(i).split("\\n");
                UI.Cart().selectPickupStore(i)
                        .verifyPerOrderRadioButtonVerifyMessage(locationNameAndAddress[0]);
            }
        }
    }

    @TmsLink("TC_Shopping_Cart_010")
    @Feature("Shopping_Cart")
    @Test(description = "Verify <<Go to Checkout>> button as guest")
    public void test11GoToCheckoutSuccessful() throws Exception {
        UI.Cart()
                .clickGoToCheckoutSpan();
        UI.Checkout().waitUntilCheckoutLoaderDisappears();
        UI.Checkout().verifyCheckoutPage();
    }

    @TmsLink("TC_Shopping_Cart_011")
    @Feature("Shopping_Cart")
    @Test(description = "Verify <<Go to Checkout>> button when all fields are filled as guest")
    public void test12GoToCheckoutDetailed() {
        if (!UI.Cart()
                .isDeleteButtonPresent()) {
            UI.Cart()
                    .clickGiftOptionsSpan()
                    .enterToInput(testData.getSentence())
                    .enterFromInput(testData.getSentence())
                    .enterGiftOptionsMessage(testData.getSentence())
                    .clickUpdateButton()
                    .waitGiftOptionEditButton()
                    .waitUntilCartPageIsLoaded();
            deleteGiftOptions = true;
        }
        if (testData.getStore().getPromoCode() != null) {
            UI.Cart().clickApplyDiscountCode()
                    .waitUntilDiscountCodeInputIsLoaded()
                    .enterDiscountCode(testData.getStore().getPromoCode().getStandardCode())
                    .clickApplyDiscountCodeButton();
            cancelCoupon = true;
        }
        if (!UI.Cart().isCityInputVisible()) {
            UI.Cart().clickEstimateShippingAndTaxSpan();
        }
        UI.Cart().enterCityInput(testData.getCity())
                .enterZipPostalCodeInput(testData.getZipCode());
        UI.Cart()
                .clickGoToCheckoutSpan();
        UI.Checkout().waitUntilCheckoutLoaderDisappears();
        UI.Checkout().verifyCheckoutPage();
    }

    @TmsLink("TC_Shopping_Cart_012")
    @Feature("Shopping_Cart")
    @Test(description = "Verify <<Remove item>> button on shopping cart as guest")
    public void test13GuestVerifyRemoveItemButton() {
        UI.Cart().clickAllRemoveItemButtons()
                .verifyShoppingCartIsEmpty();
    }

    @TmsLink("TC_Shopping_Cart_014")
    @Feature("Shopping_Cart")
    @Test(description = "Verify <<Clear Shopping Cart>> button on shopping cart as guest")
    public void test14VerifyClearShoppingCartButton() {
        UI.Cart().clickClearShoppingCartButton()
                .verifyShoppingCartIsEmpty();
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
