package data.bean;

public class Services {

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
        return "Services{" +
                "menuTitle='" + menuTitle + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }

}
