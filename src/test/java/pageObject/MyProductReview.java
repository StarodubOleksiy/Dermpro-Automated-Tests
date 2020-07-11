package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class MyProductReview extends PageObjectInitializer {

    @FindBy(xpath = "//span[contains(text(),'See Details')]")
    private List<WebElement> seeDetailsLink;

    @FindBy(xpath = "//span[@class='toolbar-number']")
    private WebElement toolbarNumbersOfReview;

    @FindBy(xpath = "//a[contains(@class,'back')]")
    private WebElement backLink;

    @FindBy(xpath = "//strong[@class='product-name']//a")
    private List<WebElement> productNames;

    public MyProductReview(WebDriver driver) {
        super(driver);
    }

    @Step("Click on <<See Details>> link {index}")
    public MyProductReview clickSeeDetailsLink(int index) {
        click(seeDetailsLink.get(index));
        return this;
    }

    @Step("Verify review {productName} on product with name {review}")
    public MyProductReview verifyProductReview(String productName, String review) {
        Assert.assertTrue(isElementPresent(String.format("//a[contains(text(),'%s')]", productName), driver), productName + " should be displayed in the Product name field");
        Assert.assertTrue(isElementPresent(String.format("//td[contains(text(),'%s')]", review), driver), review + " should be displayed in the Review field");
        return this;
    }

    @Step("Verify review {review} and summary {summary} on product with name {productName}")
    public MyProductReview verifyProductReviewDetails(String productName, String review, String summary) {
        Assert.assertTrue(isElementPresent(String.format("//h2[contains(text(),'%s')]", productName), driver), productName + " product name should be displayed in Review details");
        Assert.assertTrue(isElementPresent(String.format("//div[contains(text(),'%s')]", review), driver), review + " review should be displayed in Review details");
        Assert.assertTrue(isElementPresent(String.format("//div[contains(text(),'%s')]", summary), driver), summary + " summary should be displayed in Review details");
        return this;
    }

    public int getNumbersOfReview() {
        if (isElementPresent("//span[@class='toolbar-number']", driver)) {
            return getToolbarNumber();
        } else return 0;
    }

    private int getToolbarNumber() {
        List<String> numbers = Arrays.asList(toolbarNumbersOfReview.getText().split(" "));
        if (numbers.size() < 5)
            return Integer.parseInt(numbers.get(0));
        else
            return Integer.parseInt(numbers.get(5));
    }

    @Step("Verify numbers of review on <<My Product Reviews>> page {numbers}")
    public MyProductReview verifyNumbersOfProductReview(int numbers) {
        wait.until(ExpectedConditions.visibilityOf(toolbarNumbersOfReview));
        Assert.assertTrue(toolbarNumbersOfReview.getText().contains(String.valueOf(numbers)), numbers + " should be displayed on the page");
        return this;
    }

    @Step("Click on <<Back>> link")
    public MyProductReview clickBackLink() {
        click(backLink);
        return this;
    }


    @Step("verify pagination present")
    public MyProductReview verifyPagination() {
       Assert.assertTrue(isElementPresent("//select[@id='limiter']",driver),"Page limiter should be present");
       return this;
    }

    public String getPaginationToolbarMessage() {
        return toolbarNumbersOfReview.getText();
    }

    @Step("Verify empty fields")
    public MyProductReview verifyToolbarNumberMessage(String message) {
        wait.until(ExpectedConditions.visibilityOf(toolbarNumbersOfReview));
        Assert.assertEquals(toolbarNumbersOfReview.getText(), message);
        return this;
    }

    @Step("Verify My Product Review Content")
    public MyProductReview verifyMyProductReviewContent() {
        Assert.assertTrue(isElementPresent("//th[contains(text(),'Created')]", driver), "Table should contain 'Created' column");
        Assert.assertTrue(isElementPresent("//th[contains(text(),'Product Name')]", driver), "Table should contain 'Product Name' column");
        Assert.assertTrue(isElementPresent("//th[contains(text(),'Rating')]", driver), "Table should contain 'Rating' column");
        Assert.assertTrue(isElementPresent("//th[contains(text(),'Review')]", driver), "Table should contain 'Review' column");
        return this;
    }

    @Step("Retrieve Product Name by index {index}")
    public String getProductName(int index) {
        return productNames.get(index).getText();
    }
}
