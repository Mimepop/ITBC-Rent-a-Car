package com.RentACar.model.request;

public class ChangeCarInfoRequestModel {
    private String licencePlate;
    private String make;
    private String model;
    private int year;
    private int engineCapacity;
    private String color;
    private double price;
    private int doors;
    private String size;
    private int power;
    private boolean automatic;
    private String fuel;
    private String image;
    public ChangeCarInfoRequestModel(String licencePlate, String make, String model, int year, int engineCapacity,
                                     String color, double price, int doors, String size, int power, boolean automatic,
                                     String fuel, String image) {
        this.licencePlate = licencePlate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineCapacity = engineCapacity;
        this.color = color;
        this.price = price;
        this.doors = doors;
        this.size = size;
        this.power = power;
        this.automatic = automatic;
        this.fuel = fuel;
        this.image = image;
    }
    public String getLicencePlate() {
        return licencePlate;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public int getEngineCapacity() {
        return engineCapacity;
    }
    public String getColor() {
        return color;
    }
    public double getPrice() {
        return price;
    }
    public int getDoors() {
        return doors;
    }
    public String getSize() {
        return size;
    }
    public int getPower() {
        return power;
    }
    public boolean isAutomatic() {
        return automatic;
    }
    public String getFuel() {
        return fuel;
    }
    public String getImage() {
        return image;
    }
}
