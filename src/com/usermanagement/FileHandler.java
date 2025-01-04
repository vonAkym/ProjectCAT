package com.usermanagement;

import java.io.*;

public class FileHandler {
    public static boolean checkCredentials(String email, String password, String fileName, int expectedFields) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length == expectedFields) {
                    if (userDetails[3].trim().equals(email.trim()) && userDetails[4].trim().equals(password.trim())) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file " + fileName + ": " + e.getMessage());
        }
        return false;
    }

    public static boolean saveDetails(String firstName, String lastName, String phoneNumber, String email, String password, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(firstName + "," + lastName + "," + phoneNumber + "," + email + "," + password + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Error saving details: " + e.getMessage());
            return false;
        }
    }

    public static String getFullNameByEmail(String email, String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(","); // Split the line by comma

                if (userDetails[3].equals(email)) { // Check if email matches (Index 3)
                    return userDetails[0] + " " + userDetails[1]; // Concatenate firstname + lastname
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown"; // Default if not found
    }

    public static String getPhoneByEmail(String email, String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(","); // Split the line by comma

                if (userDetails[3].equals(email)) { // Check if email matches (Index 3)
                    return userDetails[2]; // Return phone number (Index 2)
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "N/A"; // Default if not found
    }




}
