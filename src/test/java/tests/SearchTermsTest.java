package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class SearchTermsTest extends BaseTest {

    @TmsLink("TC_Search_Terms_Test_001")
    @Feature("DermPRO Search Terms page")
    @Test(description = "Popular terms search")
    public void test01PopularTermsSearch() {
        UI.FooterBottom()
                .searchTermsClick();
        UI.SearchTerms()
                .verifyTitle("Popular Search Terms");
        String itemName = UI.SearchTerms().getItemName();
        if (itemName != null) {
            UI.SearchTerms().clickSearchResultItem();
            UI.Search()
                    .verifyItemInSearchResultPageTitle(itemName);
        }

    }
}