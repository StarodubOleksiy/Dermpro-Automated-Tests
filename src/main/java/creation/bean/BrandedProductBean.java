package creation.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandedProductBean {

    private Integer id;
    private String price;
    private String name;
    private String brand;
    private String makeUpOptions;
    private String size;
    private String taxable;
    private String comment;
    private String createdAt;
    private String updatedAt;

    @JsonProperty("BrandedProductId")
    private Integer brandedProductId;

    @JsonProperty("StoreId")
    private Integer storeId;

    @JsonProperty("BrandedProduct")
    private BrandedProductBean brandedProduct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTaxable() {
        return taxable;
    }

    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Integer getBrandedProductId() {
        return brandedProductId;
    }

    public void setBrandedProductId(Integer brandedProductId) {
        this.brandedProductId = brandedProductId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMakeUpOptions() {
        return makeUpOptions;
    }

    public void setMakeUpOptions(String makeUpOptions) {
        this.makeUpOptions = makeUpOptions;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BrandedProductBean getBrandedProduct() {
        return brandedProduct;
    }

    public void setBrandedProduct(BrandedProductBean brandedProduct) {
        this.brandedProduct = brandedProduct;
    }

    @Override
    public String toString() {
        return "BrandedProductBean{" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", makeUpOptions='" + makeUpOptions + '\'' +
                ", size='" + size + '\'' +
                ", taxable='" + taxable + '\'' +
                ", comment='" + comment + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", brandedProductId=" + brandedProductId +
                ", storeId=" + storeId +
                ", brandedProduct=" + brandedProduct +
                '}';
    }
}
