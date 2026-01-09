package dev.luhwani.eCommerceSystem.product;

import java.util.HashSet;
import java.util.Set;

public class Category {

    private static int counter = 0;
    private String name;
    private int id;
    private Set<Product> products = new HashSet<>();
    
    public Category(String name) {
        counter++;
        this.id = counter;
        this.name = name;
    }
    
    public void addProduct(Product product) {
        this.products.add(product);
        product.addCategory(this);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
