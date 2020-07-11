package creation.features;

public class FeatureState {

    private String code;
    private boolean enabled;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "FeatureState{" +
                "code='" + code + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
