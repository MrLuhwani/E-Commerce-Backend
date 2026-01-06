package dev.luhwani.eCommerceSystem.services;

import java.util.ArrayList;
import java.util.List;

import dev.luhwani.eCommerceSystem.product.Category;
import dev.luhwani.eCommerceSystem.product.Product;

public class ProductServices {
    
    static List<Category> categories = new ArrayList<>();
    static List<Product> products = new ArrayList<>();
    
    static void printCategories() {
        int count = 1;
        for (Category category : categories) {
            System.out.println(count + "." + category.getName());
            count++;
        }
    }

    static List<Product> filterProductByCategory(int categoryId) {
        //for the argument of this function, use category ID
        List<Product> filteredProducts = new ArrayList<>();
        if (products.isEmpty()) {
            return null;
        }
        for (Product product : products) {
            for (Category category : product.getCategories()) {
                if (category.getId() == categoryId) {
                    filteredProducts.add(product);
                }
            }
        }
        return filteredProducts;
    }
    
}
