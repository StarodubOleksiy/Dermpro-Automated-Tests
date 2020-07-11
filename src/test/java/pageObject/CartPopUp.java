package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartPopUp extends PageObjectInitializer {

    @FindBy(xpath = "//div[@id='header-container']//div[@id='minicart']")
    private WebElement cartLink;

    @FindBy(xpath = "//button[@id='top-cart-btn-checkout']")
    private WebElement goToCheckoutButton;

    @FindBy(xpath = "//div[@id='minicart']//a[@title='Remove item']")
    private List<WebElement> removeItemLinks;

    @FindBy(xpath = "//div[@id='minicart']//a[@title='Edit item']")
    private List<WebElement> editItemLinks;

    @FindBy(xpath = "//button[contains(@class,'action-accept')]")
    private WebElement okButton;

    @FindBy(xpath = "//button[contains(@class,'action-dismiss')]")
    private WebElement cancelButton;

    @FindBy(xpath = "//a[@data-bind='attr: {href: product_url}, html: product_name']")
    private List<WebElement> productNames;

    @FindBy(xpath = "//div[@class='price-container']//span[@class='price']")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//span[@data-bind='text: getCartLineItemsCount()']")
    private WebElement numberOfItemsInCart;

    @FindBy(xpath = "//span[@class='counter-number']")
    private WebElement counterNumber;

    @FindBy(xpath = "//input[@type='number' and contains(@class,'item-qty')]")
    List<WebElement> qtyItemInput;

    public CartPopUp(WebDriver driver) {
        super(driver);
    }


    @Step("Verify Cart Pop Up with data")
    private CartPopUp verifyCartPopUp() {
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        if (isElementsInCartPresent()) {
            Assert.assertTrue(isElementPresent("//div[@id='minicart']//a[@title='Remove item']", driver), "Remove item button should be displayed");
            Assert.assertTrue(isElementPresent("//div[@id='minicart']//a[@title='Edit item']", driver), "Edit item button should be displayed");
            Assert.assertTrue(isElementPresent("//label[contains(text(),'Qty')]", driver), "Qty should be displayed");
            Assert.assertTrue(isElementPresent("//input[@type='number']", driver), "Number of items should be displayed");
            Assert.assertTrue(isElementPresent("//button[contains(text(),'Go to Checkout')]", driver), "Go to Checkout button should be displayed");
            Assert.assertTrue(isElementPresent("//span[contains(text(),'in Cart')]", driver), "Item(s) in Cart should be displayed");
            Assert.assertTrue(isElementPresent("//span[contains(text(),'Cart Subtotal')]", driver), "Cart Subtotal should be displayed");
            Assert.assertTrue(isElementPresent("//span[contains(text(),'View and Edit Cart')]", driver), "View and Edit Cart link should be displayed");
        }
        return this;
    }

    @Step("Verify Cart Pop Up with data")
    public CartPopUp verifyCartPopUpWithData() {
        verifyCartPopUp();
        if (isElementsInCartPresent()) {
            Assert.assertTrue(isElementPresent("//span[contains(text(),'Checkout now to earn')]", driver), "Checkout now to earn text should be displayed");
        }
        return this;

    }

    @Step("Guest - Verify Cart Pop Up with data")
    public CartPopUp verifyCartPopUpWithDataAsGuest() {
        verifyCartPopUp();
        if (isElementsInCartPresent()) {
            Assert.assertFalse(isElementPresent("//span[contains(text(),'Checkout now to earn')]", driver), "Checkout now to earn text should not be displayed");
        }
        return this;
    }

    @Step("Select current product which is in Cart {productName}")
    public CartPopUp clickOnCurrentProductInCart(String productName) {
        scrollIntoView(cartLink, driver);
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        WebElement product = driver.findElement(By.xpath(String.format("//div[@class='product-item-details']//strong[@class='product-item-name']//a[contains(text(),'%s')]", productName)));
        click(product);
        return this;
    }

    @Step("Click on <<View Edit Cart>> link")
    public CartPopUp clickViewEditCart() {
        scrollIntoView(cartLink, driver);
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        Assert.assertTrue(isElementsInCartPresent(), "Item(s) should be displayed in Cart Popup");
        WebElement viewEditCartLink = driver.findElement(By.xpath("//a[@class='action viewcart']"));
        Assert.assertTrue(viewEditCartLink.isDisplayed(),"'View Edit Cart' link should be displayed");
        viewEditCartLink.click();
        return this;
    }

    @Step("Click on <<Go to Checkout>> button")
    public CartPopUp clickGoToCheckoutButton() {
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        goToCheckoutButton.click();
        return this;
    }

    @Step("Click on <<Remove Item>> link {index}")
    public CartPopUp clickRemoveItemLink(int index) {
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        removeItemLinks.get(index).click();
        return this;
    }


    @Step("Click on <<Edit item>> link {index}")
    public CartPopUp clickEditItemLink(int index) {
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        List<WebElement> editItemLinks = driver.findElements(By.xpath("//div[@id='minicart']//a[@title='Edit item']"));
        click(editItemLinks.get(index));
        return this;
    }

    @Step("Check elements in cart present")
    public boolean isElementsInCartPresent() {
        return isElementPresent("//div[@id='minicart']//a[@title='Remove item']", driver);
    }

    @Step("Verify if shopping cart is empty")
    public CartPopUp verifyCartPopUpIsEmpty() {
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        Assert.assertTrue(isElementPresent("//strong[contains(text(),'You have no items in your shopping cart.')]", driver), "Cart Popup should be empty");
        return this;
    }

    @Step("Click on <<OK>> button")
    public CartPopUp clickOkButton() {
        wait.until(ExpectedConditions.elementToBeClickable(okButton));
        okButton.click();
        return this;
    }

    @Step("Click on <<Cancel>> button")
    public CartPopUp clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelButton.click();
        return this;
    }

    @Step("Click on <<Update>> button")
    public CartPopUp clickUpdateButton() {
        WebElement updateButton = driver.findElement(By.xpath("//button[@title='Update']"));
        click(updateButton);
        return this;
    }

    @Step("Enter Qty item {number}")
    public CartPopUp enterQtyItem(String number) {
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        List<WebElement> qtyItemInput = driver.findElements(By.xpath("//input[@type='number'][contains(@class, 'item-qty')]"));
        wait.until(ExpectedConditions.elementToBeClickable(qtyItemInput.get(0)));
        qtyItemInput.get(0).sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), number + Keys.ENTER);
        return this;
    }

    @Step("Verify Qty item was updated  umber}")
    public CartPopUp verifyQtyItemUpdated(String number) {
        this.waitUntilCheckoutLoaderDisappears();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("(//input[@type='number'])[1]"), "data-item-qty", number));
        return this;
    }


    @Step("Verify number of items in Cart")
    public CartPopUp verifyNumberOfItem() {
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        wait.until(ExpectedConditions.visibilityOf(numberOfItemsInCart));
        Assert.assertEquals(numberOfItemsInCart.getText(), String.valueOf(productNames.size()));
        return this;
    }

    @Step("Verify Cart Subtotal")
    public CartPopUp verifyCartSubtotal() {
        double sum = 0;
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        WebElement cartSubtotalUpdates = driver.findElement(By.xpath("//span[@class='price-wrapper']//span[@class='price']"));
        for (int i = 0; i < productPrices.size(); i++) {
            String priceStr = productPrices.get(i).getText().substring(1);
            if (priceStr.contains(",")) {
                priceStr = priceStr.replaceAll(",", "");
            }
            double price = Double.parseDouble(priceStr);
            wait.until(ExpectedConditions.visibilityOf(qtyItemInput.get(i)));
            int number = Integer.parseInt(qtyItemInput.get(i).getAttribute("data-item-qty"));
            double finalPrice = price * number;
            sum += finalPrice;
        }
        StringBuilder finalCartSubtotalString = new StringBuilder(NumberFormat.getNumberInstance(Locale.US).format(sum));
        finalCartSubtotalString.insert(0, "$");
        if (!finalCartSubtotalString.toString().contains("."))
            finalCartSubtotalString.append(".00");
        wait.until(ExpectedConditions.visibilityOf(cartSubtotalUpdates));
        Assert.assertEquals(cartSubtotalUpdates.getText(), finalCartSubtotalString.toString());
        return this;
    }


    @Step("Verify if product name {name} is present in Cart Pop Up")
    public CartPopUp verifyProductIsPresent(String name) {
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        Assert.assertTrue(isElementPresent(String.format("//a[contains(text(),'%s')]", name), driver), "Product " + name + " should be displayed in Cart Popup");
        return this;
    }

    @Step("Verify if product price {price} is present in Cart Pop Up")
    public CartPopUp verifyProductPriceIsPresent(String price) {
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        Assert.assertTrue(isElementPresent(String.format("//span[contains(text(),'%s')]", price), driver), "Product price " + price + " should be displayed in Cart Popup");
        return this;
    }

    @Step("Wait until Cart counter will be visible")
    public CartPopUp waitUntilCounterWillBeVisible() {
        WebElement cartItemCounter = driver.findElement(By.xpath("//span[@title='Cart']//span[contains(@class,'counter-number')]"));
        wait.until(ExpectedConditions.visibilityOf(cartItemCounter));
        return this;
    }

    @Step("Verify counter number on Cart icon")
    public CartPopUp verifyCounterNumber(int numberOfProducts) {
        Assert.assertEquals(String.valueOf(numberOfProducts), counterNumber.getText());
        return this;
    }

    @Step("Get qty of products in cart")
    public int getQtyOfProductsInCart() {
        int qtyOfProducts = 0;
        Actions action = new Actions(driver);
        action.moveToElement(cartLink).perform();
        for (int i = 0; i < qtyItemInput.size(); ++i) {
            int number = Integer.parseInt(qtyItemInput.get(i).getAttribute("value"));
            qtyOfProducts += number;
        }
        return qtyOfProducts;
    }
}
