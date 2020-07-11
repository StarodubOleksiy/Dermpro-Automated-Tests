package data.bean;

public class BusinessInfo {

    private String verbiageForShipmentConfirmationForTreatments;

    public String getVerbiageForShipmentConfirmationForTreatments() {
        return verbiageForShipmentConfirmationForTreatments;
    }

    public void setVerbiageForShipmentConfirmationForTreatments(String verbiageForShipmentConfirmationForTreatments) {
        this.verbiageForShipmentConfirmationForTreatments = verbiageForShipmentConfirmationForTreatments;
    }

    @Override
    public String toString() {
        return "BusinessInfo{" +
                "verbiageForShipmentConfirmationForTreatments='" + verbiageForShipmentConfirmationForTreatments + '\'' +
                '}';
    }
}
