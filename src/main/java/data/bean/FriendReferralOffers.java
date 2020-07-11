package data.bean;

public class FriendReferralOffers {

    private int productPercentDiscountToFriend;
    private int treatmentDollarDiscountToFriend;
    private int ptsToReferrer;

    public int getProductPercentDiscountToFriend() {
        return productPercentDiscountToFriend;
    }

    public void setProductPercentDiscountToFriend(int productPercentDiscountToFriend) {
        this.productPercentDiscountToFriend = productPercentDiscountToFriend;
    }

    public int getPtsToReferrer() {
        return ptsToReferrer;
    }

    public void setPtsToReferrer(int ptsToReferrer) {
        this.ptsToReferrer = ptsToReferrer;
    }

    public int getTreatmentDollarDiscountToFriend() {
        return treatmentDollarDiscountToFriend;
    }

    public void setTreatmentDollarDiscountToFriend(int treatmentDollarDiscountToFriend) {
        this.treatmentDollarDiscountToFriend = treatmentDollarDiscountToFriend;
    }

    @Override
    public String toString() {
        return "FriendReferralOffers{" +
                "productPercentDiscountToFriend='" + productPercentDiscountToFriend + '\'' +
                ", ptsToReferrer='" + ptsToReferrer + '\'' +
                ", treatmentDollarDiscountToFriend='" + treatmentDollarDiscountToFriend + '\'' +
                '}';
    }

}
