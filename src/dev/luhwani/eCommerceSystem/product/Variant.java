package dev.luhwani.eCommerceSystem.product;

public class Variant {
    
    private static int counter = 0;
    private int id;
    private boolean isActive;
    private int priceInLowestUnit;
    private String variation;
    private int stock;
    private Product product;
    
    public Variant(boolean isActive, int priceInLowestUnit, String variation, int stock) {
        counter++;
        this.id = counter;
        this.isActive = isActive;
        this.priceInLowestUnit = priceInLowestUnit;
        this.variation = variation;
        this.stock = stock;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
