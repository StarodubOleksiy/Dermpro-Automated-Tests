package pageObject.admin;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminOrders extends PageObjectInitializerAdmin {

    public AdminOrders(WebDriver driver) {
        super(driver);
    }

    @Step("Click on Order by number {orderNumber}")
    public AdminOrders clickOrderByNumber(String orderNumber) {
        WebElement review = driver.findElement(By.xpath(String.format("//div[text()='%s']", orderNumber)));
        scrollIntoView(review, driver);
        jsClick(review, driver);
        return this;
    }

}
