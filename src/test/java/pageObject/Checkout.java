package pageObject;

import creation.bean.AdditionalRequiredFields;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Checkout extends PageObjectInitializer {

    @FindBy(xpath = "//input[@id='pas-yes']")
    private WebElement orderInOfficeYesButton;

    @FindBy(xpath = "//select[@id='pas-pos-selector']")
    private WebElement storeSelect;

    @FindBy(xpath = "//div[@class='control _with-tooltip']//input[@id='customer-email']")
    private WebElement customerEmailInput;

    @FindBy(xpath = "//input[@id='customer-password']")
    private WebElement customerPasswordInput;

    @FindBy(xpath = "//input[@id='password-confirmation']")
    private WebElement customerConfirmPasswordInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='firstname']")
    private WebElement customerFirstNameInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='lastname']")
    private WebElement customerLastNameInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='company']")
    private WebElement customerCompanyInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='street[0]']")
    private WebElement customerFirstStreetAddressInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='street[1]']")
    private WebElement customerSecondStreetAddressInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='street[2]']")
    private WebElement customerThirdStreetAddressInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='city']")
    private WebElement customerCityInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//select[@name='country_id']")
    private WebElement customerCountrySelect;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//select[@name='region_id']")
    private WebElement customerStateSelect;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='postcode']")
    private WebElement customerZipCodeInout;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[@name='telephone']")
    private WebElement customerPhoneInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//input[contains(@name,'dateofbirth')]")
    private WebElement dateOfBirthInput;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//span[@class='field-tooltip-action action-help']")
    private WebElement customerTooltip;

    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[@class='field-tooltip-content']")
    private WebElement customerTooltipContent;

    @FindBy(xpath = "//span[text()='New Address']")
    private WebElement newAddressButton;

    @FindBy(xpath = "//input[@id='shipping-save-in-address-book']")
    private WebElement saveInAddressBookCheckbox;

    @FindBy(xpath = "//span[text()='Save Address']")
    private WebElement saveAddressButton;

    @FindBy(xpath = "//div[@class='primary']//span[contains(text(),'Continue Shopping')]")
    private WebElement continueShoppingButton;

    // Payment method information

    @FindBy(xpath = "//input[@id='billing-address-same-as-shipping-bluepay_payment']")
    private WebElement myBillingShippingCheckbox;

    @FindBy(xpath = "//input[@id='bluepay_payment_cc_number']")
    private WebElement creditCardInput;

    @FindBy(xpath = "//select[@id='bluepay_payment_expiration']")
    private WebElement expirationMonthSelect;

    @FindBy(xpath = "//select[@id='bluepay_payment_expiration_yr']")
    private WebElement expirationYearSelect;

    @FindBy(xpath = "//input[@id='bluepay_payment_cc_cid']")
    private WebElement cvvCodeInput;

    @FindBy(xpath = "//span[@class='field-tooltip-action action-cvv _active']")
    private WebElement cvvTooltip;

    @FindBy(xpath = "//div[@id='bluepay_payment_cc_type_cvv_div']//div[@class='field-tooltip-content']")
    private WebElement cvvTooltipContent;

    @FindBy(xpath = "//div[@id='checkout-payment-method-load']//input[@name='firstname']")
    private WebElement paymentFirstNameInput;

    @FindBy(xpath = "//div[@id='checkout-payment-method-load']//input[@name='lastname']")
    private WebElement paymentLastNameInput;

    @FindBy(xpath = "//div[@id='checkout-payment-method-load']//input[@name='city']")
    private WebElement paymentCityInput;

    @FindBy(xpath = "//div[@id='checkout-payment-method-load']//select[@name='region_id']")
    private WebElement paymentStateSelect;

    @FindBy(xpath = "//div[@id='checkout-payment-method-load']//input[@name='postcode']")
    private WebElement paymentZipCodeInput;

    @FindBy(xpath = "//div[@id='checkout-payment-method-load']//input[@name='telephone']")
    private WebElement paymentPhoneInput;

    @FindBy(xpath = "//fieldset[@class='fieldset']//input[@name='company']")
    private WebElement paymentCompanyInput;

    @FindBy(xpath = "//div[@id='checkout-payment-method-load']//input[@name='street[0]']")
    private WebElement paymentFirstStreetAddressInput;

    @FindBy(xpath = "//fieldset[@class='fieldset']//input[@name='street[1]']")
    private WebElement paymentSecondStreetAddressInput;

    @FindBy(xpath = "//fieldset[@class='fieldset']//input[@name='street[2]']")
    private WebElement paymentThirdStreetAddressInput;

    @FindBy(xpath = "//fieldset[@id='billing-new-address-form-bluepay_payment-form']//span[@class='field-tooltip-action action-help']")
    private WebElement paymentPhoneTooltip;

    @FindBy(xpath = "//fieldset[@id='billing-new-address-form-bluepay_payment-form']//div[@class='field-tooltip-content']")
    private WebElement paymentPhoneTooltipContent;

    @FindBy(xpath = "//fieldset[@class='fieldset']//select[@name='gender']")
    private WebElement paymentGenderSelect;

    @FindBy(xpath = "//button[@class='action action-update']")
    private WebElement paymentUpdateButton;

    @FindBy(xpath = "//button[@class='action action-cancel']")
    private WebElement paymentCancelButton;

    @FindBy(xpath = "//b[text()='Survey Question']")
    private WebElement surveyQuestionSelect;

    @FindBy(xpath = "//div[@class='payment-options-content servey-content']//span")
    private WebElement surveyQuestionText;

    @FindBy(xpath = "//select[@name='suorder_comment']")
    private WebElement providerSelect;

    @FindBy(xpath = "//button[@class='action-primary action-accept']//span[text()='OK']")
    private WebElement surveyMessageOkButton;

    @FindBy(xpath = "//span[text()='Apply Gift Cards']")
    private WebElement giftCardSelect;

    @FindBy(xpath = "//input[@id='giftcard_code']")
    private WebElement giftCardInput;

    @FindBy(xpath = "//button[contains(@data-bind,'click: applyGiftCardCode')]")
    private WebElement apllyGiftCardButton;

    @FindBy(xpath = "//div[@class='payment-method-content']//div[@data-ui-id='checkout-cart-validationmessages-message-error']")
    private WebElement paymentMethodValidationMessage;

    @FindBy(xpath = "//button[contains(@class,'ui-datepicker-trigger')]")
    private WebElement openDatePickerButton;

    // Your order section

    @FindBy(xpath = "//input[@name='gift_message']")
    private WebElement giftMessageCheckbox;

    @FindBy(xpath = "//span[@class='edit-link']")
    private WebElement editGiftMessageLink;

    @FindBy(xpath = "//input[@id='gift-message-whole-to']")
    private WebElement giftMessageToInput;

    @FindBy(xpath = "//input[@id='gift-message-whole-from']")
    private WebElement giftMessageFromInput;

    @FindBy(xpath = "//textarea[@id='gift-message-whole-message']")
    private WebElement giftMessageInput;

    @FindBy(xpath = "//textarea[@name='additional[comment]']")
    private WebElement ordersCommentInput;

    @FindBy(xpath = "//button[@class='action primary action-save-address']")
    private WebElement giftMessageUpdateButton;

    @FindBy(xpath = "//input[@name='additional[subscribe]']")
    private WebElement signUpNewsletterCheckbox;

    @FindBy(xpath = "//div[@class='checkout-agreement required'][1]//input")
    private WebElement agreementCheckbox;

    @FindBy(xpath = "//div[@class='checkout-agreement required'][2]//input")
    private WebElement subscriptionTermsCheckbox;

    @FindBy(xpath = "//div[contains(@class,'checkout-agreement') and contains(@class,'required')]//span[contains(text(),'Terms and Conditions')]")
    private WebElement termsAndConditionsLink;

    @FindBy(xpath = "//div[@class='checkout-agreements-item-content']")
    private WebElement termsAndConditionsText;

    @FindBy(xpath = "//footer[@class='modal-footer']//span[text()='Close']")
    private WebElement termsAndConditionsCloseButton;

    @FindBy(xpath = "//div[@class='checkout-payment-method']//span[@id='block-discount-heading']")
    private WebElement discountCodeSelect;

    @FindBy(xpath = "//form[@id='discount-form']//input[@id='discount-code']")
    private WebElement discountCodeInput;

    @FindBy(xpath = "//form[@id='discount-form']//button[@class='action action-apply']")
    private WebElement discountCodeApplyButton;

    @FindBy(xpath = "//form[@id='discount-form']//button[@class='action action-cancel']")
    private WebElement cancelCouponButton;

    @FindBy(xpath = "//div[@class='payment-option-content']//div[@class='messages']")
    private WebElement discountMessage;

    @FindBy(xpath = "(//div[@class='payment-option-content'])[2]//div[@data-ui-id='checkout-cart-validationmessages-message-error']")
    private WebElement discountCodeValidationMessage;

    @FindBy(xpath = "//span[@class='price' and @data-th='Shipping']")
    private WebElement shippingPrice;

    @FindBy(xpath = "//div[@class='payment-methods']//button[contains(@title,'Place Order')]")
    private WebElement placeOrderButton;

    // Shipping method section


    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@class='description']")
    private WebElement description;

    //After successful checkout page

    @FindBy(xpath = "//a[@class='order-number']")
    private WebElement orderNumber;

    public Checkout(WebDriver driver) {
        super(driver);
    }

    @Step("Verify you are on Checkout page")
    public Checkout verifyCheckoutPage() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        verifyTitle(pageTitle, "Checkout");
        Assert.assertTrue(isElementPresent("//div[contains(text(),'Please fill in the fields below and click Place Order  Now to complete your purchase.')]", driver), "Checkout tutorial should be displayed");
        return this;
    }

    @Step("Verify <<Create account later>> span for guest on Checkout page")
    public Checkout verifyCreateAccountLater() {
        Assert.assertTrue(isElementPresent("//div[contains(text(),'You can create account later')]", driver), "'You can create account later' should be displayed");
        return this;
    }

    // Customer information section

    @Step("Order in office, click on <<Yes>> button")
    public Checkout clickOrderInOfficeYesButton() {
        click(orderInOfficeYesButton);
        return this;
    }

    @Step("Select Store for pickup")
    public Checkout selectStoreForPickup() {
        scrollIntoView(storeSelect, driver);
        Select store = new Select(driver.findElement(By.xpath("//select[@id='pas-pos-selector']")));
        store.selectByIndex(0);
        return this;
    }

    @Step("Enter customer Email {customerEmail}")
    public Checkout enterCustomerEmail(String customerEmail) {
        sendText(customerEmailInput, customerEmail);
        return this;
    }

    @Step("Enter customer First Name {customerFirstName}")
    public Checkout enterCustomerFirstName(String customerFirstName) {
        sendText(customerFirstNameInput, customerFirstName);
        return this;
    }

    @Step("Enter payment First Name {paymentFirstName}")
    public Checkout enterPaymentFirstName(String paymentFirstName) {
        sendText(paymentFirstNameInput, paymentFirstName);
        return this;
    }

    @Step("Enter orders comment {comment}")
    public Checkout enterOrdersComment(String comment) {
        sendText(ordersCommentInput, comment);
        return this;
    }


    @Step("Enter customer Last Name {customerLastName}")
    public Checkout enterCustomerLastName(String customerLastName) {
        sendText(customerLastNameInput, customerLastName);
        return this;
    }

    @Step("Enter payment Last Name {paymentLastName}")
    public Checkout enterPaymentLastName(String paymentLastName) {
        sendText(paymentLastNameInput, paymentLastName);
        return this;
    }


    @Step("Enter customer first address {customerFirstAddress}")
    public Checkout enterCustomerFirstAddress(String customerFirstAddress) {
        sendText(customerFirstStreetAddressInput, customerFirstAddress);
        return this;
    }

    @Step("Enter payment first address {paymentFirstAddress}")
    public Checkout enterPaymentFirstAddress(String paymentFirstAddress) {
        sendText(paymentFirstStreetAddressInput, paymentFirstAddress);
        return this;
    }


    @Step("Enter customer city {customerCity}")
    public Checkout enterCustomerCity(String customerCity) {
        sendText(customerCityInput, customerCity);
        return this;
    }

    @Step("Enter payment city {paymentCity}")
    public Checkout enterPaymentCity(String paymentCity) {
        sendText(paymentCityInput, paymentCity);
        return this;
    }

    @Step("Enter zip code {customerZipCode}")
    public Checkout enterCustomerZipCode(String customerZipCode) {
        sendText(customerZipCodeInout, customerZipCode);
        return this;
    }

    @Step("Enter zip code {paymentZipCode}")
    public Checkout enterPaymentZipCode(String paymentZipCode) {
        sendText(paymentZipCodeInput, paymentZipCode);
        return this;
    }

    @Step("Enter phone {customerPhone}")
    public Checkout enterCustomerPhone(String customerPhone) {
        sendText(customerPhoneInput, customerPhone);
        return this;
    }

    @Step("Enter phone {paymentPhone}")
    public Checkout enterPaymentPhone(String paymentPhone) {
        sendText(paymentPhoneInput, paymentPhone);
        return this;
    }

    @Step("Enter date of birth {dayOfMonth}")
    public Checkout enterDateOfBirth(String dayOfMonth) {
        WebElement day = driver.findElement(By.xpath(String.format("//a[contains(@class,'ui-state') and contains(text(),'%s')]", dayOfMonth)));
        day.click();
        return this;
    }

    @Step("Select customer country {customerCountry}")
    public Checkout selectCustomerCountry(String customerCountry) {
        scrollIntoView(customerCountrySelect, driver);
        Select country = new Select(driver.findElement(By.xpath("//div[@id='shipping-new-address-form']//select[@name='country_id']")));
        country.selectByVisibleText(customerCountry);
        return this;
    }


    @Step("Select customer state {customerState}")
    public Checkout selectCustomerState(String customerState) {
        scrollIntoView(customerStateSelect, driver);
        Select state = new Select(driver.findElement(By.xpath("//div[@id='shipping-new-address-form']//select[@name='region_id']")));
        state.selectByVisibleText(customerState);
        return this;
    }


    @Step("Select payment state {customerState}")
    public Checkout selectPaymentState(String paymentState) {
        scrollIntoView(paymentStateSelect, driver);
        Select state = new Select(driver.findElement(By.xpath("//div[@id='checkout-payment-method-load']//select[@name='region_id']")));
        state.selectByVisibleText(paymentState);
        return this;
    }

    @Step("Clear all customer information")
    public Checkout clearCustomerInformation() {
        clear(customerFirstNameInput);
        customerLastNameInput.clear();
        customerCompanyInput.clear();
        customerFirstStreetAddressInput.clear();
        customerSecondStreetAddressInput.clear();
        customerThirdStreetAddressInput.clear();
        customerCityInput.clear();
        customerZipCodeInout.clear();
        customerPhoneInput.clear();
        return this;
    }

    @Step("Click on <<New Address>> button")
    public Checkout clickNewAddressButton() {
        click(newAddressButton);
        return this;
    }

    @Step("Click on <<Update>> button")
    public Checkout clickOnPaymentUpdateButton() {
        click(paymentUpdateButton);
        return this;
    }

    @Step("Click on <<Save in Address book>> checkbox")
    public Checkout clickSaveInAddressBookCheckbox() {
        click(saveInAddressBookCheckbox);
        return this;
    }

    @Step("Click on <<Save Address>> button")
    public Checkout clickSaveAddressButton() {
        click(saveAddressButton);
        return this;
    }

    // Shipping method section

    @Step("Click on <<My billing and shipping address are the same>> checkbox")
    public Checkout clickBillingShippingTheSame() {
        click(myBillingShippingCheckbox);
        return this;
    }

    @Step("Enter credit card number {creditCardNumber}")
    public Checkout enterCreditCardNumber(String creditCardNumber) {
        sendText(creditCardInput, creditCardNumber);
        return this;
    }

    @Step("Select expiration month {expirationMonth}")
    public Checkout selectExpirationMonth(String expirationMonth) {
        scrollIntoView(expirationMonthSelect, driver);
        Select month = new Select(driver.findElement(By.xpath("//select[@id='bluepay_payment_expiration']")));
        month.selectByVisibleText(expirationMonth);
        return this;
    }

    @Step("Select expiration year {expirationYear}")
    public Checkout selectExpirationYear(String expirationYear) {
        scrollIntoView(expirationYearSelect, driver);
        Select year = new Select(driver.findElement(By.xpath("//select[@id='bluepay_payment_expiration_yr']")));
        year.selectByVisibleText(expirationYear);
        return this;
    }

    @Step("Enter card verification number {verificationNumber}")
    public Checkout enterCardVerificationNumber(String verificationNumber) {
        sendText(cvvCodeInput, verificationNumber);
        return this;
    }

    @Step("Click on <<Survey Question>>")
    public Checkout clickSurveyQuestion() {
        click(surveyQuestionSelect);
        return this;
    }

    @Step("Select survey question provider {provider}")
    public Checkout selectSurveyProvider(String provider) {
        scrollIntoView(providerSelect, driver);
        Select surveyProvider = new Select(driver.findElement(By.xpath("//select[@name='suorder_comment']")));
        surveyProvider.selectByVisibleText(provider);
        return this;
    }

    @Step("Click on <<Apply Gift Card>>")
    public Checkout clickApplyGiftCard() {
        click(giftCardSelect);
        return this;
    }

    @Step("Enter Gift Card number {giftCardNumber}")
    public Checkout enterGiftCard(String giftCardNumber) {
        sendText(giftCardInput, giftCardNumber);
        return this;
    }

    @Step("Click on <<Gift Card Apply>> button")
    public Checkout clickApplyGiftCardButton() {
        click(apllyGiftCardButton);
        return this;
    }

    @Step("Verify invalid Gift Card message {giftNumber}")
    public Checkout verifyInvalidGiftCardMessage(String giftNumber) {
        WebElement giftCardValidationMessage = driver.findElement(By.xpath("//div[@class='messages giftcard-messages']//div[@data-ui-id='checkout-cart-validationmessages-message-error']"));
        wait.until(ExpectedConditions.visibilityOf(giftCardValidationMessage));
        scrollIntoView(giftCardValidationMessage, driver);
        Assert.assertEquals(giftCardValidationMessage.getText(), "Could not apply gift card " + giftNumber + "");
        return this;
    }


    @Step("Clear Credit Card Number")
    public Checkout clearCreditCardNumber() {
        clear(creditCardInput);
        return this;
    }

    @Step("Verify validation of error messages in the payment method {errorMessage}")
    public Checkout verifyValidationErrorsInPaymentMethod(String errorMessage) {
        wait.until(ExpectedConditions.visibilityOf(paymentMethodValidationMessage));
        scrollIntoView(paymentMethodValidationMessage, driver);
        Assert.assertEquals(paymentMethodValidationMessage.getText(), errorMessage);
        wait.until(ExpectedConditions.invisibilityOf(paymentMethodValidationMessage));
        return this;
    }

    // Your order section

    @Step("Click on <<Add a gift message>>")
    public Checkout clickAddGiftMessageCheckbox() {
        click(giftMessageCheckbox);
        return this;
    }

    @Step("Check if gift card message checkbox is checked")
    public boolean isGiftCardCheckboxChecked() {
        boolean value = isElementPresent("//input[@name='gift_message' and @value=1]", driver);
        return value;
    }

    @Step("Enter gift card message To {messageTo}")
    public Checkout enterGiftMessageTo(String messageTo) {
        wait.until(ExpectedConditions.visibilityOf(giftMessageToInput));
        sendText(giftMessageToInput, messageTo);
        return this;
    }

    @Step("Enter gift card message From {messageFrom}")
    public Checkout enterGiftMessageFrom(String messageFrom) {
        sendText(giftMessageFromInput, messageFrom);
        return this;
    }

    @Step("Enter gift card message {message}")
    public Checkout enterGiftMessage(String message) {
        sendText(giftMessageInput, message);
        return this;
    }


    @Step("Verify if gift card message filled")
    public Checkout verifyGiftMessageFilled() {
        Assert.assertTrue(isElementPresent("//span[@class='edit-link']", driver), "Edit link should be displayed");
        Assert.assertTrue(isElementPresent("//div[@class='admin__field admin__field-option am-gift-message checked']", driver), "Gift message checkbox should be checked");
        Assert.assertTrue(isElementPresent("//div[@id='checkout']//div[@class='message message-success success']", driver), "Gift message success should be present");
        WebElement checkoutSuccessMessages = driver.findElement(By.xpath("//div[@id='checkout']//div[@class='message message-success success']"));
        Assert.assertEquals(checkoutSuccessMessages.getText(), "Gift messages has been successfully updated");
        return this;
    }

    @Step("Click on <<Update>> gift message button")
    public Checkout clickUpdateGiftMessageButton() {
        click(giftMessageUpdateButton);
        return this;
    }


    @Step("Click on Edit gift message link")
    public Checkout clickEditGiftMessageLink() {
        WebElement editGiftMessageLink = driver.findElement(By.xpath("//span[@class='edit-link']"));
        click(editGiftMessageLink);
        return this;
    }

    @Step("Click on <<Sign Up for our newsletter>> checkbox")
    public Checkout clickSignUpNewsletterCheckbox() {
        click(signUpNewsletterCheckbox);
        return this;
    }

    @Step("Click on <<Terms and conditions>> checkbox")
    public Checkout clickTermsConditionsCheckbox() {
        click(agreementCheckbox);
        return this;
    }

    @Step("Click on <<Terms and conditions>> link")
    public Checkout clickTermsConditionsLink() {
        click(termsAndConditionsLink);
        return this;
    }

    @Step("Click on Terms and conditions <<Close>> button")
    public Checkout clickTermsConditionsCloseButton() {
        click(termsAndConditionsCloseButton);
        return this;
    }

    @Step("Click on <<Apply Discount Code>> drop-down")
    public Checkout clickApplyDiscountCode() {
        click(discountCodeSelect);
        return this;
    }

    @Step("Enter Discount Code {discountCode}")
    public Checkout enterDiscountCode(String discountCode) {
        sendText(discountCodeInput, discountCode);
        return this;
    }


    @Step("Click on <<Apply Discount Code>> button")
    public Checkout clickApplyDiscountCodeButton() {
        click(discountCodeApplyButton);
        return this;
    }

    @Step("Click on <<Cancel Discount Code>> button")
    public Checkout clickCancelCouponButton() {
        click(cancelCouponButton);
        return this;
    }

    @Step("Check discount code already filled")
    public boolean isDiscountCodeFilled() {
        return isElementPresent("//form[@id='discount-form']//button[@class='action action-cancel']", driver);
    }

    @Step("Check cancel button visible")
    public boolean isCancelButtonVisible() {
        return cancelCouponButton.isDisplayed();
    }

    @Step("Wait until discount message disappears")
    public Checkout waitUntilDiscountCodeMessageDisappears() {
        wait.until(ExpectedConditions.invisibilityOf(discountMessage));
        return this;
    }

    @Step("Verify invalid discount code message")
    public Checkout verifyInvalidDiscountCodeMessage() {
        wait.until(ExpectedConditions.visibilityOf(discountCodeValidationMessage));
        scrollIntoView(discountCodeValidationMessage, driver);
        Assert.assertEquals(discountCodeValidationMessage.getText(), "Coupon code is not valid");
        return this;
    }


    @Step("Verify cancel discount code message")
    public Checkout verifyCancelDiscountCodeMessage() {
        WebElement checkoutSuccessMessages = driver.findElement(By.xpath("//div[@id='checkout']//div[@class='message message-success success']"));
        wait.until(ExpectedConditions.visibilityOf(checkoutSuccessMessages));
        scrollIntoView(checkoutSuccessMessages, driver);
        Assert.assertEquals(checkoutSuccessMessages.getText(), "Your coupon was successfully removed.");
        return this;
    }

    @Step("Verify free shipping discount code")
    public Checkout verifyFreeShippingDiscountCode() {
        wait.until(ExpectedConditions.visibilityOf(shippingPrice));
        scrollIntoView(shippingPrice, driver);
        Assert.assertEquals(shippingPrice.getText(), "$0.00");
        return this;
    }

    @Step("Click on <<Place Order>> button")
    public Checkout clickPlaceOrder() {
        click(placeOrderButton);
        return this;
    }

    @Step("Click to open date picker button")
    public Checkout clickOpenDatePicker() {
        click(openDatePickerButton);
        return this;
    }

    @Step("Verify successful Checkout")
    public Checkout verifySuccessfulCheckout() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@class='action primary continue']//span[text()='Continue Shopping']"))));
        scrollIntoView(pageTitle, driver);
        Assert.assertEquals(pageTitle.getText(), "Thank you for your purchase!");
        return this;
    }

    @Step("Wait until Checkout page loads")
    public Checkout waitUntilCheckoutPageLoads() {
        wait.until(ExpectedConditions.elementToBeClickable(cvvCodeInput));
        wait.until(ExpectedConditions.elementToBeClickable(creditCardInput));
        wait.until(ExpectedConditions.elementToBeClickable(agreementCheckbox));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        wait.until(ExpectedConditions.elementToBeClickable(discountCodeSelect));
        return this;
    }


    @Step("Verify survey question alert")
    public Checkout verifySurveyQuestionAlert() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='*Please complete the required Survey Question']")));
        return this;
    }

    @Step("Click on <<OK>> button in Survey Question message")
    public Checkout clickSurveyMessageOkButton() {
        click(surveyMessageOkButton);
        return this;
    }

    @Step("Verify checkout survey question options {option}")
    public Checkout verifyCheckoutSurveyQuestionOptions(String option) {
        Assert.assertTrue(isElementPresent(String.format("//option[contains(text(),'%s')]", option), driver), "Survey " + option + " option should be displayed");
        return this;
    }

    @Step("Verify checkout survey question {question}")
    public Checkout verifyCheckoutSurveyQuestion(String question) {
        wait.until(ExpectedConditions.visibilityOf(surveyQuestionText));
        Assert.assertEquals(surveyQuestionText.getText(), question);
        return this;
    }

    @Step("Verify additional required fields")
    public Checkout verifyAdditionalRequiredFields(List<AdditionalRequiredFields> data) {
        data.forEach(n -> {
            String name = n.getFieldName();
            String enabledStatus = n.getEnabledStatus().toLowerCase();
            String status = n.getStatus().toLowerCase();
            if (name.equalsIgnoreCase("Birthdate")) {
                n.setFieldName(name.replaceAll(name, "Date of Birth"));
            }
            if (enabledStatus.equalsIgnoreCase("no")) {
                Assert.assertFalse(isElementPresent(String.format("//span[text()='%s']", n.getFieldName()), driver), "Field " + n.getFieldName() + " should not be displayed");
            }
            if (enabledStatus.equalsIgnoreCase("yes")) {
                if (status.equalsIgnoreCase("required")) {
                    Assert.assertTrue(isElementPresent(String.format("//div[contains(@class,'field') and contains(@class,'_required')]//span[contains(text(),'%s')]", n.getFieldName()), driver), "Field " + n.getFieldName() + " should be displayed");
                }
                if (status.equalsIgnoreCase("optional")) {
                    Assert.assertTrue(isElementPresent(String.format("//div[@class='field']//span[text()='%s']", n.getFieldName()), driver), "Field " + n.getFieldName() + " should be displayed");
                }
            }
        });
        return this;
    }

    @Step("Verify Terms and Conditions information {terms}")
    public Checkout verifyTermsConditionsInformation(String terms) {
        wait.until(ExpectedConditions.visibilityOf(termsAndConditionsText));
        Assert.assertEquals(termsAndConditionsText.getText().replaceAll("\\s", ""), terms.replaceAll("\\s", ""));
        return this;
    }

    @Step("Get order number")
    public String getOrderNumber() {
        return orderNumber.getText();
    }

    @Step("Check if Subscription Terms checkbox is present")
    public boolean isSubscriptionTermsPresent() {
        return (isElementPresent("//div[@class='checkout-agreement required'][2]//input", driver));
    }

    @Step("Click on <<Subscription Terms>> checkbox")
    public Checkout clickSubscriptionTermsCheckbox() {
        click(subscriptionTermsCheckbox);
        return this;
    }

     @Step("Click on <<Continue Shopping>> button")
    public Checkout clickContinueShoppingButton() {
        click(continueShoppingButton);
        return this;
    }

    @Step("Verification if payment input fields are present on checkout page")
    public boolean ifPaymentInputFieldsArePresent() {
        return isElementPresent("//div[@id='checkout-payment-method-load']//input[@name='city']", driver);
    }


}
