package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

public class AdvancedSearchTest extends BaseTest {

    private String firstProductPrice;

    @Override
    public void init() {
        UI.Menu()
                .selectMenu(testData.getMenuVariable());
        firstProductPrice = UI.MenuPageWithProducts().getPriceByProductName(testData.getFirstProductName());
    }

    @Override
    public void initMethod() {
        UI.FooterBottom()
                .advancedSearchClick();
    }

    @TmsLink("TC_Advanced_Search_Test_001")
    @Feature("DermPRO Advanced Search page")
    @Test(description = "Empty advanced search")
    public void test01EmptyAdvancedSearch() {
        UI.AdvancedSearch()
                .clickSearchButton()
                .verifyEmptyResultSearch();
    }

    @TmsLink("TC_Advanced_Search_Test_002")
    @Feature("DermPRO Advanced Search page")
    @Test(description = "Advanced search by name")
    public void test02AdvancedSearchByName() {
        String productTitle = testData.getFirstProductName();
        String itemsNumber = "1 item";
        UI.AdvancedSearch()
                .enterProductName(productTitle)
                .clickSearchButton()
                .verifyAdvancedSearchResult(productTitle, itemsNumber);
    }

    @TmsLink("TC_Advanced_Search_Test_003")
    @Feature("DermPRO Advanced Search page")
    @Test(description = "Advanced search by price")
    public void test03AdvancedSearchByPrice() {
        String productTitle = testData.getFirstProductName();
        Long price = Long.valueOf(firstProductPrice.substring(1, firstProductPrice.indexOf(".00")));
        String priceFrom = Long.toString(price - 1);
        String priceTo = Long.toString(price + 1);
        UI.AdvancedSearch()
                .enterPriceFrom(priceFrom)
                .enterPriceTo(priceTo)
                .clickSearchButton()
                .verifyAdvancedSearchResultItemsPresent(productTitle);
    }

    @TmsLink("TC_Advanced_Search_Test_004")
    @Feature("DermPRO Advanced Search page")
    @Test(description = "Invalid advanced search")
    public void test04InvalidAdvancedSearch() {
        UI.AdvancedSearch()
                .enterProductName("invalidProductName")
                .clickSearchButton()
                .verifyInvalidAdvancedSearch();
    }

    @TmsLink("TC_Advanced_Search_Test_005")
    @Feature("DermPRO Advanced Search page")
    @Test(description = "Check empty search results")
    public void test05EmptyResults() {
        final String minPrice = "50";
        final String maxPrice = "5000";
        UI.AdvancedSearch()
                .enterProductName("invalidProductName")
                .enterDescription(testData.getSecondSentence())
                .enterShortDescription(testData.getSentence())
                .enterPriceFrom(minPrice)
                .enterPriceTo(maxPrice)
                .clickSearchButton()
                .verifyMatchingSearchCriteria("invalidProductName", testData.getSecondSentence(), testData.getSentence(), minPrice, maxPrice)
                .clickModifyYourSearchLink()
                .verifyIfYouOnAdvancedSearchPage();
    }

    //NOTE can be tested only on totalskinhealth store
    //    @TmsLink("TC_Advanced_Search_Test_003")
//    @Feature("DermPRO Advanced Search page")
//    @Test(description = "Advanced search by SKU")
//    public void test03AdvancedSearchBySku() {
//        String productTitle = testData.getFirstProductName();
//        String itemsNumber = "1 item";
//        String sku = "SKUPROD9951";
//        UI.AdvancedSearch()
//                .enterSku(sku)
//                .clickSearchButton()
//                .verifyAdvancedSearchResult(productTitle, itemsNumber);
//    }

//    @TmsLink("TC_Advanced_Search_Test_004")
//    @Feature("DermPRO Advanced Search page")
//    @Test(description = "Advanced search by description")
//    public void test04AdvancedSearchByDescription() {
//        String productTitle = "EltaMD® UV Pure Broad-Spectrum SPF 47";
//        String description = "chemical-free active ingredients";
//        String itemsNumber = "1 item";
//        UI.AdvancedSearch()
//                .enterDescription(description)
//                .clickSearchButton()
//                .verifyAdvancedSearchResult(productTitle, itemsNumber);
//    }

//    @TmsLink("TC_Advanced_Search_Test_005")
//    @Feature("DermPRO Advanced Search page")
//    @Test(description = "Advanced search by short description")
//    public void test05AdvancedSearchByShortDescription() {
//        String productTitle = "EltaMD® UV Pure Broad-Spectrum SPF 47";
//        String shortDescription = "lightweight sunscreen";
//        String itemsNumber = "1 item";
//        UI.AdvancedSearch()
//                .enterShortDescription(shortDescription)
//                .clickSearchButton()
//                .verifyAdvancedSearchResult(productTitle, itemsNumber);
//    }

//    @TmsLink("TC_Advanced_Search_Test_007")
//    @Feature("DermPRO Advanced Search page")
//    @Test(description = "Advanced search by Color")
//    public void test07AdvancedSearchByColor() {
//        String productTitle = "ALASTIN™ TriHex Transition Duo";
//        String itemsNumber = "54 items";
//        String color = "White";
//        UI.AdvancedSearch()
//                .selectColor(color)
//                .clickSearchButton()
//                .verifyAdvancedSearchResult(productTitle, itemsNumber);
//    }

//    @TmsLink("TC_Advanced_Search_Test_008")
//    @Feature("DermPRO Advanced Search page")
//    @Test(description = "Advanced search by Treatment Type")
//    public void test08AdvancedSearchByTreatmentType() {
//        String productTitle = "Test_SARP";
//        String itemsNumber = "1 item";
//        String treatmentType = "Regenerative Medicine";
//        UI.AdvancedSearch()
//                .selectTreatmentType(treatmentType)
//                .clickSearchButton()
//                .verifyAdvancedSearchResult(productTitle, itemsNumber);
//    }
//
//    @TmsLink("TC_Advanced_Search_Test_009")
//    @Feature("DermPRO Advanced Search page")
//    @Test(description = "Advanced search by Skin Type")
//    public void test09AdvancedSearchBySkinType() {
//        String productTitle = "Biopelle® Tensage® Intensive Serum 40 ";
//        String itemsNumber = "5 items";
//        String skinType = "Body Areas";
//        UI.AdvancedSearch()
//                .selectSkinType(skinType)
//                .clickSearchButton()
//                .verifyAdvancedSearchResult(productTitle, itemsNumber);
//    }

//    @TmsLink("TC_Advanced_Search_Test_010")
//    @Feature("DermPRO Advanced Search page")
//    @Test(description = "Advanced search by Color, Treatment & Skin Types")
//    public void test10AdvancedSearchByThreeTypes() {
//        String productTitle = "Test_SARP";
//        String itemsNumber = "1 item";
//        String color = "White";
//        String treatmentType = "Regenerative Medicine";
//        String skinType = "Body Areas";
//        UI.AdvancedSearch()
//                .selectColor(color)
//                .selectTreatmentType(treatmentType)
//                .selectSkinType(skinType)
//                .clickSearchButton()
//                .verifyAdvancedSearchResult(productTitle, itemsNumber);
//    }

}
