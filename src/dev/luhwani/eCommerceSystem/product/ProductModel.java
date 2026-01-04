package dev.luhwani.eCommerceSystem.product;

public abstract class ProductModel {
    static int count;
    int id;
    String name;
    String description;
    int priceInLowestUnit;
    int stock;
    //dont know what to call category, either a string or a class
    //need to refactor, and think this true
    boolean isActive;
    //dont know how to implement variants
    //for variants, what makes you different is your id, description, price, stock and active
    public ProductModel() {
        count++;
    }
}
