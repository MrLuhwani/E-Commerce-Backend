package dev.luhwani.app.services.productServices;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import dev.luhwani.app.models.productModels.Category;
import dev.luhwani.app.models.productModels.Product;
import dev.luhwani.app.models.productModels.Variant;
import dev.luhwani.app.repositories.ProductRepo;

public class ProductService {

    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Category> getCategories() {
        return productRepo.getCategories();
    }
    
    public List<Variant> getVariants(Product product) {
        return product.getVariants();
    }

    public List<Product> getProducts() {
        return productRepo.getProducts();
    }

    public Map<String, Category> getNameToCategoryMap() {
        return productRepo.getNameToCategoryMap();
    }

    public Map<String, Product> getNameToProductMap() {
        return productRepo.getNameToProductMap();
    }

    public boolean getIsActive(Variant variant) {
        return variant.getIsActive();
    }

    public int getStock(Variant variant) {
        return variant.getStock();
    }

    public List<Product> filterProductByCategory(int categoryId) {
        List<Product> filteredProducts = new ArrayList<>();
        if (getProducts().isEmpty()) {
            return filteredProducts;
        }
        for (Product product : getProducts()) {
            for (Category category : product.getCategories()) {
                if (category.getId() == categoryId) {
                    filteredProducts.add(product);
                }
            }
        }
        return filteredProducts;
    }

    public void addNewCategory(String name) {
        Category category = new Category(name);
        getCategories().add(category);
        getNameToCategoryMap().put(category.getName(), category);
    }

    public void addNewProduct(String name, String description, String[] categories) {
        Product product = new Product(name, description);
        for (String str : categories) {
            getCategories().get(Integer.parseInt(str) - 1).addProduct(product);
        }
        getProducts().add(product);
        getNameToProductMap().put(name, product);
    }

}
