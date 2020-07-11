package data.bean;

public class Membership {

    private String menuTitle;
    private String productName;

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "menuTitle='" + menuTitle + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
