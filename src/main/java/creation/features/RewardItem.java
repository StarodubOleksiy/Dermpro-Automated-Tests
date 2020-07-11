package creation.features;

import java.util.List;

public enum RewardItem {

    PRODUCT("product"),
    SERVICE("service"),
    GIFT_CARD("gift card"),
    TREATMENT("treatment"),
    MEMBERSHIP("membership"),
    ;

    private final String rewardItem;

    private RewardItem(String rewardItem) {
        this.rewardItem = rewardItem;
    }

    public String getRewardItem() {
        return rewardItem;
    }

}
