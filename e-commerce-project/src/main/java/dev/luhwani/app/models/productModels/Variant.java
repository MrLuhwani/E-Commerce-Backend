package dev.luhwani.app.models.productModels;

import java.math.BigDecimal;

public class Variant {
    
    private static int counter = 0;
    private int id;
    private boolean isActive;
    //I stored it as Kobo because of products that cost decimal prices
    //it is easier to work in the lowest unit of currency so as to prevent miscalculations
    private long priceInKobo;
    //there should be a better implementation of the variations in product
    private String variation;
    private int stock;
    private Product product;
    
    public Variant(boolean isActive, long priceInKobo, String variation, int stock) {
        counter++;
        this.id = counter;
        this.isActive = isActive;
        this.priceInKobo = priceInKobo;
        this.variation = variation;
        this.stock = stock;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getPriceInKobo() {
        return priceInKobo;
        //im still using a double here though but when actual transactions come,
        //there'll be a better implementation of the kobo logic
    }

    public BigDecimal getPriceInNaira() {
        return BigDecimal.valueOf(priceInKobo).divide(BigDecimal.valueOf(100));
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void getDetails() {
        System.out.printf("""
                Name: %s
                Description: %s
                Price: #%f
                Feature: %s \n""",product.getName(),product.getDescription(),getPriceInNaira(),variation);
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setKoboPrice(long price) {
        this.priceInKobo = price;
    }
}