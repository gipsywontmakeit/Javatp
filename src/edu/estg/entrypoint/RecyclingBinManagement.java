package edu.estg.entrypoint;

import edu.estg.json.Importer;

import java.io.IOException;
import java.util.Scanner;

public class RecyclingBinManagement {

    public static void menu() throws IOException {
        if (!Importer.isMeasurementsFileImported) {
            throw new IOException("Your need to import the measurements file before acccess this menu!");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("|------------------------------------------------------|");
        System.out.println("| 1 - List available recycling bins and its containers |");
        System.out.println("| 2 - List readings from a recycling bin               |");
        System.out.println("| 3 - Back                                             |");
        System.out.println("|------------------------------------------------------|");
        System.out.print("--> ");
        int opContainer = scanner.nextInt();

        switch (opContainer) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
