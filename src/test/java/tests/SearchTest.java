package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Override
    public void initMethod() {
            UI.Header()
                    .clearSearch();
    }

    @TmsLink("TC_Search_Test_001")
    @Feature("DermPRO Search page")
    @Test(description = "Verify result after searching")
    public void test01SearchResult() {
        String productTitle = testData.getFirstProductName();
        String searchWord = productTitle.substring(0, productTitle.indexOf(" "));

        String itemsNumber = "1 Item";
        UI.Header()
                .clickOnSearchInput()
                .enterSearchWord(productTitle)
                .searchButtonClick();
        UI.Search()
                .verifySearchResult(productTitle, itemsNumber);
    }

    @TmsLink("TC_Search_Test_002")
    @Feature("DermPRO Search page")
    @Test(description = "Verify the number of matches before searching")
    public void test02NumberOfMatchesBefore() {
        String productTitle = testData.getFirstProductName();
        String searchWord = productTitle.substring(0, productTitle.indexOf(" "));
        UI.Header()
                .clickOnSearchInput()
                .enterSearchWord(searchWord);
        UI.Search()
                .verifyMatchesAmount();
    }

    @TmsLink("TC_Search_Test_003")
    @Feature("DermPRO Search page")
    @Test(description = "Search result not found")
    public void test03SearchNoResult() {
        UI.Header()
                .clickOnSearchInput()
                .enterSearchWord("notserchedtext")
                .searchButtonClick();
        UI.Search()
                .verifySearchNoResult();
    }
}
