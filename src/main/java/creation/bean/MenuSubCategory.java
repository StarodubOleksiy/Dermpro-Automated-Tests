package creation.bean;


public class MenuSubCategory {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MenuSubCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}
