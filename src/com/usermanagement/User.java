package com.usermanagement;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {
    public static void showDashboard(Scanner scanner,String name,String email,String phone_num) {
        while (true) {
            System.out.println("\nUser Dashboard");
            System.out.println("1. View User Dashboard");
            System.out.println("2. Log Out");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (option == 1) {
                Customer customer = new Customer(name,email, phone_num);
                System.out.println("User Dashboard - Browse and shop.");
                List<String> items = Arrays.asList("Laptop", "Smartphone", "Headphones", "Tablet");

                System.out.println("Welcome " + customer.getName());
                System.out.println("What would you like to do?");

                while (true) {
                    // Display menu options

                    System.out.println("\n1. View Items");
                    System.out.println("2. Add to Cart");
                    System.out.println("3. View Cart");
                    System.out.println("4. Clear Cart");
                    System.out.println("5. Checkout");
                    System.out.println("6. View Order History");
                    System.out.println("7. Cancel Order");
                    System.out.println("8. View Status");
                    System.out.println("9. Add adress");
                    System.out.println("0. Log out");

                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            customer.viewItems(items);
                            break;

                        case 2:
                            System.out.print("Enter item to add: ");
                            String itemToAdd = scanner.nextLine();
                            if (items.contains(itemToAdd)) {
                                customer.addToCart(itemToAdd);
                            } else {
                                System.out.println("Item not available.");
                            }
                            break;

                        case 3:
                            customer.viewCart();
                            break;

                        case 4:
                            customer.deleteCart();
                            break;

                        case 5:
                            //System.out.print("Enter payment method (COD/Online Banking): ");
                            //String paymentMethod = scanner.nextLine();
                            customer.checkOut("", email);
                            break;

                        case 6:
                            customer.viewOrderHistory();
                            break;

                        case 7:
                            System.out.print("Enter order to cancel: ");
                            String orderToCancel = scanner.nextLine();
                            customer.cancelOrder(orderToCancel);
                            break;

                        case 8:
                            customer.viewStatus();
                            break;

                        case 9:
                            customer.addAddress(scanner);
                            break;

                        case 0:
                            System.out.println("Logging out...");
                            return;

                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            } else if (option == 2) {
                System.out.println("Logging out...");
                break; // Exit back to the main menu (login page)
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}
