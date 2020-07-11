package data.bean;

public class Office {
    private String phone;
    private String officeHours;
    private String address;

    public void setPhoneNumber(String number) {
        this.phone = number;
    }

    public String getPhoneNumber() {
        return this.phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setOfficeHours(String hours) {
        this.officeHours = hours;
    }

    public String getOfficeHours() {
        return this.officeHours;
    }

    @Override
    public String toString() {
        return "Office{" +
                ", phone=" + phone +
                ", officeHours=" + officeHours +
                ", address=" + address +
                '}';
    }
}
