package pageObject;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Wishlist extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement wishListTitle;

    @FindBy(xpath = "//button[@title='Update Wish List']")
    private WebElement updateWishListButton;

    @FindBy(xpath = "//button[@title='Share Wish List']")
    private WebElement shareWishListButton;

    @FindBy(xpath = "//button[@title='Add All to Cart']")
    private WebElement addAllToCartButton;


    @FindBy(xpath = "//a[@title='Remove Item']")
    private List<WebElement> removeItemLinks;

    @FindBy(xpath = "//span[contains(text(),'Back')]")
    private WebElement backSpan;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement wishlistMessage;

    @FindBy(xpath = "//textarea[@title='Comment']")
    private List<WebElement> commentInputs;

    @FindBy(xpath = "//input[@data-role='qty']")
    private List<WebElement> qtyInputs;

    @FindBy(xpath = "//div[@class='mage-error']")
    private WebElement qtyError;

    public Wishlist(WebDriver driver) {
        super(driver);
    }

    @Step("Click on <<Add to Cart>> button {index}")
    public Wishlist clickAddToCartButton(String productName) {
        WebElement addToCartButton = driver.findElement(By.xpath(String.format("(//a[contains(@title,'%s')]/following::button[@title='Add to Cart'])[1]", productName)));
        click(addToCartButton);
        return this;
    }

    @Step("Click on <<Update Wish List>> button")
    public Wishlist clickUpdateWishListButton() {
        click(updateWishListButton);
        return this;
    }

    @Step("Click on <<Share Wish List>> button")
    public Wishlist clickShareWishListButton() {
        click(shareWishListButton);
        return this;
    }

    @Step("Click on <<Add All to Cart>> button")
    public Wishlist clickAddAllToCartButton() {
        click(addAllToCartButton);
        return this;
    }

    @Step("Click on <<Edit>> link {productName}")
    public Wishlist clickEditLink(String productName) {
        WebElement editLink = driver.findElement(By.xpath(String.format("(//a[contains(@title,'%s')]/following::a[@title='Edit'])[1]", productName)));
        click(editLink);
        return this;
    }

    @Step("Click on <<Remove Item>> link {index}")
    public Wishlist clickRemoveItemLink(int index) {
        click(removeItemLinks.get(index));
        return this;
    }

    @Step("Click on <<Back>> span")
    public Wishlist clickBackSpan() {
        click(backSpan);
        return this;
    }

    @Step("Verify product was added to wishlist successfully {name}")
    public Wishlist verifyProductWasAddedToWishlist(String name) {
        wait.until(ExpectedConditions.visibilityOf(wishlistMessage));
        Assert.assertEquals(wishlistMessage.getText(), name + " has been added to your Wish List. Click here to continue shopping.");
        return this;
    }

    @Step("Verify product has been moved to wishlist successfully {name}")
    public Wishlist verifyProductHasBeenMovedToWishlist(String name) {
        wait.until(ExpectedConditions.visibilityOf(wishlistMessage));
        scrollIntoView(wishlistMessage, driver);
        Assert.assertEquals(wishlistMessage.getText(), name + " has been moved to your wish list.");
        return this;
    }

    @Step("Verify Qty input {error}")
    public Wishlist verifyQtyInputError(String error) {
        waitUntilPageLoaded();
        wait.until(ExpectedConditions.visibilityOf(qtyError));
        scrollIntoView(qtyError, driver);
        Assert.assertEquals(qtyError.getText(), error);
        return this;
    }

    @Step("Verify if wish list has been shared")
    public Wishlist verifyWishListWasShared() {
        wait.until(ExpectedConditions.visibilityOf(wishlistMessage));
        scrollIntoView(wishlistMessage, driver);
        Assert.assertEquals(wishlistMessage.getText(), "Your wish list has been shared.");
        return this;
    }

    @Step("Verify if wish list is empty")
    public Wishlist verifyWishListIsEmpty() {
        Assert.assertTrue(isElementPresent("//span[contains(text(),'You have no items in your wish list.')]", driver), "Wish list should be empty");
        return this;
    }

    @Step("Enter comment {message} on wishlist to product with index {index}")
    public Wishlist enterComment(String message, int index) {
        sendText(commentInputs.get(index), message);
        return this;
    }


    @Step("Clear Qty input with index {index}")
    public Wishlist clearQty(int index) {
        clear(qtyInputs.get(index));
        return this;
    }

    @Step("Enter qty input {message} on wishlist to product with index {index}")
    public Wishlist enterQty(String message, int index) {
        sendText(qtyInputs.get(index), message);
        return this;
    }

    @Step("Verify comment {comment} on wishlist to product with index {index}")
    public Wishlist verifyComment(String comment, int index) {
        wait.until(ExpectedConditions.visibilityOf(commentInputs.get(index)));
        scrollIntoView(commentInputs.get(index), driver);
        Assert.assertEquals(commentInputs.get(index).getText(), comment);
        return this;
    }


    @Step("Verify qty {qty} on wishlist to product with index {index}")
    public Wishlist verifyQty(String qty, int index) {
        wait.until(ExpectedConditions.visibilityOf(qtyInputs.get(index)));
        scrollIntoView(qtyInputs.get(index), driver);
        Assert.assertEquals(qtyInputs.get(index).getAttribute("value"), qty);
        return this;
    }

    @Step("Verify if you on WihList page")
    public Wishlist verifyWishListPage() {
        wait.until(ExpectedConditions.visibilityOf(wishListTitle));
        scrollIntoView(wishListTitle, driver);
        verifyTitle(wishListTitle, "Wish List");
        return this;
    }

}
