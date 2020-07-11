package configuration;

import io.github.bonigarcia.wdm.*;
import io.qameta.allure.Step;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class BrowserFactory {
 public static void detectBrowser() {
        switch (PropertyLoader.browser.toLowerCase()) {
            case "chrome":
            case "chrome_headless":
            case "opera":
                String chromeVersion = PropertyLoader.chromeBrowserVersion;
                if ("default".equalsIgnoreCase(chromeVersion)) {
                    ChromeDriverManager.chromedriver().setup();
                } else {
                    ChromeDriverManager.chromedriver().version(chromeVersion).setup();
                }
                break;

            case "firefox":
                FirefoxDriverManager.firefoxdriver().setup();
                break;

            case "ie":
            case "internet explorer":
                InternetExplorerDriverManager.iedriver().setup();
                break;

            case "edge":
                EdgeDriverManager.edgedriver().setup();
                break;

            case "ghost":
            case "phantom":
                PhantomJsDriverManager.phantomjs().setup();
                break;

            case "safari":
                break;

            default:
                throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser");
        }
    }

    @Step("Create web driver with url {baseUrl}")
    public static WebDriver createDriver(String baseUrl) throws Exception {
        WebDriver driver;

        // In case browser is set from a file system, use it instead of property file
        String browser = System.getenv("AUTOMATION_BROWSER");

        if (browser == null) {
            browser = PropertyLoader.browser;
        }

        ChromeOptions chromeOptions = new ChromeOptions();

        switch (browser.toLowerCase()) {
            case "chrome":
                chromeOptions.addArguments("--disable-notifications");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "chrome_headless":
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--remote-debugging-port=9222");
                chromeOptions.addArguments("--window-size=1200,1024");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "ie":
            case "internet explorer":
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driver = new InternetExplorerDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "safari":
                driver = new SafariDriver();
                break;

            default:
                throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser");
        }
        if (PropertyLoader.implicitlyWait > 0) {
            driver.manage().timeouts().implicitlyWait(PropertyLoader.maxPageLoadTime, TimeUnit.SECONDS);
        }

        if (PropertyLoader.maxPageLoadTime > 0) {
            driver.manage().timeouts().pageLoadTimeout(PropertyLoader.maxPageLoadTime, TimeUnit.SECONDS);

        }
        if (baseUrl == null) {
            baseUrl = PropertyLoader.defaultBaseUrl;
        }
        driver.get(baseUrl);
        driver.manage().window().maximize();

        return driver;
    }

}
