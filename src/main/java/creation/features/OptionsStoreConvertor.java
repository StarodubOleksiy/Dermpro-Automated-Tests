package creation.features;

import configuration.PropertyLoader;
import creation.bean.*;
import data.bean.*;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OptionsStoreConvertor {
    private List<StoreConfigBean> configs;
    private String url;
    private String code;
    private boolean useTestStore;
    private boolean guestOnly;
    private String timezone;
    private HomepageCategoryStyle homepageCategoryStyle;
    private boolean checkFooterIcons = false;

    private List<String> homepageTabs;
    private List<FeatureState> featureStates;
    private Set<String> firstTabSKUs;
    private Set<String> secondTabSKUs;
    private Set<String> thirdTabSKUs;
    private List<SocialMediaInfo> socialMediaInfoList;
    private String name;
    private List<Office> offices;
    private GiftCard giftCard;
    private List<String> locations;
    private String contactInformation;
    private String checkoutTermsAndConditions;
    private String checkoutSurveyQuestion;
    private BusinessInfo businessInfo;
    private List<String> checkoutSurveyQuestionOptions;
    private List<MenuCategory> mainMenuCategories;
    private Rewards rewards;
    private Membership membership;
    private Services services;
    private List<AdditionalRequiredFields> additionalRequiredFields;
    private PromoCode promoCode;
    private int discount;
    private List<Integer> periodicityOptionsMonth;
    private List<String> autoReplenishProductsNames;
    private String shippingFees;

    public Store getStore(List<StoreConfigBean> configs, boolean useTestStore, boolean guestOnly) {
        this.configs = configs;
        this.useTestStore = useTestStore;
        this.guestOnly = guestOnly;
        this.socialMediaInfoList = new ArrayList<>();
        this.homepageTabs = new ArrayList<>();
        this.featureStates = new ArrayList<>();
        this.firstTabSKUs = new HashSet<>();
        this.secondTabSKUs = new HashSet<>();
        this.thirdTabSKUs = new HashSet<>();
        this.offices = new ArrayList<>();
        this.name = new String();
        this.locations = new ArrayList<>();
        this.checkoutSurveyQuestionOptions = new ArrayList<>();
        this.mainMenuCategories = new ArrayList<>();
        this.rewards = new Rewards();
        this.businessInfo = new BusinessInfo();
        this.additionalRequiredFields = new ArrayList<>();
        this.promoCode = new PromoCode();

        this.giftCard = new GiftCard();
        this.membership = new Membership();
        this.services = new Services();
        periodicityOptionsMonth = new ArrayList<>();
        autoReplenishProductsNames = new ArrayList<>();

        parseOptions();

        Store store = new Store();

        store.setUrl(url);
        store.setCode(code);
        store.setTimeZone(timezone);
        store.setFooterIconsInfo(socialMediaInfoList);
        store.setHomepageCategoryStyle(homepageCategoryStyle);
        store.setHomepageTabs(homepageTabs);
        store.setFirstTabSKUs(firstTabSKUs);
        store.setSecondTabSKUs(secondTabSKUs);
        store.setThirdTabSKUs(thirdTabSKUs);
        store.setFeatureStates(featureStates);
        store.setOffices(offices);
        store.setName(name);
        store.setGiftCard(giftCard);
        store.setMembership(membership);
        store.setServices(services);
        store.setLocations(locations);
        store.setContactInformation(contactInformation);
        store.setCheckoutTermsAndConditions(checkoutTermsAndConditions);
        store.setCheckoutSurveyQuestion(checkoutSurveyQuestion);
        store.setCheckoutSurveyQuestionOptions(checkoutSurveyQuestionOptions);
        store.setMainMenuCategories(mainMenuCategories);
        store.setBusinessInfo(businessInfo);
        store.setRewards(rewards);
        store.setAdditionalRequiredFields(additionalRequiredFields);
        store.setPromoCode(promoCode);
        store.setDiscount(discount);
        store.setPeriodicityOptionsMonth(periodicityOptionsMonth);
        store.setAutoReplenishProductsNames(autoReplenishProductsNames);
        store.setShippingFees(shippingFees);


        readAccountDataFromFile(store, this.code);

        return store;
    }

    private void readAccountDataFromFile(Store store, String fileNameSuffix) {

        if (fileNameSuffix == null) {
            fileNameSuffix = PropertyLoader.jsonFileDefaultSuffix;
        }

        Properties prop = new Properties();
        try {

            FileInputStream fileInputStream;
            File file = new File("./src/main/resources/users_" + fileNameSuffix + ".properties");
            if (file.exists()) {
                System.out.println("Use specific user property file \'users_" + fileNameSuffix + ".properties\'");
                fileInputStream = new FileInputStream(file);
            } else {
                System.out.println("Use general user property file \'users_login.properties\'");
                fileInputStream = new FileInputStream("./src/main/resources/users_login.properties");
            }
            prop.load(fileInputStream);

            String user1Email = prop.getProperty("user1email");
            String user1Password = prop.getProperty("user1password");
            String user2Email = prop.getProperty("user2email");
            String user2Password = prop.getProperty("user2password");

            Account account1 = new Account();
            account1.setEmail(user1Email);
            account1.setPassword(user1Password);
            store.setAccount(account1);

            Account account2 = new Account();
            account2.setEmail(user2Email);
            account2.setPassword(user2Password);
            store.setAccountForUpdate(account2);

            prop.load(new FileInputStream("./src/main/resources/admin_user.properties"));
            String userName = prop.getProperty("username");
            String password = prop.getProperty("password");

            AdminAccount adminAccount = new AdminAccount();
            adminAccount.setUserName(userName);
            adminAccount.setPassword(password);
            store.setAdminAccount(adminAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseOptions() {
        List<String> codesToCheck = getListOfFeaturesToTest();

        configs.stream().forEach(c ->
        {
            String code = c.getOptionCode();
            String value = c.getValue();
            if (value == null) {
                value = c.getOptionDefaultValue();
                System.out.println("Default value was used for option with code " + code);
            }
            String description = c.getOptionDescription();
            // Feature states
            if (codesToCheck.contains(code)) {
                featureStates.add(getFeatureState(code, value));
            }

            if (code != null && isOptionValuePresent(value)) {

                if (code.equals(OptionCodes.SO_01A.getOptionCode())) {
                    name = value;
                    this.code = createStoreCode(value);
                }

                // Store site and code
                if (code.equals(OptionCodes.SO_05A.getOptionCode())) {
                    this.url = value;
                }

                if (code.equals(OptionCodes.SO_05B.getOptionCode()) && useTestStore) {
                    //  this.code = extractTestStoreCode(value);
                    this.url = value;
                }

                if (code.equals(OptionCodes.SO_07.getOptionCode())) {
                    this.timezone = value;
                }

                if (code.equals(OptionCodes.SO_08.getOptionCode())) {
                    offices = extractOffices(value);
                    this.contactInformation = value.replaceAll("=", "");
                }

                if (code.equals(OptionCodes.SO_12A.getOptionCode())) {
                    this.businessInfo.setVerbiageForShipmentConfirmationForTreatments(value);
                }

                if (code.equals(OptionCodes.SO_13A.getOptionCode())) {
                    checkFooterIcons = isOptionValueEnabled(value);
                }

                if (checkFooterIcons &&
                        (code.equals(OptionCodes.SO_13B.getOptionCode()) ||
                                code.equals(OptionCodes.SO_13C.getOptionCode()) ||
                                code.equals(OptionCodes.SO_13D.getOptionCode()) ||
                                code.equals(OptionCodes.SO_13E.getOptionCode()) ||
                                code.equals(OptionCodes.SO_13F.getOptionCode()) ||
                                code.equals(OptionCodes.SO_13G.getOptionCode()) ||
                                code.equals(OptionCodes.SO_13H.getOptionCode()) ||
                                code.equals(OptionCodes.SO_13I.getOptionCode()) ||
                                code.equals(OptionCodes.SO_13J.getOptionCode()) ||
                                code.equals(OptionCodes.SO_13K.getOptionCode()))
                ) {
                    extractIconsInFooter(description, value);
                }

                if (code.equals(OptionCodes.SO_15.getOptionCode())) {
                    this.homepageCategoryStyle = extractHomepageCategoryStyle(value);
                }

                // Homepage tab SO-16C, SO-17C, SO-18C
                if (code.equals(OptionCodes.SO_16C.getOptionCode()) ||
                        code.equals(OptionCodes.SO_17C.getOptionCode()) ||
                        code.equals(OptionCodes.SO_18C.getOptionCode())) {
                    extractTabName(value);
                }

                // Homepage tabs SKUs SO-16G, SO-17G, SO-18G
                if (code.equals(OptionCodes.SO_16G.getOptionCode())) {
                    firstTabSKUs.addAll(extractHomepageSKUs(value));
                }

                if (code.equals(OptionCodes.SO_17G.getOptionCode())) {
                    secondTabSKUs.addAll(extractHomepageSKUs(value));
                }

                if (code.equals(OptionCodes.SO_18G.getOptionCode())) {
                    thirdTabSKUs.addAll(extractHomepageSKUs(value));
                }

                if (code.equals(OptionCodes.SO_19.getOptionCode())) {
                    mainMenuCategories = extractMainMenuCategories(value);
                }

                if (code.equals(OptionCodes.SO_33B.getOptionCode())) {
                    this.discount = extractDiscountValue(value);
                }
                if (code.equals(OptionCodes.SO_33C.getOptionCode())) {
                    periodicityOptionsMonth.addAll(extractPeriodicityOptionsMonthList(value));
                }
                if (code.equals(OptionCodes.SO_33D.getOptionCode())) {
                    autoReplenishProductsNames.addAll(extractAutoReplenishProductsNames(value));
                }

                if (code.equals(OptionCodes.SO_34B.getOptionCode())) {
                    this.giftCard = extractGiftCard(value);
                }


                if (code.equals(OptionCodes.SO_37B.getOptionCode())) {
                    this.membership.setProductName(value);
                }

                if (code.equals(OptionCodes.SO_40.getOptionCode())) {
                    this.shippingFees = value;
                }

                if (code.equals(OptionCodes.SO_44B.getOptionCode())
                        || code.equals(OptionCodes.SO_44C.getOptionCode())
                        || code.equals(OptionCodes.SO_44D.getOptionCode())
                        || code.equals(OptionCodes.SO_44E.getOptionCode())
                        || code.equals(OptionCodes.SO_44F.getOptionCode())
                        || code.equals(OptionCodes.SO_44G.getOptionCode())) {
                    locations.add(value);
                }
                if (code.equals(OptionCodes.SO_45.getOptionCode())) {
                    checkoutTermsAndConditions = value;
                }
                if (code.equals(OptionCodes.SO_46B.getOptionCode())) {
                    checkoutSurveyQuestion = value;
                }
                if (code.equals(OptionCodes.SO_46C.getOptionCode())) {
                    checkoutSurveyQuestionOptions.addAll(extractProviders(value));
                }
                if (code.equals(OptionCodes.SO_48A.getOptionCode())) {
                    additionalRequiredFields.addAll(extractAdditionalRequiredFields(value));
                }
                if (code.equals(OptionCodes.SO_49A.getOptionCode())) {
                    this.promoCode.setStandardCode(value);
                }
                if (code.equals(OptionCodes.SO_49B.getOptionCode())) {
                    this.promoCode.setAdditionalCode(this.extractAdditionalCode(value));
                }
                if (code.equals(OptionCodes.SO_53C.getOptionCode())) {
                    rewards.setSignupRegisterOnStore(extractRewardsPoints(OptionCodes.SO_53C.getOptionCode(), value));
                }
                if (code.equals(OptionCodes.SO_53D.getOptionCode())) {
                    rewards.setNewsletterSignup(extractRewardsPoints(OptionCodes.SO_53D.getOptionCode(), value));
                }
                if (code.equals(OptionCodes.SO_53E.getOptionCode())) {
                    rewards.setSubmitProductReview(extractRewardsPoints(OptionCodes.SO_53E.getOptionCode(), value));
                }
                if (code.equals(OptionCodes.SO_53F.getOptionCode())) {
                    rewards.setMaxEarningPerDayForReviews(extractMaxEarningPerDayForReviews(value));
                }
                if (code.equals(OptionCodes.SO_54.getOptionCode())) {
                    rewards.setPointsExpiration(value);
                }// Todo if pointsExpiration is Duration type it will be com.fasterxml.jackson.databind.JsonMappingException
                if (code.equals(OptionCodes.SO_55A.getOptionCode())) {
                    rewards.setErningRateOnPurchase(extractEarningRateOnPurchase(value));
                }
                if (code.equals(OptionCodes.SO_55B.getOptionCode())) {
                    rewards.setPointsEarnedOn(extractPtsItems(value));
                }
                if (code.equals(OptionCodes.SO_55C.getOptionCode())) {
                    rewards.setPointsExcludedFrom(extractPtsItems(value));
                }
                if (code.equals(OptionCodes.SO_56.getOptionCode())) {
                    rewards.setPointsSpending(extractPtsItems(value));
                }
                if (code.equals(OptionCodes.SO_57B.getOptionCode())) {
                    rewards.setFriendReferralOffers(extractFriendReferralOffers(value));
                }
            }

        });

        String testEnvState = (this.url != null && (this.url.contains("shopbuild") || this.url.contains("storebuild"))) ? "yes" : "no";
        featureStates.add(getFeatureState("admin", testEnvState));

        featureStates.add(getFeatureState("signedUser", guestOnly? "no":"yes"));
        featureStates.add(getFeatureState("guest", "yes")); //currently we are not creating tests for only for signed users
    }


    private List<String> getListOfFeaturesToTest() {
        String[] codes = {
                OptionCodes.SO_09A.getOptionCode(),
                OptionCodes.SO_13A.getOptionCode(),
                OptionCodes.SO_16C.getOptionCode(),
                OptionCodes.SO_17C.getOptionCode(),
                OptionCodes.SO_18C.getOptionCode(),
                OptionCodes.SO_22.getOptionCode(),
                OptionCodes.SO_25.getOptionCode(),
                OptionCodes.SO_26.getOptionCode(),
                OptionCodes.SO_27.getOptionCode(),
                OptionCodes.SO_28.getOptionCode(),
                OptionCodes.SO_29.getOptionCode(),
                OptionCodes.SO_30.getOptionCode(),
                OptionCodes.SO_32A.getOptionCode(),
                OptionCodes.SO_33A.getOptionCode(),
                OptionCodes.SO_34A.getOptionCode(),
                OptionCodes.SO_35.getOptionCode(),
                OptionCodes.SO_36A.getOptionCode(),
                OptionCodes.SO_37A.getOptionCode(),
                OptionCodes.SO_44A.getOptionCode(),
                OptionCodes.SO_46A.getOptionCode(),
                OptionCodes.SO_47.getOptionCode(),
                OptionCodes.SO_49B.getOptionCode(),
                OptionCodes.SO_53A.getOptionCode(),
                OptionCodes.SO_53B.getOptionCode(),
                OptionCodes.SO_57A.getOptionCode(),
                OptionCodes.SO_57B.getOptionCode(),
                OptionCodes.SO_101.getOptionCode(),
                OptionCodes.SO_105A.getOptionCode()};
        return Arrays.asList(codes);
    }

    private FeatureState getFeatureState(String code, String value) {
        FeatureState fs = new FeatureState();
        fs.setCode(code);
        fs.setEnabled(isOptionValueEnabled(value));
        return fs;
    }

    private boolean isOptionValuePresent(String value) {
        return !(value == null || value.equalsIgnoreCase("n/a"));
    }

    private boolean isOptionValueEnabled(String value) {
        return !(value == null || value.equalsIgnoreCase("n/a") || value.equalsIgnoreCase("no"));
    }

    private void extractTabName(String value) {
        homepageTabs.add(value);
    }

    private List<String> extractHomepageSKUs(String value) {
        return Arrays.asList(value.split("[\\r\\n]+"));
    }

    private String createStoreCode(String value) {
        if (value.contains("&")) {
            value = value.replace("&", "");
        }
        return value.trim().replaceAll("\\s+", "_").replaceAll("[\\W]+", "");
    }

    private String extractTestStoreCode(String value) {
        // https://shop.dermpro.com/totalskinhealth/
        int startIndex = value.lastIndexOf(".com/");
        int endIndex = value.lastIndexOf("/");
        return value.substring(startIndex + 5, endIndex);
    }

    private void extractIconsInFooter(String description, String value) {
        int beginIndex = description.indexOf("Social Media: ") + 14;
        String name = description.substring(beginIndex, description.length());
        SocialMediaInfo smi = new SocialMediaInfo();
        smi.setName(name);
        smi.setUrl(value);
        socialMediaInfoList.add(smi);
    }

    private Integer extractRewardsPoints(String optionCode, String value) {
        int digit = 0;
        try {
            int endIndexPt = value.toLowerCase().indexOf(" pt");
            int endIndexPoints = value.toLowerCase().indexOf(" point");
            if (value.toLowerCase().contains("pt")) {
                digit = extractIntValue(value, endIndexPt);
            }
            if (value.toLowerCase().contains("point")) {
                digit = extractIntValue(value, endIndexPoints);
            }
        } catch (Exception ignore) {
            System.out.println(String.format("In option code '%s' invalid Rewards point value: %s", optionCode, value));
        }
        return digit;
    }

    private boolean isWord(String s) {
        return (s.length() > 0 && s.split("\\s+").length == 1);
    }

    private String substringLastIndexOf(String str, String value) throws Exception {
        return str.substring(str.lastIndexOf(value) + 1).trim();
    }

    private Integer extractIntValue(String str, int endIndex) throws Exception {
        String str1 = substringLastIndexOf(str.substring(0, endIndex), " ");
        String str2;

        if (str1.contains("(")) {
            str2 = substringLastIndexOf(str1, "(");
        } else if (isWord(str1)) {
            str2 = str1;
        } else {
            str2 = substringLastIndexOf(str1, " ");
        }
        return Integer.parseInt(str2);
    }

    public static void main(String... arg) {

    }


    private MaxEarningPerDayForReviews extractMaxEarningPerDayForReviews(String value) {
        MaxEarningPerDayForReviews maxEarningPerDayForReviews = new MaxEarningPerDayForReviews();
        int beginReviewsIndex = value.indexOf("(");
        int endReviewsIndex = value.lastIndexOf(" review");
        int endPointsIndex = value.indexOf(" pt");
        if (value.contains("review")) {
            maxEarningPerDayForReviews.setReviews(Integer.parseInt(value.substring(beginReviewsIndex + 1, endReviewsIndex)));
        }
        maxEarningPerDayForReviews.setPoints(Integer.parseInt(value.substring(endPointsIndex - 3, endPointsIndex).replaceAll("\\D+", "")));
        return maxEarningPerDayForReviews;
    }


    private HomepageCategoryStyle extractHomepageCategoryStyle(String value) {
        if (value.equalsIgnoreCase("tabbed")) {
            return HomepageCategoryStyle.TABBED;
        } else return HomepageCategoryStyle.CAROUSEL;
    }

    private List<MenuCategory> extractMainMenuCategories(String value) {
        List<String> data = Arrays.asList(value.split("[\\r\\n]+"));
        List<MenuCategory> menuCategoryList = new ArrayList<>();
        data.stream().forEach(c -> {
            MenuCategory mc = new MenuCategory();
            if (c.contains("(")) {
                int index1 = c.indexOf("(");
                int index2 = c.indexOf(")");
                String subMenus = c.substring(index1 + 1, index2);
                mc.setName(c.substring(0, index1 - 1).trim());
                List<String> subNames = Arrays.asList(subMenus.split(","));
                List<MenuSubCategory> menuSubCategoryList = new ArrayList<>();
                subNames.stream().forEach(sn -> {
                    MenuSubCategory smc = new MenuSubCategory();
                    smc.setName(sn.trim());
                    menuSubCategoryList.add(smc);
                });
                mc.setSubCategoryList(menuSubCategoryList);
            } else mc.setName(c.trim());
            menuCategoryList.add(mc);

            if (c.contains("Gift Card")) {
                giftCard.setMenuTitle(c);
            }
            if (c.contains("Membership")) {
                membership.setMenuTitle(c);
            }
            if (c.contains("Treatment") || c.contains("Procedures")|| c.contains("Service"))
                services.setMenuTitle(extractTreatmentMenuTitle(c));
        });
        return menuCategoryList;
    }

    // Legacy method to split office information into array of strings
    private List<Office> extractOffices(String option) {
        String[] officeStrings = option.split("\n\n");
        List<Office> offices = new ArrayList<>();
        if (officeStrings[0].equals(name)) {
            officeStrings = ArrayUtils.removeElement(officeStrings, name);
        }
//        for (int i = 0; i < officeStrings.length; i += 3) { //TBD after some template will be implemented
//            Office office = new Office();
//            office.setAddress(officeStrings[i]);
//            office.setPhoneNumber(officeStrings[i + 1]);
//            office.setOfficeHours(officeStrings[i + 2]);
//            offices.add(office);
//        }
        return offices;
    }

    private List<String> extractProviders(String value) {
        String[] optionsStrings = value.split("\n");
        List<String> providers = new ArrayList<>();
        for (int i = 0; i < optionsStrings.length; i++)
            providers.add(optionsStrings[i]);
        return providers;
    }


    private GiftCard extractGiftCard(String value) {
        int startIndexOfMinValue = value.indexOf("$");
        int endIndexOfMinValue = value.indexOf("to");
        int startIndexOfMaxValue = value.lastIndexOf("$");
        int endIndexOfMaxValue = value.length();
        giftCard.setMinPrice(convertValueToPrice(value, startIndexOfMinValue + 1, endIndexOfMinValue - 1));
        giftCard.setMaxPrice(convertValueToPrice(value, startIndexOfMaxValue + 1, endIndexOfMaxValue));
        return giftCard;
    }

    private String extractTreatmentMenuTitle(String value) {
        StringBuilder menu = new StringBuilder();
        if (value.contains("(")) {
            menu.append(value.substring(0, value.indexOf(" (")));
            menu.append("/");
            menu.append(value.substring(value.indexOf("(") + 1, value.indexOf(")")));
        } else
            menu.append(value);
        return menu.toString();
    }


    private String convertValueToPrice(String value, int minValue, int maxValue) {
        String price = value.substring(minValue, maxValue);
        Double doublePrice = Double.parseDouble(price);
        return (NumberFormat.getNumberInstance(Locale.US).format(doublePrice));
    }

    private List<AdditionalRequiredFields> extractAdditionalRequiredFields(String value) {
        List<String> info = Arrays.asList(value.split(", "));
        List<AdditionalRequiredFields> additionalRequiredFields = new ArrayList<>();
        info.stream().forEach(n -> {
            AdditionalRequiredFields data = new AdditionalRequiredFields();
            String[] arr = n.split(" ");
            arr = ArrayUtils.removeElement(arr, "–");
            arr = ArrayUtils.removeElement(arr, "");
            data.setFieldName(arr[0]);
            data.setEnabledStatus(arr[1]);
            data.setStatus(arr[2]);
            additionalRequiredFields.add(data);
        });
        return additionalRequiredFields;
    }


    private ErningRateOnPurchase extractEarningRateOnPurchase(String value) {
        ErningRateOnPurchase earningRateOnPurchase = new ErningRateOnPurchase();
        int endIndexOfPercent = value.indexOf("%");
        int startIndexOfEarnedPoints = value.indexOf("$");
        int endIndexOfEarnedPoints = value.indexOf("per");
        int startIndexOfAmountSpent = value.lastIndexOf("$");
        int endIndexOfAmountSpent = value.lastIndexOf("spent");
        earningRateOnPurchase.setPersent(Integer.parseInt(value.substring(0, endIndexOfPercent)));
        earningRateOnPurchase.setErnedPoints(Integer.parseInt(value.substring(startIndexOfEarnedPoints + 1, endIndexOfEarnedPoints - 1).replaceAll("\\D+", "")));
        earningRateOnPurchase.setAmountSpent(Integer.parseInt(value.substring(startIndexOfAmountSpent + 1, endIndexOfAmountSpent - 1).replaceAll("\\D+", "")));
        return earningRateOnPurchase;
    }

    private String extractAdditionalCode(String value) {
        String[] additionalCode = value.split(" ");
        return additionalCode[0];
    }

    private Integer parseFriendReferralData(String value, int index1, int index2) {
        return Integer.parseInt(value.substring(index1, index2).replaceAll("\\D+", ""));
    }

    private FriendReferralOffers extractFriendReferralOffers(String value) {
        FriendReferralOffers friendReferralOffers = new FriendReferralOffers();
        int indexOfPercent = value.indexOf("%");
        int indexOfFriend = value.toLowerCase().indexOf("friend");
        int indexOfEarnedPts = value.toLowerCase().indexOf("pt");
        int indexOfEarnedPoints = value.toLowerCase().indexOf("points");

        int parsedPercents = Integer.parseInt(value.substring(0, indexOfPercent));
        friendReferralOffers.setProductPercentDiscountToFriend(parsedPercents);

        if (value.toLowerCase().contains("treatment")) {
            friendReferralOffers.setTreatmentDollarDiscountToFriend(parseFriendReferralData(value, indexOfPercent, indexOfFriend));
        }
        if (value.toLowerCase().contains("pt")) {
            friendReferralOffers.setPtsToReferrer(parseFriendReferralData(value, indexOfEarnedPts - 3, indexOfEarnedPts));
        }
        if (value.toLowerCase().contains("point")) {
            friendReferralOffers.setPtsToReferrer(parseFriendReferralData(value, indexOfEarnedPoints - 3, indexOfEarnedPoints));
        }
        return friendReferralOffers;
    }

    private List<String> extractPtsItems(String value) {
        List<String> ptsItems = new ArrayList<>();
        List<RewardItem> items = Arrays.asList(RewardItem.values());
        items.forEach(i -> {
            if (value.toLowerCase().contains(i.getRewardItem())) {
                ptsItems.add(i.getRewardItem());
            }
        });
        return ptsItems;
    }

    private Integer extractDiscountValue(String value) {
        int index = value.indexOf("%");
        return Integer.parseInt(value.substring(0, index).replaceAll("\\D+", ""));
    }

    private List<Integer> extractPeriodicityOptionsMonthList(String value) {
        return Arrays.asList(value.substring(0, value.toLowerCase().indexOf(" months")).split(" "))
                .stream().map(i -> Integer.parseInt(i)).collect(Collectors.toList());
    }

    private List<String> extractAutoReplenishProductsNames(String value) {
        return Arrays.asList(value.split("\\n"));
    }


}
