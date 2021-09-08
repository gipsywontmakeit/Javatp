package edu.estg.entrypoint;

import edu.estg.json.Importer;
import edu.maen.core.exceptions.CityException;
import edu.maen.core.interfaces.ICity;

import java.io.IOException;
import java.util.Scanner;

public class StorageManagement {

    private static final Importer jsonImporter = new Importer();

    public static void menu(ICity city) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("|---------------------------------------------|");
        System.out.println("| Do you wanna import data from a .json file? |");
        System.out.println("|---------------------------------------------|");
        System.out.println("| 1 - Yes |  ----------------------  | 2 - No |");
        System.out.println("|---------------------------------------------|");

        while (true) {
            System.out.print("--> ");
            String op = scanner.nextLine();

            if (op.equals("1")) {
                System.out.println("|-----------------------------------------------|");
                System.out.println("| Write the path from the file you wanna import |");
                System.out.println("|-----------------------------------------------|");

                System.out.print("--> ");
                String path = scanner.nextLine();
                readFile(city, path);

                while(op.equals("1")) {
                    System.out.println("|-----------------------------------------------|");
                    System.out.println("| Do you wanna import more files?               |");
                    System.out.println("|-----------------------------------------------|");
                    System.out.println("| 1 - Yes |  ------------------------  | 2 - No |");
                    System.out.println("|-----------------------------------------------|");
                    System.out.print("--> ");

                    if (op.equals("1")) {
                        readFile(city, path);
                    } else if (op.equals("2")) {
                        break;
                    }
                }
                break;
            } else if (op.equals("2")) {
                System.out.println("Program Terminated...");
                System.exit(0);
            } else {
                System.out.println("Invalid Choice! Please chose a number between 1 and 2");
            }
        }
    }

    private static void readFile(ICity city, String path) {
        try {
            jsonImporter.importData(city, path);
            System.out.println("Successfully imported!");
        } catch (IOException | CityException e) {
            System.out.println(e);
            System.exit(-1);
        }
    }

}
