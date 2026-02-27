package com.pricetracker.proekt.repository;

import com.pricetracker.proekt.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product findByUrl(String url) {
        return products.stream()
                .filter(p -> p.getUrl().equals(url))
                .findFirst()
                .orElse(null);
    }

    public List<Product> getAll() {
        return products;
    }
}