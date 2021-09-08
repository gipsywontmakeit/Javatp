package edu.estg.entrypoint;

import edu.estg.json.Importer;

import java.io.IOException;
import java.util.Scanner;

public class RouteManagement {

    public static void menu() throws IOException {

        if (!(Importer.isMeasurementsFileImported && Importer.isDistancesFileImported)) {
            throw new IOException("Your need to importe both measurements and distances file before access this menu!");
        }

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("|---------------------------------------------|");
        System.out.println("| 1 - Get route for waste pick up             |");
        System.out.println("| 2 - Associate vehicle to route              |");
        System.out.println("| 3 - Back                                    |");
        System.out.println("|---------------------------------------------|");
        System.out.print("--> ");
        int opRoute = scanner.nextInt();

        switch (opRoute) {
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
