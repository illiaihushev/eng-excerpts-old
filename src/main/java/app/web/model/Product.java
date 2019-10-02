package app.web.model;

public class Product {
    private int id;
    private String name;
    private double cost;
    private int quantity;
    private int locationId;
    private int familyId;

    public Product(){

    }

    public Product(int id, String name, double cost, int quantity, int locationId, int familyId) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.locationId = locationId;
        this.familyId = familyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }
}
