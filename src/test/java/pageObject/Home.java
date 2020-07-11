package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Home extends PageObjectInitializer {

    @FindBy(xpath = "//ul[@class='r-tabs-nav']")
    private WebElement tabs;

    @FindBy(xpath = "//ul[@class='slides']")
    private WebElement banner;

    public Home(WebDriver driver) {
        super(driver);
    }

    @Step("Wait until home page is loaded")
    public Home waitUntilHomePageIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(tabs));
        return this;
    }

    @Step("Click on Tab {tabName}")
    public Home clickOnTab(String tabName) {
        if (tabName.contains("'")) {
            tabName = tabName.substring(0, tabName.indexOf("'"));
        }
        WebElement tabWebElement = driver.findElement(By.xpath(String.format("//main[@id='maincontent']//a[contains(text(),'%s')]", tabName)));
        tabWebElement.click();
        return this;
    }


    @Step("Check if promo banner is present")
    public Home verifyBannerPresenceOnHomePage() {
        Assert.assertTrue(banner.isDisplayed(), "Banner should be displayed");
        return this;
    }

    @Step("Check all products which are on tab {id}, {productNames}")
    public Home verifyProductsOnTab(int idOfTab, Set<String> productNames, String tabName) {
        List<WebElement> productWebElements = driver.findElements(By.xpath(String.format("//div[@id='tab-%s']//a[@class='product-item-link']", idOfTab)));
        Map<String, String> productNamesTitleMap = productWebElements.stream().collect(Collectors.
                toMap(p -> p.getAttribute("title"), p -> removeAllCharactersExceptLettersAndNumbers(p.getAttribute("title")).toLowerCase()));
        Collection<String> productWebElementsValues = productNamesTitleMap.values();
        Map<String, String> tabSKUsMap = productNames.stream().collect(Collectors.
                toMap(p -> p, p -> removeAllCharactersExceptLettersAndNumbers(p).toLowerCase()));
        Collection<String> productNamesValues = tabSKUsMap.values();
        List<String> missingElements = new ArrayList<String>();
        List<String> extraElements = new ArrayList<String>();
        List<String> matchesProduct = new ArrayList<String>();
        tabSKUsMap.forEach((k, v) -> {
            if (!productWebElementsValues.contains(v)) {
                extraElements.add(k);
            } else
                matchesProduct.add(k);
        });
        productNamesTitleMap.forEach((k, v) -> {
            if (!productNamesValues.contains(v))
                missingElements.add(k);
        });
        System.out.println(String.format("\n=====List of missing products on tab %s======", tabName));
        printList(missingElements);
        System.out.println(String.format("\n=====List of extra products on tab %s======", tabName));
        printList(extraElements);
        System.out.println(String.format("\n=====List of matched products on tab %s======", tabName));
        printList(matchesProduct);

        Assert.assertTrue(missingElements.size() == 0, String.format("Should be no missing products on tab %s", tabName));
        Assert.assertTrue(extraElements.size() == 0, String.format("Should be no extra products on tab %s", tabName));

        return this;
    }

    public void printList(List<String> list) {
        list.forEach((element) -> {
            System.out.println(element);
        });
    }

    public String removeAllCharactersExceptLettersAndNumbers(String productName) {
        return productName.replaceAll("[^a-zA-Z0-9]", "");
    }

}
