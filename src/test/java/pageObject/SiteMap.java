package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObject.menu.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SiteMap extends PageObjectInitializer {

    @FindBy(xpath = "//li[@class='page-url']//a")
    private List<WebElement> pageWebList;

    @FindBy(xpath = "//h1[@class='page-title']/span")
    private WebElement pageTitle;

    public SiteMap(WebDriver driver) {
        super(driver);
    }

    @Step("Verify if you on Site Map page")
    public SiteMap verifySiteMapPage() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        verifyTitle(pageTitle, "Sitemap");
        Assert.assertTrue(isElementPresent("//h3[contains(text(),'Catalog')]", driver), "Header <<Catalog>> is not present on this page.");
        Assert.assertTrue(isElementPresent("//h3[contains(text(),'Pages')]", driver), "Header <<Pages>> is not present on this page.");
        return this;
    }

    @Step("Click on current product name {productName}")
    public SiteMap clickProduct(String productName) {
        WebElement currentProductName = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]", productName)));
        currentProductName.click();
        return this;
    }

    @Step("Click on current page {pageName}")
    public SiteMap clickPage(String pageName) {
        WebElement page = driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]", pageName)));
        page.click();
        return this;
    }

    @Step("Verify current page {pageName}")
    public SiteMap verifyPage(String pageName) {
        Assert.assertTrue(isElementPresent("//a[contains(text(),'Home')]", driver));
        Assert.assertTrue(isElementPresent(String.format("//strong[contains(text(),'%s')]", pageName), driver));
        return this;
    }


    public List<String> createPageList() {
        return pageWebList.stream().map(i -> i.getText()).collect(Collectors.toList());
    }
}
