package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CompareProducts extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement compareProductsPageTitle;

    @FindBy(xpath = "//li[contains(@class,'product-item')]//div[contains(@class,'product-item-details')]//a[@class='product-item-link']")
    private List<WebElement> productNames;

    @FindBy(xpath = "//a[@class='action delete']")
    private List<WebElement> removeProductList;

    @FindBy(xpath = "//ol[@id='compare-items']//a[@class='action delete']")
    private List<WebElement> removeProductListLeftSideBar;

    @FindBy(xpath = "//a[@id='compare-clear-all']")
    private WebElement clearAll;

    @FindBy(xpath = "//span[@data-ui-id='page-title-wrapper']")
    private WebElement compareProductsHeader;

    @FindBy(xpath = "//a[@title='Remove Product']")
    private List<WebElement> removeProductTooltip;

    @FindBy(xpath = "//footer[@class='modal-footer']//span[text()='OK']")
    private WebElement okButton;

    @FindBy(xpath = "//footer[@class='modal-footer']//span[text()='Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement comparePageMessage;

    @FindBy(xpath = "//div[@class='message info empty']")
    private WebElement compareEmptyMessage;

    @FindBy(xpath = "//div[@class='item link compare']//span[@class='counter qty']")
    private WebElement compareQtyButton;

    @FindBy(xpath = "//a[@class='action compare no-display']")
    private WebElement compareIconState;

    @FindBy(xpath = "//a[@title='Print This Page']")
    private WebElement printThisPageButton;

    @FindBy(xpath = "//tbody//span[contains(text(),'SKU')]")
    private WebElement skuAttribute;

    @FindBy(xpath = "//tbody//span[contains(text(),'Description')]")
    private WebElement descriptionAttribute;

    @FindBy(xpath = "//tbody//span[contains(text(),'Short Description')]")
    private WebElement shortDescriptionAttribute;

    @FindBy(xpath = "//tbody//span[contains(text(),'Color')]")
    private WebElement colorAttribute;

    @FindBy(xpath = "//tbody//span[contains(text(),'Recommended Uses')]")
    private WebElement recommendedUsesAttribute;

    @FindBy(xpath = "//tbody//span[contains(text(),'FAQs')]")
    private WebElement faqsAttribute;

    @FindBy(xpath = "//tbody//span[contains(text(),'Ingredients')]")
    private WebElement ingredientsAttribute;

    @FindBy(xpath = "//tbody//span[contains(text(),'Downloads')]")
    private WebElement downloadsAttribute;

    @FindBy(xpath = "//tbody//span[contains(text(),'Skin Type')]")
    private WebElement skinTypeAttribute;

    @FindBy(xpath = "//tbody//span[contains(text(),'Size')]")
    private WebElement sizeAttribute;

    @FindBy(xpath = "//div[@class='product-reviews-summary short']")
    private WebElement productReviewSummary;

    @FindBy(xpath = "//div[@class='rating-summary']")
    private WebElement ratingSummary;

    @FindBy(xpath = "//div[@class='reviews-actions']")
    private WebElement reviewQty;

    public CompareProducts(WebDriver driver) {
        super(driver);
    }

    @Step("Wait until Compare Products page loaded")
    public CompareProducts waitUntilCompareProductsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(compareProductsPageTitle));
        verifyTitle(compareProductsPageTitle, "Compare Products");
        return this;
    }

    @Step("Get first product title")
    public String getFirstProductTitle() {
        List<WebElement> productTitle = driver.findElements(By.xpath("//strong[@class='product-item-name']/a"));
        return productTitle.get(0).getAttribute("title");
    }

    @Step("Retrieve product names")
    public List<String> getProductNamesList() {
        if (productNames.isEmpty()) {
            System.out.println("Can't find products");
            return null;
        }
        return retrieveProductNames(productNames, 7);
    }


    @Step("Verify product {name} was added to compare list successfully")
    public CompareProducts verifyAddProductToCompareSuccessMessage(String name) {
        wait.until(ExpectedConditions.visibilityOf(comparePageMessage));
        scrollIntoView(comparePageMessage, driver);
        Assert.assertEquals(comparePageMessage.getText(), "You added product " + name + " to the comparison list.");
        return this;
    }

    @Step("Verify that product {name} added to compare list only once")
    public CompareProducts verifyProductAddedOnceToCompareList(String name) {
        List<WebElement> productName = driver.findElements(By.xpath(String.format("//strong[@class='product-item-name' and contains(.,'%s')]", name)));
        Assert.assertEquals(productName.size(), 1);
        return this;
    }

    @Step("Verify if product was deleted from compare list {name}")
    public CompareProducts verifyDeleteProductFromCompare(String name) {
        wait.until(ExpectedConditions.visibilityOf(comparePageMessage));
        scrollIntoView(comparePageMessage, driver);
        Assert.assertEquals(comparePageMessage.getText(), "You removed product " + name + " from the comparison list.");
        return this;
    }

    @Step("Verify if compare products list is empty")
    public CompareProducts verifyEmptyCompareMessage() {
        wait.until(ExpectedConditions.visibilityOf(compareEmptyMessage));
        scrollIntoView(comparePageMessage, driver);
        Assert.assertEquals(compareEmptyMessage.getText(), "You have no items to compare.");
        return this;
    }

    @Step("Delete product from compare list")
    public CompareProducts deleteProductFromCompareList() {
        click(removeProductList.get(0));
        click(okButton);
        return this;
    }

    @Step("Delete all products from compare list")
    public CompareProducts deleteAllProductsFromCompareList() {
        if (!removeProductList.isEmpty()) {
            removeProductList.forEach(n -> {
                deleteProductFromCompareList();
            });
        }
        return this;
    }

    @Step("Delete all products from compare list left side bar")
    public CompareProducts deleteAllProductsFromCompareLeftSideBar() {
        if (!removeProductListLeftSideBar.isEmpty()) {
            click(clearAll);
            click(okButton);
        }

        return this;
    }

    @Step("Click on <Compare products>>")
    public CompareProducts clickCompareProducts() {
        click(compareQtyButton);
        return this;
    }

    @Step("Verify if product is present on <<Compare Products>> page {productName} {price}")
    public CompareProducts verifyProductPresence(String productName, String price) {
        Assert.assertTrue(isElementPresent(String.format("//a[contains(text(),'%s')]", productName), driver), String.format("Product with name %s should be present", productName));
        Assert.assertTrue(isElementPresent(String.format("//span[contains(text(),'%s')]", price), driver), String.format("Price %s for product %s should be present", price, productName));
        return this;
    }

    @Step("Verify quantity of products added to compare {qty}")
    public CompareProducts verifyCompareProductQty(String qty) {
        wait.until(ExpectedConditions.elementToBeClickable(compareQtyButton));
        scrollIntoView(compareQtyButton, driver);
        Assert.assertEquals(compareQtyButton.getText(), qty);
        return this;
    }

    @Step("Click on <<Compare>> button on product {productTitle}")
    public CompareProducts clickCompareButton(String productTitle) {
        WebElement compareButton = driver.findElement(By.xpath(String.format("//li[@class='item product product-item' and contains(.,'%s')]//span[contains(@class,'ic-compare')]", productTitle)));
        scrollIntoView(compareButton, driver);
        waitUntilPageLoaded();
        Actions action = new Actions(driver);
        action.moveToElement(compareButton).perform();
        click(compareButton);
        return this;
    }

    @Step("Click on <<Add to WishList>> button from Compare products on {productTitle}")
    public CompareProducts clickAddToWishListFromCompare(String productTitle) {
        WebElement wishWebElement = driver.findElement(By.xpath(String.format("//*[@class='cell product info' and contains(.,'%s')]//a[@class='action towishlist']", productTitle)));
        wishWebElement.click();
        return this;
    }

    @Step("Click on <<Add to Cart> button from Compare products on {productTitle}")
    public CompareProducts clickAddToCartFromCompare(String productTitle) {
        WebElement wishWebElement = driver.findElement(By.xpath(String.format("//*[@class='cell product info' and contains(.,'%s')]//button[@class='action tocart primary']", productTitle)));
        wishWebElement.click();
        return this;
    }

    @Step("Verify if you on <<Compare Products>> page")
    public CompareProducts verifyIfYouOnCompareProductsPage() {
        wait.until(ExpectedConditions.visibilityOf(compareProductsHeader));
        scrollIntoView(compareProductsHeader, driver);
        Assert.assertEquals(compareProductsHeader.getText(), "Compare Products");
        return this;
    }

    @Step("Delete items from Compare page")
    public CompareProducts deleteItemsFromCompare() {
        if (compareQtyButton.getText().equals("0 items")) {
            return this;
        } else
            click(compareQtyButton);
        removeProductList.forEach(n -> {
            click(n);
        });
        return this;
    }

    @Step("Check elements in compare present")
    public boolean isElementsInComparePresent() {
        return isElementPresent("//a[@class='action compare']//span[@class='counter qty']", driver);
    }


    @Step("Verify visibility of six or more products are in the compare list")
    public CompareProducts verifySixAndMoreProductInCompareList() {
        wait.until(ExpectedConditions.elementToBeClickable(removeProductList.get(0)));
        Assert.assertTrue(removeProductList.size() >= 6);
        return this;
    }

    @Step("Verify Print This Page tooltip when hover on element")
    public CompareProducts verifyPrintThisPageTooltip() {
        Actions action = new Actions(driver);
        action.moveToElement(printThisPageButton).perform();
        Assert.assertTrue(printThisPageButton.isDisplayed(), "Print this Page tooltip should be displayed");
        return this;
    }

    @Step("Verify Remove button tooltip when hover on element")
    public CompareProducts verifyRemoveButtonTooltip() {
        Actions action = new Actions(driver);
        action.moveToElement(removeProductList.get(0)).perform();
        Assert.assertTrue(removeProductTooltip.get(0).isDisplayed(), "Remove Product tooltip should be displayed");
        return this;
    }

    private CompareProducts verifyProductAttributePresent() {
        Assert.assertTrue(descriptionAttribute.isDisplayed(), "Description attribute should be displayed");
        Assert.assertTrue(shortDescriptionAttribute.isDisplayed(), "Short Description attribute should be displayed");
        Assert.assertTrue(recommendedUsesAttribute.isDisplayed(), "Recommended uses attribute should be displayed");
        Assert.assertTrue(faqsAttribute.isDisplayed(), "FAQs attribute should be displayed");
        Assert.assertTrue(ingredientsAttribute.isDisplayed(), "Ingredients attribute should be displayed");
        Assert.assertTrue(skinTypeAttribute.isDisplayed(), "Skin Type attribute should be displayed");
        Assert.assertTrue(sizeAttribute.isDisplayed(), "Size attribute should be displayed");
        return this;
    }

    @Step("Verify product attributes present")
    public CompareProducts verifyProductAttributePresence() {
        Assert.assertFalse(isElementPresent("//tbody//span[contains(text(),'SKU')]", driver), "SKU attribute should not be displayed");
        Assert.assertFalse(isElementPresent("//tbody//span[contains(text(),'Color')]", driver), "Color attribute should not be displayed");
        verifyProductAttributePresent();
        return this;
    }

    @Step("Verify product attributes present on guest page")
    public CompareProducts verifyProductAttributePresentForGuest() {
        verifyProductAttributePresent();
        return this;
    }

    @Step("Verify product Review summary")
    public CompareProducts verifyProductReviewSummary() {
        if (isElementPresent("//div[@class='product-reviews-summary short']", driver)) {
            Assert.assertTrue(productReviewSummary.isDisplayed());
            Assert.assertTrue(ratingSummary.isDisplayed());
            Assert.assertTrue(reviewQty.isDisplayed());
        } else {
            System.out.println("Current product іs not rated yet.");
        }
        return this;
    }

    @Step("Click on product name {name} on Compare page")
    public CompareProducts clickProductName(String name) {
        WebElement productName = driver.findElement(By.xpath(String.format("//strong[@class='product-item-name' and contains(.,'%s')]//a", name)));
        click(productName);
        return this;
    }

}
