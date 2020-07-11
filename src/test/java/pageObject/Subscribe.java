package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Subscribe extends PageObjectInitializer {

    @FindBy(xpath = "//div[@class='content']//div[text()='This email address is already subscribed.']")
    private WebElement alreadySubscribedAlert;

    @FindBy(xpath = "//div[@id='newsletter-error']")
    private WebElement emptySubscribeAlert;

    @FindBy(xpath = "//div[@class='content']//div[@class='response-msg']")
    private WebElement subscribePageMessage;

    @FindBy(xpath = "//div[@class='content']//div[@class='response-box message message-success success']")
    private WebElement subscribeSuccessMessage;

    public Subscribe(WebDriver driver) {
        super(driver);
    }

    @Step("Verify already subscribed alert")
    public Subscribe verifyAlreadySubscribedAlert() {
        wait.until(ExpectedConditions.visibilityOf(subscribePageMessage));
        Assert.assertTrue(alreadySubscribedAlert.isDisplayed(), "Alert that you are already subscribed should be displayed");
        return this;
    }

    @Step("Verify empty subscribe alert")
    public Subscribe verifyEmptySubscribeAlert() {
        wait.until(ExpectedConditions.visibilityOf(emptySubscribeAlert));
        Assert.assertEquals(emptySubscribeAlert.getText(), "This is a required field.");
        return this;
    }

    @Step("Verify invalid subscription")
    public Subscribe verifyInvalidSubscription() {
        wait.until(ExpectedConditions.visibilityOf(emptySubscribeAlert));
        Assert.assertEquals(emptySubscribeAlert.getText(), "Please enter a valid email address (Ex: johndoe@domain.com).");
        return this;
    }

    @Step("Verify successful subscription")
    public Subscribe verifySuccessfulSubscription() {
        wait.until(ExpectedConditions.visibilityOf(subscribeSuccessMessage));
        Assert.assertEquals(subscribeSuccessMessage.getText(), "Thank you for your subscription.");
        return this;
    }
}
