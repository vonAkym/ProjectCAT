package com.usermanagement;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the E-commerce System");

        // Loop to allow multiple users to log in after logout
        while (true) {
            // First, ask for the role (Admin or User)
            System.out.println("Choose your role:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.print("Enter your choice: ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            // Ensure valid role selection
            if (roleChoice != 1 && roleChoice != 2) {
                System.out.println("Invalid choice. Exiting...");
                break; // Exit the program if invalid choice
            }

            // Ask whether the user wants to login or sign up
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Choose an option: ");
            int actionChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            // Handle login or sign up based on role and choice
            if (actionChoice == 1) {
                // Perform login
                if (roleChoice == 1) {
                    if (LoginHandler.login(scanner, "admin")) {
                        Admin.showDashboard(scanner); // Redirect to admin dashboard
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                } else if (roleChoice == 2) {
                    if (LoginHandler.login(scanner, "user")) {
                        //User.showDashboard(scanner); // Redirect to user dashboard
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                }
            } else if (actionChoice == 2) {
                // Perform sign up
                if (roleChoice == 1) {
                    SignUpHandler.signUp(scanner, "admin");
                } else if (roleChoice == 2) {
                    SignUpHandler.signUp(scanner, "user");
                }
                // After sign-up, prompt to login again
                System.out.println("Sign-up successful! Please log in.");
            } else {
                System.out.println("Invalid choice. Exiting...");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}
