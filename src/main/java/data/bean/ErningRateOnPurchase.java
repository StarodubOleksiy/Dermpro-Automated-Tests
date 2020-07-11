package data.bean;

public class ErningRateOnPurchase {

    private int persent;
    private int ernedPoints;
    private int amountSpent;

    public int getPersent() {
        return persent;
    }

    public void setPersent(int persent) {
        this.persent = persent;
    }

    public int getErnedPoints() {
        return ernedPoints;
    }

    public void setErnedPoints(int ernedPoints) {
        this.ernedPoints = ernedPoints;
    }

    public int getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(int amountSpent) {
        this.amountSpent = amountSpent;
    }

    @Override
    public String toString() {
        return "ErningRateOnPurchase{" +
                "persent=" + persent +
                ", ernedPoints=" + ernedPoints +
                ", amountSpent=" + amountSpent +
                '}';
    }
}
