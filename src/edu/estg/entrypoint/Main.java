package edu.estg.entrypoint;

import edu.estg.container.Container;
import edu.estg.container.Measurement;
import edu.estg.container.RecyclingBin;
import edu.estg.json.Importer;
import edu.estg.route.City;
import edu.maen.core.enumerations.WasteType;
import edu.maen.core.exceptions.CityException;
import edu.maen.core.interfaces.*;

import java.io.IOException;

public class Main {

    private static final IMenu storageManagement = new StorageManagement();
    private static final IMenu routeManagement = new RouteManagement();

    public static void main(String[] args) throws Exception {
        //Main.storageManagement.menu();
        ICity city = new City("Felgueiras");

        testDriveFn();
        displayAllBins(city);

    }

    public static void testDriveFn() throws Exception {

        City city = new City("Felgueiras");

        Importer jsonImporter = new Importer();
        try {
            jsonImporter.importData(city, "jso");
        } catch (IOException | CityException e) {
            throw new Exception(e.toString());
        }

        try {
            jsonImporter.importData(city, "D:\\Javatp\\resources\\leituras.json");
        } catch (IOException | CityException e) {
            throw new Exception(e.toString());
        }

        try {
            jsonImporter.importData(city, "D:\\Javatp\\resources\\distances.json");
        }catch (IOException | CityException e) {
            throw new Exception(e.toString());
        }

        for (IRecyclingBin iBin : city.getRecyclingBin()) {
            if (iBin != null) {
                RecyclingBin bin = (RecyclingBin) iBin;
                System.out.println("|-" + bin);

                for (IPath path : bin.paths) {
                    if (path != null) {
                        System.out.println(path);
                    }
                }

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

    public static void displayAllBins(ICity iCity) {
        IRecyclingBin[] recyclingBins = iCity.getRecyclingBin();

        for (int i = 0; i < recyclingBins.length; i++) {
            System.out.println("fodeu");
            if (recyclingBins[i] != null) {
                System.out.println("|---------------------------------------------|");
                System.out.println("Codigo: " + recyclingBins[i].getCode());
                System.out.println("Ref. Localização: " + recyclingBins[i].getRefLocal());
                System.out.println("Zona: " + recyclingBins[i].getZone());
                System.out.println("Latitude: " + recyclingBins[i].getCoordinates().getLatitude());
                System.out.println("Longitude: " + recyclingBins[i].getCoordinates().getLongitude());

                IContainer[] iContainers = recyclingBins[i].getContainers();
                System.out.println("Contentores: " + recyclingBins[i].getCode());

                for(int j = 0; j < iContainers.length; j++) {
                    if (iContainers[j] != null) {
                        System.out.println(" codigo: " + iContainers[j].getCode());
                        System.out.println(" tipo: " + WasteType.getUnitString(iContainers[j].getType()));
                        System.out.println(" capacidade(kg): " + iContainers[j].getCapacity());
                    }
                }

            }

        }


    }
}
