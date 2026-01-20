package dev.luhwani.app.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.luhwani.app.models.productModels.Category;
import dev.luhwani.app.models.productModels.Product;

public class ProductRepo {
    
    private List<Category> categories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private Map<String, Category> nameToCategoryMap = new HashMap<>();
    private Map<String, Product> nameToProductMap = new HashMap<>();

    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Map<String, Category> getNameToCategoryMap() {
        return nameToCategoryMap;
    }
    
    public Map<String, Product> getNameToProductMap() {
        return nameToProductMap;
    }

}
