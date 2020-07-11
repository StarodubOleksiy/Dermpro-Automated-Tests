package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class CompareLeftSideBar extends PageObjectInitializer {

    @FindBy(xpath = "//div[@class='block-title']//span[contains(@class,'counter')]")
    private WebElement counter;

    @FindBy(xpath = "//div[@class='block-content']//a[@title='Compare']")
    private WebElement compareButton;

    /*@FindBy(xpath = "//ol[@id='compare-items']//a[@title='Remove This Item']")
    private List<WebElement> removeItemLinks;*/

    @FindBy(xpath = "//button[contains(@class,'action-accept')]")
    private WebElement okButton;

    public CompareLeftSideBar(WebDriver driver) {
        super(driver);
    }

    @Step("Verify products {firstProductName} and {secondProductName} are present in compare left side bar")
    public CompareLeftSideBar verifyProductsArePresentInCompareCorner(String firstProductName, String secondProductName) {
        WebElement firstProduct = driver.findElement(By.xpath(String.format("//ol[@id='compare-items']//a[contains(text(),'%s')]", firstProductName)));
        wait.until(ExpectedConditions.visibilityOf(firstProduct));
        scrollIntoView(firstProduct, driver);
        Assert.assertTrue(isElementPresent(String.format("//ol[@id='compare-items']//a[contains(text(),'%s')]", firstProductName), driver));
        WebElement secondProduct = driver.findElement(By.xpath(String.format("//ol[@id='compare-items']//a[contains(text(),'%s')]", secondProductName)));
        wait.until(ExpectedConditions.visibilityOf(secondProduct));
        scrollIntoView(secondProduct, driver);
        Assert.assertTrue(isElementPresent(String.format("//ol[@id='compare-items']//a[contains(text(),'%s')]", secondProductName), driver));
        return this;
    }

    @Step("Click on <<Compare>> button")
    public CompareLeftSideBar clickCompareButton() {
        click(compareButton);
        return this;
    }

    @Step("Verify items counter is equals to {items} on compare left side bar")
    public CompareLeftSideBar verifyItemsCounterIsEqualsOnCompareLeftSideBar(String items) {
        wait.until(ExpectedConditions.visibilityOf(counter));
        scrollIntoView(counter, driver);
        Assert.assertEquals(counter.getText(), items);
        return this;
    }

    @Step("Click on <<Remove>> span {index}")
    public CompareLeftSideBar clickRemoveItemFromCompareLeftSideBar(String productName) {
        WebElement removeItemLink = driver.findElement(By.xpath(String.format("(//ol[@id='compare-items']//a[contains(text(),'%s')]/following::a[@title='Remove This Item'])[1]", productName)));
        click(removeItemLink);
         /*@FindBy(xpath = "//ol[@id='compare-items']//a[@title='Remove This Item']")
    private List<WebElement> removeItemLinks;*/
        // click(removeItemLinks.get(index));
        return this;
    }

    @Step("Click on <<OK>> button")
    public CompareLeftSideBar clickOkButton() {
        click(okButton);
        return this;
    }

}
