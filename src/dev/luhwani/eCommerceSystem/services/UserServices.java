package dev.luhwani.eCommerceSystem.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import dev.luhwani.eCommerceSystem.cartModel.CartItem;
import dev.luhwani.eCommerceSystem.product.Category;
import dev.luhwani.eCommerceSystem.product.Product;
import dev.luhwani.eCommerceSystem.product.Variant;
import dev.luhwani.eCommerceSystem.userModels.Customer;

public class UserServices {

    static Map<String, Customer> emailToCustomerMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static Customer createAcct() {
        String email;
        while (true) {
            while (true) {
                System.out.print("Enter your email: ");
                email = scanner.nextLine();
                if (validEmail(email)) {
                    break;
                }
                System.out.println("Invalid email");
            }
            boolean found = false;
            for (String emailString : emailToCustomerMap.keySet()) {
                if (emailString.equals(email)) {
                    found = true;
                }
            }
            if (!found) {
                break;
            }
            System.out.println("This email is already in use");
            // When you learn that aspect, send email verification OTP
        }
        String password;
        while (true) {
            System.out.println("""
                    Set new Password:
                    Password requirements:
                    1.Between 7 - 20 characters long
                    2.No space
                    3.Contains both letters and numbers
                    4.Contains at least one symbol""");
            System.out.print("Response: ");
            password = scanner.nextLine();
            if (validPassword(password)) {
                break;
            }
            System.out.println("Invalid Password");
        }
        String firstName;
        while (true) {
            System.out.print("Enter your first name: ");
            firstName = scanner.nextLine();
            if (validName(firstName)) {
                break;
            }
            System.out.println("Invalid name");
        }
        String lastName;
        while (true) {
            System.out.print("Enter your last name: ");
            lastName = scanner.nextLine();
            if (validName(lastName)) {
                break;
            }
            System.out.println("Invalid name");
        }
        Customer customer = new Customer(firstName, lastName, email, password);
        emailToCustomerMap.put(email, customer);
        return customer;
    }

    public static void menu(Customer customer) {
        String response;
        boolean running = true;
        while (running) {
            System.out.println("""
                    Enter the number for your choice:
                    1.View product categories
                    2.View Cart
                    3.Change password
                    4.Log Out""");
            System.out.print("Response: ");
            response = scanner.nextLine();
            switch (response) {
                case "1" -> viewProducts(customer);
                case "2" -> {
                }
                case "3" -> changePassword(customer);
                case "4" -> {
                    running = false;
                    System.out.println("Logging Out...");
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static void viewCart(Customer customer) {
        if (customer.getCart().getCartItems().isEmpty()){
            System.out.println("No items in cart");
            return;
        }
        List<CartItem> cartItems = new ArrayList<>();
        int counter = 0;
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            counter++;
            cartItems.add(cartItem);
            System.out.println("Product "+ counter +".");
            System.out.println("____________");
            cartItem.getVariant().showDetails();
            System.out.print("Quantity: "+ cartItem.getQuantity());
        }
        //to be continued...
    }

    private static void viewProducts(Customer customer) {
        int numOfCategories;
        List<Product> productsInCategory;
        if (ProductServices.categories.isEmpty()) {
            System.out.println("No available categories");
            return;
        }
        numOfCategories = ProductServices.categories.size();
        ProductServices.printCategories();
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
        Category chosenCategory = ProductServices.categories.get(Integer.parseInt(choice) - 1);
        productsInCategory = ProductServices.filterProductByCategory(chosenCategory.getId());
        if (productsInCategory.isEmpty() || Objects.isNull(productsInCategory)) {
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
                    int choiceInt;
                    while (true) {
                        System.out.println("Enter the amount of the product\nto add to cart");
                        System.out.print("Response: ");
                        choice = scanner.nextLine();
                        try {
                            choiceInt = Integer.parseInt(choice);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    CartItem cartItem = new CartItem(chosenVariant, choiceInt);
                    customer.getCart().addCartItem(cartItem);
                    System.out.println("Product has been added to cart");
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private static boolean validEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9]+@[A-za-z-]+\\.[A-Za-z]{2,}$");
        return emailPattern.matcher(email).matches();
    }

    private static boolean validPassword(String password) {
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z0-9])(?=.*[^A-Za-z0-9\s])[^\s]{7,20}$");
        return passwordPattern.matcher(password).matches();
    }

    private static boolean validName(String name) {
        Pattern namePattern = Pattern.compile("^[A-Za-z]$");
        return namePattern.matcher(name).matches();
    }

    private static void changePassword(Customer customer) {
        String oldPassword = customer.getPassword();
        while (true) {
            System.out.print("Enter your old password: ");
            String input = scanner.nextLine();
            if (input.equals(oldPassword)) {
                break;
            }
            System.out.println("Invalid Password");
        }
        String newPassword;
        while (true) {
            System.out.println("""
                    Set new Password:
                    Password requirements:
                    1.Between 7 - 20 characters long
                    2.No space
                    3.Contains both letters and numbers
                    4.Contains at least one symbol""");
            System.out.print("Response: ");
            newPassword = scanner.nextLine();
            if (validPassword(newPassword)) {
                break;
            }
            System.out.println("Invalid Password");
        }
        String confirmPassword;
        while (true) {
            System.out.println("Confirm new password: ");
            confirmPassword = scanner.nextLine();
            if (newPassword.equals(confirmPassword)) {
                break;
            }
            System.out.println("Password doesnt match");
        }
        emailToCustomerMap.get(customer.getEmail()).setPassword(newPassword);
    }
}
