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
                System.out.println("Import");
            } else if (op == 2) {
                System.out.println("Program Terminated...");
                break;
            } else {
                System.out.println("Invalid Choice! Please chose a number between 1 and 2\n");
            }
        }
    }
}
