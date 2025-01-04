package com.usermanagement;

import java.util.Scanner;

public class LoginHandler {
    public static boolean login(Scanner scanner, String role) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Select the file based on role
        String fileName;
        if (role.equals("admin")) {
            fileName = "C://Users//dh910//Downloads//src_saja//admin.txt";
        } else {
            fileName = "C://Users//dh910//Downloads//src_saja//users.txt";
        }

        // Authenticate the user
        boolean isValid = authenticateUser(email, password, fileName);


        if (isValid) {
            System.out.println("Welcome " + role + "! Access granted to the " + role + " dashboard.");
            if (role.equals("user")) {
                String name = FileHandler.getFullNameByEmail(email, fileName);         // Fetch name
                String phoneNumber = FileHandler.getPhoneByEmail(email, fileName);
                User.showDashboard(scanner,name,email,phoneNumber);
            }
            else{
                System.out.println("Invalid email or password. Please try again.");//admin
            }
            return true; // Login successful
        } else {
            System.out.println("Invalid email or password. Please try again.");
            return false; // Login failed
        }


    }

    private static boolean authenticateUser(String email, String password, String fileName) {
        // Call FileHandler to check credentials and return the result
        return FileHandler.checkCredentials(email, password, fileName, 5);
    }
}
