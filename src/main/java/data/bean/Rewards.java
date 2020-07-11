package data.bean;

import java.util.List;

public class Rewards {

    private int signupRegisterOnStore;
    private int newsletterSignup;
    private int submitProductReview;
    private List<String> pointsEarnedOn;
    private List<String> pointsSpending;
    private String pointsExpiration; //Todo if pointsExpiration is Duration type it will be com.fasterxml.jackson.databind.JsonMappingException
    private MaxEarningPerDayForReviews maxEarningPerDayForReviews;
    private ErningRateOnPurchase erningRateOnPurchase;
    private FriendReferralOffers friendReferralOffers;
    private List<String> pointsExcludedFrom;

    public int getSignupRegisterOnStore() {
        return signupRegisterOnStore;
    }

    public void setSignupRegisterOnStore(int signupRegisterOnStore) {
        this.signupRegisterOnStore = signupRegisterOnStore;
    }

    public int getNewsletterSignup() {
        return newsletterSignup;
    }

    public void setNewsletterSignup(int newsletterSignup) {
        this.newsletterSignup = newsletterSignup;
    }

    public int getSubmitProductReview() {
        return submitProductReview;
    }

    public void setSubmitProductReview(int submitProductReview) {
        this.submitProductReview = submitProductReview;
    }

    public List<String> getPointsEarnedOn() {
        return pointsEarnedOn;
    }

    public void setPointsEarnedOn(List<String> pointsEarnedOn) {
        this.pointsEarnedOn = pointsEarnedOn;
    }

    public List<String> getPointsSpending() {
        return pointsSpending;
    }

    public void setPointsSpending(List<String> pointsSpending) {
        this.pointsSpending = pointsSpending;
    }

    public MaxEarningPerDayForReviews getMaxEarningPerDayForReviews() {
        return maxEarningPerDayForReviews;
    }

    public void setMaxEarningPerDayForReviews(MaxEarningPerDayForReviews maxEarningPerDayForReviews) {
        this.maxEarningPerDayForReviews = maxEarningPerDayForReviews;
    }

    public String getPointsExpiration() {
        return pointsExpiration;
    }

    public void setPointsExpiration(String pointsExpiration) {
        this.pointsExpiration = pointsExpiration;
    }

    public ErningRateOnPurchase getErningRateOnPurchase() {
        return erningRateOnPurchase;
    }

    public void setErningRateOnPurchase(ErningRateOnPurchase erningRateOnPurchase) {
        this.erningRateOnPurchase = erningRateOnPurchase;
    }

    public FriendReferralOffers getFriendReferralOffers() {
        return friendReferralOffers;
    }

    public void setFriendReferralOffers(FriendReferralOffers friendReferralOffers) {
        this.friendReferralOffers = friendReferralOffers;
    }

    public List<String> getPointsExcludedFrom() {
        return pointsExcludedFrom;
    }

    public void setPointsExcludedFrom(List<String> pointsExcludedFrom) {
        this.pointsExcludedFrom = pointsExcludedFrom;
    }

    @Override
    public String toString() {
        return "Rewards{" +
                "signupRegisterOnStore='" + signupRegisterOnStore + '\'' +
                ", newsletterSignup='" + newsletterSignup + '\'' +
                ", submitProductReview='" + submitProductReview + '\'' +
                ", pointsEarnedOn='" + pointsEarnedOn + '\'' +
                ", pointsSpending='" + pointsSpending + '\'' +
                ", pointsExpiration=" + pointsExpiration +
                ", maxEarningPerDayForReviews=" + maxEarningPerDayForReviews +
                ", erningRateOnPurchase=" + erningRateOnPurchase +
                ", friendReferralOffers=" + friendReferralOffers +
                ", pointsExcludedFrom=" + pointsExcludedFrom +
                '}';
    }
}
