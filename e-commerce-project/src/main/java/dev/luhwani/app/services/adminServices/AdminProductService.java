package dev.luhwani.app.services.adminServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import dev.luhwani.app.models.productModels.Category;
import dev.luhwani.app.models.productModels.Product;
import dev.luhwani.app.models.productModels.Variant;
import dev.luhwani.app.models.userModels.Customer;
import dev.luhwani.app.repositories.ProductRepo;
import dev.luhwani.app.services.Utils;
import dev.luhwani.app.services.productServices.ProductService;

public class AdminProductService {

    static {
        //run this in the admin app instead
        List<Variant> lowStockVariants = new ArrayList<>();
        
        for (Product product : ProductService.products) {
            for (Variant variant : product.getVariants()) {
                if (variant.getStock() <= 12) {
                    lowStockVariants.add(variant);
                }
            }
        }
        if (!lowStockVariants.isEmpty()) {
            System.out.println("Products with low stock...");
            for (Variant variant : lowStockVariants) {
                variant.getDetails();
                System.out.println("Stock: " + variant.getStock());   
            }
        }
    }

    private ProductRepo productRepo;
    public AdminProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    static Scanner scanner = new Scanner(System.in);

    public static void addNewProduct() {
        String name;
        while (true) {
            System.out.println("Enter product name: ");
            name = scanner.nextLine().trim().toLowerCase();
            if (ProductService.productNameToObjectMap.containsKey(name)) {
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
        ProductService.printCategories();
        String productCategories;
        String[] categoriesArr = null;
        while (true) {
            boolean validCategories = true;
            System.out.print("Enter product categories: ");
            productCategories = scanner.nextLine().trim();
            Pattern pattern = Pattern.compile("^\\d+(,\\d+)*$");
            boolean validFormat = pattern.matcher(productCategories).matches();
            if (validFormat) {
                categoriesArr = productCategories.split(",");
                for (String string : categoriesArr) {
                    if (Integer.parseInt(string) > ProductService.categories.size()) {
                        System.out.printf("%s is invalid\n", string);
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
            ProductService.categories.get(Integer.parseInt(str) - 1).addProduct(product);
        }
        System.out.println("Product has successfuly been added to categories");
    }

    public static void addProductVariant() {
        Product product = getProductChoice();
        if (Objects.isNull(product)) {
            return;
        }
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
    }

    public static void editProductStock() {
        Product product = getProductChoice();
        if (Objects.isNull(product)) {
            return;
        }
        int count = 0;
        for (Variant variant : product.getVariants()) {
            count++;
            System.out.println("product " + count + ".");
            System.out.println("_____________");
            variant.getDetails();
        }
        String choice = getChoice(count);
        Variant variant = product.getVariants().get(Integer.parseInt(choice) - 1);
        boolean makingChoice = true;
        while (makingChoice) {
            System.out.println("""
                    a.Increase stock
                    b.Decrease stock""");
            System.out.print("Enter the option of your response: ");
            choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "a", "b" -> {
                    makingChoice = false;
                }
                default -> System.out.println("Invalid input");
            }
        }
        String stockChange;
        while (true) {
            System.out.print("Enter the amount of the stock change: ");
            stockChange = scanner.nextLine().trim();
            if (!stockChange.startsWith("-")) {
                try {
                    Integer.parseInt(stockChange);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        int change = Integer.parseInt(stockChange);
        int newStock = 0;
        if (change < 0) {
            System.out.println("Megatives not allowed");
            return;
        }
        if (choice.equals("a")) {
            newStock = variant.getStock() + change;
            variant.setStock(newStock);
        } else {
            newStock = variant.getStock() - Integer.parseInt(stockChange);
            variant.setStock(newStock);
        }
        System.out.println("Stock change successful");
    }

    public static void temporaryDeactivation() {
        Product product = getProductChoice();
        if (Objects.isNull(product)) {
            return;
        }
        int count = 0;
        for (Variant variant : product.getVariants()) {
            count++;
            System.out.println("product " + count + ".");
            System.out.println("_____________");
            variant.getDetails();
        }
        String choice = getChoice(count);
        Variant variant = product.getVariants().get(Integer.parseInt(choice) - 1);
        variant.setIsActive(false);
        System.out.println("Product is temporarily deactivated");
    }

    public static void permanentDeactivation() {
        Product product = getProductChoice();
        if (Objects.isNull(product)) {
            return;
        }
        int count = 0;
        for (Variant variant : product.getVariants()) {
            count++;
            System.out.println("product " + count + ".");
            System.out.println("_____________");
            variant.getDetails();
        }
        String choice = getChoice(count);
        Variant variant = product.getVariants().get(Integer.parseInt(choice) - 1);
        product.getVariants().remove(variant);
        ProductService.productNameToObjectMap.get(product.getName()).getVariants().remove(variant);
        // for (Customer customer : CustomerService.customers) {
        //     for (int i = customer.getCart().getCartItems().size() - 1; i >= 0; i--) {
        //         if (customer.getCart().getCartItems().get(i).getItemId() == variant.getId()) {
        //             customer.getCart().getCartItems().remove(i);
        //             //think of how to notify the customers when deleted
        //         }
        //     }
        // }
        System.out.println("Product deleted successfully");
    }

    public static void changeProductPrice() {
        Product product = getProductChoice();
        if (Objects.isNull(product)) {
            return;
        }
        int count = 0;
        for (Variant variant : product.getVariants()) {
            count++;
            System.out.println("product " + count + ".");
            System.out.println("_____________");
            variant.getDetails();
        }
        String choice = getChoice(count);
        Variant variant = product.getVariants().get(Integer.parseInt(choice) - 1);
        String nairaString;
        while (true) {
            System.out.print("Enter the new price(₦): ");
            nairaString = scanner.nextLine().trim();
            try {
                Double.parseDouble(nairaString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long kobo = Utils.nairaStringToKobo(nairaString);
        variant.setKoboPrice(kobo);
        System.out.println("Price updated successfully");
    }

    private static Product getProductChoice() {
        String productName;
        while (true) {
            System.out.println("Enter product name: ");
            productName = scanner.nextLine().trim().toLowerCase();
            if (ProductService.productNameToObjectMap.containsKey(productName)) {
                break;
            }
            System.out.println("Product not found");
            return null;
        }
        return ProductService.productNameToObjectMap.get(productName);
    }

    private static String getChoice(int count) {
        String choice;
        while (true) {
            System.out.print("Enter the number for the variant: ");
            choice = scanner.nextLine().trim().toLowerCase();
            try {
                if (Integer.parseInt(choice) > count || Integer.parseInt(choice) <= 0) {
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
        return choice;
    }
}
