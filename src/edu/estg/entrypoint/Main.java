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
import java.util.Scanner;

public class Main {

    /*
        TODO QuickChart [x]
        TODO Relatorio de erros de importação [x]
        TODO Calcular rotas
        TODO Associar veiculo a uma hora
        TODO Listar veiculos disponiveis mediante os ocupados
     */

    private static final ICity city = new City("Felgueiras");

    public static void main(String[] args) {
        StorageManagement.menu(city);
        Main.menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("|---------------------------------------------|");
        System.out.println("| 1 - Route Management                        |");
        System.out.println("| 2 - Vehicle Management                      |");
        System.out.println("| 3 - Recycling Bin Management                |");
        System.out.println("| 4 - Exit                                    |");
        System.out.println("|---------------------------------------------|");

        while (true) {
            System.out.print("--> ");
            int op = scanner.nextInt();

            switch (op) {
                case 1:
                    try {
                        RouteManagement.menu();
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                    break;
                case 2:
                    //VehicleManagement().menu();
                    break;
                case 3:
                    try {
                        RecyclingBinManagement.menu();
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

            if (op == 4) {
                System.exit(0);
            }
        }
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
        } catch (IOException | CityException e) {
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
            if (recyclingBins[i] != null) {

                RecyclingBin bin = (RecyclingBin) recyclingBins[i];

                System.out.println(bin);

                IContainer[] iContainers = recyclingBins[i].getContainers();
                System.out.println("Contentores: " + recyclingBins[i].getCode());

                for (int j = 0; j < iContainers.length; j++) {
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
