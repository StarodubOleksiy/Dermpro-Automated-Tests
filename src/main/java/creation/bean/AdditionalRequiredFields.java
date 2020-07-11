package creation.bean;

public class AdditionalRequiredFields {
    private String fieldName;
    private String enabledStatus;
    private String status;


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getEnabledStatus() {
        return enabledStatus;
    }

    public void setEnabledStatus(String enabledStatus) {
        this.enabledStatus = enabledStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "AdditionalRequiredFields{" +
                "fieldName=" + fieldName +
                ", enabledStatus=" + enabledStatus +
                ", status=" + status +
                '}';
    }
}
