package data.bean;

public class MaxEarningPerDayForReviews {

    private int points;
    private int reviews;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "MaxEarningPerDayForReviews{" +
                "points='" + points + '\'' +
                ", reviews='" + reviews + '\'' +
                '}';
    }
}
