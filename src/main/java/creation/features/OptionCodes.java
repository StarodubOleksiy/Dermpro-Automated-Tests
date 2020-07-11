package creation.features;

public enum OptionCodes {

    SO_01A("SO-01A"), // Store name
    SO_05A("SO-05A"), // URL of store we are building
    SO_05B("SO-05B"), // Test Site URL
    SO_07("SO-07"), // Timezone
    SO_08("SO-08"), // Mailing Address and Phone Number and Hours to use for Store Contact and in footer
    SO_09A("SO-09A"),//Sales Tax to be Charged
    SO_12A("SO-12A"),//Verbiage for order/shipment confirmation regarding treatments purchased
    SO_13A("SO-13A"), // Social icons present yes/no
    SO_13B("SO-13B"), // Social Media: Facebook
    SO_13C("SO-13C"), // Social Media: Instagram
    SO_13D("SO-13D"), // Social Media: Twitter
    SO_13E("SO-13E"), // Social Media: Pinterest
    SO_13F("SO-13F"), // Social Media: Yelp
    SO_13G("SO-13G"), // Social Media: YouTube
    SO_13H("SO-13H"), // Social Media: LinkedIn
    SO_13I("SO-13I"), // Social Media: Other1
    SO_13J("SO-13J"), // Social Media: Other2
    SO_13K("SO-13K"), // Social Media: Other3
    SO_15("SO-15"), // Homepage Category style
    SO_16C("SO-16C"), // Homepage Store View Category Name for HP Category # 1
    SO_16G("SO-16G"), // Homepage Items to be shown in HP Category # 1
    SO_17C("SO-17C"), // Homepage Store View Category Name for HP Category # 2
    SO_17G("SO-17G"), // Homepage Items to be shown in HP Category # 2
    SO_18C("SO-18C"), // Homepage Store View Category Name for HP Category # 3
    SO_18G("SO-18G"), // Homepage Items to be shown in HP Category # 9
    SO_19("SO-19"), // Main menu categories
    SO_22("SO-22"),//Newsletter signup in footer section
    SO_24("SO_24"),// Homepage Second tab
    SO_25("SO-25"), //WishList Y/N
    SO_26("SO-26"),//Compare Y/N
    SO_27("SO-27"),//AddThis sharing icons
    SO_28("SO-28"),//Product Ratings (5-star) and Reviews Y/N
    SO_29("SO-29"),//Allow Guests to write reviews? Y/N
    SO_30("SO-30"),//Are Products Being Listed on Store?
    SO_32A("SO-32A"),//Private Labels on Store?
    SO_33A("SO-33A"),//Auto-Replenish (Subscriptions) available Y/N
    SO_33B("SO-33B"),//discount
    SO_33C("SO-33C"),//List of months
    SO_33D("SO-33D"),//auto replenish products
    SO_34A("SO-34A"),//Want E-Gift Cards?
    SO_34B("SO-34B"),// Homepage Third tab SKUs
    SO_35("SO-35"),// Treatments Listed on Store?
    SO_36("SO-36"),// Main menu categories
    SO_36A("SO-36A"),// Is California patient certification
    SO_37A("SO-37A"),//Any Memberships?
    SO_37B("SO-37B"),//which member groups should be created and applied to all store configurations
    SO_40("SO-40"),//Shipping fees
    SO_44A("SO-44A"),//Offer “In-Office Pickup” option?
    SO_44B("SO-44B"),//Pickup Location 1
    SO_44C("SO-44C"),//Pickup Location 2
    SO_44D("SO-44D"),//Pickup Location 3
    SO_44E("SO-44E"),//Pickup Location 4
    SO_44F("SO-44F"),//Pickup Location 5
    SO_44G("SO-44G"),//Pickup Location 6
    SO_45("SO-45"),//Terms and Conditions upon checkout?
    SO_46A("SO-46A"),//Want customer to have to respond to a required survey question at checkout.
    SO_46B("SO-46B"),//If Yes, which question should be shown?
    SO_46C("SO-46C"),//If Yes, which names or options should be listed, and in what order?
    SO_47("SO-47"),//Allow Guest Checkout?
    SO_48A("SO-48A"), //For new account creation, want additional required fields?
    SO_49A("SO-49A"), //Promos/codes. A standard coupon code for free shipping
    SO_49B("SO-49B"), //Promos/codes. Additional VIP code or other code?
    SO_53A("SO-53A"),//Implement Rewards program Y/N
    SO_53B("SO-53B"),//Points earning – Behavior Earning Rules
    SO_53C("SO-53C"),//Earn Points for Signup/Register on Store?
    SO_53D("SO-53D"),//Earn Points for Newsletter Signup?
    SO_53E("SO-53E"),//Earn Points for Submit Product Review?
    SO_53F("SO-53F"),//Max Earning per Day for Reviews?
    SO_54("SO-54"),//Points expiration?
    SO_55A("SO-55A"),//Earning rate on purchases (Shopping Cart Earning Rule)
    SO_55B("SO-55B"),//What can points be earned on?
    SO_55C("SO-55C"),//What should points be excluded from?
    SO_56("SO-56"),//Spending rules
    SO_57A("SO-57A"),//Implement Friend Referral offer? (Y/N)
    SO_57B("SO-57B"),
    SO_57C("SO-57C"),
    SO_57D("SO-57D"),
    SO_101("SO-101"),//Custom product images  Y/N
    SO_105A("SO-105A"),//Integrate a Blog?  Y/N
    ;


    private final String optionCode;

    private OptionCodes(String optionCode) {
        this.optionCode = optionCode;
    }

    public String getOptionCode() {
        return optionCode;
    }


}
