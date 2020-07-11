package creation.bean;


import java.util.List;

public class MenuCategory {
    private String name;
    private List<MenuSubCategory> subCategoryList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuSubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<MenuSubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    @Override
    public String toString() {
        return "MenuCategory{" +
                "name='" + name + '\'' +
                ", subCategoryList=" + subCategoryList +
                '}';
    }
}
