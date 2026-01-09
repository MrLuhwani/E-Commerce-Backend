package dev.luhwani.eCommerceSystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.luhwani.eCommerceSystem.product.Category;
import dev.luhwani.eCommerceSystem.product.Product;
import dev.luhwani.eCommerceSystem.product.Variant;
import dev.luhwani.eCommerceSystem.userModels.Customer;

public class ProductServices {
    
    static List<Category> categories = new ArrayList<>();
    static List<Product> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    
    public static void viewProducts(Customer customer) {
        int numOfCategories;
        List<Product> productsInCategory;
        if (categories.isEmpty()) {
            System.out.println("No available categories");
            return;
        }
        numOfCategories = categories.size();
        printCategories();
        String choice;
        while (true) {
            System.out.println("Enter the number of your choice: ");
            choice = scanner.nextLine();
            try {
                if (Integer.parseInt(choice) > numOfCategories || Integer.parseInt(choice) < 1) {
                    System.out.println("Invalid choice");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Category chosenCategory = categories.get(Integer.parseInt(choice) - 1);
        productsInCategory = filterProductByCategory(chosenCategory.getId());
        if (productsInCategory.isEmpty()) {
            System.out.println("No available products yet");
            return;
        }
        List<Variant> activeVariants = new ArrayList<>();
        int numOfVariants = 0;
        for (Product product : productsInCategory) {
            for (Variant variant : product.getVariants()) {
                if (variant.getIsActive() && variant.getStock() > 0) {
                    numOfVariants += 1;
                    System.out.println("Product " + numOfVariants + ".");
                    System.out.println("_____________");
                    variant.showDetails();
                    activeVariants.add(variant);
                }
            }
        }
        while (true) {
            System.out.println("Enter the number of the product: ");
            choice = scanner.nextLine();
            try {
                if (Integer.parseInt(choice) > activeVariants.size() || Integer.parseInt(choice) < 1) {
                    System.out.println("Invalid choice");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Variant chosenVariant = activeVariants.get(Integer.parseInt(choice) - 1);
        while (true) {
            System.out.println("""
                    Enter number for your choice:
                    1.Buy Product
                    2.Add to Cart
                    Response: """);
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> System.out.println("""
                        Purchase feature is not available yet
                        Please try again later
                        """);
                        //Not good for an e commerce store
                        //I'll think of something for now
                case "2" -> {
                    CartServices.addToCart(customer, chosenVariant);
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static void printCategories() {
        int count = 1;
        for (Category category : categories) {
            System.out.println(count + "." + category.getName());
            count++;
        }
    }

    private static List<Product> filterProductByCategory(int categoryId) {
        List<Product> filteredProducts = new ArrayList<>();
        if (products.isEmpty()) {
            return filteredProducts;
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
