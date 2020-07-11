package pageObject;

import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GiftCard extends PageObjectInitializer {

    @FindBy(xpath = "//span[@data-ui-id='page-title-wrapper' and @itemprop='name']")
    private WebElement giftCartNameHeader;

    @FindBy(xpath = "//input[@class='giftcard-design-open-amount']")
    private WebElement amountInput;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement recipientEmailInput;

    @FindBy(xpath = "//input[@id='from']")
    private WebElement fromInput;

    @FindBy(xpath = "//input[@id='to']")
    private WebElement toInput;

    @FindBy(xpath = "//input[@id='qty']")
    private WebElement qtyInput;

    @FindBy(xpath = "//textarea[@name='message']")
    private WebElement messageTextArea;

    @FindBy(xpath = "//button[contains(@class,'ui-datepicker-trigger')]")
    private WebElement datePicker;

    @FindBy(xpath = "//button[@id='product-addtocart-button']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@data-ui-id='checkout-cart-validationmessages-message-success']")
    private WebElement giftCardSuccessMessage;

    @FindBy(xpath = "//span[@itemprop='name']")
    private WebElement giftCardName;

    @FindBy(xpath = "//div[@id='email-error']")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//div[@id='qty-error']")
    private WebElement qtyErrorMessage;

    @FindBy(xpath = "//td[@data-th='FAQs']")
    private WebElement dataFAQ;

    @FindBy(xpath = "//td[@data-th='Benefits']//ul//li")
    private WebElement dataBenefits;

    @FindBy(xpath = "//td[@data-th='Skin Type']")
    private WebElement dataSkinType;

    @FindBy(xpath = "//td[@data-th='Brand']")
    private WebElement dataBrand;

    @FindBy(xpath = "//div[@id='ingredients.tab']")
    private WebElement dataIngredients;

    @FindBy(xpath = "//div[@class='giftcard-field-wrapper']//p[@class='note']//span")
    private WebElement numberCharactersInMessage;

    public GiftCard(WebDriver driver) {
        super(driver);
    }

    @Step("Wait until cart page is loaded")
    public GiftCard waitUntilGiftCardPageIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(giftCartNameHeader));
        return this;
    }

    @Step("Enter amount {amount} of gift card")
    public GiftCard enterAmount(String amount) {
        sendText(amountInput, amount);
        return this;
    }

    @Step("Clear amount of gift card")
    public GiftCard clearAmount() {
        clear(amountInput);
        return this;
    }

    @Step("Enter message {message} text area of gift card")
    public GiftCard enterMessage(String amount) {
        sendText(messageTextArea, amount);
        return this;
    }

    @Step("Clear message area of gift card")
    public GiftCard clearMessage() {
        clear(messageTextArea);
        return this;
    }

    @Step("Enter recipient email {email}")
    public GiftCard enterRecipientEmail(String email) {
        sendText(recipientEmailInput, email);
        return this;
    }

    @Step("Clear recipient email")
    public GiftCard clearEmail() {
        clear(recipientEmailInput);
        return this;
    }

    @Step("Enter From {from}")
    public GiftCard enterFrom(String from) {
        sendText(fromInput, from);
        return this;
    }

    @Step("Clear From field")
    public GiftCard clearFrom() {
        clear(fromInput);
        return this;
    }

    @Step("Enter To field")
    public GiftCard enterTo(String to) {
        sendText(toInput, to);
        return this;
    }

    @Step("Clear To field")
    public GiftCard clearTo() {
        clear(toInput);
        return this;
    }

    @Step("Enter Quantity {qty}")
    public GiftCard enterQty(String qty) {
        sendText(qtyInput, qty);
        return this;
    }

    @Step("Clear Quantity")
    public GiftCard clearQty() {
        clear(qtyInput);
        return this;
    }

    @Step("Click on <<Add to Cart>> button")
    public GiftCard clickAddToCart() {
        click(addToCartButton);
        return this;
    }

    @Step("Verify if Gift Card added to Cart {giftCardName}")
    public GiftCard verifyIfGiftCardAddedToCart(String giftCardName) {
        Assert.assertEquals(giftCardSuccessMessage.getText(), "You added " + giftCardName + " to your shopping cart.");
        return this;
    }

    @Step("Verify Gift Card fields which are filled {nameTo}, {message}, {image}, {qtyNumber}")
    public GiftCard verifyGiftCardFields(String nameTo, String message, String image, String qtyNumber) {
        Assert.assertTrue(isElementPresent(String.format("//dd[contains(text(),'%s')]", nameTo), driver), String.format("Field <<nameTo>>  with value '%s' should be present", nameTo));
        Assert.assertTrue(isElementPresent(String.format("//dd[contains(text(),'%s')]", message), driver), String.format("Field <<message>> with value '%s' should be present", message));
        Assert.assertTrue(isElementPresent(String.format("//dd[contains(text(),'%s')]", image), driver), String.format("Field <<image>> with value '%s' should be present", image));
        Assert.assertTrue(Cart().verifyQty(qtyNumber), String.format("Field <<qtyNumber>> with value '%s' should be present", qtyNumber));
        return this;
    }


    @Step("Verify if you on selected Gift Card page {name}")
    public GiftCard verifySelectedGiftCardPage(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(amountInput));
        wait.until(ExpectedConditions.textToBePresentInElement(giftCardName, name));
        return this;
    }

    @Step("Verify email validation")
    public GiftCard verifyEmailValidation() {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage));
        Assert.assertEquals(emailErrorMessage.getText(), "Please enter a valid email address (Ex: johndoe@domain.com).");
        return this;
    }

    @Step("Verify qty validation")
    public GiftCard verifyQtyValidation() {
        wait.until(ExpectedConditions.visibilityOf(qtyErrorMessage));
        Assert.assertEquals(qtyErrorMessage.getText(), "Please enter a quantity greater than 0.");
        return this;
    }

    @Step("Verify if qty is too big")
    public GiftCard verifyQtyIsTooBig() {
        wait.until(ExpectedConditions.visibilityOf(qtyErrorMessage));
        Assert.assertEquals(qtyErrorMessage.getText(), "The maximum you may purchase is 10000.");
        return this;
    }

    @Step("Verify email error message")
    public GiftCard verifyEmailErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage));
        Assert.assertEquals(emailErrorMessage.getText(), "This is a required field.");
        return this;
    }

    @Step("Verify qty error message")
    public GiftCard verifyQtyErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(qtyErrorMessage));
        Assert.assertEquals(qtyErrorMessage.getText(), "Please enter a valid number in this field.");
        return this;
    }


    @Step("Verify user name {userName} is displayed in <<From>> field on preview gift card image")
    public GiftCard verifyUserName(String userName) {
        Assert.assertTrue(fromInput.getAttribute("value").contains(userName), "Sent From field should contain user name: " + userName);
        return this;
    }

    private String setPriceFormat(String price) {
        StringBuilder correctPrice = new StringBuilder(price);
        if (correctPrice.toString().contains(","))
            correctPrice.deleteCharAt(correctPrice.toString().indexOf(","));
        if (!correctPrice.toString().contains(".00"))
            correctPrice.delete(correctPrice.toString().indexOf(".00"), correctPrice.toString().indexOf(".00") + 3);
        return correctPrice.toString();
    }

    @Step("Verify Gift Card price from is present {priceFrom}")
    public GiftCard verifyGiftCardPriceFrom(String priceFrom) {
        String priceFromFormatted = setPriceFormat(priceFrom);
        Assert.assertTrue(isElementPresent(String.format("//button[@class='giftcard-design-button']//span[text()='%s']", priceFromFormatted), driver), String.format("Price From '%s' should be present", priceFromFormatted));
        return this;
    }

    @Step("Verify Gift Card price To is present {priceTo}")
    public GiftCard verifyGiftCardPriceTo(String priceTo) {
        String priceToFormatted = setPriceFormat(priceTo);
        Assert.assertTrue(isElementPresent(String.format("//button[@class='giftcard-design-button']//span[text()='%s']", priceToFormatted), driver), String.format("Price To '%s' should be present", priceToFormatted));
        return this;
    }


    @Step("Verify icons of Add to Wish List, Add to Compare icons are not present")
    public GiftCard verifyIconsNotPresent() {
        Assert.assertFalse(isElementPresent("//div[@class='product-social-links']//span[contains(text(),'Add to Wishlist')]", driver), "Span 'Add to Wishlist' should not be present");
        Assert.assertFalse(isElementPresent("//div[@class='product-social-links']//span[contains(@class,'ic-heart')]", driver), "Heart icon should not be present");
        Assert.assertFalse(isElementPresent("//div[@class='product-social-links']//span[contains(text(),'Add to Compare')]", driver), "Span 'Add to Compare' should not be present");
        Assert.assertFalse(isElementPresent("//span[contains(@class,'ib-hover') and contains(@class,'ic-compare') and contains(@class,'icon-color-productview')]", driver), "Compare icon should not be present");
        return this;
    }

    @Step("Verify icons of Email, letter are present")
    public GiftCard verifyIconsPresent() {
        Assert.assertTrue(isElementPresent("//span[contains(@class,'letter')]", driver), "Letter icon should be present");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Email')]", driver), "Span 'Email' should be present");
        return this;
    }

    @Step("Verify additional info values")
    public GiftCard verifyAdditionalInfoValues() {
        Assert.assertTrue(isElementPresent("//th[contains(text(),'Benefits')]", driver), "'Benefits' section should be present");
        Assert.assertFalse(StringUtils.isEmpty(dataBenefits.getText()), "'Benefits' value should be present");
        Assert.assertTrue(isElementPresent("//th[contains(text(),'FAQs')]", driver), "'FAQs' section should be present");
        Assert.assertFalse(StringUtils.isEmpty(dataFAQ.getText()), "'FAQs' value should not be empty");
        Assert.assertTrue(isElementPresent("//th[contains(text(),'Skin Type')]", driver), "'Skin Type' section should be present");
        Assert.assertFalse(StringUtils.isEmpty(dataSkinType.getText()), "'Skin Type' value should be present");
        return this;
    }

    @Step("Verify ingredients values")
    public GiftCard verifyIngredientsValues() {
        Assert.assertFalse(StringUtils.isEmpty(dataIngredients.getText()), "'Ingredients' value should be present");
        return this;
    }


    @Step("Click on current image {name}")
    public GiftCard clickOnCurrentImage(String imageName) {
        WebElement image = driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]", imageName)));
        click(image);
        return this;
    }

    @Step("Click on date picker button")
    public GiftCard clickOnDatePickerButton() {
        click(datePicker);
        return this;
    }


    @Step("Verify number of message characters {number}")
    public GiftCard verifyNumberOfMessageCharacters(int number) {
        int numberOfCharacters = 120 - number;
        Assert.assertEquals(numberCharactersInMessage.getText(), String.format("%s characters remaining", numberOfCharacters));
        return this;
    }

    @Step("Click current day of the month {number}")
    public GiftCard chooseDayOfMonth(int number) {
        WebElement day = driver.findElement(By.xpath(String.format("//td[@data-handler='selectDay']//a[contains(text(),'%s')]", number)));
        click(day);
        return this;
    }

}
