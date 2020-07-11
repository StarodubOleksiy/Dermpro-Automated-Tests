package creation.bean;


import java.util.List;

public class StoreBeanCollection {
    private List<StoreBean> stores;
    private boolean success;

    public List<StoreBean> getStores() {
        return stores;
    }

    public void setStores(List<StoreBean> stores) {
        this.stores = stores;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "StoreBeanCollection{" +
                "stores=" + stores +
                ", success=" + success +
                '}';
    }
}
