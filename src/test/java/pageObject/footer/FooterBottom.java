package pageObject.footer;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObjectInitializer;

public class FooterBottom extends PageObjectInitializer {

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[@title='Search Terms']")
    private WebElement searchTerms;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[@title='Advanced Search']")
    private WebElement advancedSearch;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[@title='Contact Us']")
    private WebElement contactUs;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[@title='Site Map']")
    private WebElement siteMap;


    public FooterBottom(WebDriver driver) {
        super(driver);
    }

    @Step("Click on <<Search Terms>> link")
    public FooterBottom searchTermsClick() {
        click(searchTerms);
        return this;
    }

    @Step("Click on <<Advanced Search>> link")
    public FooterBottom advancedSearchClick() {
        click(advancedSearch);
        return this;
    }

    @Step("Click on <<Contact Us>> link")
    public FooterBottom contactUsClick() {
        click(contactUs);
        return this;
    }

    @Step("Click on <<Site Map>> link")
    public FooterBottom siteMapClick() {
        click(siteMap);
        return this;
    }

}
