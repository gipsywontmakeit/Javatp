package edu.estg.entrypoint;

import edu.estg.route.IVehicle;
import edu.estg.route.Vehicle;
import edu.estg.route.VehicleException;

import java.util.Scanner;

public class VehicleManagement {

    private IVehicle[] vehicles;
    private int vehiclesSize = 10;
    private int vehiclesIndex = 0;
    private int deletePosition = 0;

    public boolean  addVehicle(IVehicle vehicle) throws VehicleException {
        if (vehicle == null) {
            throw new VehicleException("Vehicle is null!");
        }

        if (vehicleExists(vehicle)) {
            return false;
        }

        if (this.vehiclesIndex == this.vehiclesSize) {
            expandVehicleArray();
        }

        this.vehicles[this.vehiclesIndex++] = vehicle;

        return true;
    }

    private void expandVehicleArray() {
        IVehicle[] resizedArray = new IVehicle[vehiclesSize *= 2];

        for (int i = 0; i < this.vehiclesIndex; i++) {
            resizedArray[i] = this.vehicles[i];
        }

        this.vehicles = resizedArray;
    }


    private boolean vehicleExists(IVehicle iVehicle) {
        Vehicle vehicle = (Vehicle) iVehicle;

        for (int i = 0; i < this.vehiclesIndex; i++) {
            if (vehicle.equals(this.vehicles[i])) {
                return true;
            }
        }

        return false;
    }

    private void displayAllVehicles() {
        for (int i = 0; i < this.vehiclesIndex; i++) {
            System.out.println(this.vehicles[i]);
        }
    }

    public void deleteVehicle(int positionToDelete) {
        IVehicle[] shrunkenArray = new IVehicle[this.vehiclesIndex - 1];

        for (int i = 0, k = 0; i < this.vehiclesIndex; i++) {

            if (i == positionToDelete) {
                continue;
            }

            shrunkenArray[k++] = this.vehicles[i];
        }

        this.vehicles = shrunkenArray;
    }


    public void menu(IVehicle iVehicle) {

        Vehicle vehicle = (Vehicle) iVehicle;
        Scanner scanner = new Scanner(System.in);

        System.out.println("|---------------------------------------------|");
        System.out.println("| 1 - Add a vehicle                           |");
        System.out.println("| 2 - List available vehicles                 |");
        System.out.println("| 3 - Delete vehicle                          |");
        System.out.println("| 4 - Back                                    |");
        System.out.println("|---------------------------------------------|");
        System.out.print("--> ");
        int opVehicle = scanner.nextInt();

        switch (opVehicle) {
            case 1:
                try {
                    addVehicle(vehicle);
                } catch (VehicleException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                displayAllVehicles();
                break;
            case 3:
                deleteVehicle(this.deletePosition);
                break;
            case 4:

                break;
        }
    }

}
