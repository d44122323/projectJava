package com.pricetracker.proekt.model;

public class Product {

    private String name;
    private String url;
    private double currentPrice;

    public Product(String name, String url, double currentPrice) {
        this.name = name;
        this.url = url;
        this.currentPrice = currentPrice;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void updatePrice(double newPrice) {
        this.currentPrice = newPrice;
    }
}