package com.admin;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesManager {
    private List<Sale> sales = new ArrayList<>();
    private String fileName;

    public SalesManager(String fileName) {
        this.fileName = fileName;
        loadSales();
    }

    public void addSale(String productName, int quantity, double totalPrice) {
        sales.add(new Sale(productName, quantity, totalPrice, new Date().toString()));
        saveSales();
        System.out.println("Sale added successfully.");
    }

    public void generateSalesReport() {
        System.out.println("Sales Report:");
        System.out.printf("%-15s %-10s %-10s %-25s%n", "Product Name", "Quantity", "Total Price", "Date");
        for (Sale sale : sales) {
            System.out.println(sale);
        }
    }

    private void saveSales() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Sale sale : sales) {
                writer.write(String.join(",",
                        sale.getProductName(),
                        String.valueOf(sale.getQuantity()),
                        String.valueOf(sale.getTotalPrice()),
                        sale.getDate()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving sales: " + e.getMessage());
        }
    }

    private void loadSales() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String productName = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    double totalPrice = Double.parseDouble(parts[2]);
                    String date = parts[3];
                    sales.add(new Sale(productName, quantity, totalPrice, date));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing sales data found. Starting fresh.");
        } catch (IOException e) {
            System.err.println("Error loading sales: " + e.getMessage());
        }
    }
}
