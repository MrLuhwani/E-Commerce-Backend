package dev.luhwani.app.models.productModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Product {

    private static int counter = 0;
    private String name;
    private int id;
    private String description;
    private Set<Category> categories = new HashSet<>();
    private List<Variant> variants = new ArrayList<>();
    
    public Product(String name, String description) {
        counter++;
        this.name = name;
        this.id = counter;
        this.description = description;
    }
    
    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void addVariant(Variant variant) {
        this.variants.add(variant);
        variant.setProduct(this);
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
