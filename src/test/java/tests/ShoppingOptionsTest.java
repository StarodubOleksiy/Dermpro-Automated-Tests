package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class ShoppingOptionsTest extends BaseTest {

    @TmsLink("TC_Shopping_Options_001")
    @Feature("Shopping_Options")
    @Test( description = "Verify Shopping Option filters in Now Shopping By section")
    public void test01VerifyShoppingOptionFiltersInNowShoppingBySection() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        UI.ShoppingOptions()
                .verifyShoppingOptionFiltersInNowShoppingBySection();
    }

}
