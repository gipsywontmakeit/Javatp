package edu.estg.entrypoint;

import edu.estg.container.Container;
import edu.estg.container.Measurement;
import edu.estg.container.RecyclingBin;
import edu.estg.json.Importer;
import edu.estg.route.City;
import edu.maen.core.exceptions.CityException;
import edu.maen.core.interfaces.IContainer;
import edu.maen.core.interfaces.IMeasurement;
import edu.maen.core.interfaces.IRecyclingBin;

import java.io.IOException;

public class Main {

    private static final IMenu storageManagement = new StorageManagement();
    private static final IMenu routeManagement = new RouteManagement();

    public static void main(String[] args) throws Exception {
        //Main.storageManagement.menu();
        testDriveFn();
    }

    public static void testDriveFn() throws Exception {

        City city = new City("Felgueiras");

        Importer jsonImporter = new Importer();
        try {
            jsonImporter.importData(city, "D:\\Javatp\\resources\\ecopontos.json");
        } catch (IOException | CityException e) {
            throw new Exception(e.toString());
        }


        try {
            jsonImporter.importData(city, "D:\\Javatp\\resources\\leituras.json");
        } catch (IOException | CityException e) {
            throw new Exception(e.toString());
        }

        for (IRecyclingBin iBin : city.getRecyclingBin()) {
            if (iBin != null) {
                RecyclingBin bin = (RecyclingBin) iBin;
                System.out.println("|-" + bin);

                for (IContainer iContainer : bin.getContainers()) {
                    if (iContainer != null) {
                        Container container = (Container) iContainer;

                        System.out.println("|- -->" + container);

                        for (IMeasurement iMeasurement : container.getMeasurements()) {
                            if (iMeasurement != null) {
                                Measurement measurement = (Measurement) iMeasurement;

                                System.out.println("|- --> -->" + measurement);
                            }
                        }
                    }
                }

                System.out.println("***************************************\n");
            }
        }
    }
}
