package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class ProductDetailsReview extends PageObjectInitializer {

    @FindBy(xpath = "//input[@id='nickname_field']")
    private WebElement nickNameInput;

    @FindBy(xpath = "//input[@id='summary_field']")
    private WebElement summaryFieldInput;

    @FindBy(xpath = "//textarea[@id='review_field']")
    private WebElement reviewTextInput;

    @FindBy(xpath = "//button[@title='Submit Review']")
    private WebElement submitReviewButton;

    @FindBy(xpath = "//div[@id='ratings[4]-error']")
    private WebElement ratingErrorMessage;

    @FindBy(xpath = "//div[@id='nickname_field-error']")
    private WebElement nickNameErrorMessage;

    @FindBy(xpath = "//div[@id='summary_field-error']")
    private WebElement summaryErrorMessage;

    @FindBy(xpath = "//div[@id='review_field-error']")
    private WebElement reviewErrorMessage;

    @FindBy(xpath = "//div[@data-bind='html: message.text']")
    private WebElement reviewSubmitMessage;

    @FindBy(xpath = "//div[@class='review-content']")
    private WebElement text;

    @FindBy(xpath = "//strong[@class='review-details-value']")
    private WebElement author;

    @FindBy(xpath = "//time[@class='review-details-value']")
    private WebElement time;

    @FindBy(xpath = "//a[@id='tab-label-reviews-title']//span[@class='counter']")
    private WebElement reviewTabCounter;

    @FindBy(xpath = "//div[@id='review-form']")
    private WebElement reviewFormMessage;

    public ProductDetailsReview(WebDriver driver) {
        super(driver);
    }


    @Step("Clear nick name")
    public ProductDetailsReview clearNickName() {
        clear(nickNameInput);
        return this;
    }

    @Step("Enter summary {summary}")
    public ProductDetailsReview enterSummary(String summary) {
        sendText(summaryFieldInput, summary);
        return this;
    }

    @Step("Enter review {summary}")
    public ProductDetailsReview enterReview(String summary) {
        sendText(reviewTextInput, summary);
        return this;
    }

    @Step("Click on ranting {ratingNumber}")
    public ProductDetailsReview clickRating(int ratingNumber) {
        WebElement rating = driver.findElement(By.xpath(String.format("//label[@class='rating-%s']", ratingNumber)));
        wait.until(ExpectedConditions.elementToBeClickable(rating));
        scrollIntoView(rating, driver);
        jsClick(rating, driver);
        return this;
    }

    @Step("Verify empty fields")
    public ProductDetailsReview verifyEmptyFields() {
        wait.until(ExpectedConditions.visibilityOf(ratingErrorMessage));
        Assert.assertEquals(ratingErrorMessage.getText(), "Please select one of each of the ratings above.");
        Assert.assertEquals(nickNameErrorMessage.getText(), "This is a required field.");
        Assert.assertEquals(summaryErrorMessage.getText(), "This is a required field.");
        Assert.assertEquals(reviewErrorMessage.getText(), "This is a required field.");
        return this;
    }

    @Step("Verify review was submitted successfully")
    public ProductDetailsReview verifyReviewSubmittedSuccessfully() {
        wait.until(ExpectedConditions.visibilityOf(reviewSubmitMessage));
        Assert.assertEquals(reviewSubmitMessage.getText(), "You submitted your review for moderation.");
        return this;
    }

    @Step("Click on <<Submit Review>> button")
    public ProductDetailsReview clickSubmitReviewButton() {
        click(submitReviewButton);
        return this;
    }


    @Step("Verify product review name {productName}")
    public ProductDetailsReview verifyProductReviewName(String productName) {
        Assert.assertTrue(isElementPresent(String.format("//h2[contains(text(),'%s')]", productName), driver));
        return this;
    }

    @Step("Verify product review attributes")
    public ProductDetailsReview verifyReviews() {
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Rating')]", driver), "'Rating' should be displayed");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Review by')]", driver), "'Review by' should be displayed");
        Assert.assertTrue(isElementPresent("//span[contains(text(),'Posted on')]", driver), "'Posted on' should be displayed");
        scrollIntoView(text, driver);
        Assert.assertTrue(text.getAttribute("itemprop").equals("description"));
        scrollIntoView(author, driver);
        Assert.assertTrue(author.getAttribute("itemprop").equals("author"));
        scrollIntoView(time, driver);
        Assert.assertTrue(time.getAttribute("itemprop").equals("datePublished"));
        Assert.assertTrue(isElementPresent("//div[@class='review-title']", driver), "Title should be displayed in review");
        Assert.assertTrue(isElementPresent("//div[@class='rating-result']", driver), "Total rating result should be displayed at the product");
        return this;
    }

    @Step("Check reviews are present")
    public boolean isReviewsAreNotPresent() {
        return isElementPresent("//a[contains(text(),'Be the first to review this product')]", driver);
    }

    @Step("Verify default {nickName}")
    public ProductDetailsReview verifyNickName(String nickName) {
        wait.until(ExpectedConditions.attributeToBe(nickNameInput, "value", nickName));
        return this;
    }

    @Step("Get Review tab count")
    public Integer getReviewTabCount() {
        return (isElementPresent("//a[@id='tab-label-reviews-title']//span[@class='counter']", driver)) ? Integer.parseInt(reviewTabCounter.getText()) : 0;
    }

    @Step("Verify Review tab counter {reviewTabCount}")
    public ProductDetailsReview verifyReviewTabCounter(int reviewTabCount) {
        Assert.assertEquals(Integer.parseInt(reviewTabCounter.getText()), reviewTabCount);
        return this;
    }

    @Step("Verify Review message for not logged users")
    public ProductDetailsReview verifyReviewGuestMessage() {
        Assert.assertEquals(reviewFormMessage.getText(), "Only registered users can write reviews. Please Log in or register");
        return this;
    }

}
