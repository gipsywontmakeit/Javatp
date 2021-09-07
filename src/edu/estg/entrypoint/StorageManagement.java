package edu.estg.entrypoint;

import java.util.Scanner;

public class StorageManagement implements IMenu {


    @Override
    public void display() {

    }

    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("|---------------------------------------------|");
        System.out.println("| Do you wanna import data from a .json file? |");
        System.out.println("|---------------------------------------------|");
        System.out.println("| 1 - Yes |  ----------------------  | 2 - No |");
        System.out.println("|---------------------------------------------|");

        while (true) {
            System.out.print("--> ");
            int op = scanner.nextInt();

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
                break;
            } else {
                System.out.println("Invalid Choice! Please chose a number between 1 and 2\n");
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
