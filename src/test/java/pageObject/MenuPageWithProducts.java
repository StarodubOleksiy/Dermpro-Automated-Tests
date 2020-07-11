package pageObject;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.Step;
import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//TODO rename
public class MenuPageWithProducts extends ProductListContainer {

    @FindBy(xpath = "//div[contains(@class,'product-item-info')][.//a[@title='Add to Wishlist']]")
    private List<WebElement> productsWithAddToWishlist;

    @FindBy(xpath = "//div[contains(@class,'product-item-info')][.//a[@title='Add to Wishlist']]//a[@class='product-item-link']")
    private List<WebElement> productNamesWithAddToWishlist;

    @FindBy(xpath = "//div[contains(@class,'product-item-info')][.//a[@title='Add to Compare']]")
    private List<WebElement> productsWithAddToCompare;

    @FindBy(xpath = "//div[contains(@class,'product-item-info')][.//span[contains(text(),'Starting at')]]//a[@class='product-item-link']")
    private List<WebElement> productNamesWithStartingAtSpan;

    @FindBy(xpath = "//div[contains(@class,'product-item-info')][.//a[@title='Add to Compare']]//a[@class='product-item-link']")
    private List<WebElement> productNamesWithAddToCompare;

    @FindBy(xpath = "//div[contains(@class,'product-item-info imgdim-x')][.//button[@title='Add to Cart']]")
    private List<WebElement> productsWithAddToCart;

    @FindBy(xpath = "//div[contains(@class,'product-item-info imgdim-x')][.//button[@title='Add to Cart']]//a[@class='product-item-link']")
    private List<WebElement> productNamesWithAddToCart;

    @FindBy(xpath = "//div[contains(@class,'product-item-info')][.//div[@class='reviews-actions']//a[contains(@class,'view')]]//a[@class='product-item-link']")
    private List<WebElement> productNamesWithReview;

    @FindBy(xpath = "//span[contains(@class,'icon') and contains(@class,'ib-hover') and contains(@class,'ic-heart')]")
    private List<WebElement> addToWishlistSpans;

    @FindBy(xpath = "//span[contains(@class,'icon') and contains(@class,'ib-hover') and contains(@class,'ic-compare')]")
    private List<WebElement> addToCompareSpans;

    @FindBy(xpath = "//div[contains(@class,'product-item-info imgdim-x')]//button[@title='Add to Cart']")
    private List<WebElement> addToCardButtons;

    @FindBy(xpath = "//a[@id='compare-clear-all']")
    private WebElement clearAllLink;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement menuPageMessage;

    @FindBy(xpath = "//button[contains(@class,'action-accept')]")
    private WebElement okButton;

    @FindBy(xpath = "(//select[@id='sorter'])[1]")
    private WebElement sortByList;

    @FindBy(xpath = "(//option[@value='position'])[1]")
    private WebElement positionOption;

    @FindBy(xpath = "(//option[@value='name'])[1]")
    private WebElement nameOption;

    @FindBy(xpath = "(//option[@value='price'])[1]")
    private WebElement priceOption;

    @FindBy(xpath = "//div[@class='reviews-actions']//a[contains(@class,'view')]")
    private List<WebElement> reviewLinks;

    @FindBy(xpath = "//div[@class='message-success success message']")
    private WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class,'product-item-details')]//a[@class='product-item-link']")
    private List<WebElement> productNames;

    @FindBy(xpath = "//a[contains(@class,'action') and contains(@class,'action')]")
    private List<WebElement> addYourReviewLinks;

    @FindBy(xpath = "//a[contains(@class,'action') and contains(@class,'more')]")
    private List<WebElement> learnMoreLinks;

    @FindBy(xpath = "(//select[@id='limiter'])[2]")
    private WebElement pageLimiter;

    @FindBy(xpath = "(//p[@id='toolbar-amount']//span[@class='toolbar-number'])[1]")
    private WebElement firstValueOfToolbarAmount;

    @FindBy(xpath = "(//p[@id='toolbar-amount']//span[@class='toolbar-number'])[3]")
    private WebElement totalNumberOfItemsInToolbarAmount;

    //limiter

    public MenuPageWithProducts(WebDriver driver) {
        super(driver);
    }

    @Step("Verify if products with <<Starting At>> span are present")
    public boolean isProductsStartingAtSpanPresent() {
        return !CollectionUtils.isEmpty(productNamesWithStartingAtSpan);
    }

    @Step("Click to <<Add to wish list>> span {name}")
    public MenuPageWithProducts clickAddToWishList(String name) {
        return clickAddTo(name, productNamesWithAddToWishlist, productsWithAddToWishlist, addToWishlistSpans);
    }

    @Step("Click to <<Add to compare>> span {name}")
    public MenuPageWithProducts clickAddToCompare(String name) {
        return clickAddTo(name, productNamesWithAddToCompare, productsWithAddToCompare, addToCompareSpans);
    }

    @Step("Click to <<Add to Cart>> span {name}")
    public MenuPageWithProducts clickAddToCart(String name) {
        return clickAddTo(name, productNamesWithAddToCart, productsWithAddToCart, addToCardButtons);
    }

    public boolean ifProductNamesWithStartingAtSpanIsNotEmpty() {
        CollectionUtils.isEmpty(productNamesWithStartingAtSpan);
        return productNamesWithStartingAtSpan.size() > 0;
    }

    @Step("Click on product which have <<Starting at>> span ")
    public MenuPageWithProducts clickTreatmentWithStartingAtSpan() {
        productNamesWithStartingAtSpan.get(0).click();
        return this;
    }

    private MenuPageWithProducts clickAddTo(String name, List<WebElement> productNamesList, List<WebElement> productList, List<WebElement> spansToClick) {
        for (int index = 0; index < productNamesList.size(); index++) {
            Actions action = new Actions(driver);
            scrollIntoView(productNamesList.get(index), driver);
            action.moveToElement(productNamesList.get(index)).perform();
            if (productNamesList.get(index).getText().equals(name.toUpperCase())) {
                action.moveToElement(productList.get(index)).perform();
                scrollIntoView(spansToClick.get(index), driver);
                spansToClick.get(index).click();
                break;
            }
        }
        return this;
    }

    @Step("Click to <<Reviews>> link {name}")
    public MenuPageWithProducts clickReviewLink(String name) {
        for (int i = 0; i < productNamesWithReview.size(); i++) {
            Actions action = new Actions(driver);
            scrollIntoView(productNamesWithReview.get(i), driver);
            action.moveToElement(productNamesWithReview.get(i)).perform();
            if (productNamesWithReview.get(i).getText().equals(name.toUpperCase())) {
                reviewLinks.get(i).click();
                break;
            }
        }
        return this;
    }

    @Step("Verify if product was added to compare {name}")
    public MenuPageWithProducts verifyProductAddToCompare(String name) {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        Assert.assertEquals(menuPageMessage.getText(), "You added product " + name + " to the comparison list.");
        return this;
    }

    @Step("Click to <<Clear All>> link")
    public MenuPageWithProducts clickClearAllLink() {
        click(clearAllLink);
        return this;
    }

    @Step("Click on <<OK>> button")
    public MenuPageWithProducts clickOkButton() {
        click(okButton);
        return this;
    }

    @Step("Verify product name is present on Menu Page {productName}")
    public MenuPageWithProducts verifyProductNamePresent(String productName) {
        Assert.assertTrue(isElementPresent(String.format("//a[contains(text(),'%s')]", productName), driver), productName + " should be displayed on the page");
        return this;
    }


    @Step("Verify <<Review>> span  is present on Menu Page")
    public MenuPageWithProducts verifyReviewSpanPresent() {
        Assert.assertTrue(isElementPresent("//div[@class='reviews-actions']//span[contains(text(),'Reviews')]", driver), "Review text should be displayed on reviewed products");
        return this;
    }

    @Step(" Verify <<Add Your Review>> link for list view")
    public MenuPageWithProducts verifyAddYourReviewLinkOnListView(String productName) {
        Assert.assertTrue(isAddYourReviewSpanPresent(productName));
        return this;
    }

    @Step("Verify Reviews count")
    public MenuPageWithProducts verifyReviewsCount() {
        Assert.assertTrue(isElementPresent("//span[@itemprop='reviewCount']", driver), "Review counter should be displayed on reviewed products");
        return this;
    }

    @Step("Verify <<add to wish list>> button is present on Menu Page")
    public MenuPageWithProducts verifyAddToWishListButtonIsPresent() {
        Assert.assertTrue(isElementPresent("//span[contains(@class,'ic-heart')]", driver), "Add to Wish list button should be displayed");
        return this;
    }


    @Step("Verify <<Add to Compare>> button is present on Menu Page")
    public MenuPageWithProducts verifyAddToCompareButtonIsPresent() {
        Assert.assertTrue(isElementPresent("//span[contains(@class,'ic-compare')]", driver), "Add to Compare button should be displayed");
        return this;
    }

    @Step("Click on <<Sort>> list")
    public MenuPageWithProducts chooseSorterOption(int index) {
        Select sorterOption = new Select(driver.findElement(By.xpath("(//select[@id='sorter'])[1]")));
        sorterOption.selectByIndex(index);
        return this;
    }

    @Step("Click on <<Position>> option")
    public MenuPageWithProducts clickPositionOption() {
        click(positionOption);
        return this;
    }

    @Step("Click on <<Name>> option")
    public MenuPageWithProducts clickNameOption() {
        click(nameOption);
        return this;
    }

    @Step("Click on <<Price>> option")
    public MenuPageWithProducts clickPriceOption() {
        click(priceOption);
        return this;
    }

    @Step("Get price by product name {name}")
    public String getPriceByProductName(String name) {
        WebElement price = driver.findElement(By.xpath(String.format("//a[contains(@title,'%s')]/following::span[@class='price'][1]", name)));
        return price.getText();
    }

    public boolean isReviewSpanPresent(String productName) {
        return isElementPresent(String.format("(//a[contains(@title,'%s')]/following::a//span[contains(text(),'Reviews')])[1]", productName), driver);
    }

    @Step("Verify if <<Add your review span>> is present on current product {productName}")
    public boolean isAddYourReviewSpanPresent(String productName) {
        return isElementPresent(String.format("(//a[contains(@title,'%s')]/following::a[contains(text(),'Add Your Review')])[1]", productName), driver);
    }


    @Step("Click on <<Learn More>> link {productName}")
    public MenuPageWithProducts clickLearnMoreLink(String productName) {
        WebElement link = driver.findElement(By.xpath(String.format("//a[@title='%s' and contains(@class,'action') and contains(@class,'more')]", productName)));
        click(link);
        return this;
    }

    @Step("Verify rating result is present on Menu Page")
    public MenuPageWithProducts verifyRatingResultPresent() {
        Assert.assertTrue(isElementPresent("//div[@class='rating-result']", driver), "Rating result should be present on the Menu page");
        return this;
    }

    @Step("Verify if products are displayed at the Menu Page")
    public MenuPageWithProducts verifyProductsPresence() {
        Assert.assertTrue(isElementPresent("//div[contains(@class,'product-item-details')]//a[@class='product-item-link']", driver), "Products should be displayed on the page");
        return this;
    }

    public List<String> getProductNameList() {
        return productNames.stream().map(i -> i.getText()).collect(Collectors.toList());
    }

    @Step("Verify if menu page is loaded")
    public MenuPageWithProducts verifyMenuPageIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(productNames.get(0)));
        scrollIntoView(productNames.get(0), driver);
        return this;
    }

    @Step("Click on category")
    public MenuPageWithProducts clickCategory(String category) {
        WebElement subMenu = driver.findElement(By.xpath(String.format("//li[contains(@class,'category')]//a[contains(text(),'%s')]", category)));
        click(subMenu);
        return this;
    }

    @Step("Verify Private Label products page")
    public MenuPageWithProducts verifyPrivateLabelProductsPage(String shopName) {
        productNames.forEach(n -> {
            Assert.assertTrue(n.getText().toLowerCase().contains(shopName.toLowerCase()), n.getText() + " title should contain " + shopName);
        });
        return this;
    }


    @Step("Click on page limiter")
    public MenuPageWithProducts clickPageLimiter() {
        click(pageLimiter);
        return this;
    }


    @Step("Verify number of items on this product page")
    public MenuPageWithProducts verifyNumberOfAllItems() {
        wait.until(ExpectedConditions.visibilityOf(firstValueOfToolbarAmount));
        Assert.assertTrue(firstValueOfToolbarAmount.getText().equals(String.valueOf(productNames.size())), "Toolbar amount and product list size must be equals.");
        return this;
    }

    @Step("Get first value of toolbar amount")
    public String getFirstValueOfToolbarAmount() {
        return firstValueOfToolbarAmount.getText();
    }

    @Step("Verify first value {value} of toolbar amount")
    public MenuPageWithProducts verifyFirstValueOfToolbarAmount(String value) {
        Assert.assertEquals(firstValueOfToolbarAmount.getText(), value);
        return this;
    }

    @Step("Get total value of toolbar amount")
    public int getValueOfToolbarAmount() {
        return Integer.parseInt(totalNumberOfItemsInToolbarAmount.getText());
    }


}
