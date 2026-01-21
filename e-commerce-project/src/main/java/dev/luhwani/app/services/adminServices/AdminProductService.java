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
