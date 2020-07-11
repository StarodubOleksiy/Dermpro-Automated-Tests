package pageObject.menu;

import creation.bean.MenuCategory;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObject.PageObjectInitializer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;


public class Menu extends PageObjectInitializer {

    @FindBy(xpath = "//div[@class='breadcrumbs']//ul[@class='items']//li[contains(@class,'item')]")
    private List<WebElement> menuItems;

    @FindBy(xpath = "//div[@class='breadcrumbs']//strong")
    private WebElement activeBreadcrumb;

    public Menu(WebDriver driver) {
        super(driver);
    }


    @Step("Click on Menu items {item}")
    public Menu selectMenu(String item) {
        List<String> menuLevels = Arrays.asList(item.split("/"));
        for (int i = 0; i < menuLevels.size(); i++) {
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
            wait.until(ExpectedConditions.elementToBeClickable(menuLevel));
            if (i == (menuLevels.size() - 1))
                menuLevel.click();
        }
        return this;
    }


    @Step("Verify number of menu elements {0}")
    public Menu verifyNumberOfMenuElements(int numberOfItems) {
        Assert.assertEquals(menuItems.size(), numberOfItems);
        return this;
    }

    @Step("Verify categories {categories}")
    public Menu verifyCategories(List<MenuCategory> categories) {
        if (categories == null) {
            System.out.println("No categories found");
        }
        categories.forEach(mainCategory -> {
            String menu = mainCategory.getName().trim();
            Assert.assertTrue(isElementPresent(String.format("//a[@class='level-top']//span[text()='%s']", menu), driver), "Category " + menu + " should be displayed");
            selectMenu(menu);
            Assert.assertEquals(activeBreadcrumb.getText(), menu);
            if (mainCategory.getSubCategoryList() != null) {
                mainCategory.getSubCategoryList().forEach(subCategory -> {
                    String subMenu = subCategory.getName();
                    selectMenu(menu + "/" + subMenu);

                    Assert.assertEquals(activeBreadcrumb.getText(), subMenu);
                });
            }
        });
        return this;
    }


}
