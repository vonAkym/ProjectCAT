package com.admin;

import java.util.Scanner;

public class Admin {

    private static ProductManager productManager;

    public Admin() {
        // Initialize the product manager
        productManager = new ProductManager("products.txt");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        boolean running = true;

        // Main loop to display the menu and process user input
        while (running) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. Edit Product");
            System.out.println("4. Generate Sales Report");
            System.out.println("5. Show All Products");
            System.out.println("6. Exit");
            System.out.print("Please select an option (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            if (choice == 1) {
                admin.addProduct(scanner);
            } else if (choice == 2) {
                admin.deleteProduct(scanner);
            } else if (choice == 3) {
                admin.editProduct(scanner);
            } else if (choice == 4) {
                admin.generateReport();
            } else if (choice == 5) {
                admin.showAllProducts();
            } else if (choice == 6) {
                running = false;
                System.out.println("Exiting the system.");
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    private void addProduct(Scanner scanner) {
        System.out.println("\n--- Add New Product ---");
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter product category: ");
        String category = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        System.out.print("Enter product SKU: ");
        String sku = scanner.nextLine();

        Product product = new Product(name, description, category, price, quantity, sku);
        productManager.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private void deleteProduct(Scanner scanner) {
        System.out.println("\n--- Delete Product ---");
        System.out.print("Enter product SKU to delete: ");
        String sku = scanner.nextLine();

        boolean success = productManager.deleteProduct(sku);
        if (success) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found with SKU: " + sku);
        }
    }

    private void editProduct(Scanner scanner) {
        System.out.println("\n--- Edit Product ---");
        System.out.print("Enter product SKU to edit: ");
        String sku = scanner.nextLine();

        Product product = productManager.getProductBySku(sku);
        if (product != null) {
            System.out.print("Enter new price (current: " + product.getPrice() + "): ");
            double newPrice = scanner.nextDouble();
            System.out.print("Enter new quantity (current: " + product.getQuantity() + "): ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            product.setPrice(newPrice);
            product.setQuantity(newQuantity);
            productManager.updateProduct(product);

            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found with SKU: " + sku);
        }
    }

    private void generateReport() {
        System.out.println("\n--- Sales Report ---");
        productManager.generateSalesReport();
    }

    private void showAllProducts() {
        productManager.showAllProducts();
    }
}
