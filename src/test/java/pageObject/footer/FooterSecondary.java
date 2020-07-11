package pageObject.footer;

import creation.bean.MenuCategory;
import io.qameta.allure.Step;
import org.testng.Assert;
import pageObject.PageObjectInitializer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.menu.Menu;

import java.util.List;

public class FooterSecondary extends PageObjectInitializer {

    @FindBy(xpath = "(//*[@class='title_stripes']//span)[1]")
    private WebElement title;

    @FindBy(xpath = "//h1[@class='page-title']//span")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[contains(text(),'My Account')]")
    private WebElement myAccount;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[contains(text(),'My Addresses')]")
    private WebElement myAddresses;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[contains(text(),'My Order History')]")
    private WebElement myOrderHistory;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[contains(text(),'My Rewards')]")
    private WebElement myRewards;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[contains(text(),'About the Rewards Program')]")
    private WebElement aboutTheRewardsProgram;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[contains(text(),'Fitzpatrick Scale')]")
    private WebElement fitzpatrickScale;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[contains(text(),'Terms & Conditions')]")
    private WebElement termsConditions;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[contains(text(),'Return Policy')]")
    private WebElement returnPolicy;

    @FindBy(xpath = "//div[contains(@class,'footer-container')]//a[contains(text(),'Contact Us')]")
    private WebElement contactUs;


    @Step("Click on <<My Account>> link")
    public FooterSecondary accountClick() {
        click(myAccount);
        return this;
    }

    @Step("Click on <<My Addresses>> link")
    public FooterSecondary myAddressesClick() {
        click(myAddresses);
        return this;
    }

    @Step("Click on <<My Order History>> link")
    public FooterSecondary myOrderHistoryClick() {
        click(myOrderHistory);
        return this;
    }

    @Step("Click on <<My Rewards>> link")
    public FooterSecondary myRewardsClick() {
        click(myRewards);
        return this;
    }

    @Step("Click on <<About the Rewards Program>> link")
    public FooterSecondary aboutTheRewardsProgramClick() {
        click(aboutTheRewardsProgram);
        return this;
    }


    @Step("Click on <<Fitzpatrick Scale>> link")
    public FooterSecondary fitzpatrickScaleClick() {
        click(fitzpatrickScale);
        return this;
    }

    @Step("Click on <<Terms & Conditions>> link")
    public FooterSecondary termsConditionsClick() {
        click(termsConditions);
        return this;
    }

    @Step("Click on <<Return Policy>> link")
    public FooterSecondary returnPolicyClick() {
        click(returnPolicy);
        return this;
    }

    @Step("Click on <<Contact Us>> link")
    public FooterSecondary contactUsClick() {
        click(contactUs);
        return this;
    }


    @Step("Verify link from <<SUPPORT & INFORMATION>> section redirects to appropriate page {pageHeader}")
    public FooterSecondary verifySupportInformationLinkRedirection(String pageHeader) {
        verifyTitle(title, pageHeader);
        return this;
    }

    @Step("Verify link from <<SUPPORT & INFORMATION>> section redirects <<Contact Us>>")
    public FooterSecondary verifySupportInformationLinkRedirectionContactUs() {
        verifyTitle(pageTitle, "Contact Us");
        return this;
    }


    @Step("Verify <<Website Terms & Conditions>> page")
    public FooterSecondary verifyWebsiteTermsConditions() {
        Assert.assertTrue(isElementPresent("//h1[contains(text(),'Website Terms & Conditions')]", driver));
        return this;
    }

    public FooterSecondary(WebDriver driver) {
        super(driver);
    }

}
