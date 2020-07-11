package pageObject.footer;

import data.bean.Office;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObject.PageObjectInitializer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import creation.bean.SocialMediaInfo;

import java.util.List;
//import io.qameta.allure.Step;


public class FooterPrimary extends PageObjectInitializer {

    @FindBy(xpath = "//input[@id='newsletter']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@title='Subscribe']")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[@class='footer-primary-bottom']")
    private WebElement footerBlock;

    @FindBy(xpath = "//div[@class='no-gutter grid12-12']")
    private WebElement contactUsInfoBlock;

    public FooterPrimary(WebDriver driver) {
        super(driver);
    }

    @Step("Enter email {0}")
    public FooterPrimary enterEmail(String mail) {
        sendText(emailInput, mail);
        return this;
    }

    @Step("Clear email")
    public FooterPrimary clearEmail() {
        clear(emailInput);
        return this;
    }

    @Step("Click on <<Subscribe>> button")
    public FooterPrimary subscribeButtonClick() {
        click(subscribeButton);
        return this;
    }

    @Step("Verify socials block")
    public FooterPrimary verifySocialsBlock(List<SocialMediaInfo> name) {
        scrollIntoView(footerBlock, driver);
        name.forEach(n -> {
            WebElement link = driver.findElement(By.xpath("//div[@class='footer-primary-bottom']//div[@class='item item-left']//a[@title='" + n.getName() + "']"));
            wait.until(ExpectedConditions.visibilityOf(link));
            Assert.assertEquals(link.getAttribute("href"), n.getUrl());
        });
        return this;
    }

    @Step("Verify Contact Us information")
    public FooterPrimary verifyContactUsInformation(String text) {
        wait.until(ExpectedConditions.visibilityOf(contactUsInfoBlock));
        scrollIntoView(footerBlock, driver);
        Assert.assertEquals(contactUsInfoBlock.getText().replaceAll("\\s", ""), text.replaceAll("\\s", ""));
        return this;
    }

}
