package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class GiftCardTest extends BaseTest {

    private final String giftCardName = "E-Gift Card";
    private final String imageName = "Birthday Images";
    private String priceFrom;
    private String priceTo;
    private final String amount = "55";
    private final String invalidEmail = "invalid";


    @Override
    public void init() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-34A")) {
            StringBuilder minPrice = new StringBuilder(testData.getStore().getGiftCard().getMinPrice());
            StringBuilder maxPrice = new StringBuilder(testData.getStore().getGiftCard().getMaxPrice());
            minPrice.insert(0, "$");
            if (!minPrice.toString().contains("."))
                minPrice.append(".00");
            maxPrice.insert(0, "$");
            if (!maxPrice.toString().contains("."))
                maxPrice.append(".00");
            priceFrom = minPrice.toString();
            priceTo = maxPrice.toString();
        }
    }

    @Override
    public void initMethod() {
        if (isFeatureEnabled(testData.getStore().getFeatureStates(), "SO-34A")) {
            UI.Menu()
                    .selectMenu(testData.getStore().getGiftCard().getMenuTitle());
            testData.getStore().getGiftCard().getMenuTitle();// testData.Store з mainMenuCategories
            testData.getStore().getMainMenuCategories();
            UI.GiftCardList()
                    .waitUntilGiftCardListPageIsLoaded();
        }
    }

    @TmsLink("TC_Gift_Card_001")
    @Feature("Gift_Card")
    @Test(groups = "SO-34A", description = "Verify Gift Card page")
    public void test01VerifyGiftCardPage() {
        UI.GiftCardList()
                .verifyGiftCardListPage(testData.getStore().getGiftCard().getMenuTitle());
    }

    @TmsLink("TC_Gift_Card_002")
    @Feature("Gift_Card")
    @Test(groups = "SO-34A", description = "Verify Gift Card prices and name")
    public void test02VerifyGiftCardNamePrices() {
        UI.GiftCardList()
                .verifyIfGiftCardNameAndPricesArePresent(giftCardName, priceFrom, priceTo);
    }

    @TmsLink("TC_Gift_Card_003")
    @Feature("E_Gift_Card")
    @Test(groups = "SO-34A", description = "Verify Gift Card prices and name")
    public void test03VerifyGiftCardPreview() {
        UI.GiftCardList()
                .addToCartClick(giftCardName);
        UI.GiftCard()
                .verifySelectedGiftCardPage(giftCardName);
    }

    @TmsLink("TC_Gift_Card_004")
    @Feature("Gift_Card")
    @Test(groups = "SO-34A", description = "Verify Add Gift Card to Cart")
    public void test04VerifyAddGiftCardToCartFilledRequiredFields() {
        UI.GiftCardList()
                .addToCartClick(giftCardName);
        UI.GiftCard()
                .verifySelectedGiftCardPage(giftCardName)
                .enterAmount(amount)
                .enterRecipientEmail(testData.getEmailAddress())
                .clickAddToCart()
                .verifyIfGiftCardAddedToCart(giftCardName);
    }

    @TmsLink("TC_Gift_Card_005")
    @Feature("Gift_Card")
    @Test(groups = "SO-34A", description = "Verify Add Gift Card to Cart when all fields are filled")
    public void test05VerifyAddGiftCardToCartFilledAllFields() {
        LocalDate date = LocalDate.now();
        int numberOfMonth = date.getDayOfMonth() + 1;
        final int qtyNumber = 1;
        UI.GiftCardList()
                .addToCartClick(giftCardName);
        UI.GiftCard()
                .waitUntilGiftCardPageIsLoaded()
                .enterAmount(amount)
                .verifySelectedGiftCardPage(giftCardName)
                .enterRecipientEmail(testData.getEmailAddress())
                .enterTo(testData.getFirstName())
                .enterMessage(testData.getSentence());
        UI.GiftCard()
                .verifyNumberOfMessageCharacters(testData.getSentence().length())
                .enterQty(String.valueOf(qtyNumber))
                .clickOnCurrentImage(imageName)
                .clickOnDatePickerButton()
                .chooseDayOfMonth(numberOfMonth)
                .clickAddToCart()
                .verifyGiftCardFields(testData.getFirstName(), testData.getSentence(), imageName, String.valueOf(qtyNumber));
    }

    @TmsLink("TC_Gift_Card_006")
    @Feature("Gift_Card")
    @Test(groups = "SO-34A", description = "Verify Add Gift Card to Cart")
    public void test06VerifyRequiredFieldValidation() {
        UI.GiftCardList()
                .addToCartClick(giftCardName);
        UI.GiftCard()
                .waitUntilGiftCardPageIsLoaded()
                .verifySelectedGiftCardPage(giftCardName)
                .clearQty()
                .clickAddToCart()
                .verifyEmailErrorMessage()
                .verifyQtyErrorMessage()
                .enterRecipientEmail(invalidEmail)
                .enterQty(String.valueOf(0))
                .clickAddToCart()
                .verifyEmailValidation()
                .verifyQtyValidation()
                .clearEmail()
                .clearQty()
                .enterRecipientEmail(testData.getEmailAddress())
                .enterQty(String.valueOf(10001))
                .clickAddToCart()
                .verifyQtyIsTooBig();
    }

    @TmsLink("TC_Gift_Card_007")
    @Feature("Gift_Card")
    @Test(groups = "SO-34A", description = "Verify Gift Card value range in a format.")
    public void test07VerifyGiftCardValueRangeFormat() {
        UI.GiftCardList()
                .addToCartClick(giftCardName);
        UI.GiftCard()
                .verifyGiftCardPriceFrom(priceFrom)
                .verifyGiftCardPriceTo(priceTo);
    }

    @TmsLink("TC_Gift_Card_008")
    @Feature("Gift_Card")
    @Test(groups = "SO-34A", description = "Verify that there are no Add to Wish List, Add to Compare icons. And Email icon is present.")
    public void test08VerifyGiftCardIcons() {
        UI.GiftCardList()
                .addToCartClick(giftCardName);
        UI.GiftCard()
                .verifyIconsNotPresent()
                .verifyIconsPresent();
    }

    @TmsLink("TC_Gift_Card_009")
    @Feature("Gift_Card")
    @Test(groups = "SO-34A", description = "Verify that in product data tabs opposite to FAQ, Skin Type and Ingredients there is N/A text.")
    public void test09NAText() {
        UI.GiftCardList()
                .addToCartClick(giftCardName)
                .waitUntilCheckoutLoaderDisappears();
        UI.ProductDetails()
                .clickOnAdditionalInfoTab();
        UI.GiftCard().verifyAdditionalInfoValues();
        UI.ProductDetails()
                .clickOnIngredientsTab();
        UI.GiftCard().verifyIngredientsValues();
    }

    @AfterClass
    public void after() {
        UI.clearCart();
    }

}
