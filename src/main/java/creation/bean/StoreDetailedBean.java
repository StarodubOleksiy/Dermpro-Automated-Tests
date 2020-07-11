package creation.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StoreDetailedBean {

    private Integer id;
    private String name;
    private String status;
    private String createdAt;
    private String updatedAt;
    @JsonProperty("StoreConfigs")
    private List<StoreConfigBean> storeConfigs;

    private boolean success;


    public List<StoreConfigBean> getStoreConfigs() {
        return storeConfigs;
    }

    public void setStoreConfigBeans(List<StoreConfigBean> storeConfigBeans) {
        this.storeConfigs = storeConfigs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStoreConfigs(List<StoreConfigBean> storeConfigs) {
        this.storeConfigs = storeConfigs;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "StoreDetailedBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", storeConfigBeans=" + storeConfigs +
                ", success=" + success +
                '}';
    }
}
