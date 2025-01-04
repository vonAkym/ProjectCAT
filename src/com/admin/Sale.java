package com.admin;

public class Sale {
    private String productName;
    private int quantity;
    private double totalPrice;
    private String date;

    public Sale(String productName, int quantity, double totalPrice, String date) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-10d %-10.2f %-25s", productName, quantity, totalPrice, date);
    }
}
