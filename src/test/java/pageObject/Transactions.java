package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

public class Transactions extends PageObjectInitializer {

    @FindBy(xpath = "//h1[@class='page-title']/span")
    private WebElement transactionsTitle;

    @FindBy(xpath = "//td[@class='col id']")
    private List<WebElement> transactionIds;

    @FindBy(xpath = "//td[@class='col created']")
    private List<WebElement> transactionDates;

    @FindBy(xpath = "//td[@class='col comment']")
    private List<WebElement> transactionComments;

    @FindBy(xpath = "//td[@class='col amount']")
    private List<WebElement> transactionPointsAmount;

    @FindBy(xpath = "//td[@class='col status']")
    private List<WebElement> transactionStatuses;

    @FindBy(xpath = "//td[@class='col expire']")
    private List<WebElement> transactionEspirationDates;

    public Transactions(WebDriver driver) {
        super(driver);
    }

    @Step("Verify Transactions page")
    public Transactions verifyTransactionsPage() {
        wait.until(ExpectedConditions.visibilityOf(transactionsTitle));
        verifyTitle(transactionsTitle, "Transactions");
        return this;
    }
}
