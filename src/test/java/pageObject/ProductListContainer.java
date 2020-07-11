package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ProductListContainer extends PageObjectInitializer {

    @FindBy(xpath = "//div[contains(@class,'product-item-info imgdim-x')and not(contains(.,'Starting at'))]//a[@class='product-item-link']")
    private List<WebElement> productNames;

    @FindBy(xpath = "//span[@data-price-type='finalPrice']//span[@class='price']")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//*[@id='layer-product-list']/div[1]//a[@id='mode-list']")
    private WebElement modeListButton;

    @FindBy(xpath = "//*[@id='layer-product-list']/div[1]//a[@id='mode-grid']")
    private WebElement gridButton;

    @FindBy(xpath = "//div[contains(@class,'product-item-info imgdim-x') and not(contains(.,'Starting at'))]//button[@title='Add to Cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "(//div[@class='pages'])[2]//li[contains(@class,'item')]")
    private List<WebElement> pages;

    @FindBy(xpath = "//*[@id='layer-product-list']/div[1]//*[@data-role='direction-switcher']")
    private WebElement directionSwitcherButton;

    public ProductListContainer(WebDriver driver) {
        super(driver);
    }

    @Step("Retrieve product names")
    public List<String> getProductNamesList() {
        if (productNames.isEmpty()) {
            System.out.println("Can't find products");
            return null;
        }
        return retrieveProductNames(productNames, 4);
    }

    @Step("Click on list mode button")
    public ProductListContainer clickModeListButton() {
        click(modeListButton);
        return this;
    }

    @Step("Click on product by Name {name}")
    public ProductListContainer clickProductByName(String name) {
        WebElement productName = driver.findElement(By.xpath(String.format("//a[@class='product-item-link' and contains(@title,'%s')]", name)));
        click(productName);
        return this;
    }

    @Step("Click to <<Add to Cart>> span {name}")
    public ProductListContainer clickAddToCart(String name) {
        WebElement productName = driver.findElement(By.xpath(String.format("//li[.//div[contains(@class,'product-item-details')][.//a[ contains(@title,'%s')]]]", name)));
        scrollIntoView(productName, driver);
        Actions action = new Actions(driver);
        action.moveToElement(productName).perform();
        WebElement addToCardButton = driver.findElement(By.xpath(String.format("//li[.//div[contains(@class,'product-item-details')][.//a[ contains(@title,'%s')]]]//button[@title='Add to Cart']", name)));

        click(addToCardButton);
        return this;
    }

    @Step("Click to <<Add to Cart>> span {name}")
    public ProductListContainer addToCartClick(String name) {
        int index;
        for (index = 0; index < productNames.size(); ++index) {
            Actions action = new Actions(driver);
            scrollIntoView(productNames.get(index), driver);
            action.moveToElement(productNames.get(index)).perform();
            if (productNames.get(index).getText().equals(name.toUpperCase())) {
                break;
            }
        }
        click(addToCartButtons.get(index));
        return this;
    }

    @Step("Click on <<Grid>> button")
    public ProductListContainer clickGridButton() {
        click(gridButton);
        return this;
    }


    @Step("Click on page with index {index}")
    public ProductListContainer clickOnPageWithIndex(int index) {
        click(pages.get(index));
        return this;
    }

    @Step("Get size of pages")
    public int getPagesSize() {
        return pages.size();
    }


    @Step("Verify product names sorting")
    public ProductListContainer verifyProductNamesSorting() {
        if (productNames.size() > 1) {
            String name1 = productNames.get(0).getText();
            String name2 = productNames.get(1).getText();
            this.waitUntilCheckoutLoaderDisappears();
            Assert.assertTrue(name1.compareTo(name2) < name2.compareTo(name1));
        }
        return this;
    }

    @Step("Verify products price sorting")
    public ProductListContainer verifyProductPriceSorting() {
        if (productPrices.size() > 1) {
            Long price1 = extractUSDPrice(productPrices.get(0).getText());
            Long price2 = extractUSDPrice(productPrices.get(1).getText());
            Assert.assertTrue(price1 <= price2);
        }
        return this;
    }

    private Long extractUSDPrice(String value) {
        Long result = 0l;
        try {
            int startIndx = value.indexOf("$");
            int endIndx = value.indexOf(".00");

            String trimmedValue = value.substring(startIndx + 1, endIndx);
            result = Long.valueOf(trimmedValue);
        } catch (Exception e) {
            System.out.println("Invalid price value " + value);
        }
        return result;
    }

    @Step("Verify if there is list view")
    public ProductListContainer verifyListView() {
        Assert.assertTrue(isElementPresent("//div[contains(@class,'products-list')]", driver), "List view should be applied at the page");
        return this;
    }

    @Step("Verify if there is grid view")
    public ProductListContainer verifyGridView() {
        Assert.assertTrue(isElementPresent("//div[contains(@class,'items-grid')]", driver), "Grid view should be applied at the page");
        return this;
    }

    // Categories methods
    /*
       Actions action = new Actions(driver);
            WebElement menuLevel = null;
            if (i == 0) {
                menuLevel = driver.findElement(By.xpath(String.format("//div[@id='header-container']//a[contains(@class,'level-top')]//span[text()='%s']", menuLevels.get(i))));
            } else {
                menuLevel = driver.findElement(By.xpath(String.format("//div[@id='header-container']//li[contains(@class,'level%s')]//span[text()='%s']", i, menuLevels.get(i))));
            }
            scrollIntoView(menuLevel, driver);
            wait.until(ExpectedConditions.elementToBeClickable(menuLevel));
            action.moveToElement(menuLevel).perform();
     */

    @Step("Click on Category {categoryName}")
    public ProductListContainer clickCategory(String categoryName) {//span[text()='%s']
        String element = String.format("//div[@class='block-content']//span[text()='%s']", categoryName);
        Assert.assertTrue(isElementPresent(element, driver), "Category name " + categoryName + " should be present");
        WebElement category = driver.findElement(By.xpath(element));
        /*Actions action = new Actions(driver);
        action.moveToElement(category).perform();*/
        click(category);
        return this;
    }

    @Step("Click on sub Category {categoryName}")
    public ProductListContainer clickSubCategory(String categoryName) {//span[text()='%s']
        String element = String.format("//div[@class='block-content']//ul[@style='display: block;']//span[contains(text(),'%s')]", categoryName);
        WebElement category = driver.findElement(By.xpath(element));
        wait.until(ExpectedConditions.visibilityOf(category));
        Assert.assertTrue(isElementPresent(element, driver), "Category name " + categoryName + " should be present");
        click(category);
        return this;
    }

    @Step("Verify Category page {categoryName}")
    public ProductListContainer verifyActiveCategoryPage(String categoryName) {
        Assert.assertTrue(isElementPresent("//li[@class='item product product-item']", driver));
        Assert.assertTrue(isElementPresent(String.format("//div[@class='block-content']//li[contains(@class,'item-active')]//span[text()='%s']", categoryName), driver), "Category name " + categoryName + " should be selected on the page");
        return this;
    }


    @Step("Verify if Next Page button is present")
    public ProductListContainer verifyNextPageButtonIsPresent() {
        Assert.assertTrue(isElementPresent(String.format("//a[@title='Next']"), driver));
        return this;
    }

    @Step("Verify if Previous Page button is present")
    public ProductListContainer verifyPreviousPageButtonIsPresent() {
        Assert.assertTrue(isElementPresent(String.format("//a[@title='Previous']"), driver));
        return this;
    }

    public boolean isPagePresent(int index) {
        return (isElementPresent((String.format("//a[@class='page']//span[contains(text(),'%s')]", index)), driver));
    }

    public int getProductsListSize() {
        return productPrices.size();
    }

    public String getProductName(int index) {
        return productNames.get(index).getAttribute("title");
    }

    public int calculationPages() {
        return (int) Math.ceil(Double.valueOf(MenuPageWithProducts().getValueOfToolbarAmount()) /
                Double.valueOf(MenuPageWithProducts().getProductNameList().size()));
    }

    @Step("Verify <<Show per page>> options on Grid page")
    public ProductListContainer verifyShowPerPageOptionsOnGridView() {
        Assert.assertTrue(isElementPresent("//option[contains(text(),'8')]", driver), "Option <<8>> should be present.");
        Assert.assertTrue(isElementPresent("//option[contains(text(),'12')]", driver), "Option <<12>> should be present.");
        Assert.assertTrue(isElementPresent("//option[contains(text(),'16')]", driver), "Option <<16>> should be present.");
        Assert.assertTrue(isElementPresent("//option[contains(text(),'24')]", driver), "Option <<24>> should be present.");
        Assert.assertTrue(isElementPresent("//option[contains(text(),'32')]", driver), "Option <<32>> should be present.");
        Assert.assertTrue(isElementPresent("//option[contains(text(),'All')]", driver), "Option <<All>> should be present.");
        return this;
    }

    @Step("Verify <<Show per page>> options on List page")
    public ProductListContainer verifyShowPerPageOptionsOnListView() {
        Assert.assertTrue(isElementPresent("//option[contains(text(),'5')]", driver), "Option <<5>> should be present.");
        Assert.assertTrue(isElementPresent("//option[contains(text(),'10')]", driver), "Option <<10>> should be present.");
        Assert.assertTrue(isElementPresent("//option[contains(text(),'15')]", driver), "Option <<15>> should be present.");
        Assert.assertTrue(isElementPresent("//option[contains(text(),'20')]", driver), "Option <<20>> should be present.");
        Assert.assertTrue(isElementPresent("//option[contains(text(),'25')]", driver), "Option <<25>> should be present.");
        Assert.assertTrue(isElementPresent("//option[contains(text(),'All')]", driver), "Option <<All>> should be present.");
        return this;
    }

    @Step("Choose number of pages option {index}")
    public ProductListContainer chooseShowPerPageOption(int index) {
        Select options = new Select(driver.findElement(By.xpath("(//select[@id='limiter'])[2]")));
        options.selectByIndex(index);
        return this;
    }

    @Step("Get show per page option by index {index}")
    public String getValueOfShowPerPageOptionByIndex(int index) {
        Select options = new Select(driver.findElement(By.xpath("(//select[@id='limiter'])[2]")));
        return options.getOptions().get(index).getText();
    }

    @Step("Verify products on page by show per page option {index}")
    public ProductListContainer verifyProductsPerPage(int index) {
        Select options = new Select(driver.findElement(By.xpath("(//select[@id='limiter'])[2]")));
        Assert.assertEquals(String.valueOf(productNames.size()), options.getOptions().get(index).getText().replaceAll(" ", ""));
        return this;
    }

    @Step("Get show per page options size")
    public int getShowPerPageOptionsSize() {
        Select options = new Select(driver.findElement(By.xpath("//select[@id='limiter']")));
        return options.getOptions().size();
    }

    /*
       /*@Step("Get options size")
    public int getOptionsSize() {
        Select options = new Select(driver.findElement(By.xpath("//select[@id='limiter']")));
        return options.getOptions().size();
    }*/


    @Step("verify if next button is present")
    public boolean verifyIfNextButtonIsPresent() {
        return isElementPresent("(//a[contains(@class,'action') and contains(@class,'next')])[2]", driver);
    }

    @Step("verify if previous button is present")
    public boolean verifyIfPreviousButtonIsPresent() {
        return isElementPresent("(//a[contains(@class,'action') and contains(@class,'previous')])[2]", driver);
    }

    @Step("Click on next page button")
    public ProductListContainer clickOnNextPageButton() {
        WebElement nextPageButton = driver.findElement(By.xpath("(//a[contains(@class,'action') and contains(@class,'next')])[2]"));
        click(nextPageButton);
        return this;
    }

    @Step("Click on previous page button")
    public ProductListContainer clickOnPreviousPageButton() {
        WebElement previousPageButton = driver.findElement(By.xpath("(//a[contains(@class,'action') and contains(@class,'previous')])[2]"));
        click(previousPageButton);
        return this;
    }


    @Step("Verify number of pages {value},{pageSize}")
    public ProductListContainer verifyNumberOfPages(int value, int pageSize) {
        Assert.assertEquals(value, pageSize);
        return this;
    }

    @Step("Get options size")
    public int getOptionsSize() {
        Select options = new Select(driver.findElement(By.xpath("//select[@id='limiter']")));
        return options.getOptions().size();
    }

    @Step("Click on <<Sort>> list")
    public ProductListContainer chooseSorterOption(int index) {
        Select sorterOption = new Select(driver.findElement(By.xpath("(//select[@id='sorter'])[1]")));
        sorterOption.selectByIndex(index);
        return this;
    }


    }
