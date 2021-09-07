package edu.estg.route;

public class Vehicle implements IVehicle {

    private final String brand;
    private final String model;
    private final double maxCapacity;


    Vehicle(String brand, String model, double maxCapacity) {
        this.brand = brand;
        this.model = model;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public double getMaxCapacity() {
        return this.maxCapacity;
    }

}
