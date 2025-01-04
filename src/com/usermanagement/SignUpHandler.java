package com.usermanagement;

import java.util.Scanner;

public class SignUpHandler {
    public static void signUp(Scanner scanner, String role) {
        System.out.println("--- Signup Form ---");

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        String password;
        String confirmPassword;

        while (true) {
            System.out.print("Enter Password: ");
            password = scanner.nextLine();

            System.out.print("Confirm Password: ");
            confirmPassword = scanner.nextLine();

            if (password.equals(confirmPassword)) {
                break;
            } else {
                System.out.println("Passwords do not match. Please try again.\n");
            }
        }

        boolean isSuccess;

        // Replacing the ternary operator with an if-else structure
        String filePath;
        if (role.equals("admin")) {
            filePath = "C://Users//Lenovo//IdeaProjects//saja//src//admin.txt";
        } else {
            filePath = "C://Users//Lenovo//IdeaProjects//saja//src//users.txt";
        }

        isSuccess = FileHandler.saveDetails(firstName, lastName, phoneNumber, email, password, filePath);

        if (isSuccess) {
            System.out.println("Signup successful! You can now log in.");
        } else {
            System.out.println("An error occurred during signup. Please try again.");
        }
    }
}

