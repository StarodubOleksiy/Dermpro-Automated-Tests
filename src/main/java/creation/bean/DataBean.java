package creation.bean;

public class DataBean {
    private String version_number;

    public String getVersion_number() {
        return version_number;
    }

    public void setVersion_number(String version_number) {
        this.version_number = version_number;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "version_number='" + version_number + '\'' +
                '}';
    }
}
