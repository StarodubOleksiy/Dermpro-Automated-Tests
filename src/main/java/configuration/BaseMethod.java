package configuration;

import creation.features.FeatureState;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BaseMethod {

    public static WebDriver driver;
    public static WebDriver adminDriver;

    protected static WebDriverWait wait;
    protected static WebDriverWait adminWait;
    private static int WEB_DRIVER_WAIT = 10;

    private boolean caseInsensitiveTitle = PropertyLoader.caseInsensitiveTitle;

    public boolean isElementPresent(String element, WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.xpath(element));
        return elements.size() > 0;
    }

    protected void scrollIntoView(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -160);", element);
    }

    protected void jsClick(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void waitUntilPageLoaded() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    private void waitWithScroll(WebElement element) {
        waitWithScroll(element, getDriver(), wait);
    }

    private void waitWithScroll(WebElement element, WebDriver driver, WebDriverWait driverWait) {
        driverWait.until(ExpectedConditions.elementToBeClickable(element));
        scrollIntoView(element, driver);
    }

    protected void clickAdmin(WebElement element) {
        waitWithScroll(element, getAdminDriver(), adminWait);
        element.click();
    }

    protected void click(WebElement element) {
        waitWithScroll(element);
        element.click();
    }

    protected void sendText(WebElement element, String value) {
        waitWithScroll(element);
        element.sendKeys(value);
    }

    protected void sendTextAdmin(WebElement element, String value) {
        waitWithScroll(element, getAdminDriver(), adminWait);
        element.sendKeys(value);
    }

    protected void clear(WebElement element) {
        waitWithScroll(element);
        element.clear();
    }

    protected void clearAdmin(WebElement element) {
        waitWithScroll(element, getAdminDriver(), adminWait);
        element.clear();
    }

    public void waitUntilProductsPageIsLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@aria-busy='false']")));
    }

    protected boolean isFeatureEnabled(List<FeatureState> features, String code) {
        Optional<FeatureState> feature = features.stream()
                .filter(f -> f.getCode().equalsIgnoreCase(code))
                .findFirst();
        return feature.get().isEnabled();
    }

    public String paste() {
        String text = "";
        try {
            text = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); // extracting the text that was copied to the clipboard
        } catch (Exception e) {
            System.out.println(e);
        }
        return text;
    }

    public void setDriverAndWait(WebDriver wd) {
        this.driver = wd;
        wait = new WebDriverWait(driver, WEB_DRIVER_WAIT);
    }


    public void setAdminDriverAndWait(WebDriver wd) {
        this.adminDriver = wd;
        adminWait = new WebDriverWait(driver, WEB_DRIVER_WAIT);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver getAdminDriver() {
        return adminDriver;
    }

    public void OpenUrl(String url) {
        getDriver().navigate().to(url);
    }

    protected String getCurrentDate() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDateTime currentDate = LocalDateTime.now();
        return dateFormat.format(currentDate);
    }

    public void waitUntilCheckoutLoaderDisappears() throws TimeoutException {
        String loaderPath = "//div[@id='checkout-loader']";
        if (isElementPresent(loaderPath, driver)) {
            WebElement loader = driver.findElement(By.xpath(loaderPath));
            try {
                wait.until(ExpectedConditions.invisibilityOf(loader));
            } catch (TimeoutException ex) {
                throw ex;
            }
        }
    }

    public List<String> retrieveProductNames(List<WebElement> productNames, int maxElements) {
        List<String> names = new ArrayList<>();
        int i = 0;
        for (WebElement we : productNames) {
            if (we.getText().toLowerCase().contains("gift card")) {
                continue;
            } else if (i == maxElements) {
                break;
            } else {
                names.add(we.getAttribute("title").trim());
                i++;
            }
        }
        return names;
    }

    protected void verifyTitle(WebElement element, String titleText) {
        if (caseInsensitiveTitle) {
            Assert.assertEquals(element.getText().toLowerCase(), titleText.toLowerCase());
        } else {
            Assert.assertEquals(element.getText(), titleText);
        }
    }

}
