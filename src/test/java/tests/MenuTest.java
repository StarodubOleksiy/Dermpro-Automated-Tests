package tests;

import creation.bean.MenuCategory;
import creation.bean.MenuSubCategory;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class MenuTest extends BaseTest {

    @TmsLink("TC_Menu_001")
    @Feature("DermPRO Menu")
    @Test(description = "Verify Categories")
    public void test01VerifyCategories() {
        List<MenuCategory> categories = testData.getStore().getMainMenuCategories();
        UI.Menu()
                .verifyCategories(categories);
    }


    @TmsLink("TC_Menu_002")
    @Feature("DermPRO Menu")
    @Test(description = "Menu breadcrumbs")
    public void test02MenuBreadcrumbs() {
        testData.getStore().getMainMenuCategories().forEach(mc -> {
            List<MenuSubCategory> subCategoryList = mc.getSubCategoryList();
            if (mc.getSubCategoryList() != null && !mc.getSubCategoryList().isEmpty()) {
                String subMenu = subCategoryList.get(0).getName();
                UI.Menu()
                        .selectMenu(mc.getName() + "/" + subMenu)
                        .verifyNumberOfMenuElements(3);
            }
        });
    }

}
