package dev.luhwani.app.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.luhwani.app.models.productModels.Category;
import dev.luhwani.app.models.productModels.Product;

public class ProductRepo {
    
    public List<Category> categories = new ArrayList<>();
    public List<Product> products = new ArrayList<>();
    public Map<String, Category> nameToCategoryMap = new HashMap<>();
    public Map<String, Product> nameToProductMap = new HashMap<>();

}
