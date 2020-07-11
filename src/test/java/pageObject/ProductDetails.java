package pageObject;

import creation.features.OptionCodes;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ProductDetails extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']//span[ @itemprop='name']")
    private WebElement productName;

    @FindBy(xpath = "//button[@id='product-addtocart-button']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[contains(@class,'ic-heart')]")
    private WebElement addToWishListButton;

    @FindBy(xpath = "//div[@class='product-social-links']//span[contains(@class,'ic-compare')]")
    private WebElement addToCompareButton;

    @FindBy(xpath = "//a[@class='prev-arrow']")
    private WebElement previousButton;

    @FindBy(xpath = "//a[@class='next-arrow']")
    private WebElement nextButton;

    @FindBy(xpath = "//div[@id='tab-label-description']")
    private WebElement descriptionTab;

    @FindBy(xpath = "//div[@class='product attribute description']")
    private WebElement itemDescription;

    @FindBy(xpath = "//span[@itemprop='name']")
    private WebElement itemName;

    @FindBy(xpath = "//strong[@class='product name product-item-name']")
    private WebElement recomendedProductName;

    @FindBy(xpath = "//div[@class='message-success success message']")
    private WebElement successMessage;

    @FindBy(xpath = "//a[@class='action add']")
    private WebElement addReviewLink;

    @FindBy(xpath = "//a[@class='action add' and contains(text(),'Be the first to review this product')]")
    private WebElement beTheFirstLink;

    @FindBy(xpath = "//a[@class='action add' and contains(text(),'Add Your Review')]")
    private WebElement addYourReviewLink;

    @FindBy(xpath = "//a[@id='tab-label-reviews-title']")
    private WebElement reviewTab;

    @FindBy(xpath = "//a[@id='tab-label-additional-title']")
    private WebElement additionalInfoTab;

    @FindBy(xpath = "//a[@id='tab-label-ingredients.tab-title']")
    private WebElement ingredientsTab;

    @FindBy(xpath = "//a[@class='action mailto friend feature feature-icon-hover']")
    private WebElement emailToFriendLink;

    @FindBy(xpath = "//div[@class='product-info-main']/div[@class='mp-reward-earning']")
    private WebElement pointsEarnedForReview;

    @FindBy(xpath = "//input[@id='qty']")
    private WebElement qtyInput;

    @FindBy(xpath = "//div[@id='qty-error']")
    private WebElement qtyErrorMessage;

    @FindBy(xpath = "//div[@class='mageplaza-frequently-bought-together-block']")
    private WebElement frequentlyBoughtBlock;

    @FindBy(xpath = "//div[@class='mageplaza-fbt-add-to-wishlist']")
    private WebElement frequentlyAddToWishListButton;

    @FindBy(xpath = "//div[@class='mageplaza-fbt-add-to-cart']")
    private WebElement frequentlyAddToCartButton;

    @FindBy(xpath = "//div[@class='mage-error']")
    private WebElement certificationMessage;

    @FindBy(xpath = "//div[@class='patient certification']")
    private WebElement patirntCertificationRadioButton;

    @FindBy(xpath = "//input[@class='related-checkbox']")
    private List<WebElement> frequentlyProductCheckbox;

    @FindBy(xpath = "//span[@class='mageplaza-fbt-price']")
    private WebElement frequentlyTotalPrice;

    @FindBy(xpath = "//div[@class='wrapper grid products-grid']")
    private WebElement frequentlyProductsBlock;

    @FindBy(xpath = "//span[@itemprop='reviewCount']")
    private WebElement reviewCounter;

    @FindBy(xpath = "//div[contains(@class,'mageworx-swatch-option')]")
    private List<WebElement> boxOptions;

    @FindBy(xpath = "//span[@id='value']")
    private WebElement optionName;

    @FindBy(xpath = "//div[@id='product-options-wrapper']//div[contains(@class,'options-list')]//label[contains(@for,'options') and contains(@class,'admin__field-label')]")
    private List<WebElement> radioButtonOptions;

    @FindBy(xpath = "//div[@id='product-options-wrapper']//span[contains(@class,'price-container') and contains(@class,'tax')and contains(@class,'weee')]//span")
    private List<WebElement> radioButtonOptionsPrices;

    @FindBy(xpath = "//div[@class='product-info-price']//span[@class='price']")
    private WebElement productFinalPrice;

    @FindBy(xpath = "//div[contains(text(),'Recurring Price')]")
    private WebElement recurringPriceSpan;

    @FindBy(xpath = "//div[contains(text(),'Regular Price')]")
    private WebElement regularPriceSpan;

    @FindBy(xpath = "//div[contains(text(),'Due Today')]")
    private WebElement dueTodaySpan;


    public ProductDetails(WebDriver driver) {
        super(driver);
    }

    @Step("Verify if you are on edit product page {productName}")
    public ProductDetails verifyProductDetailsPage(String productName) {
        Assert.assertTrue(isElementPresent("//span[contains(text(),'" + productName + "')]", driver), productName + " product name should be present on details page");
        Assert.assertTrue(isElementPresent("//strong[contains(text(),'Description')]", driver), "'Description' option should be present on details page");
        Assert.assertTrue(isElementPresent("//strong[contains(text(),'Additional Info')]", driver), "'Additional Info' option should be present on details page");
        Assert.assertTrue(isElementPresent("//strong[contains(text(),'Reviews')]", driver), "'Reviews' option should be present on details page");
        Assert.assertTrue(isElementPresent("//strong[contains(text(),'Usage')]", driver), "'Usage' option should be present on details page");
        Assert.assertTrue(isElementPresent("//strong[contains(text(),'Ingredients')]", driver), "'Ingredients' option should be present on details page");
        Assert.assertTrue(isElementPresent("//button[@id='product-addtocart-button']", driver), "Add to Cart button should be present on details page");
        return this;
    }

    @Step("Wait until Product Details page loaded")
    public ProductDetails waitUntilProductDetailsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(productName));
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        return this;
    }


    @Step("Click on Description tab if not active")
    public ProductDetails clickDescriptionTabIfNotActive() {
        if (isElementPresent("//div[@class='data item title active' and @id='tab-label-description']", driver)) {
            return this;
        } else
            descriptionTab.click();
        return this;
    }

    @Step("Click on Add To Cart button")
    public ProductDetails clickAddToCartButton() {
        click(addToCartButton);
        return this;
    }

    @Step("Click on Add To Wish list button")
    public ProductDetails clickAddToWishListButton() {
        click(addToWishListButton);
        return this;
    }

    @Step("Click on Add To Compare button")
    public ProductDetails clickAddToCompareButton() {
        click(addToCompareButton);
        return this;
    }

    @Step("Verify Add To Compare message {productName}")
    public ProductDetails verifyAddToCompareMessage(String productName) {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        scrollIntoView(successMessage, driver);
        Assert.assertEquals(successMessage.getText(), "You added product " + productName + " to the comparison list.");
        return this;
    }

    @Step("Click on Previous product button")
    public ProductDetails clickPreviousButton() {
        click(previousButton);
        return this;
    }

    @Step("Click on Next product button")
    public ProductDetails clickNextButton() {
        click(nextButton);
        return this;
    }

    @Step("Verify item name {productName}")
    public ProductDetails verifyItemName(String productName) {
        scrollIntoView(itemName, driver);
        wait.until(ExpectedConditions.textToBePresentInElement(itemName, productName));
        return this;
    }

    @Step("Verify if next/previous products appear")
    public ProductDetails verifyNextPreviousProductPage(String productName) {
        scrollIntoView(itemName, driver);
        wait.until(ExpectedConditions.visibilityOf(itemName));
        Assert.assertNotEquals(itemName.getText(), productName);
        return this;
    }

    @Step("Verify description")
    public ProductDetails verifyDescription() {
        wait.until(ExpectedConditions.visibilityOf(itemDescription));
        scrollIntoView(descriptionTab, driver);
        Assert.assertTrue(itemDescription.isDisplayed(), "Description should be displayed at the page");
        return this;
    }

    @Step("Verify recommended product section")
    public ProductDetails verifyRecommendedProduct() {
        waitUntilPageLoaded();
        Assert.assertTrue(isElementPresent("//strong[@id='block-upsell-heading']", driver), "Recommended product section should be displayed");
        Assert.assertTrue(isElementPresent("//strong[@class='product name product-item-name']", driver), "Recommended product section should be displayed");
        return this;
    }

    @Step("Verify if only registered users can write reviews.")
    public ProductDetails verifyOnlyRegisteredUsersCanWriteReviews() {
        waitUntilPageLoaded();
        Assert.assertTrue(isElementPresent("//div[contains(text(),'Only registered users can write reviews.')]", driver));
        return this;
    }

    @Step("Click Add Review link")
    public ProductDetails clickAddReview() {
        click(addReviewLink);
        return this;
    }

    @Step("Click on <<Review>> tab")
    public ProductDetails clickOnReviewTab() {
        click(reviewTab);
        return this;
    }

    @Step("Click on <<Additional Info>> tab")
    public ProductDetails clickOnAdditionalInfoTab() {
        click(additionalInfoTab);
        return this;
    }

    @Step("Click on <<Ingredients>> tab")
    public ProductDetails clickOnIngredientsTab() {
        click(ingredientsTab);
        return this;
    }

    @Step("Click on <<Email to friend>> link")
    public ProductDetails clickEmailToFriendLink() {
        click(emailToFriendLink);
        return this;
    }

    @Step("Verify earned points {points} for writing a review")
    public ProductDetails verifyEarnedPointsForReview(String points) {
        wait.until(ExpectedConditions.visibilityOf(pointsEarnedForReview));
        Assert.assertEquals(pointsEarnedForReview.getText(), "Earn " + points + " points for writing a review for this product");
        return this;
    }

    @Step("Check if <<Be the first to review this product>> link is present")
    public boolean isBeTheFirstPresent() {
        return (isElementPresent("//a[@class='action add' and contains(text(),'Be the first to review this product')]", driver));
    }

    @Step("Verify if Review tab is active")
    public ProductDetails verifyReviewTab() {
        WebElement reviewActiveTab = driver.findElement(By.xpath("//div[@class='data item title active' and @id='tab-label-reviews']"));
        wait.until(ExpectedConditions.visibilityOf(reviewActiveTab));
        return this;
    }

    @Step("Verify guest review tab is active")
    public ProductDetails verifyReviewGuestTab() {
        WebElement reviewActiveTab = driver.findElement(By.xpath("//div[@class='data item title active' and @id='tab-label-reviews']"));
        wait.until(ExpectedConditions.visibilityOf(reviewActiveTab));
        Assert.assertTrue(isElementPresent("//div[contains(text(),'Only registered users can write reviews')]", driver));
        return this;
    }

    @Step("Verify Add Your Review link")
    public ProductDetails verifyAddYouReview() {
        Assert.assertTrue(isElementPresent("//a[@class='action add' and contains(text(),'Add Your Review')]", driver), "Add You Review link should be displayed under product name");
        return this;
    }

    @Step("Click on <Be the first to review this product> link")
    public ProductDetails clickBeTheFirstLink() {
        click(beTheFirstLink);
        return this;
    }

    @Step("Clear Qty field")
    public ProductDetails clearQty() {
        clear(qtyInput);
        return this;
    }

    @Step("Enter product Qty")
    public ProductDetails enterQty(String qty) {
        sendText(qtyInput, qty);
        return this;
    }

    @Step("Verify invalid Qty error message {message}")
    public ProductDetails verifyQtyErrorMessage(String message) {
        wait.until(ExpectedConditions.visibilityOf(qtyErrorMessage));
        Assert.assertEquals(qtyErrorMessage.getText(), message);
        return this;
    }

    @Step("Verify required fields")
    public ProductDetails verifyRequiredFields() {
        Assert.assertTrue(isElementPresent("//fieldset[@class='field required review-field-ratings']", driver), "Your Rating field should be required field");
        Assert.assertTrue(isElementPresent("//div[@class='field review-field-nickname required']", driver), "Nickname field should be required field");
        Assert.assertTrue(isElementPresent("//div[@class='field review-field-summary required']", driver), "Summary field should be required field");
        Assert.assertTrue(isElementPresent("//div[@class='field review-field-text required']", driver), "Review field should be required field");
        return this;
    }

    @Step("Verify if Review options are unavailable when it Disabled in config")
    public ProductDetails verifyDisabledReviewOptions() {
        Assert.assertFalse(reviewTab.isDisplayed(), "Review tab should not be displayed");
        Assert.assertFalse(addReviewLink.isDisplayed(), "Review link should not be displayed");
        return this;
    }

    @Step("Verify if Review options are available when it Enabled in config")
    public ProductDetails verifyEnabledReviewOptions() {
        Assert.assertTrue(reviewTab.isDisplayed(), "Review tab should be displayed");
        Assert.assertTrue(addReviewLink.isDisplayed(), "Review link should be displayed");
        return this;
    }

    @Step("Verify if Add to Compare option is available when it Enable in config")
    public ProductDetails verifyEnabledAddToCompareOption() {
        Assert.assertTrue(addToCompareButton.isDisplayed(), "Add to Compare button should be displayed");
        return this;
    }

    @Step("Verify if Add to Compare option is unavailable when it Disable in config")
    public ProductDetails verifyDisabledAddToCompareOption() {
        Assert.assertFalse(addToCompareButton.isDisplayed(), "Add to Compare button should not be displayed");
        return this;
    }

    @Step("Verify if Add to WishList option is available when it Enable in config")
    public ProductDetails verifyEnabledAddToWishListOption() {
        Assert.assertTrue(addToWishListButton.isDisplayed(), "Add to WishList button should be displayed");
        return this;
    }

    @Step("Verify if Add to WishList option is unavailable when it Disable in config")
    public ProductDetails verifyDisabledAddToWishListOption() {
        Assert.assertFalse(addToWishListButton.isDisplayed(), "Add to WishList button should not be displayed");
        return this;
    }

    @Step("Check if Patient Certification radio button is present")
    public boolean isPatientCertificationPresent() {
        return (isElementPresent("//input[@class='related-checkbox']", driver));
    }

    @Step("Click on <<Patient Certification>> radio button")
    public ProductDetails clickPatientCertificationButton() {
        click(patirntCertificationRadioButton);
        return this;
    }

    @Step("Verify if Patient Certification is required field")
    public ProductDetails patientCertificationButtonValidation() {
        Assert.assertEquals(certificationMessage.getText(), "This is a required field.");
        return this;
    }

    @Step("Check if Frequently Bought together block is present")
    public boolean isFrequentlyBoughtPresent() {
        return (isElementPresent("//div[@class='mageplaza-fbt-buttons']", driver));
    }

    @Step("Verify Frequently bought product {productName}")
    public ProductDetails verifyFrequentlyBoughtProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(frequentlyBoughtBlock));
        scrollIntoView(frequentlyBoughtBlock, driver);
        Assert.assertTrue(frequentlyBoughtBlock.getText().contains(productName), productName + " product should be displayed in Frequently Bought block");
        return this;
    }

    @Step("Click on <<Add to Cart>> button from frequently bought together block")
    public ProductDetails clickFrequentlyAddToCartButton() {
        wait.until(ExpectedConditions.visibilityOf(frequentlyBoughtBlock));
        frequentlyAddToCartButton.click();
        return this;
    }

    @Step("Click on <<Add to Wishlist>> button from frequently bought together block")
    public ProductDetails clickFrequentlyAddToWishListButton() {
        wait.until(ExpectedConditions.visibilityOf(frequentlyBoughtBlock));
        frequentlyAddToWishListButton.click();
        return this;
    }

    @Step("Click on This item checkbox in frequently bought together block")
    public ProductDetails clickThisItemCheckbox() {
        click(frequentlyProductCheckbox.get(0));
        return this;
    }

    @Step("Uncheck all frequently bought together checkboxes")
    public ProductDetails uncheckAllFrequentlyBoughtCheckboxes() {
        frequentlyProductCheckbox.forEach(productCheckbox -> {
            if (productCheckbox.isSelected()) {
                click(productCheckbox);
            }
        });
        return this;
    }

    @Step("Get Frequently bought products total price")
    public Integer getFrequentlyTotalPrice() {
        String totalPrice = frequentlyTotalPrice.getText();
        return Integer.parseInt(totalPrice.substring(1, totalPrice.indexOf(".")));
    }

    @Step("Verify Total Price {totalPrice} excluding current product price {currentProductPrice}")
    public ProductDetails verifyTotalPriceExcludingCurrentProduct(int totalPrice, int currentProductPrice) {
        int currentTotalPrice = Integer.parseInt(frequentlyTotalPrice.getText().substring(1, frequentlyTotalPrice.getText().indexOf(".")));
        Assert.assertEquals(currentTotalPrice, totalPrice - currentProductPrice);
        return this;
    }

    @Step("Verify product price")
    public ProductDetails verifyProductPrice(int index) {
        Assert.assertEquals(productFinalPrice.getText(), radioButtonOptionsPrices.get(index).getText());
        return this;
    }


    @Step("Verify if Frequently Products block is not displayed")
    public ProductDetails verifyIfFrequentlyProductsIsNotVisible() {
        Assert.assertTrue(frequentlyProductsBlock.isDisplayed(), "Frequently bought products should not be displayed");
        return this;
    }

    @Step("Get Review count")
    public Integer getReviewCount() {
        return (isElementPresent("//span[@itemprop='reviewCount']", driver)) ? Integer.parseInt(reviewCounter.getText()) : 0;
    }

    @Step("Verify Review counter {reviewCount}")
    public ProductDetails verifyReviewCounter(int reviewCount) {
        Assert.assertEquals(Integer.parseInt(reviewCounter.getText()), reviewCount);
        return this;
    }

    @Step("Verify if radio buttons are present")
    public boolean isRadioButtonsOptionsPresent() {
        return isElementPresent("//div[contains(@class,'admin__field-option')]//input[@value='radio']", driver);
    }

    @Step("Get name from current options box {index}")
    public String getOptionName(int index) {
        return boxOptions.get(index).getText();
    }

    @Step("verify box option")
    public ProductDetails verifyBoxOptions() {
        for (int i = 0; i < boxOptions.size(); i++) {
            this.clickOnBoxOption(i);
            String name = this.getOptionName(i);
            this.verifyOptionName(name);
        }
        return this;
    }

    @Step("verify radio button options")
    public ProductDetails verifyRadioButtonOptions() {
        for (int i = 0; i < radioButtonOptions.size(); i++)
            this.clickOnRadioButtonOption(i)
                    .verifyProductPrice(i);
        return this;
    }


    @Step("Click on box option {index}")
    public ProductDetails clickOnBoxOption(int index) {
        click(boxOptions.get(index));
        return this;
    }

    @Step("Click on curent radio button {index}")
    public ProductDetails clickOnRadioButtonOption(int index) {
        click(radioButtonOptions.get(index));
        return this;
    }


    @Step("Verify option name {name}")
    public ProductDetails verifyOptionName(String name) {
        Assert.assertEquals(optionName.getText(), name);
        return this;
    }

    @Step("Verify Auto Replenish message {discount}")
    public ProductDetails verifyAutoReplenishMessage(int discount) {
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Auto-Replenish and Save " + discount + "%')]", driver), String.format("Span <<Auto-Replenish and Save %s should be present", discount));
        return this;
    }

    /*@Step("Click on subscription option select")
    public ProductDetails clickOnSubscriptionOptionSelect() {


        click(subscriptionOptionSelect);
        return this;
    }*/

    @Step("Choose subscription option {index}")
    public ProductDetails chooseSubscriptionOption(int index) {
        Select options = new Select(driver.findElement(By.xpath("//select[@id='subscription_option']")));
        options.selectByIndex(index);
        /*WebElement option = driver.findElement(By.xpath(String.format("//select[@id='subscription_option']//option[contains(text(),'%s')]", text)));
        click(option);*/
        return this;
    }

    @Step("Verify auto replenish price is displayed")
    public ProductDetails verifyAutoReplenishPriceIsDisplayed() {
        Assert.assertTrue(recurringPriceSpan.isDisplayed(), "Recurring Price Span should be displayed");
        Assert.assertTrue(regularPriceSpan.isDisplayed(), "Regular PriceSpan should be displayed");
        Assert.assertTrue(dueTodaySpan.isDisplayed(), "Due Today Span. should be displayed");
        return this;
    }

    @Step("Verify auto replenish price is not displayed")
    public ProductDetails verifyAutoReplenishPriceIsNotDisplayed() {
        Assert.assertFalse(recurringPriceSpan.isDisplayed(), "Recurring Price Span should be displayed");
        Assert.assertFalse(regularPriceSpan.isDisplayed(), "Regular Price Span should be displayed");
        Assert.assertFalse(dueTodaySpan.isDisplayed(), "Due Today Span should be displayed");
        return this;
    }

    @Step("Verify auto replenish product list size {validAutoReplenishProductsListSize} {autoReplenishProductsListFromStoreSize}")
    public ProductDetails verifyAutoReplenishProductListSize(int validAutoReplenishProductsListSize, int autoReplenishProductsListFromStoreSize) {
        Assert.assertEquals(validAutoReplenishProductsListSize, autoReplenishProductsListFromStoreSize);
        return this;
    }

}
