package dev.luhwani.eCommerceSystem.services;

import java.util.Scanner;
import java.util.regex.Pattern;

import dev.luhwani.eCommerceSystem.productModels.Category;
import dev.luhwani.eCommerceSystem.productModels.Product;
import dev.luhwani.eCommerceSystem.productModels.Variant;

public class AdminProductServices {
    
    static Scanner scanner = new Scanner(System.in);

    public static void addNewCategory() {
        String name;
        while (true) {
            System.out.println("Enter new category: ");
            name = scanner.nextLine().trim().toLowerCase();
            if (ProductServices.categoryNameToObjectMap.containsKey(name)) {
                System.out.println("This category has already been created");
                return;
            } else {
                break;
            }
        }
        Category category = new Category(name);
        ProductServices.categories.add(category);
        ProductServices.categoryNameToObjectMap.put(category.getName(), category);
    }

    public static void addNewProduct() {
        String name;
        while (true) {
            System.out.println("Enter product name: ");
            name = scanner.nextLine().trim().toLowerCase();
            if (ProductServices.productNameToObjectMap.containsKey(name)) {
                System.out.println("This product already exists");
                return;
            } else {
                break;
            }
        }
        String description;
        System.out.print("Enter product description: ");
        description = scanner.nextLine().trim();
        Product product = new Product(name, description);
        System.out.println("""
            How to Register Products Categories
            Example: 1.Jewelry 2.Tech 3.Accessory 4.Furniture
            If the product belongs to furniture and accessory category
            Your response: 3,4
            """);
        System.out.println("Available Product Categories:");
        ProductServices.printCategories();
        String productCategories;
        String[] categoriesArr = null;
        while(true) {
            boolean validCategories = true;
            System.out.print("Enter product categories: ");
            productCategories = scanner.nextLine().trim();
            Pattern pattern = Pattern.compile("^\\d+(,\\d+)*$");
            boolean validFormat = pattern.matcher(productCategories).matches();
            if(validFormat) {
                categoriesArr = productCategories.split(",");
                for (String string : categoriesArr) {
                    if (Integer.parseInt(string) > ProductServices.categories.size()) {
                        System.out.printf("%s is invalid\n",string);
                        validCategories = false;
                    }
                }
            } else {
                validCategories = false;
                System.out.println("Invalid input");
            }
            if (validCategories) {
                break;
            }
        }
        for (String str : categoriesArr) {
            ProductServices.categories.get(Integer.parseInt(str) - 1).addProduct(product);
        }
        System.out.println("Product has successfuly been added to categories");
    }
    
    public static void addProductVariant() {
        String productName;
        while (true) {
            System.out.println("Enter product name: ");
            productName = scanner.nextLine().trim().toLowerCase();
            if (ProductServices.productNameToObjectMap.containsKey(productName)) {
                break;
            }
            System.out.println("Product not found");
            return;
        }
        Product product = ProductServices.productNameToObjectMap.get(productName);
        String variation;
        System.out.println("Enter variant features: ");
        variation = scanner.nextLine().trim();
        String nairaString;
        while (true) {
            System.out.print("Enter price of product in naira: ");
            nairaString = scanner.nextLine();
            try {
                Double.parseDouble(nairaString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
              e.printStackTrace();
            }
        }
        long priceInKobo = Utils.nairaStringToKobo(nairaString);
        Variant variant = new Variant(true, priceInKobo, variation, 0);
        product.addVariant(variant);
        /*
        run produ.addVar
         */
    }
}
