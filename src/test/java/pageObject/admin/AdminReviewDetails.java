package pageObject.admin;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AdminReviewDetails extends AdminReview {

    @FindBy(xpath = "//button[@id='save_button']")
    private WebElement saveReviewButton;

    @FindBy(xpath = "//select[@id='status_id']")
    private WebElement statusDropDown;

    public AdminReviewDetails(WebDriver driver) {
        super(driver);
    }

    @Step("Click on Save Review button")
    public AdminReviewDetails clickSaveReview() {
        saveReviewButton.click();
        return this;
    }

    @Step("Select Review status {status}")
    public AdminReviewDetails selectReviewStatus(String status) {
        Select statusSelect = new Select(statusDropDown);
        statusSelect.selectByVisibleText(status);
        return this;
    }

    @Step("Verify Review status {status} by title {title}")
    public AdminReviewDetails verifyReviewStatus(String status, String title) {
        WebElement reviewStatus = driver.findElement(By.xpath(String.format("//tr[td[contains(@class,'col-title') and contains(text(),'%s')]]//td[contains(@class,'col-status')]", title)));
        wait.until(ExpectedConditions.visibilityOf(reviewStatus));
        Assert.assertEquals(reviewStatus.getText(), status);
        return this;
    }

}
