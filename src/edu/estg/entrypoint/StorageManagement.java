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

            if (op == 1) {
                System.out.println("Data imported.");
            } else if (op == 2) {
                System.out.println("Program Terminated...");
                break;
            } else {
                System.out.println("Invalid Choice! Please chose a number between 1 and 2\n");
            }
        }

        System.out.println("|---------------------------------------------|");
        System.out.println("| 1 - Route Management                        |");
        System.out.println("| 2 - Vehicle Management                      |");
        System.out.println("| 3 - Recycling Bin Management                |");
        System.out.println("| 4 - Exit                                    |");
        System.out.println("|---------------------------------------------|");

        while (true) {
            System.out.print("--> ");
            int opManagement = scanner.nextInt();

            switch (opManagement) {
                case 1:
                    System.out.println("|---------------------------------------------|");
                    System.out.println("| 1 - Get route for waste pick up             |");
                    System.out.println("| 2 - Back                                    |");
                    System.out.println("|---------------------------------------------|");
                    System.out.print("--> ");
                    int opRoute = scanner.nextInt();

                    if (opRoute == 1) {
                        System.out.println("Method needed");
                    } else if (opRoute == 2) {
                        return; // so para nao der erro
                    }

                    break;

                case 2:
                    System.out.println("|---------------------------------------------|");
                    System.out.println("| 1 - Add a vehicle                           |");
                    System.out.println("| 2 - List available vehicles                 |");
                    System.out.println("| 3 - Delete vehicle                          |");
                    System.out.println("| 4 - Associate vehicle to route              |");
                    System.out.println("| 5 - Back                                    |");
                    System.out.println("|---------------------------------------------|");
                    System.out.print("--> ");
                    int opVehicle = scanner.nextInt();

                    if (opVehicle == 1)  {
                        System.out.println("");
                    } else if (opVehicle == 2) {
                        System.out.println("");
                    } else if (opVehicle == 3) {
                        System.out.println("");
                    }else if (opVehicle == 4) {
                        System.out.println("");
                    } else if (opVehicle == 5) {
                        break;
                    }
                    break;

                case 3:
                    System.out.println("|---------------------------------------------|");
                    System.out.println("| 1 - List available recycling bins           |");
                    System.out.println("| 2 - List readings from a container          |");
                    System.out.println("| 3 - Back                                    |");
                    System.out.println("|---------------------------------------------|");
                    System.out.print("--> ");
                    int opContainer = scanner.nextInt();

                    if (opContainer == 1) {
                        System.out.println("");
                    } else if (opContainer == 2) {
                        System.out.println("");
                    } else if (opContainer == 3) {
                        break;
                    }
                    break;

            }
        }

    }


}
