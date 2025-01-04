package com.admin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ProductManager {

    private List<Product> products;
    private String fileName;

    public ProductManager(String fileName) {
        this.fileName = fileName;
        products = new ArrayList<>();
        loadProducts();
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProducts();
    }

    public boolean deleteProduct(String sku) {
        for (Product product : products) {
            if (product.getSku().equals(sku)) {
                products.remove(product);
                saveProducts();
                return true;
            }
        }
        return false;
    }

    public Product getProductBySku(String sku) {
        for (Product product : products) {
            if (product.getSku().equals(sku)) {
                return product;
            }
        }
        return null;
    }

    public void updateProduct(Product product) {
        saveProducts();
    }

    public void generateSalesReport() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void showAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("\n--- All Products ---");
            for (Product product : products) {
                System.out.println("Name: " + product.getName());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Category: " + product.getCategory());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("SKU: " + product.getSku());
                System.out.println("--------------------------");
            }
        }
    }

    private void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0].trim();
                    String description = parts[1].trim();
                    String category = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());
                    int quantity = Integer.parseInt(parts[4].trim());
                    String sku = parts[5].trim();

                    products.add(new Product(name, description, category, price, quantity, sku));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading products: " + e.getMessage());
        }
    }

    private void saveProducts() {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving products: " + e.getMessage());
        }
    }
}
