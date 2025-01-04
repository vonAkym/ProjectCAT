package com.usermanagement;

import java.io.*;
import java.util.*;

public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private List<String> cart;
    private List<String> orderHistory;

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    // View items
    public void viewItems(List<String> items) {
        System.out.println("Available Items:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    // Add to cart
    public void addToCart(String item) {
        cart.add(item);
        System.out.println(item + " added to cart.");
    }

    // View cart
    public void viewCart() {
        System.out.println("Your Cart:");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (String item : cart) {
                System.out.println("- " + item);
            }
        }
    }

    // Delete cart
    public void deleteCart() {
        cart.clear();
        System.out.println("Cart has been cleared.");
    }

    // Check out
    public void checkOut(String paymentMethod, String email) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Add items before checkout.");
        } else {
            Scanner scanner = new Scanner(System.in);
            viewCart();
            System.out.print("Enter items to check out (separate by comma): ");
            String input = scanner.nextLine();
            String[] itemsToCheckout = input.split(",");

            List<String> validItems = new ArrayList<>();
            for (String item : itemsToCheckout) {
                item = item.trim();
                if (cart.contains(item)) {
                    validItems.add(item);
                } else {
                    System.out.println(item + " is not in the cart.");
                }
            }

            if (validItems.isEmpty()) {
                System.out.println("No valid items to check out.");
                return;
            }

            String address = getAddressForEmail(email);
            if (address == null) {
                System.out.println("No address found. Please add an address before checkout.");
                return;
            }

            System.out.println("Shipping to: " + address);
            System.out.print("Enter payment method (COD/Online Banking): ");
            paymentMethod = scanner.nextLine();

            for (String item : validItems) {
                System.out.println("Order for " + item + " placed using " + paymentMethod + ".");
                orderHistory.add(item);
                cart.remove(item);
            }
            System.out.println("Thank you for shopping with us!");
        }
    }

    // View order history
    public void viewOrderHistory() {
        System.out.println("Order History:");
        if (orderHistory.isEmpty()) {
            System.out.println("No orders placed yet.");
        } else {
            for (String order : orderHistory) {
                System.out.println("- " + order);
            }
        }
    }

    // Cancel order
    public void cancelOrder(String order) {
        if (orderHistory.contains(order)) {
            orderHistory.remove(order);
            System.out.println("Order " + order + " has been canceled.");
        } else {
            System.out.println("Order not found in history.");
        }
    }
    private void saveAddressToFile(String email, String address, String zipcode, String city, String state) {
        String fileName = "C://Users//Lenovo//IdeaProjects//saja//src//address.csv";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(email + "," + address + "," + zipcode + "," + city + "," + state + "\n");
        } catch (IOException e) {
            System.out.println("Error saving address: " + e.getMessage());
        }
    }

    public void addAddress(Scanner scanner) {
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter zipcode: ");
        String zipcode = scanner.nextLine();

        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        System.out.print("Enter state: ");
        String state = scanner.nextLine();

        saveAddressToFile(email, address, zipcode, city, state);
        System.out.println("Address added successfully.");
    }

    private String getAddressForEmail(String email) {
        String fileName = "C://Users//Lenovo//IdeaProjects//saja//src//address.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(email)) {
                    return parts[1] + ", " + parts[2] + ", " + parts[3] + ", " + parts[4];
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading address: " + e.getMessage());
        }
        return null;
    }


    // Status
    public void viewStatus() {
        System.out.println("Order status is pending for processing.");
    }
}

