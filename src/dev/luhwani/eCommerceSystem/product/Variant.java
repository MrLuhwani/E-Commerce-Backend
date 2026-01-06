package dev.luhwani.eCommerceSystem.product;

public class Variant {
    
    private static int counter = 0;
    private int id;
    private boolean isActive;
    private int priceInKobo;
    //I stored it as Kobo because of products that cost decimal prices
    //it is easier to work in the lowest unit of currency so as to prevent miscalculations 
    private String variation;
    private int stock;
    private Product product;
    
    public Variant(boolean isActive, int priceInKobo, String variation, int stock) {
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

    double priceInNaira() {
        return priceInKobo / 100;
        //im still using a double here though but when actual transactions come,
        //there'll be a better implementation of the kobo logic
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void showDetails() {
        System.out.printf("""
                Name: %s
                Description: %s
                Price: #%f
                Feature: %s \n""",product.getName(),product.getDescription(),priceInNaira(),variation);
    }

    public int getStock() {
        return stock;
    }
}