package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NewsletterSubscriptions extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement newsletterTitle;

    @FindBy(xpath = "//input[@id='subscription']")
    private WebElement generalSubscriptionCheckbox;

    @FindBy(xpath = "//button[@class='action save primary']/span[text()='Save']")
    private WebElement saveButton;

     @FindBy(xpath = "//legend[@class='legend']/span")
    private WebElement subscriptionOptionTitle;

    @FindBy(xpath = "//div[@class='messages']//div[@data-bind='html: message.text']")
    private WebElement subscriptionMessage;

    @FindBy(xpath = "//span[contains(text(),'Back')]")
    private WebElement backLink;

    public NewsletterSubscriptions(WebDriver driver) {
        super(driver);
    }

    @Step("Click on <<Save>> button")
    public NewsletterSubscriptions clickSaveButton() {
        click(saveButton);
        return this;
    }

    @Step("Verify update subscription message {message}")
    public NewsletterSubscriptions verifySubscriptionMessage(String message) {
        wait.until(ExpectedConditions.visibilityOf(subscriptionMessage));
        Assert.assertTrue(isElementPresent(String.format("//div[@class='messages']//div[text()='%s']", message),driver), message+" text should be displayed in page message alert");
        return this;
    }

    @Step("Click on <<General Subscription>> checkbox")
    public NewsletterSubscriptions clickGeneralSubscriptionCheckbox() {
        click(generalSubscriptionCheckbox);
        return this;
    }

    @Step("Check <<General Subscription>> checkbox")
    public NewsletterSubscriptions checkGeneralSubscriptionCheckbox() {
        scrollIntoView(generalSubscriptionCheckbox, driver);
        if (generalSubscriptionCheckbox.isSelected()) {
            return this;
        }
        else
            click(generalSubscriptionCheckbox);
        return this;
    }

    @Step("Verify Newsletter Subscription page")
    public NewsletterSubscriptions verifyNewsletterSubscriptionsPage() {
        wait.until(ExpectedConditions.visibilityOf(newsletterTitle));
        verifyTitle(newsletterTitle, "Newsletter Subscription");
        return this;
    }


    @Step("Click on <<Back>> link")
    public NewsletterSubscriptions clickBackLink() {
        click(backLink);
        return this;
    }


}
