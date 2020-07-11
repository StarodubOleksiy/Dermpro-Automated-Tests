package pageObject.admin;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminReview extends PageObjectInitializerAdmin {

    public AdminReview(WebDriver driver) {
        super(driver);
    }

    @Step("Click on Review by title {title}")
    public AdminReview clickReviewByTitle(String title) {
        WebElement review = driver.findElement(By.xpath(String.format("//td[contains(@class,'col-title') and contains(text(),'%s')]", title)));
        scrollIntoView(review, driver);
        jsClick(review, driver);
        return this;
    }

}
