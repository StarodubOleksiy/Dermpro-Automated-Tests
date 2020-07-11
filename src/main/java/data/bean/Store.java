package data.bean;

import creation.bean.*;
import creation.features.FeatureState;

import java.util.List;
import java.util.Set;

public class Store {

    private String url;
    private String name;
    private String code;
    private String lastUpdateDate;
    private Account account;
    private Account accountForUpdate;
    private AdminAccount adminAccount;
    private String timeZone;
    private List<SocialMediaInfo> footerIconsInfo;
    private HomepageCategoryStyle homepageCategoryStyle;
    private List<String> homepageTabs;
    private Set<String> firstTabSKUs;
    private Set<String> secondTabSKUs;
    private Set<String> thirdTabSKUs;
    private List<String> productNames;
    private List<FeatureState> featureStates;
    private List<Office> offices;
    private GiftCard giftCard;
    private Membership membership;
    private Services services;
    private List<String> locations;
    private String contactInformation;
    private String checkoutTermsAndConditions;
    private String checkoutSurveyQuestion;
    private List<String> checkoutSurveyQuestionOptions;
    private List<MenuCategory> mainMenuCategories;
    private Rewards rewards;
    private BusinessInfo businessInfo;
    private List<AdditionalRequiredFields> additionalRequiredFields;
    private PromoCode promoCode;
    private int discount;
    private List<Integer> periodicityOptionsMonth;
    private List<String> autoReplenishProductsNames;
    private String shippingFees;

    public String getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(String shippingFees) {
        this.shippingFees = shippingFees;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccountForUpdate() {
        return accountForUpdate;
    }

    public void setAccountForUpdate(Account accountForUpdate) {
        this.accountForUpdate = accountForUpdate;
    }

    public AdminAccount getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(AdminAccount adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public List<SocialMediaInfo> getFooterIconsInfo() {
        return footerIconsInfo;
    }

    public void setFooterIconsInfo(List<SocialMediaInfo> footerIconsInfo) {
        this.footerIconsInfo = footerIconsInfo;
    }

    public HomepageCategoryStyle getHomepageCategoryStyle() {
        return homepageCategoryStyle;
    }

    public void setHomepageCategoryStyle(HomepageCategoryStyle homepageCategoryStyle) {
        this.homepageCategoryStyle = homepageCategoryStyle;
    }

    public List<String> getHomepageTabs() {
        return homepageTabs;
    }

    public void setHomepageTabs(List<String> homepageTabs) {
        this.homepageTabs = homepageTabs;
    }

    public Set<String> getFirstTabSKUs() {
        return firstTabSKUs;
    }

    public void setFirstTabSKUs(Set<String> firstTabSKUs) {
        this.firstTabSKUs = firstTabSKUs;
    }

    public Set<String> getSecondTabSKUs() {
        return secondTabSKUs;
    }

    public void setSecondTabSKUs(Set<String> secondTabSKUs) {
        this.secondTabSKUs = secondTabSKUs;
    }

    public Set<String> getThirdTabSKUs() {
        return thirdTabSKUs;
    }

    public void setThirdTabSKUs(Set<String> thirdTabSKUs) {
        this.thirdTabSKUs = thirdTabSKUs;
    }

    public List<String> getProductsNames() {
        return productNames;
    }

    public void setProductNames(List<String> productNames) {
        this.productNames = productNames;
    }

    public List<FeatureState> getFeatureStates() {
        return featureStates;
    }

    public void setFeatureStates(List<FeatureState> featureStates) {
        this.featureStates = featureStates;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public GiftCard getGiftCard() {
        return giftCard;
    }

    public void setGiftCard(GiftCard giftCard) {
        this.giftCard = giftCard;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }


    public String getCheckoutTermsAndConditions() {
        return checkoutTermsAndConditions;
    }

    public void setCheckoutTermsAndConditions(String checkoutTermsAndConditions) {
        this.checkoutTermsAndConditions = checkoutTermsAndConditions;
    }

    public String getCheckoutSurveyQuestion() {
        return checkoutSurveyQuestion;
    }

    public void setCheckoutSurveyQuestion(String checkoutSurveyQuestion) {
        this.checkoutSurveyQuestion = checkoutSurveyQuestion;
    }

    public List<String> getCheckoutSurveyQuestionOptions() {
        return checkoutSurveyQuestionOptions;
    }

    public void setCheckoutSurveyQuestionOptions(List<String> checkoutSurveyQuestionOptions) {
        this.checkoutSurveyQuestionOptions = checkoutSurveyQuestionOptions;
    }

    public List<MenuCategory> getMainMenuCategories() {
        return mainMenuCategories;
    }

    public void setMainMenuCategories(List<MenuCategory> mainMenuCategories) {
        this.mainMenuCategories = mainMenuCategories;
    }

    public Rewards getRewards() {
        return rewards;
    }

    public void setRewards(Rewards rewards) {
        this.rewards = rewards;
    }

    public List<AdditionalRequiredFields> getAdditionalRequiredFields() {
        return additionalRequiredFields;
    }

    public void setAdditionalRequiredFields(List<AdditionalRequiredFields> additionalRequiredFields) {
        this.additionalRequiredFields = additionalRequiredFields;
    }

    public PromoCode getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCode promoCode) {
        this.promoCode = promoCode;
    }


    public BusinessInfo getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(BusinessInfo businessInfo) {
        this.businessInfo = businessInfo;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public List<Integer> getPeriodicityOptionsMonth() {
        return periodicityOptionsMonth;
    }

    public void setPeriodicityOptionsMonth(List<Integer> periodicityOptionsMonth) {
        this.periodicityOptionsMonth = periodicityOptionsMonth;
    }

    public List<String> getAutoReplenishProductsNames() {
        return autoReplenishProductsNames;
    }

    public void setAutoReplenishProductsNames(List<String> autoReplenishProductsNames) {
        this.autoReplenishProductsNames = autoReplenishProductsNames;
    }

    @Override
    public String toString() {
        return "Store{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", lastUpdateDate='" + lastUpdateDate + '\'' +
                ", account=" + account +
                ", accountForUpdate=" + accountForUpdate +
                ", adminAccount=" + adminAccount +
                ", timeZone='" + timeZone + '\'' +
                ", footerIconsInfo=" + footerIconsInfo +
                ", homepageCategoryStyle=" + homepageCategoryStyle +
                ", homepageTabs=" + homepageTabs +
                ", firstTabSKUs=" + firstTabSKUs +
                ", secondTabSKUs=" + secondTabSKUs +
                ", thirdTabSKUs=" + thirdTabSKUs +
                ", productNames=" + productNames +
                ", featureStates=" + featureStates +
                ", offices=" + offices +
                ", giftCard=" + giftCard +
                ", membership=" + membership +
                ", services=" + services +
                ", locations=" + locations +
                ", contactInformation='" + contactInformation + '\'' +
                ", checkoutTermsAndConditions='" + checkoutTermsAndConditions + '\'' +
                ", checkoutSurveyQuestion='" + checkoutSurveyQuestion + '\'' +
                ", checkoutSurveyQuestionOptions=" + checkoutSurveyQuestionOptions +
                ", mainMenuCategories=" + mainMenuCategories +
                ", rewards=" + rewards +
                ", businessInfo=" + businessInfo +
                ", additionalRequiredFields=" + additionalRequiredFields +
                ", promoCode=" + promoCode +
                ", discount=" + discount +
                ", periodicityOptionsMonth=" + periodicityOptionsMonth +
                ", autoReplenishProductsNames=" + autoReplenishProductsNames +
                ", shippingFees=" + shippingFees +
                '}';
    }
}
