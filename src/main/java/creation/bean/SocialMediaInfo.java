package creation.bean;


public class SocialMediaInfo {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SocialMediaInfo{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
