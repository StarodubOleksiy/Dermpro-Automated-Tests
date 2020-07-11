package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.List;

public class SearchTerms extends PageObjectInitializer {

    @FindBy(xpath = "//span[@data-ui-id='page-title-wrapper']")
    private WebElement title;

    @FindBy(xpath = "//main[@id='maincontent']//li[@class='item']//a")
    List<WebElement> items;


    public SearchTerms(WebDriver driver) {
        super(driver);
    }


    @Step("Get item name")
    public String getItemName() {
        String itemName=null;
        if(items!=null && !items.isEmpty()) {
            WebElement element = items.get(0);
            itemName=items.get(0).getText();
        }
        return itemName;
    }


    @Step("Click search result item")
    public SearchTerms clickSearchResultItem() {
        if(items!=null && !items.isEmpty()) {
            WebElement element = items.get(0);
            click(element);
        }
        return this;
    }

    @Step("Verify Popular Search Terms Page")
    public SearchTerms verifyTitle(String pageName) {
        scrollIntoView(title, driver);
        verifyTitle(title, pageName);
        return this;
    }

}
