package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class CategoriesTest extends BaseTest {

    @TmsLink("TC_Item_Categories_001")
    @Feature("Categories")
    @Test(description = "Verify categories and sub categories on the page")
    public void test01VerifyCategory() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        testData.getStore().getMainMenuCategories().forEach(mc -> {
            UI.MenuPageWithProducts().clickCategory(mc.getName())
                    .verifyActiveCategoryPage(mc.getName());
            if (mc.getSubCategoryList() != null)
                mc.getSubCategoryList().forEach(sc -> {
                    UI.MenuPageWithProducts().clickSubCategory(sc.getName())
                            .verifyActiveCategoryPage(sc.getName());
                });
        });
    }
}
