package dev.luhwani.eCommerceSystem.product;

public class Category {

    private static int counter = 0;
    private String name;
    private int id;
    private Product product;
    
    public Category(String name, int id) {
        counter++;
        this.id = counter;
        this.name = name;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
