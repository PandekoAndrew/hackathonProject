package by.vsu.hata.entity;

public class Property {

    private Long id;
    private String region;
    private String city;
    private String address;
    private int price;
    private int area;
    private int roomsAmount;
    private String type;

    public Property(Long id, String region, String city, String address, int price, int area, int roomsAmount, String type) {
        this.id = id;
        this.region = region;
        this.city = city;
        this.address = address;
        this.price = price;
        this.area = area;
        this.roomsAmount = roomsAmount;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(int roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
