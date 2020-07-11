package creation.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StoreConfigBean {

    private String value;
    private String status;
    private String createdAt;
    private String updatedAt;
    @JsonProperty("Option.name")
    private String optionName;
    @JsonProperty("Option.code")
    private String optionCode;
    @JsonProperty("Option.description")
    String optionDescription;
    @JsonProperty("Option.defaultValue")
    String optionDefaultValue;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    public String getOptionDefaultValue() {
        return optionDefaultValue;
    }

    public void setOptionDefaultValue(String optionDefaultValue) {
        this.optionDefaultValue = optionDefaultValue;
    }

    @Override
    public String toString() {
        return "StoreConfigBean{" +
                "value='" + value + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", optionName='" + optionName + '\'' +
                ", optionCode='" + optionCode + '\'' +
                ", optionDescription='" + optionDescription + '\'' +
                ", optionDefaultValue='" + optionDefaultValue + '\'' +
                '}';
    }
}
