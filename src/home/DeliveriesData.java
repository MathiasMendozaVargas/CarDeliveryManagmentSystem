package home;

public class DeliveriesData {
    private String id;
    private String model;
    private String brand;
    private String color;
    private String city;
    private String country;
    private String date;
    private String buyerName;

    public DeliveriesData(String id, String model, String brand, String color, String city, String country, String date,
            String buyerName) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.city = city;
        this.country = country;
        this.date = date;
        this.buyerName = buyerName;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

}
