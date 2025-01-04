package com.usermanagement;

import java.util.Scanner;

public class Admin {
    public static void showDashboard(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Dashboard");
            System.out.println("1. View Admin Dashboard");
            System.out.println("2. Log Out");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (option == 1) {
                System.out.println("Admin Dashboard - Here you can manage the system.");
            } else if (option == 2) {
                System.out.println("Logging out...");
                break; // Exit back to the main menu (login page)
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}
