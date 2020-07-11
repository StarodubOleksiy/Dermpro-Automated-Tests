package creation.bean;

public class PromoCode {

    private String standardCode;
    private String additionalCode;

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getAdditionalCode() {
        return additionalCode;
    }

    public void setAdditionalCode(String additionalCode) {
        this.additionalCode = additionalCode;
    }

    @Override
    public String toString() {
        return "PromoCode{" +
                "standardCode='" + standardCode +
                ", additionalCode='" + additionalCode +
                '}';
    }

}
