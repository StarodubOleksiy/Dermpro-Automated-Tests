package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class Cart extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//table[@id='shopping-cart-table']//span[contains(text(),'Move to Wishlist')]")
    private List<WebElement> moveToWishlistButtons;

    @FindBy(xpath = "//tbody[contains(@class,'cart') and contains(@class,'item')]//a[@title='Remove item']")
    private WebElement removeItemButton;

    @FindBy(xpath = "//tbody[contains(@class,'cart')]//a[@title='Edit item parameters']")
    private WebElement editButton;

    @FindBy(xpath = "//form[@id='form-validate']//a[@title='Continue Shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//tbody[contains(@class,'cart')]//button[@title='Clear Shopping Cart']")
    private WebElement clearShoppingCartButton;

    @FindBy(xpath = "//div[@data-ui-id='checkout-cart-validationmessages-message-success']")
    private WebElement cartByMessage;

    @FindBy(xpath = "//div[@id='gift-options-cart']//span[contains(text(),'Gift options')]")
    private WebElement giftOptionsSpan;

    @FindBy(xpath = "//input[@id='gift-message-whole-to-giftOptionsCart']")
    private WebElement giftOptionsToInput;

    @FindBy(xpath = "//input[@id='gift-message-whole-from-giftOptionsCart']")
    private WebElement giftOptionsFromInput;

    @FindBy(xpath = "//div[@class='cart-gift-item']//div[@class='gift-message-summary']")
    private WebElement giftOptionsMessage;

    @FindBy(xpath = "//textarea[@id='gift-message-whole-message']")
    private WebElement giftOptionsMessageInput;

    @FindBy(xpath = "//div[@class='gift-options']//button[@title='Update']")
    private WebElement giftOptionsUpdateButton;

    @FindBy(xpath = "//button[@title='Edit']")
    private WebElement giftOptionsEditButton;

    @FindBy(xpath = "//button[@title='Delete']")
    private WebElement giftOptionsDeleteButton;

    @FindBy(xpath = "//strong[@id='block-shipping-heading']")
    private WebElement estimateShippingTaxSubMenu;

    @FindBy(xpath = "//input[@name='postcode']")
    private WebElement zipPostalCodeInput;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement cityInput;

    @FindBy(xpath = "//strong[@id='block-discount-heading']")
    private WebElement applyDiscountCodeSubMenu;

    @FindBy(xpath = "//input[@name='coupon_code']")
    private WebElement discountCodeInput;

    @FindBy(xpath = "//button[@value='Apply Discount']")
    private WebElement applyDiscountCodeButton;

    @FindBy(xpath = "//button[@value='Cancel Coupon']")
    private WebElement cancelCouponButton;

    @FindBy(xpath = "//strong[@id='gift-card-block-discount-heading']")
    private WebElement applyGiftCardSubMenu;

    @FindBy(xpath = "//input[@id='giftcard_code']")
    private WebElement giftCardCodeInput;

    @FindBy(xpath = "//button[@title='Apply']")
    private WebElement applyGiftCardCodeButton;

    @FindBy(xpath = "//div[@data-ui-id='checkout-cart-validationmessages-message-error']")
    private WebElement giftCardValidationErrorMessage;

    @FindBy(xpath = "//span[contains(text(),'Go to Checkout')]")
    private WebElement goToCheckoutSpan;

    @FindBy(xpath = "//div[@class='cart-container']//button[@title='Go to Checkout']")
    private WebElement goToCheckoutButton;

    @FindBy(xpath = "//input[@id='s_method_flatrate_flatrate']")
    private WebElement flatRateRadioButton;

    @FindBy(xpath = "//input[contains(@id,'s_method_pickupatstore_pickupatstore')]")
    private List<WebElement> storePickupList;

    @FindBy(xpath = "//span[@data-bind='text: getShippingMethodTitle()']")
    private WebElement perOrderRadioButtonVerifyMessage;

    @FindBy(xpath = "//span[@data-bind='text: element.warn']")
    private WebElement zipPostalCodeErrorMessage;

    @FindBy(xpath = "//button[@id='empty_cart_button']")
    private WebElement clearCartButton;

    @FindBy(xpath = "//div[@class='table-wrapper' and @data-bind='blockLoader: isLoading']")
    private WebElement cartSummaryBlock;

    @FindBy(xpath = "//tr[@class='totals sub' and contains(.,'You will earn')]//span[@class='price']")
    private WebElement pointsEarnedForPurchase;

    @FindBy(xpath = "//tr[@class='totals sub']//span[@data-th='Subtotal']")
    private WebElement subTotalInfo;

    @FindBy(xpath = "//tr[@class='grand totals']//span[@class='price']")
    private WebElement grandTotalInfo;

    @FindBy(xpath = "//input[@title='Qty']")
    private List<WebElement> qtyInputs;

    public Cart(WebDriver driver) {
        super(driver);
    }

    @Step("Wait until cart page is loaded")
    public Cart waitUntilCartPageIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(cartSummaryBlock));
        return this;
    }

    @Step("Wait until cart elements are loaded")
    public Cart waitUntilCartElementsLoaded() {
        WebElement cartTotal = driver.findElement(By.xpath("//div[@id='cart-totals']"));
        wait.until(ExpectedConditions.visibilityOf(cartTotal));
        return this;
    }

    @Step("Wait until gift options block is loaded")
    public Cart waitUntilGiftOptionsBlockIsLoaded() {
        WebElement optionBlock = driver.findElement(By.xpath("//div[contains(@class,'gift-item-block')]"));
        wait.until(ExpectedConditions.visibilityOf(optionBlock));
        return this;
    }

    @Step("Wait until discount code input field is present")
    public Cart waitUntilDiscountCodeInputIsLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(discountCodeInput));
        return this;
    }

    @Step("Wait until gift cart code input field is present")
    public Cart waitUntilGiftCardCodeInputIsLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(giftCardCodeInput));
        return this;
    }

    @Step("Wait until zip postal code input field is present")
    public Cart waitUntilZipPostalCodeInputIsLoaded() {
        wait.until(ExpectedConditions.elementToBeClickable(zipPostalCodeInput));
        return this;
    }

    @Step("Verify Qty input {qty}")
    public boolean verifyQty(String number) {
        for (int i = 0; i < qtyInputs.size(); ++i)
            if (qtyInputs.get(i).getAttribute("value").contains(number))
                return true;
        return false;
    }

    @Step("Verify if <<Gift Options>> input span is present")
    public boolean isGiftOptionsToInputPresent() {
        return isElementPresent("//input[@id='gift-message-whole-to-giftOptionsCart']", driver);
    }


    @Step("Click on <<Estimate Shipping and Tax>> span")
    public Cart clickEstimateShippingAndTaxSpan() {
        click(estimateShippingTaxSubMenu);
        return this;
    }

    @Step("Click on <<Flat Rate>> radio button")
    public Cart clickOnFlatRateRadioButton() {
        click(flatRateRadioButton);
        return this;
    }

    @Step("Select pickup store with index {index}")
    public Cart selectPickupStore(int index) {
        click(storePickupList.get(index));
        return this;
    }

    @Step("Click on <<Remove item>> button")
    public Cart clickRemoveItemButton() {
        click(removeItemButton);
        return this;
    }

    @Step("Click on all buttons <<Remove item>> to clear shopping cart")
    public Cart clickAllRemoveItemButtons() {
        while (isElementPresent("//tbody[contains(@class,'cart')]//a[@title='Remove item']", driver)) {
            click(removeItemButton);
        }
        return this;
    }

    @Step("Click on <<Clear Shopping Cart>> button")
    public Cart clearShoppingCartButtonClick() {
        scrollIntoView(clearCartButton, driver);
        clearCartButton.click();
        return this;
    }

    @Step("Click on <<Move to Wishlist>> button {index}")
    public Cart clickMoveToWishListButton(int index) {
        click(moveToWishlistButtons.get(index));
        return this;
    }


    @Step("Click on <<Gift options>> span")
    public Cart clickGiftOptionsSpan() {
        if (!isElementPresent("//div[contains(@class,'gift-item-block block _active')]", driver)) {
            click(giftOptionsSpan);

        }
        return this;
    }

    @Step("Click on <<Go to Checkout>> span") //TODO need to find a solution how to correctly omit pause
    public Cart clickGoToCheckoutSpan() {
        waitUntilCartPageIsLoaded();
        sleep(2000);
        click(goToCheckoutSpan);
        return this;
    }

    @Step("Click on <<Go to Checkout>> button")
    public Cart clickGoToCheckout() {
        waitUntilCartElementsLoaded();
        sleep(2000);
        click(goToCheckoutSpan);
        return this;
    }

    @Step("Click on <<Edit>> button")
    public Cart clickEditButton() {
        click(editButton);
        return this;
    }

    @Step("Click on <<Continue Shopping>> button")
    public Cart clickContinueShoppingButton() {
        click(continueShoppingButton);
        return this;
    }

    @Step("Click on <<Clear Shopping Cart>> button")
    public Cart clickClearShoppingCartButton() {
        click(clearCartButton);
        return this;
    }

    @Step("Click on <<Update>> button")
    public Cart clickUpdateButton() {
        click(giftOptionsUpdateButton);
        return this;
    }

    @Step("Wait until gift option <<Edit>> button appears")
    public Cart waitGiftOptionEditButton() {
        wait.until(ExpectedConditions.visibilityOf(giftOptionsEditButton));
        return this;
    }

    @Step("Verify if product was added to cart {name}")
    public Cart verifyProductAddToCart(String name) {
        wait.until(ExpectedConditions.visibilityOf(cartByMessage));
        scrollIntoView(cartByMessage, driver);
        Assert.assertEquals(cartByMessage.getText(), "You added " + name + " to your shopping cart.");
        return this;
    }

    @Step("Verify if product was added to cart {name} v2")
    public Cart verifyProductAddToCartV2(String name) {
        this.waitUntilCheckoutLoaderDisappears();
        cartByMessage = driver.findElement(By.xpath("//div[contains(@data-ui-id,'message-success')]"));
        wait.until(ExpectedConditions.visibilityOf(cartByMessage));
        scrollIntoView(cartByMessage, driver);
        Assert.assertEquals(cartByMessage.getText(), "You added " + name + " to your shopping cart.");
        return this;
    }

    @Step("Verify if product was added to cart from wishlist {name}")
    public Cart verifyProductWasAddedToCartFromWishlist(String name) {
        WebElement cartVerifyMessage = driver.findElement(By.xpath("//div[@class='page messages']"));
        wait.until(ExpectedConditions.visibilityOf(cartVerifyMessage));
        scrollIntoView(cartVerifyMessage, driver);
        Assert.assertEquals(cartVerifyMessage.getText(), "You added " + name + " to your shopping cart.");
        return this;
    }

    @Step("Verify summary")
    private Cart verifySummary() {
        wait.until(ExpectedConditions.visibilityOf(estimateShippingTaxSubMenu));
        scrollIntoView(estimateShippingTaxSubMenu, driver);
        Assert.assertEquals(estimateShippingTaxSubMenu.getText(), "Estimate Shipping and Tax");
        Assert.assertEquals(applyDiscountCodeSubMenu.getText(), "Apply Discount Code");
        Assert.assertEquals(applyGiftCardSubMenu.getText(), "Apply Gift Cards");
        Assert.assertTrue(isElementPresent("//th[contains(text(),'Subtotal')]", driver), "'Subtotal' section should be present");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Shipping')]", driver), "'Shipping' section should be present");
        Assert.assertTrue(isElementPresent("//strong[contains(text(),'Order Total')]", driver), "'Order Total' section should be present");
        Assert.assertTrue(isElementPresent("//strong[contains(text(),'Apply Discount Code')]", driver), "'Apply Discount Code' section should be present");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Go to Checkout')]", driver), "'Go to Checkout' section should be present");
        return this;
    }

    @Step("Verify Cart summary")
    public Cart verifyCartSummary() {
        verifySummary();
        Assert.assertTrue(isElementPresent("//th[contains(text(),'You will earn')]", driver), "'You will earn' section should be present");
        return this;
    }

    @Step("Verify Cart summary Guest")
    public Cart verifyCartSummaryGuest() {
        verifySummary();
        Assert.assertFalse(isElementPresent("//th[contains(text(),'You will earn')]", driver), "'You will earn' section should be present");
        return this;
    }

    @Step("Verify Cart page title")
    public Cart verifyCartPageTitle() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        verifyTitle(pageTitle, "Shopping Cart");
        return this;
    }

    @Step("Verify is not on Cart page")
    public Cart verifyIsNotOnCartPage() {
        Assert.assertFalse(isElementPresent("//span[contains(text(),'Shopping Cart')]", driver), "'Shopping Cart' title should not be present");
        return this;
    }

    @Step("Verify Cart page {productName}")
    public Cart verifyCartPage(String productName) {
        verifyCartPageTitle();
        verifyCart(productName);
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Move to Wishlist')]", driver), "'Move to Wishlist' section should be present");
        return this;
    }

    @Step("Verify Cart {productName}")
    private Cart verifyCart(String productName) {
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Price')]", driver), "'Price' section should be present");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Qty')]", driver), "'Qty' section should be present");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Subtotal')]", driver), "'Subtotal' section should be present");
        Assert.assertTrue(isElementPresent("//a[contains(text(),'" + productName + "')]", driver), String.format("Product name '%s' should be present", productName));
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Edit')]", driver), "'Edit' section should be present");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Remove item')]", driver), "'Remove Item' section should be present");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Continue Shopping')]", driver), "'Continue Shopping' section should be present");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Clear Shopping Cart')]", driver), "'Clear Shopping Cart' section should be present");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Update Cart')]", driver), "'Update Cart' section should be present");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Gift options')]", driver), "'Gift options' section should be present");
        return this;
    }

    @Step("Verify Cart page guest")
    public Cart verifyCartPageGuest(String productName) {
        verifyCartPageTitle();
        verifyCart(productName);
        Assert.assertFalse(isElementPresent("//span[contains(text(),'Move to Wishlist')]", driver), "'Move to Wishlist' section should be present");
        return this;
    }

    @Step("Enter Gift Message (optional) {message}")
    public Cart enterGiftOptionsMessage(String message) {
        sendText(giftOptionsMessageInput, message);
        return this;
    }

    @Step("Enter gift card code {message}")
    public Cart enterGiftCardCode(String message) {
        sendText(giftCardCodeInput, message);
        return this;
    }

    @Step("Enter discount code {message}")
    public Cart enterDiscountCode(String message) {
        sendText(discountCodeInput, message);
        return this;
    }

    @Step("Clear Gift Message (optional)")
    public Cart clearGiftOptionsMessage() {
        clear(giftOptionsMessageInput);
        return this;
    }

    @Step("Enter Zip/Postal Code {code}")
    public Cart enterZipPostalCodeInput(String code) {
        sendText(zipPostalCodeInput, code);
        return this;
    }

    @Step("Check city input visible")
    public boolean isCityInputVisible() {
        return cityInput.isDisplayed();
    }

    @Step("Enter city {code}")
    public Cart enterCityInput(String code) {
        sendText(cityInput, code);
        return this;
    }

    @Step("Enter input field <<To>> {message}")
    public Cart enterToInput(String message) {
        wait.until(ExpectedConditions.elementToBeClickable(giftOptionsToInput));
        sendText(giftOptionsToInput, message);
        return this;
    }

    @Step("Enter input field <<From>> {message}")
    public Cart enterFromInput(String message) {
        wait.until(ExpectedConditions.elementToBeClickable(giftOptionsFromInput));
        sendText(giftOptionsFromInput, message);
        return this;
    }

    @Step("Verify if Zip/Postal Code is invalid")
    public Cart verifyInvalidZipPostalCode() {
        wait.until(ExpectedConditions.visibilityOf(zipPostalCodeErrorMessage));
        Assert.assertEquals(zipPostalCodeErrorMessage.getText(), "Provided Zip/Postal Code seems to be invalid. Example: 12345-6789; 12345. If you believe it is the right one you can ignore this notice.");
        return this;
    }

    @Step("Verify if product {name} was updated on cart")
    public Cart verifyProductWasUpdatedOnCart(String name) {
        WebElement cartVerifyMessage = driver.findElement(By.xpath("//div[@class='page messages']//div[@class='messages']"));
        wait.until(ExpectedConditions.visibilityOf(cartVerifyMessage));
        scrollIntoView(cartVerifyMessage, driver);
        Assert.assertEquals(cartVerifyMessage.getText(), name + " was updated in your shopping cart.");
        return this;
    }

    @Step("Verify if shopping cart is empty")
    public Cart verifyShoppingCartIsEmpty() {
        Assert.assertTrue(isElementPresent("//p[contains(text(),'You have no items in your shopping cart.')]", driver), "'You have no items in your shopping cart.' message should be present");
        return this;
    }

    @Step("Verify gift options message {message}")
    public Cart verifyGiftOptionsMessage(String message) {
        scrollIntoView(giftOptionsMessage,driver);
        Assert.assertEquals(giftOptionsMessage.getText(), message);
        return this;
    }

    @Step("Verify if gift options message is empty")
    public Cart verifyGiftOptionsMessageIsEmpty() {
        WebElement element = driver.findElement(By.xpath("//div[@class='cart-gift-item']//div[@class='gift-message-summary']"));
        wait.until(ExpectedConditions.invisibilityOf(element));
        return this;
    }


    @Step("Click on <<Edit>> gift options button")
    public Cart clickGiftOptionsEditButton() {
        click(giftOptionsEditButton);
        return this;
    }

    @Step("Click on <<Delete>> gift options button")
    public Cart clickGiftOptionsDeleteButton() {
        click(giftOptionsDeleteButton);
        return this;
    }

    @Step("Check if <<Delete>> gift options button present")
    public boolean isDeleteButtonPresent() {
        return isElementPresent("//button[@title='Delete']", driver);
    }

    @Step("Click on <<Apply Gift Cards>> submenu")
    public Cart clickApplyGiftCards() {
        if (!giftCardCodeInput.isDisplayed())
            click(applyGiftCardSubMenu);
        return this;
    }

    @Step("Click on <<Apply Discount Code>> submenu")
    public Cart clickApplyDiscountCode() {
        if (!applyDiscountCodeButton.isDisplayed()) {
            click(applyDiscountCodeSubMenu);
        }
        return this;
    }

    @Step("Click on <<Apply>> button in Apply Gift Cards submenu")
    public Cart clickApplyGiftCardsCodeButton() {
        click(applyGiftCardCodeButton);
        return this;
    }

    @Step("Click on <<Apply>> button in Discount Code submenu")
    public Cart clickApplyDiscountCodeButton() {
        click(applyDiscountCodeButton);
        return this;
    }

    @Step("Click on <<Cancel Coupon>> button in Discount Code submenu")
    public Cart clickCancelCouponButton() {
        click(cancelCouponButton);
        return this;
    }

    public boolean isCancelCouponButtonPresent() {
        return isElementPresent("//button[@value='Cancel Coupon']", driver);
    }

    @Step("Verify if discount code is invalid {code}")
    public Cart verifyInvalidDiscountCode(String code) {
        WebElement cartVerifyMessage = driver.findElement(By.xpath("//div[@data-bind='html: message.text']"));
        scrollIntoView(cartVerifyMessage, driver);
        Assert.assertEquals(cartVerifyMessage.getText(), "The coupon code \"" + code + "\" is not valid.");
        return this;
    }

    @Step("Verify if gift card code is invalid {code}")
    public Cart verifyInvalidGiftCardCode(String code) {
        wait.until(ExpectedConditions.visibilityOf(giftCardValidationErrorMessage));
        scrollIntoView(giftCardValidationErrorMessage, driver);
        Assert.assertEquals(giftCardValidationErrorMessage.getText(), "Could not apply gift card " + code);
        return this;
    }

    @Step("Verify per order radio button verify message {text}")
    public Cart verifyPerOrderRadioButtonVerifyMessage(String text) {
        wait.until(ExpectedConditions.visibilityOf(perOrderRadioButtonVerifyMessage));
        scrollIntoView(perOrderRadioButtonVerifyMessage, driver);
        Assert.assertTrue(perOrderRadioButtonVerifyMessage.getText().contains(text), "Verification message should contain: " + text);
        return this;
    }

    @Step("Verify if few products have been added to shopping cart ")
    public Cart verifyFewProductsHaveBeenAddedToShoppingCart() {
        WebElement cartVerifyMessage = driver.findElement(By.xpath("//div[@data-bind='html: message.text']"));
        wait.until(ExpectedConditions.visibilityOf(cartVerifyMessage));
        scrollIntoView(cartVerifyMessage, driver);
        String expectedMessage = "product(s) have been added to shopping cart:";
        Assert.assertTrue(cartVerifyMessage.getText().contains(expectedMessage), "Cart verify message should contain:" + expectedMessage);
        return this;
    }

    @Step("Get earned points")
    public String getEarnedPoints() {
        wait.until(ExpectedConditions.visibilityOf(pointsEarnedForPurchase));
        return pointsEarnedForPurchase.getText();
    }

    @Step("Get subtotal value")
    public String getSubTotal() {
        waitUntilCartPageIsLoaded();
        wait.until(ExpectedConditions.visibilityOf(subTotalInfo));
        return subTotalInfo.getText();
    }

    @Step("Get grand total value")
    private String getGrandTotal() {
        wait.until(ExpectedConditions.visibilityOf(grandTotalInfo));
        return grandTotalInfo.getText();
    }

    @Step("Check if grand total is even")
    public boolean isGrandTotalEven() {
        String grandTotal = getGrandTotal();
        int total = Integer.parseInt(grandTotal.substring(1, grandTotal.indexOf(".")));
        return (total % 2 == 0);
    }

    @Step("Calculate earned points {percent} {subTotal}")
    public Integer calculateEarnedPoints(int percent, String subTotal) {
        int price = Integer.parseInt(subTotal.substring(1, subTotal.indexOf(".")));
        int pointsRaw = (price * percent) / 100;
        return Math.round(pointsRaw);
    }

    @Step("Verify earned points for purchase product {pointsRounded}")
    public Cart verifyEarnedPointsForPurchaseProduct(int pointsRounded) {
        if (pointsRounded == 0) {
            Assert.assertFalse(isElementPresent("//tr[@class='totals sub' and contains(.,'You will earn')]//span[@class='price']", driver));
        } else {
            Assert.assertTrue(getEarnedPoints().contains(Integer.toString(pointsRounded)), "Amount of points according to the rule should be displayed in Cart");
        }
        return this;
    }

    @Step("Verify product {productName} qty {qty}")
    public Cart verifyProductQty(String qty, String productName) {
        WebElement productQty = driver.findElement(By.xpath(String.format("//tr[@class='item-info' and contains(.,'%s')]//input[@title='Qty']", productName)));
        wait.until(ExpectedConditions.visibilityOf(productQty));
        Assert.assertEquals(productQty.getAttribute("value"), qty);
        return this;
    }


    @Step("Return numbers of products which are in cart")
    public int getSizeOfProductsInCart() {
        return qtyInputs.size();
    }

    public boolean isOptionalGiftMessageSpanPresent()
    {
        return isElementPresent("//span[contains(text(),'Gift Message (optional)')]",driver);
    }

    public boolean isOptionalGiftMessageDisplayed()
    {
        WebElement giftMessageOptionalSpan = driver.findElement(By.xpath("//span[contains(text(),'Gift Message (optional)')]"));
        return giftMessageOptionalSpan.isDisplayed();
    }

}
