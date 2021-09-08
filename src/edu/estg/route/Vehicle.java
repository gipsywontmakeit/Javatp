package edu.estg.route;

public class Vehicle implements IVehicle {

    private final String brand;
    private final String model;
    private final double maxCapacity;

    /**
     * @param brand
     * @param model
     * @param maxCapacity
     */
    Vehicle(String brand, String model, double maxCapacity) {
        this.brand = brand;
        this.model = model;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Vehicle)) {
            return false;
        }

        Vehicle vehicle = (Vehicle) o;

        return vehicle.brand.equals(this.brand) && vehicle.model.equals(this.model);
    }

    /**
     * @return
     */
    @Override
    public String getBrand() {
        return this.brand;
    }

    /**
     * @return
     */
    @Override
    public String getModel() {
        return this.model;
    }

    /**
     * @return
     */
    @Override
    public double getMaxCapacity() {
        return this.maxCapacity;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
